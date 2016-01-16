package org.crudwizard.code.project;

import org.crudwizard.code.ProjectFile;

import java.io.IOException;
import java.util.List;

public class GeneratedGradleFile extends ProjectFile {

    private final String dropwizardPackage = "io.dropwizard:dropwizard";
    private final String dropwizardVersion = "0.9.1";

    private final List<String> dropwizardLibraries;

    public GeneratedGradleFile(String basePath, List<String> dropwizardLibraries) {
        super(basePath, "build.gradle");
        this.dropwizardLibraries = dropwizardLibraries;
    }

    @Override
    public String getContent() throws IOException {
        return buildFromTemplate("// Apply the java plugin to add support for Java\n" +
                "apply plugin: 'java'\n" +
                "\n" +
                "// In this section you declare where to find the dependencies of your project\n" +
                "repositories {\n" +
                "    // Use 'jcenter' for resolving your dependencies.\n" +
                "    // You can declare any Maven/Ivy/file repository here.\n" +
                "    jcenter()\n" +
                "}\n" +
                "\n" +
                "// In this section you declare the dependencies for your production and test code\n" +
                "dependencies {\n" +
                "    // The production code uses the SLF4J logging API at compile time\n" +
                "    compile 'org.slf4j:slf4j-api:1.7.13'\n" +
                "    compile '{{dropwizardPackage}}-core:{{dropwizardVersion}}'\n" +
                "    {{#dropwizardLibraries}}" +
                "    compile '{{dropwizardPackage}}-{{.}}:{{dropwizardVersion}}'\n" +
                "    {{/dropwizardLibraries}}" +
                "\n" +
                "    // Declare the dependency for your favourite test framework you want to use in your tests.\n" +
                "    // TestNG is also supported by the Gradle Test task. Just change the\n" +
                "    // testCompile dependency to testCompile 'org.testng:testng:6.8.1' and add\n" +
                "    // 'test.useTestNG()' to your build script.\n" +
                "    testCompile 'junit:junit:4.12'\n" +
                "    testCompile '{{dropwizardPackage}}-testing:{{dropwizardVersion}}'\n" +
                "}");
    }

    public List<String> getDropwizardLibraries() {
        return dropwizardLibraries;
    }

    public String getDropwizardPackage() {
        return dropwizardPackage;
    }

    public String getDropwizardVersion() {
        return dropwizardVersion;
    }
}
