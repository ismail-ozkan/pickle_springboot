package com.pickle.pickledemo.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //1- Create the object of Properties
    private static Properties properties = new Properties();

    static{

        try {

            //2- We need to open the file in java memory: FileInputStream
            FileInputStream file = new FileInputStream("configuration.properties");

            //3- Load the properties object using FileInputStream object
            properties.load(file);

            //close the file
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in the ConfigurationReader class.");
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }

    // Method to set property value
    public static void setProperty(String key, String value) {
        String environment = System.getProperty("environment") != null ? System.getProperty("environment") : ConfigurationReader.getProperty("environment");


        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/environments/" + environment + ".properties";
            // Open the file in java memory: FileOutputStream with append mode
            FileOutputStream file = new FileOutputStream(path);

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
