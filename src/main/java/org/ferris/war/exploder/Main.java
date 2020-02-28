package org.ferris.war.exploder;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {


    public static void main(String[] args) throws Exception
    {
        System.out.printf("=== Welcome to Ferris WAR Exploder ===%n");

        new Unzip("./src/test/jars/commons-lang3-3.7.jar", "./target/unzipped/jar")
            .unzip();

        new Unzip("./src/test/wars/sample.war", "./target/unzipped/war")
            .unzip();

        System.out.printf("%n=== DONE ===%n");
    }
}
