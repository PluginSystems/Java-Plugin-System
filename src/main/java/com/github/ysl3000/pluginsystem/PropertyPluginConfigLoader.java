package com.github.ysl3000.pluginsystem;

import com.github.ysl3000.pluginsystem.interfaces.PluginConfigLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ysl3000
 */
public class PropertyPluginConfigLoader implements PluginConfigLoader {
    @Override
    public String getPathToMainPluginClass(InputStream inputStream) throws IOException {
        Properties config = new Properties();
        config.load(inputStream);
        return config.getProperty("main");
    }
}
