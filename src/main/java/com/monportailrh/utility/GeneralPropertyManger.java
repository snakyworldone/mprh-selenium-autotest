package com.monportailrh.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeneralPropertyManger {
    private static Properties PROP_FILE;
    public static String SSO_ENDPOINT;
    public static String SSO_ENDPOINT_API;
    public static String BASE_URL;
    public static String BASE_URL_API;
    public static String CLIENT_ID;
    public static String SUPERADMIN_USERNAME;
    public static String SUPERADMIN_PASSWORD;
    public static String JOLIE_USERNAME;
    public static String JOLIE_PASSWORD;

    public static void setProperties(String filePath) {
        retrieveProperties(filePath);
        readProperties();
    }

    private static void retrieveProperties(String filePath) {
        PROP_FILE = getProperties(filePath);
    }

    private static void readProperties() {
        SSO_ENDPOINT = PROP_FILE.getProperty("SSO_ENDPOINT");
        SSO_ENDPOINT_API = PROP_FILE.getProperty("SSO_ENDPOINT_API");
        BASE_URL = PROP_FILE.getProperty("BASE_URL");
        BASE_URL_API = PROP_FILE.getProperty("BASE_URL_API");
        CLIENT_ID = PROP_FILE.getProperty("CLIENT_ID");
        SUPERADMIN_USERNAME = PROP_FILE.getProperty("SUPERADMIN_USERNAME");
        SUPERADMIN_PASSWORD = PROP_FILE.getProperty("SUPERADMIN_PASSWORD");
        JOLIE_USERNAME = PROP_FILE.getProperty("JOLIE_USERNAME");
        JOLIE_PASSWORD = PROP_FILE.getProperty("JOLIE_PASSWORD");
    }

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(path);
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
