package org.crudwizard.writer;

import org.crudwizard.code.GeneratedFile;
import org.crudwizard.generators.Generator;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import static org.crudwizard.console.ConsoleUtils.green;

public class FileManager {

    private static final GeneratedFileWriter GENERATED_FILE_WRITER = new GeneratedFileWriter();

    private final Generator generator;
    private final boolean dryRun;

    public FileManager(Generator generator, boolean dryRun) {
        this.generator = generator;
        this.dryRun = dryRun;
    }

    public void write() throws IOException {
        List<GeneratedFile> files = generator.generate();

        for (GeneratedFile file : files) {
            System.out.println(green(MessageFormat.format("+{0}", file.getFile().toString())));
            if (!dryRun) {
                GENERATED_FILE_WRITER.write(file);
            }
        }
    }
}
