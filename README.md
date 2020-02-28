# Ferris WAR Exploder

I wrote ferris-war-exploder to explode either:

1. A JAR file
2. A WAR file which every JAR file it finds also exploded.
3. An EAR file with every JAR file (see #1) and WAR file (see #2) also exploded.

Basically, ferris-war-exploder explodes anything which is a ZIP file format. Any entries
which are in a ZIP file format will also be exploded. This happens recursively so anything
that can be exploded is exploded.

I wrote ferris-war-exploder because I needed to get at all of the `.class` file in the WAR,
including all that were in the JAR file. My original problem was a Java 9+ dependency
somewhere I needed to find.

See <https://mjremijan.blogspot.com/2020/02/explode-war-file-recursively.html> for more details.

Use this in conjunction with <https://github.com/mjremijan/ferris-magic-number>.  
