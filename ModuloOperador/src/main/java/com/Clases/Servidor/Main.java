package com.Clases.Servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties config = new Properties();

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 7));
        dir += "config.properties";
        
        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            config.load(fin);
            ClienteRMI servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"), (String) config.get("SERVICENAME"));
            System.out.println(String.valueOf(servidor.suma(10, 20)));
        } catch (Exception e) {

        }
    }
}
