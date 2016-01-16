package org.crudwizard.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandNames = {"add", "new"}, commandDescription = "Add a new model")
public class AddCommand {

    @Parameter(description = "The model fields, e.g. id:int message:string", required = false)
    private List<String> fields = new ArrayList<>();

    @Parameter(names = {"-model", "-m"}, description = "Name of the model", required = true)
    private String modelName;

    @Parameter(names = "-p", description = "Package", required = false)
    private String packageName;

    @Parameter(names = "-r", description = "Whether the model is modifiable", required = false)
    private boolean readOnly = false;

    @Parameter(names = "-dry", description = "Dry run", required = false)
    private boolean dryRun = false;

    @Parameter(names = "-help", description = "See usages", help = true)
    private boolean help = false;

    public List<String> getFields() {
        return fields;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getModelName() {
        return modelName;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public boolean isHelp() {
        return help;
    }
}