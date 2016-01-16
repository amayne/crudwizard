package org.crudwizard.code.dropwizard;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GeneratedApplicationTest {

    @Test
    public void testGeneratedCode() {
        GeneratedApplication subject = new GeneratedApplication("HelloWorld", "com.example");
        String returnValue = subject.getContent();

        assertThat(returnValue, equalTo("package com.example;\n"
                + "\n"
                + "import io.dropwizard.Application;\n"
                + "import io.dropwizard.Bootstrap;\n"
                + "import io.dropwizard.Environment;\n"
                + "import java.lang.Exception;\n"
                + "import java.lang.Override;\n"
                + "import java.lang.String;\n"
                + "\n"
                + "public class HelloWorldApplication extends Application<HelloWorldConfiguration> {\n"
                + "  public static void main(String[] args) throws Exception {\n"
                + "    new HelloWorldApplication().run(args);\n"
                + "  }\n"
                + "\n"
                + "  @Override\n"
                + "  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {\n"
                + "  }\n"
                + "\n"
                + "  @Override\n"
                + "  public void run(HelloWorldConfiguration configuration, Environment environment) {\n"
                + "  }\n"
                + "}\n"
                + ""));
    }
}