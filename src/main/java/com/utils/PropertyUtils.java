package com.utils;

import enums.ConfigPropertyTypes;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static constants.FrameworkConstants.CONFIG_PROPERTIES_FILE_PATH;

public final class PropertyUtils
{
    private PropertyUtils () {} ;

    private static Properties properties = new Properties();
    private static Map<String, String> map = new HashMap();

    // "Reading config.properties file contents and load into Map"... kept this code into static block so that this gets executed only once.
    static
    {
      try(FileInputStream fis = new FileInputStream(CONFIG_PROPERTIES_FILE_PATH)) // this is called try with resources
        {
            properties.load(fis);
        }
        catch (Exception e)
        {
           e.printStackTrace();
           System.exit(0);  // if there is file not found exception, we're simply aborting program
        }

        properties.entrySet().forEach(entry->map.put((String) entry.getKey(), (String) entry.getValue()));
    }

    public static String getValue(ConfigPropertyTypes key)
    {
        return map.get(key.name().toLowerCase());
    }
}
