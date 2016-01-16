package org.crudwizard.writer;

import org.apache.commons.io.FileUtils;
import org.crudwizard.code.GeneratedFile;

import java.io.IOException;

public class GeneratedFileWriter {

    public void write(GeneratedFile file) throws IOException {
        FileUtils.write(file.getFile(), file.getContent());
    }
}
