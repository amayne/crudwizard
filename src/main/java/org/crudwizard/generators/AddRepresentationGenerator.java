package org.crudwizard.generators;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.crudwizard.code.Field;
import org.crudwizard.code.GeneratedFile;
import org.crudwizard.code.dropwizard.GeneratedRepresentation;
import org.crudwizard.code.dropwizard.GeneratedResource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AddRepresentationGenerator implements Generator {

    private final String representationName;
    private final String packageName;
    private final List<String> fields;

    public AddRepresentationGenerator(String representationName, String packageName, List<String> fields) {
        this.representationName = representationName;
        this.packageName = packageName;
        this.fields = fields;
    }

    @Override
    public List<GeneratedFile> generate() {

        GeneratedRepresentation generatedRepresentation = new GeneratedRepresentation(representationName, packageName, Lists.transform(fields, new Function<String, Field>() {
            @Override
            public Field apply(String input) {
                String[] split = input.split(":");
                assert split.length == 2;
                return new Field(split[0], resolveType(split[1]));
            }
        }));
        GeneratedResource generatedResource = new GeneratedResource(representationName, packageName);

        return ImmutableList.<GeneratedFile>of(generatedRepresentation, generatedResource);
    }

    private Class<?> resolveType(String typeName) {
        switch (typeName) {
            case "string":
                return String.class;
            case "int":
                return Integer.class;
            case "long":
                return Long.class;
            case "double":
                return Double.class;
            case "float":
                return Float.class;
            case "bigdecimal":
                return BigDecimal.class;
            case "bool":
                return Boolean.class;
            case "timestamp":
                return Date.class;
            case "date":
                return Date.class;
            default:
                throw new IllegalArgumentException("Unrecognized type");
        }
    }
}
