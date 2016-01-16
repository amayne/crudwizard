package org.crudwizard.code;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.io.File;

public class GeneratedJavaCode extends GeneratedFile {

    private final String basePath;
    private final String packageName;
    private final String className;
    private final String content;

    public GeneratedJavaCode(String basePath, String packageName, String className, String content) {
        this.basePath = basePath;
        this.packageName = packageName;
        this.className = className;
        this.content = content;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public File getFile() {
        String codePath = Joiner.on("/").join(Splitter.on(".").split(packageName));
        return new File(Joiner.on("/").join(basePath, codePath, getFileName()));
    }

    private String getFileName() {
        return Joiner.on(".").join(className, "java");
    }

}
