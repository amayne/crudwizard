package org.crudwizard.writer;

import org.crudwizard.code.GeneratedFile;
import org.crudwizard.generators.Generator;

import java.io.IOException;
import java.util.List;

public class FileManager {

    private static final GeneratedFileWriter GENERATED_FILE_WRITER = new GeneratedFileWriter();

    private final Generator generator;
    private final boolean dryRun;

    public FileManager(Generator generator, boolean dryRun) {
        this.generator = generator;
        this.dryRun = dryRun;
    }

    public void write() {
        List<GeneratedFile> files = generator.generate();

        for (GeneratedFile file : files) {
            System.out.println(file.getFile().toString());
            try {
                System.out.println(file.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
