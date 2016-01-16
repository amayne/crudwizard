package org.crudwizard.generators;

import com.google.common.collect.ImmutableList;
import org.crudwizard.code.GeneratedFile;
import org.crudwizard.code.dropwizard.GeneratedApplication;
import org.crudwizard.code.dropwizard.GeneratedConfiguration;
import org.crudwizard.code.project.GeneratedGradleFile;

import java.util.List;

public class ProjectInitializationGenerator implements Generator {

    private final String appName;
    private final String packageName;
    private final List<String> dropwizardLibraries;

    public ProjectInitializationGenerator(String appName, String packageName, List<String> dropwizardLibraries) {
        this.appName = appName;
        this.packageName = packageName;
        this.dropwizardLibraries = dropwizardLibraries;
    }

    @Override
    public List<GeneratedFile> generate() {

        GeneratedGradleFile generatedGradleFile = new GeneratedGradleFile("", dropwizardLibraries);

        GeneratedApplication generatedApplication = new GeneratedApplication(appName, packageName);

        GeneratedConfiguration generatedConfiguration = new GeneratedConfiguration(appName, packageName);

        return ImmutableList.of(generatedGradleFile, generatedApplication, generatedConfiguration);
    }
}
