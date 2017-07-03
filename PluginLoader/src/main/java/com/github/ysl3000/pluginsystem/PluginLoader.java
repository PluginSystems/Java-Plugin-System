package com.github.ysl3000.pluginsystem;

import com.github.ysl3000.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.pluginsystem.interfaces.PluginConfigLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipFile;

/**
 * Created by ysl3000
 */
public class PluginLoader<T extends IPlugin> {

    private final Set<T> plugins = new HashSet<>();
    private final Set<Class<?>> pluginClasses = new HashSet<>();
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



            File[] files = folder.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });

            if (files != null) {
                for (File jar : files) {


                    String mainClass = null;
                    try {
                        ZipFile zipFile = new ZipFile(jar);

                        InputStream is = zipFile.getInputStream(zipFile.getEntry("extension.yml"));
                        mainClass = pluginConfigLoader.getPathToMainPluginClass(is);
                        ClassLoader l = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()}, getClass().getClassLoader());

                        Class<?> clazz = l.loadClass(mainClass);
                        pluginClasses.add(clazz);

                    } catch (IOException ioException) {
                        messageLogger.error("Error while loading module file " + jar.getName());
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        messageLogger.error("Class not found! Wrong main defined in extension.yml?: " + jar.getName() + " class: " + mainClass);
                        classNotFoundException.printStackTrace();
                    }

                }
            }

        }
    }

    public void enable() {


        for (Class<?> clazz : pluginClasses) {


            try {
                T plugin = (T) clazz.newInstance();
                plugin.onEnable();
                plugins.add(plugin);
                messageLogger.info(plugin.getPluginIdentity() + " enabled!");
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }



    }

    public void disable() {

        for (T extension : plugins) {
            extension.onDisable();
            messageLogger.info(extension.getPluginIdentity() + " disabled!");
        }


    }

    public void unload() {

        plugins.clear();
        pluginClasses.clear();

    }
}
