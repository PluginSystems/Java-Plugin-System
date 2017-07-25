package com.github.ysl3000;

public interface Mathematic {
    double add(double value1, double value2);
    double subtract(double value1, double value2);
    double multiply(double value1, double value2);
    double divide(double value1, double value2) throws CannotDivideException;
}
