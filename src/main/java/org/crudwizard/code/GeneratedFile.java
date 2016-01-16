package org.crudwizard.code;

import java.io.File;
import java.io.IOException;

public abstract class GeneratedFile {

    public abstract String getContent() throws IOException;

    public abstract File getFile();
}
