package com.github.ysl3000.pluginsystem.interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ysl3000
 */
public interface PluginConfigLoader {
    String getPathToMainPluginClass(InputStream inputStream) throws IOException;
}
