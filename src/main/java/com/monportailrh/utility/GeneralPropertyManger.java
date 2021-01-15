package com.monportailrh.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeneralPropertyManger {
    private static Properties PROP_FILE;
    public static String SSO_ENDPOINT_API;
    public static String BASE_URL;
    public static String BASE_URL_API;
    public static String CLIENT_ID;
    public static String SUPERADMIN_USERNAME;
    public static String SUPERADMIN_PASSWORD;
    public static String ANGELINA_JOLIE_USERNAME;
    public static String ANGELINA_JOLIE_PASSWORD;
    public static String BRAD_PITT_USERNAME;
    public static String BRAD_PITT_PASSWORD;
    public static String DOLPH_LUNDGREN_USERNAME;
    public static String DOLPH_LUNDGREN_PASSWORD;
    public static String JESSICA_ALBA_USERNAME;
    public static String JESSICA_ALBA_PASSWORD;

    public static void setProperties(String filePath) {
        retrieveProperties(filePath);
        readProperties();
    }

    private static void retrieveProperties(String filePath) {
        PROP_FILE = getProperties(filePath);
    }

    private static void readProperties() {
        SSO_ENDPOINT_API = PROP_FILE.getProperty("SSO_ENDPOINT_API");
        BASE_URL = PROP_FILE.getProperty("BASE_URL");
        BASE_URL_API = PROP_FILE.getProperty("BASE_URL_API");
        CLIENT_ID = PROP_FILE.getProperty("CLIENT_ID");
        SUPERADMIN_USERNAME = PROP_FILE.getProperty("SUPERADMIN_USERNAME");
        SUPERADMIN_PASSWORD = PROP_FILE.getProperty("SUPERADMIN_PASSWORD");
        ANGELINA_JOLIE_USERNAME = PROP_FILE.getProperty("ANGELINA_JOLIE_USERNAME");
        ANGELINA_JOLIE_PASSWORD = PROP_FILE.getProperty("ANGELINA_JOLIE_PASSWORD");
        BRAD_PITT_USERNAME = PROP_FILE.getProperty("BRAD_PITT_USERNAME");
        BRAD_PITT_PASSWORD = PROP_FILE.getProperty("BRAD_PITT_PASSWORD");
        DOLPH_LUNDGREN_USERNAME = PROP_FILE.getProperty("DOLPH_LUNDGREN_USERNAME");
        DOLPH_LUNDGREN_PASSWORD = PROP_FILE.getProperty("DOLPH_LUNDGREN_PASSWORD");
        JESSICA_ALBA_USERNAME = PROP_FILE.getProperty("JESSICA_ALBA_USERNAME");
        JESSICA_ALBA_PASSWORD = PROP_FILE.getProperty("JESSICA_ALBA_PASSWORD");
    }

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/" + path);
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
