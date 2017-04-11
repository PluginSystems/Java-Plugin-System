package com.github.ysl3000.pluginsystem;

import com.github.ysl3000.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.pluginsystem.interfaces.PluginConfigLoader;
import org.springframework.util.StopWatch;

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


            StopWatch stopWatch = new StopWatch("fileLoadingProcess");
            stopWatch.setKeepTaskList(true);

            File[] files = folder.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });

            if (files != null) {
                for (File jar : files) {

                    stopWatch.start(jar.getName());

                    String mainClass = null;
                    try {
                        ZipFile zipFile = new ZipFile(jar);

                        InputStream is = zipFile.getInputStream(zipFile.getEntry("extension.yml"));
                        mainClass = pluginConfigLoader.getPathToMainPluginClass(is);
                        ClassLoader l = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()}, getClass().getClassLoader());

                        Class<?> clazz = l.loadClass(mainClass);
                        pluginClasses.add(clazz);

                    } catch (IOException e) {
                        messageLogger.error("Error while loading module file " + jar.getName());
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        messageLogger.error("Class not found! Wrong main defined in extension.yml?: " + jar.getName() + " class: " + mainClass);
                        e.printStackTrace();
                    }

                    stopWatch.stop();
                }
            }

            messageLogger.info2LogFile(stopWatch.prettyPrint());
        }
    }

    public void enable() {

        StopWatch stopWatch = new StopWatch("enableProcess");
        stopWatch.setKeepTaskList(true);


        for (Class<?> clazz : pluginClasses) {

            stopWatch.start(clazz.getName());

            try {
                T plugin = (T) clazz.newInstance();
                plugin.onEnable();
                plugins.add(plugin);
                messageLogger.info(plugin.getPluginIdentity() + " enabled!");
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            stopWatch.stop();
        }


        messageLogger.info2LogFile(stopWatch.prettyPrint());

    }

    public void disable() {
        StopWatch stopWatch = new StopWatch("disableProcess");
        stopWatch.setKeepTaskList(true);


        for (T extension : plugins) {
            stopWatch.start(extension.getPluginIdentity());
            extension.onDisable();
            messageLogger.info(extension.getPluginIdentity() + " disabled!");
            stopWatch.stop();
        }


        messageLogger.info2LogFile(stopWatch.prettyPrint());
    }

    public void unload() {

        StopWatch stopWatch = new StopWatch("unloadProcess");
        stopWatch.setKeepTaskList(true);
        stopWatch.start();


        plugins.clear();
        pluginClasses.clear();

        stopWatch.stop();

        messageLogger.info2LogFile(stopWatch.prettyPrint());
    }
}
