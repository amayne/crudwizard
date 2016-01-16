package org.crudwizard.code.project;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GeneratedGradleFileTest {

    @Test
    public void test() throws Exception {
        GeneratedGradleFile subject = new GeneratedGradleFile("", ImmutableList.of("assets", "views"));
        assertThat(subject.getContent(), equalTo("// Apply the java plugin to add support for Java\n"
                + "apply plugin: 'java'\n"
                + "\n"
                + "// In this section you declare where to find the dependencies of your project\n"
                + "repositories {\n"
                + "    // Use 'jcenter' for resolving your dependencies.\n"
                + "    // You can declare any Maven/Ivy/file repository here.\n"
                + "    jcenter()\n"
                + "}\n"
                + "\n"
                + "// In this section you declare the dependencies for your production and test code\n"
                + "dependencies {\n"
                + "    // The production code uses the SLF4J logging API at compile time\n"
                + "    compile 'org.slf4j:slf4j-api:1.7.13'\n"
                + "    compile 'io.dropwizard:dropwizard-core:0.9.1'\n"
                + "    compile 'io.dropwizard:dropwizard-assets:0.9.1'\n"
                + "    compile 'io.dropwizard:dropwizard-views:0.9.1'\n"
                + "    // Declare the dependency for your favourite test framework you want to use in your tests.\n"
                + "    // TestNG is also supported by the Gradle Test task. Just change the\n"
                + "    // testCompile dependency to testCompile 'org.testng:testng:6.8.1' and add\n"
                + "    // 'test.useTestNG()' to your build script.\n"
                + "    testCompile 'junit:junit:4.12'\n"
                + "    testCompile 'io.dropwizard:dropwizard-testing:0.9.1'\n"
                + "}"));
    }
}