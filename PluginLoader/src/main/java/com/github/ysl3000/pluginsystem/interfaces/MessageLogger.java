package com.github.ysl3000.pluginsystem.interfaces;

/**
 * Created by ysl3000
 */
public interface MessageLogger {

    void error(String message);

    void info(String message);

    void info2LogFile(String message);
}
