package com.github.ysl3000;

public class CannotDivideException extends Exception{
    public CannotDivideException(String cannot_be_zero) {
        super(cannot_be_zero);
    }
}
