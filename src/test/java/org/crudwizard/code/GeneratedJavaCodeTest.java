package org.crudwizard.code;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class GeneratedJavaCodeTest {

    @Test
    public void testGetFile() {
        GeneratedJavaCode subject = new GeneratedJavaCode("src/main/java", "com.example", "HelloWorld", "");
        File returnValue = subject.getFile();
        assertThat(returnValue.getAbsolutePath(), containsString("src/main/java/com/example/HelloWorld.java"));
    }
}