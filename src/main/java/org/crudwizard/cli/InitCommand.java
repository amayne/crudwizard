package org.crudwizard.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(commandNames = {"init", "initialize"}, commandDescription = "Initialize a Dropwizard skeleton")
public class InitCommand {

    @Parameter(description = "Name of the application", required = true)
    private List<String> applicationNameList;

    @Parameter(names = "-p", description = "Package", required = false)
    private String packageName;

    public String getAppName() {
        if (applicationNameList.size() != 1) {
            throw new IllegalArgumentException("Please provide one app name");
        }
        return applicationNameList.get(0);
    }

    public String getPackage() {
        return packageName;
    }
}
