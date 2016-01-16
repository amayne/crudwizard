package org.crudwizard.generators;

import org.crudwizard.code.GeneratedFile;
import org.crudwizard.code.dropwizard.GeneratedApplication;
import org.crudwizard.code.dropwizard.GeneratedConfiguration;
import org.crudwizard.code.project.GeneratedGradleFile;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ProjectInitializationGeneratorTest {

    @Test
    public void testGeneratesFiles() {
        ProjectInitializationGenerator subject = new ProjectInitializationGenerator("HelloWorld", "com.example");
        List<GeneratedFile> returnValue = subject.generate();

        assertThat(returnValue.size(), equalTo(3));
        assertThat(returnValue.get(0), instanceOf(GeneratedGradleFile.class));
        assertThat(returnValue.get(1), instanceOf(GeneratedApplication.class));
        assertThat(returnValue.get(2), instanceOf(GeneratedConfiguration.class));
    }
}