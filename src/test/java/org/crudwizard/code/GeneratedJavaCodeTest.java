package org.crudwizard.code;

import com.squareup.javapoet.TypeSpec;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class GeneratedJavaCodeTest {

    @Test
    public void testGetFile() {
        GeneratedJavaCode subject = new TestOnlyGeneratedJavaCode("src/main/java", "com.example", "HelloWorld");
        File returnValue = subject.getFile();
        assertThat(returnValue.getAbsolutePath(), containsString("src/main/java/com/example/HelloWorld.java"));
    }

    private class TestOnlyGeneratedJavaCode extends GeneratedJavaCode {

        public TestOnlyGeneratedJavaCode(String basePath, String packageName, String className) {
            super(basePath, packageName, className);
        }

        @Override
        protected TypeSpec build() {
            return null;
        }
    }
}