package org.crudwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.javapoet.*;
import org.apache.commons.lang3.text.WordUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        /////////////////////////////////////////////////////////
        String appName = "HelloWorld";
        String representationName = "Tweet";
        String paramName = "args";
        String packageName = "com.example";

        List<ClassProperty> propertyList = new ArrayList<ClassProperty>();
        propertyList.add(new ClassProperty("id", Integer.class));
        propertyList.add(new ClassProperty("content", String.class));
        propertyList.add(new ClassProperty("yetAnotherProperty", Long.class));
        propertyList.add(new ClassProperty("again", String[].class));
        /////////////////////////////////////////////////////////

        TypeSpec confClass = buildConfiguration(appName);
        TypeSpec appClass = buildApp(appName, paramName, packageName);
        TypeSpec repClass = buildRepresentation(representationName, propertyList);

        JavaFile javaFile = JavaFile.builder(packageName, confClass)
                .build();

        javaFile.writeTo(System.out);
    }

    private static TypeSpec buildConfiguration(String appName) {
        return TypeSpec.classBuilder(appName + "Configuration")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ClassName.get("io.dropwizard", "Configuration"))
                .build();
    }

    private static TypeSpec buildApp(String appName, String paramName, String packageName) {
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

        return TypeSpec.classBuilder(appName + "Application")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Application"),
                        ClassName.get(packageName, appName + "Configuration")))
                .addMethod(main)
                .addMethod(initialize)
                .addMethod(run)
                .build();
    }

    private static TypeSpec buildRepresentation(String representationName, List<ClassProperty> fieldsList) {

        MethodSpec defaultConstructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build();

        TypeSpec.Builder builder = TypeSpec.classBuilder(representationName)
                .addModifiers(Modifier.PUBLIC)
                .addField(Integer.class, "id", Modifier.PRIVATE)
                .addMethod(defaultConstructor);

        for (ClassProperty classProperty: fieldsList) {
            builder.addField(classProperty.buildFieldSpec());
            builder.addMethod(classProperty.buildGetter());
        }

        return builder.build();
    }
}
