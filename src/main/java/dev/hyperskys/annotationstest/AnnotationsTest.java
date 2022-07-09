package dev.hyperskys.annotationstest;

import dev.hyperskys.annotationstest.annotations.SetValue;
import dev.hyperskys.annotationstest.annotations.TellMeThisValue;
import dev.hyperskys.annotationstest.handler.RandomClass;
import org.reflections.Reflections;

import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;


public class AnnotationsTest {

    public static final Reflections reflections = new Reflections("dev.hyperskys");

    public static List<Class<?>> getClasses(ClassLoader cl, String pack) throws Exception {
        String dottedPackage = pack.replaceAll("/", ".");
        List<Class<?>> classes = new ArrayList<>();
        URL upackage = cl.getResource(pack);

        assert upackage != null;
        DataInputStream dis = new DataInputStream((InputStream) upackage.getContent());
        String line;
        while ((line = dis.readLine()) != null) {
            if(line.endsWith(".class")) {
                classes.add(Class.forName(dottedPackage+"."+line.substring(0,line.lastIndexOf('.'))));
            }
        }

        return classes;
    }

    public void handleAnnotations() throws Exception {
        for (Class<?> clazz : getClasses(RandomClass.class.getClassLoader(), "dev/hyperskys/annotationstest/handler")) {
            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter parameter : method.getParameters()) {
                    if (parameter.isAnnotationPresent(TellMeThisValue.class)) {
                        System.out.println();
                    }
                }

                if(method.isAnnotationPresent(SetValue.class)) {
                    System.out.println(method.getAnnotation(SetValue.class).newValue());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        AnnotationsTest annotationsTest = new AnnotationsTest();
        annotationsTest.handleAnnotations();
        RandomClass.reason("test");
    }
}