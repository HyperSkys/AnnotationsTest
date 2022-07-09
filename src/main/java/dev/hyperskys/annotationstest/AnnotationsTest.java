package dev.hyperskys.annotationstest;

import dev.hyperskys.annotationstest.annotations.ClassValueYes;
import dev.hyperskys.annotationstest.annotations.MethodValue;
import dev.hyperskys.annotationstest.handler.RandomClass;
import org.reflections.Reflections;

import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
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
            if (clazz.isAnnotationPresent(ClassValueYes.class)) {
                System.out.println("class vlaue is yessed");
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if(method.isAnnotationPresent(MethodValue.class)) {
                    System.out.println(method.getAnnotation(MethodValue.class).newValue());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        RandomClass.reason("test");
        AnnotationsTest annotationsTest = new AnnotationsTest();
        annotationsTest.handleAnnotations();
    }
}