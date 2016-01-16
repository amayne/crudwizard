package org.crudwizard.code;

import com.google.common.base.Joiner;

import java.io.File;

public class ProjectFile extends GeneratedFile {

    private final String basePath;
    private final String fileName;
    private final String content;

    public ProjectFile(String basePath, String fileName, String content) {
        this.basePath = basePath;
        this.fileName = fileName;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public File getFile() {
        return new File(Joiner.on("/").join(basePath, fileName));
    }
}
