package org.ferris.war.exploder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Unzip {

    protected File zipFile;
    protected File destinationDirectory;

    public Unzip(String zipFilePath, String destinationDirectoryPath) {
        setZipFile(zipFilePath);
        setDestinationDirectory(destinationDirectoryPath);
    }

    public Unzip(File zipFile) {
        this.zipFile = zipFile;
        setDestinationDirectory(zipFile.getParent());
    }

    protected void setDestinationDirectory(String destinationDirectoryPath) {
        destinationDirectory = new File(destinationDirectoryPath, zipFile.getName());
        if (destinationDirectory.exists()) {
            throw new RuntimeException(
                String.format(
                    "The destination directory \"%s\" already exists.",
                     destinationDirectory.getPath()
                )
            );
        }

        mkdirs(destinationDirectory,
             "Failed to create the destination directory \"%s\"."
        );
    }

    protected void setZipFile(String zipFilePath) {
        zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            throw new RuntimeException(
                String.format(
                    "The file \"%s\" does not exist", zipFile.getPath()
                )
            );
        }
        if (!zipFile.canRead()) {
            throw new RuntimeException(
                String.format(
                    "The file \"%s\" is not readable", zipFile.getPath()
                )
            );
        }
    }

    protected void unzip() throws Exception {

        try (ZipInputStream zip
            = new ZipInputStream(new FileInputStream(zipFile));
        ){
            for (ZipEntry z = zip.getNextEntry(); z != null; z = zip.getNextEntry()) {
                System.out.printf("Handling %s%n", z.getName());
                if (!z.isDirectory()) {
                    File zfile = new File(destinationDirectory, z.getName());
                    mkdirs(zfile.getParentFile(),
                         "Failed to create parent directory for zip entry file \"%s\"."
                    );
                    File unzippedFile = unzipEntry(z, zip);
                    if (isZip(unzippedFile)) {
                        new Unzip(unzippedFile).unzip();
                    }
                }
            }
        }
    }

    protected boolean isZip(File file) {
        boolean b = false;
        try {
            b = new ZipFile(file).getName().length() > 0;
        } catch (IOException ignore) {}
        return b;
    }

    protected File unzipEntry(ZipEntry z, ZipInputStream zip) throws Exception {
        File zfile = new File(destinationDirectory, z.getName());
        System.out.printf("Unzipping to %s%n", zfile.getAbsolutePath());
        try ( FileOutputStream out = new FileOutputStream(zfile)) {
            zip.transferTo(out);
        }
        zip.closeEntry();;
        return zfile;
    }

    protected void mkdirs(File dir, String errorMessageFormat) {
        dir.mkdirs();
        if (!dir.exists()) {
            throw new RuntimeException(
                String.format(errorMessageFormat, dir.getPath()
                )
            );
        }
    }
}
