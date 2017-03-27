package com.github.ysl3000.plugintest;

import com.github.ysl3000.pluginsystem.interfaces.MessageLogger;

/**
 * Created by ysl3000
 */
public class ConsoleMessageLogger implements MessageLogger {
    @Override
    public void error(String message) {
        System.out.println("[ERROR] "+message);
    }

    @Override
    public void info(String message) {
        System.out.println("[INFO] "+ message);
    }
}
