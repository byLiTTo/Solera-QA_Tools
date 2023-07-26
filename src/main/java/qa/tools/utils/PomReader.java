/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.utils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

public class PomReader {

    private PomReader() {
        throw new IllegalStateException("PomReader class");
    }

    public static String readSnapshotVersion() {
        File pomFile = new File("pom.xml"); // Ruta del archivo pom.xml
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;

        try {
            // Leer el archivo pom.xml y obtener el modelo del proyecto
            model = reader.read(Files.newBufferedReader(pomFile.toPath(), StandardCharsets.UTF_8));

            // Obtener la versión del proyecto
            String version = model.getVersion();
            if (version.endsWith("-SNAPSHOT")) { // Si la versión es snapshot
                return version; // Retornar la versión
            } else {
                return ""; // Si no es snapshot, retornar una cadena vacía
            }
        } catch (Exception e) {
            return ""; // Si ocurre un error, retornar una cadena vacía
        }
    }
}
