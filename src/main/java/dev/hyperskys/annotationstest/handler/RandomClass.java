package dev.hyperskys.annotationstest.handler;

import dev.hyperskys.annotationstest.annotations.ClassValueYes;
import dev.hyperskys.annotationstest.annotations.MethodValue;

@ClassValueYes
public class RandomClass {
    @MethodValue(newValue = "test111")
    public static void reason(String test) {
        System.out.println(test);
    }
}
