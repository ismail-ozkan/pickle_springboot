package com.pickle.pickledemo.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    public static Properties properties;
    public static String environment;

    static {

        environment = System.getProperty("environment") != null ? System.getProperty("environment") : ConfigurationReader.getProperty("environment");

        try {

            String path = System.getProperty("user.dir") + "/src/test/resources/environments/" + environment + ".properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getVariable(String varName) {
        return properties.getProperty(varName);
    }

    public static void setVariable(String key, String value) {


        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/environments/" + environment + ".properties";
            // Open the file in java memory: FileOutputStream with append mode
            FileOutputStream file = new FileOutputStream(path);
            //properties = new Properties();
            // Set the property value
            properties.setProperty(key, value);

            // Store the properties object in the file
            properties.store(file, null);

            // Close the file
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing to configuration.properties file.");
            e.printStackTrace();
        }
    }

}
