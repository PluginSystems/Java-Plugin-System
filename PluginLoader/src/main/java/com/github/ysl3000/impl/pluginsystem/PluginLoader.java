package com.github.ysl3000.impl.pluginsystem;

import com.github.ysl3000.api.PluginStateChangeListener;
import com.github.ysl3000.impl.pluginsystem.interfaces.MessageLogger;
import com.github.ysl3000.impl.pluginsystem.interfaces.PluginConfigLoader;

import java.io.File;
import java.io.FilenameFilter;
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
    private final Set<Class<?>> pluginClasses = new HashSet<>();
    private final File folder;
    private MessageLogger messageLogger;
    private PluginConfigLoader pluginConfigLoader;

    private Map<Class<? extends IPlugin>, PluginStateChangeListener> listeners = new HashMap<>();
    private PluginStateChangeListener systemListener;

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
                Class<? extends IPlugin> castedClass = (Class<? extends IPlugin>) clazz;
                final T plugin = (T) castedClass.newInstance();
                plugins.put(castedClass.getSimpleName(), plugin);
                plugin.onRegister();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        plugins.values().forEach(plugin -> {
            plugin.onEnable();
            listeners.values().parallelStream().forEach(l -> l.onPluginGetsEnabled(plugin));
            if (systemListener != null) systemListener.onPluginGetsEnabled(plugin);
            messageLogger.info(plugin.getPluginIdentity() + " enabled!");

        });


    }

    public void disable() {

        for (T extension : plugins.values()) {
            this.removeListeners(extension);
            extension.onDisable();
            listeners.values().parallelStream().forEach(l -> l.onPluginGetsDisabled(extension));
            if (systemListener != null) systemListener.onPluginGetsDisabled(extension);
            messageLogger.info(extension.getPluginIdentity() + " disabled!");
        }


    }

    private void removeListeners(IPlugin plugin) {
        this.listeners.remove(plugin.getClass());
    }

    public void unload() {
        plugins.clear();
        pluginClasses.clear();
    }

    public void addListener(IPlugin plugin, PluginStateChangeListener listener) {
        this.listeners.put(plugin.getClass(), listener);
    }

    public void addSystemListener(PluginStateChangeListener listener) {
        this.systemListener = listener;
    }

    public IPlugin getPlugin(String pluginName) {
        return plugins.get(pluginName);
    }

}
