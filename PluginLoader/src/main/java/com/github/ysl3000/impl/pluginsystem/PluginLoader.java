package com.github.ysl3000.impl.pluginsystem;

import com.github.ysl3000.impl.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.impl.pluginsystem.interfaces.PluginConfigLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

/**
 * Created by ysl3000
 */
public class PluginLoader<T extends IPlugin> {

    protected final Map<String, T> plugins = new HashMap<>();
    private final Set<Class<IPlugin>> pluginClasses = new HashSet<>();
    private final File folder;
    private MessageLogger messageLogger;
    private PluginConfigLoader pluginConfigLoader;


    public PluginLoader(MessageLogger messageLogger, File folder, PluginConfigLoader pluginConfigLoader) {
        this.messageLogger = messageLogger;
        this.folder = folder;
        this.pluginConfigLoader = pluginConfigLoader;
    }


    public void load() {

        if (folder.exists() && folder.isDirectory()) {


            File[] files = folder.listFiles((dir, name) -> name.endsWith(".jar"));


            Set<URL> urls = new HashSet<>();
            Stream.of(files).forEach(jar ->{
                try {
                    urls.add(jar.toURI().toURL());
                } catch (MalformedURLException ignored) {
                }
            });

            ClassLoader loader = URLClassLoader.newInstance(urls.toArray(new URL[]{}),getClass().getClassLoader());

            if (files != null) {
                for (File jar : files) {

                    String mainClass = null;
                    try {
                        ZipFile zipFile = new ZipFile(jar);
                        InputStream is = zipFile.getInputStream(zipFile.getEntry("extension.properties"));
                        mainClass = pluginConfigLoader.getPathToMainPluginClass(is);
                        Class<?> clazz = loader.loadClass(mainClass);
                        if(clazz.isAssignableFrom(IPlugin.class))
                        pluginClasses.add((Class<IPlugin>) clazz);

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }
            }

        }
    }

    public void enable() {
        for (Class<?> clazz : pluginClasses) {
            try {
                Class<? extends IPlugin> castedClass = (Class<? extends IPlugin>) clazz;
                final T plugin = (T) castedClass.newInstance();
                plugins.put(castedClass.getSimpleName(), plugin);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        plugins.values().forEach(plugin -> {
            plugin.onEnable();
        });
    }

    public void disable() {
        for (T extension : plugins.values()) {
            extension.onDisable();
        }
    }

    public void unload() {
        plugins.clear();
        pluginClasses.clear();
    }


    public IPlugin getPlugin(String pluginName) {
        return plugins.get(pluginName);
    }

}
