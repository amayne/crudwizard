package org.crudwizard.generators;

import com.google.common.collect.ImmutableList;
import org.crudwizard.code.Field;
import org.crudwizard.code.GeneratedFile;
import org.crudwizard.code.dropwizard.GeneratedRepresentation;
import org.crudwizard.code.dropwizard.GeneratedResource;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class AddRepresentationGeneratorTest {

    @Test
    public void testGenerateEmptyFields() {
        AddRepresentationGenerator subject = new AddRepresentationGenerator("Saying", "com.example", Collections.<String>emptyList());
        List<GeneratedFile> returnValue = subject.generate();

        assertThat(returnValue.size(), equalTo(2));
        assertThat(returnValue.get(0), instanceOf(GeneratedRepresentation.class));
        assertThat(returnValue.get(1), instanceOf(GeneratedResource.class));
    }

    @Test
    public void testGenerateWithFields() {
        List<String> fields = ImmutableList.of("message:string", "id:long");

        AddRepresentationGenerator subject = new AddRepresentationGenerator("Saying", "com.example", fields);
        List<GeneratedFile> returnValue = subject.generate();

        GeneratedRepresentation actual = (GeneratedRepresentation) returnValue.get(0);
        Field messageField = actual.getFields().get(0);
        assertThat(messageField.getVarName(), equalTo("message"));
        assertEquals(messageField.getVarType(), String.class);

        Field idField = actual.getFields().get(1);
        assertThat(idField.getVarName(), equalTo("id"));
        assertEquals(idField.getVarType(), Long.class);
    }
}