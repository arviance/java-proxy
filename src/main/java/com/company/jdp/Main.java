package com.company.jdp;

import java.lang.reflect.Proxy;

public class Main {

    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[]{itf},
                new LogHandler(target)
        );
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        Math logged = withLogging(calculator, Math.class);
        System.out.println("Start process..");
        int resultPlus = logged.plus(2, 3);
        System.out.println("Addition result: " + resultPlus);
        int resultMinus = logged.minus(5, 2);
        System.out.println("Subtraction result: " + resultMinus);
        System.out.println("End process...");
        System.out.println(logged);
    }
}
