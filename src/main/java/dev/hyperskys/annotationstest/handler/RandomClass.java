package dev.hyperskys.annotationstest.handler;

import dev.hyperskys.annotationstest.annotations.SetValue;
import dev.hyperskys.annotationstest.annotations.TellMeThisValue;
import dev.hyperskys.annotationstest.annotations.ThisHasAFuckingAnnotation;

@ThisHasAFuckingAnnotation
public class RandomClass {
    @SetValue(newValue = "test111")
    public static void reason(@TellMeThisValue String test) {
        System.out.println(test);
    }
}
