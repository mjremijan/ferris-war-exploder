package org.ferris.war.exploder;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {


    public static void main(String[] args) throws Exception
    {
        System.out.printf("=== Welcome to Ferris WAR Exploder ===%n");

        new Unzip("./src/test/jars/commons-lang3-3.7.jar", "./target/unzipped")
            .unzip();
    }

//    private static void unzip(String zipFilePath, String destDir)
//    throws Exception
//    {
//        File ff = new File(zipFilePath);
//        if (!ff.exists()) {
//            throw new RuntimeException(String.format("File does not exist \"%s\"", zipFilePath));
//        }
//
//
//        File dir = new File(destDir, ff.getName());
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        FileInputStream fis;
//        byte[] buffer = new byte[1024];
//
//        fis = new FileInputStream(zipFilePath);
//        ZipInputStream zis = new ZipInputStream(fis);
//        ZipEntry ze = zis.getNextEntry();
//        while (ze != null) {
//            String fileName = ze.getName();
//            File newFile = new File(destDir + File.separator + fileName);
//            if (ze.isDirectory()) {
//                newFile.mkdirs();
//            }
//            else {
//                System.out.printf("Unzipping to %s%n", newFile.getAbsolutePath());
//                new File(newFile.getParent()).mkdirs();
//                FileOutputStream fos = new FileOutputStream(newFile);
//                int len;
//                while ((len = zis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, len);
//                }
//                fos.close();
//                zis.closeEntry();
//
//                if (newFile.getName().toLowerCase().endsWith(".jar")) {
//                    File jarDir = new File(newFile.getParentFile(), newFile.getName().substring(0, newFile.getName().length() - 4));
//                    unzip(newFile.toString(), jarDir.toString());
//                }
//            }
//            ze = zis.getNextEntry();
//        }
//        zis.closeEntry();
//        zis.close();
//        fis.close();
//    }
}
