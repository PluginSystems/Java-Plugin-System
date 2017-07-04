package com.github.ysl3000.impl.plugintest;

import com.github.ysl3000.impl.pluginsystem.interfaces.MessageLogger;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Created by ysl3000
 */
public class ConsoleMessageLogger implements MessageLogger {


    private Logger logger;
    private FileHandler fileHandler;

    public ConsoleMessageLogger() {

        /*
        logger = Logger.getAnonymousLogger();

        try {
            fileHandler = new FileHandler("./performance_%g.log",8096,10);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }

*/
    }


    @Override
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public void info(String message) {
        //System.out.println("[INFO] " + message);
    }

    @Override
    public void info2LogFile(String message) {
        //System.out.println(message);
        //logger.info(message);
    }
}
