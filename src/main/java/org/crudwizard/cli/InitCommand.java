package org.crudwizard.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(commandNames = {"init", "initialize"}, commandDescription = "Initialize a Dropwizard skeleton")
public class InitCommand {

    @Parameter(description = "Name of the application", required = true)
    private List<String> applicationNameList;

    @Parameter(names = "-p", description = "Package", required = true)
    private String packageName;

    @Parameter(names = "-idea", description = "Enable IntelliJ support", required = false)
    private boolean intellijIdea = false;

    @Parameter(names = "-dry", description = "Dry run", required = false)
    private boolean dryRun = false;

    public String getAppName() {
        if (applicationNameList.size() != 1) {
            throw new IllegalArgumentException("Please provide one app name");
        }
        return applicationNameList.get(0);
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isIntellijIdea() {
        return intellijIdea;
    }

    public boolean isDryRun() {
        return dryRun;
    }
}
