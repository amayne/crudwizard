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

        TypeSpec appClass = TypeSpec.classBuilder(appName + "Application")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Application"), ClassName.get(packageName, appName + "Configuration")))
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, appClass)
                .build();

        javaFile.writeTo(System.out);
    }
}
