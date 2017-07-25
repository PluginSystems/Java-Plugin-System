package com.github.ysl3000;

import com.github.ysl3000.api.IPlugin;

/**
 * Created by ysl3000
 */
public class Plugin2 implements IPlugin, Mathematic {
    public String getPluginIdentity() {
        return "Plugin2";
    }

    @Override
    public void onRegister() {

    }

    public void onEnable() {


    }

    @Override
    public void onDisable() {

    }

    @Override
    public double add(double value1, double value2) {
        return value1 + value1;
    }

    @Override
    public double subtract(double value1, double value2) {
        return value1 - value2;
    }

    @Override
    public double multiply(double value1, double value2) {


        return value1 * value2;
    }

    @Override
    public double divide(double value1, double value2) throws CannotDivideException {
        if (value1 == 0 || value2 == 0) throw new CannotDivideException("either value1 or value2 is zero");
        return value1/value2;
    }


}
