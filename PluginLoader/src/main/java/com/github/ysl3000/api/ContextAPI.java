package com.github.ysl3000.api;

/**
 * Created by ysl3000
 */
public class ContextAPI {

    private static ContextInterface contextInterface;


    public static ContextInterface getSystem(){
        return contextInterface;
    }


    public static void setImpl(ContextInterface contextInterface) {
        if (ContextAPI.contextInterface != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Server");
        } else {
            ContextAPI.contextInterface = contextInterface;
        }
    }


}
