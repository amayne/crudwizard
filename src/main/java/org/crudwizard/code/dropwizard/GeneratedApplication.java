package org.crudwizard.code.dropwizard;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import org.crudwizard.code.GeneratedJavaCode;

import javax.lang.model.element.Modifier;

public class GeneratedApplication extends GeneratedJavaCode {

    private final String appName;
    private final String packageName;

    public GeneratedApplication(String appName, String packageName) {
        super("src/main/java", packageName, getClassName(appName));
        this.appName = appName;
        this.packageName = packageName;
    }

    private static java.lang.String getClassName(String appName) {
        return appName + "Application";
    }

    @Override
    protected TypeSpec build() {

        String paramName = "args";

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

        return TypeSpec.classBuilder(GeneratedApplication.getClassName(appName))
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Application"),
                        ClassName.get(packageName, appName + "Configuration")))
                .addMethod(main)
                .addMethod(initialize)
                .addMethod(run)
                .build();
    }
}
