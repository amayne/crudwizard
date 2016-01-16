package org.crudwizard.code.dropwizard;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GeneratedConfigurationTest {

    @Test
    public void testGeneratedCode() {
        GeneratedConfiguration subject = new GeneratedConfiguration("HelloWorld", "com.example");
        String returnValue = subject.getContent();
        assertThat(returnValue, equalTo("package com.example;\n"
                + "\n"
                + "import io.dropwizard.Configuration;\n"
                + "\n"
                + "public class HelloWorldConfiguration extends Configuration {\n"
                + "}\n"
                + ""));
    }
}