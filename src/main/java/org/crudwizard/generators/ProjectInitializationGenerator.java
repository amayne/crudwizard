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

    public ProjectInitializationGenerator(String appName, String packageName) {
        this.appName = appName;
        this.packageName = packageName;
    }

    @Override
    public List<GeneratedFile> generate() {
        GeneratedApplication generatedApplication = new GeneratedApplication(appName, packageName);

        GeneratedConfiguration generatedConfiguration = new GeneratedConfiguration(appName, packageName);

        GeneratedGradleFile generatedGradleFile = new GeneratedGradleFile("");

        return ImmutableList.of(generatedGradleFile, generatedApplication, generatedConfiguration);
    }
}
