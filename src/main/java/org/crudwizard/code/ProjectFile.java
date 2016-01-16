package org.crudwizard.code;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Joiner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;

import static com.google.common.base.Charsets.UTF_8;

public abstract class ProjectFile extends GeneratedFile {

    protected static final MustacheFactory MUSTACHE_FACTORY = new DefaultMustacheFactory();

    protected final String basePath;
    protected final String fileName;

    public ProjectFile(String basePath, String fileName) {
        this.basePath = basePath;
        this.fileName = fileName;
    }

    @Override
    public File getFile() {
        if ("".equals(basePath)) {
            return new File(fileName);
        }
        return new File(Joiner.on("/").join(basePath, fileName));
    }

    protected String buildFromTemplate(String template) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(stream);
        Mustache mustache = MUSTACHE_FACTORY.compile(new StringReader(template), fileName);
        mustache.execute(writer, this);
        writer.flush();
        return stream.toString(UTF_8.name());
    }
}
