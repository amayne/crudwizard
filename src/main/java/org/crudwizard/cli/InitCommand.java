package org.crudwizard.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;

@Parameters(commandNames = {"init", "initialize"}, commandDescription = "Initialize a Dropwizard skeleton")
public class InitCommand {

    @Parameter(description = "Name of the application", required = true)
    private List<String> applicationNameList;

    @Parameter(names = "-p", description = "Package", required = true)
    private String packageName;

    @Parameter(names = "-dw", description = "Add dropwizard libraries, comma separted, e.g. testing,jdbi,assets,views", required = false)
    private String dropwizardLibraries;

    @Parameter(names = "-idea", description = "Enable IntelliJ support", required = false)
    private boolean intellijIdea = false;

    @Parameter(names = "-dry", description = "Dry run", required = false)
    private boolean dryRun = false;

    @Parameter(names = "-help", description = "See usages", help = true)
    private boolean help = false;

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

    public List<String> getDropwizardLibraries() {
        if (dropwizardLibraries == null || dropwizardLibraries.isEmpty()) {
            return Collections.emptyList();
        }
        return ImmutableList.copyOf(Splitter.on(",").splitToList(dropwizardLibraries));
    }

    public boolean isHelp() {
        return help;
    }
}
