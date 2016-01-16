package org.crudwizard.code;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;

public abstract class GeneratedJavaCode extends GeneratedFile {

    private final String basePath;
    private final String packageName;
    private final String className;

    public GeneratedJavaCode(String basePath, String packageName, String className) {
        this.basePath = basePath;
        this.packageName = packageName;
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public File getFile() {
        String codePath = Joiner.on("/").join(Splitter.on(".").split(packageName));
        return new File(Joiner.on("/").join(basePath, codePath, getFileName()));
    }

    @Override
    public String getContent() {
        return buildJavaFile(packageName);
    }

    private String getFileName() {
        return Joiner.on(".").join(className, "java");
    }


    protected String buildJavaFile(String packageName) {
        return JavaFile.builder(packageName, build())
                .build()
                .toString();
    }

    protected abstract TypeSpec build();
}
