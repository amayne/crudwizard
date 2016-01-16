package org.crudwizard.code;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import org.apache.commons.lang3.text.WordUtils;

import javax.lang.model.element.Modifier;

public class Field {

    private static final String JACKSON_ANNOTATION_PACKAGE = "com.fasterxml.jackson.annotation";

    private final String varName;
    private final Class<?> varType;

    public Field(String varName, Class<?> varType) {
        this.varName = varName;
        this.varType = varType;
    }

    public String getVarName() {
        return varName;
    }

    public Class<?> getVarType() {
        return varType;
    }

    public FieldSpec buildFieldSpec() {
        return FieldSpec.builder(varType, varName, Modifier.PRIVATE).build();
    }

    public MethodSpec buildGetter() {
        return MethodSpec.methodBuilder("get" + WordUtils.capitalize(varName))
                .addAnnotation(ClassName.get(JACKSON_ANNOTATION_PACKAGE, "JsonProperty"))
                .addModifiers(Modifier.PUBLIC)
                .returns(varType)
                .addStatement("return $N", varName)
                .build();
    }

}
