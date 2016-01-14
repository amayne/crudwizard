package org.crudwizard;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String appName = "HelloWorld";
        String paramName = "args";
        String packageName = "com.example";

        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addException(Exception.class)
                .addParameter(String[].class, paramName)
                .addStatement("new $NApplication().run($L)", appName, paramName)
                .build();

        MethodSpec run = MethodSpec.methodBuilder("run")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(ClassName.get(packageName, appName + "Configuration"), "configuration")
                .addParameter(ClassName.get("io.dropwizard", "Environment"), "environment")
                .build();

        MethodSpec initialize = MethodSpec.methodBuilder("initialize")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Bootstrap"),
                        ClassName.get(packageName, appName + "Configuration")), "bootstrap")
                .build();

        TypeSpec appClass = TypeSpec.classBuilder(appName + "Application")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Application"),
                        ClassName.get(packageName, appName + "Configuration")))
                .addMethod(main)
                .addMethod(initialize)
                .addMethod(run)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, appClass)
                .build();

        javaFile.writeTo(System.out);
    }
}
