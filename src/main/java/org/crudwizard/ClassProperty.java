package org.crudwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import org.apache.commons.lang3.text.WordUtils;

import javax.lang.model.element.Modifier;

public class ClassProperty<T> {
    private final String varName;
    private final Class<T> varType;

    public ClassProperty(String varName, Class<T> varType) {
        this.varName = varName;
        this.varType = varType;
    }

    public String getVarName() {
        return varName;
    }

    public Class<T> getVarType() {
        return varType;
    }

    public FieldSpec buildFieldSpec() {
        return FieldSpec.builder(varType, varName, Modifier.PRIVATE).build();
    }

    public MethodSpec buildGetter() {
        return MethodSpec.methodBuilder("get"+ WordUtils.capitalize(varName))
                .addAnnotation(JsonProperty.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(varType)
                .addStatement("return $N", varName)
                .build();
    }

}
