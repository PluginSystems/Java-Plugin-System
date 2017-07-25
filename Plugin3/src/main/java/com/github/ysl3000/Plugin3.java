package com.github.ysl3000;

import com.github.ysl3000.api.IPlugin;

/**
 * Created by ysl3000
 */
public class Plugin3 implements IPlugin{
    public String getPluginIdentity() {
        return "Plugin3";
    }

    public void onRegister() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void printMessage(String message){
        System.out.println("Printed message: "+message);
    }

    public String returnMessage(String message){
        return "Returned message: "+message;
    }

}
