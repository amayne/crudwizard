package org.crudwizard.code.dropwizard;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import org.crudwizard.code.GeneratedJavaCode;

import javax.lang.model.element.Modifier;

public class GeneratedConfiguration extends GeneratedJavaCode {

    private final String appName;

    public GeneratedConfiguration(String appName, String packageName) {
        super("src/main/java", packageName, getClassName(appName));
        this.appName = appName;
    }

    @Override
    protected TypeSpec build() {
        return TypeSpec.classBuilder(appName + "Configuration")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ClassName.get("io.dropwizard", "Configuration"))
                .build();
    }

    private static String getClassName(String appName) {
        return appName + "Configuration";
    }
}
