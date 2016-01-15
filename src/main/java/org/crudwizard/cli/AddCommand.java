package org.crudwizard.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandNames = {"add", "new"}, commandDescription = "Add a new model")
public class AddCommand {

    @Parameter(description = "", required = false)
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = "-p", description = "Package", required = false)
    private String packageName;

    @Parameter(names = "-r", description = "Read Only", required = false)
    private boolean readOnly = false;


    public List<String> getParameters() {
        return parameters;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getModel() {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Please provide a model name");
        }
        return parameters.get(0);
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}