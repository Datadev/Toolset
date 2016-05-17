package br.com.datadev.toolset.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Fabrício
 */
public class PropertiesHelper {

    private final String file;

    public PropertiesHelper(String file) throws IOException {
        this(file, false);
    }

    public PropertiesHelper(String file, boolean create) throws FileNotFoundException, IOException {
        if (new File(file).exists()) {
            this.file = file;
        } else if (create) {
            new File(file).createNewFile();
            this.file = file;
        } else {
            throw new FileNotFoundException(file);
        }
    }

    public HashMap<String, String> getPropertiesMap() throws IOException {
        Properties props = new Properties();
        HashMap<String, String> map = new HashMap<>();
        try (InputStream resourceStream = new FileInputStream(file)) {
            props.load(resourceStream);
            for (final String name : props.stringPropertyNames()) {
                map.put(name, props.getProperty(name));
            }
        }
        return map;
    }

    public void setPropertiesMap(HashMap<String, String> map) throws IOException {
        Properties props = new Properties();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            props.put(entry.getKey(), entry.getValue());
        }
        try (OutputStream resourceStream = new FileOutputStream(file)) {
            props.store(resourceStream, null);
        }
    }
}
