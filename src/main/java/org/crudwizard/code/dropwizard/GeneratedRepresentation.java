package org.crudwizard.code.dropwizard;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.crudwizard.code.Field;
import org.crudwizard.code.GeneratedJavaCode;

import javax.lang.model.element.Modifier;
import java.util.List;

public class GeneratedRepresentation extends GeneratedJavaCode {

    private final String representationName;
    private final List<Field> fields;

    public GeneratedRepresentation(String representationName, String packageName, List<Field> fields) {
        super("src/main/java", packageName, representationName);
        this.representationName = representationName;
        this.fields = fields;
    }

    @Override
    protected TypeSpec build() {
        MethodSpec defaultConstructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build();

        TypeSpec.Builder builder = TypeSpec.classBuilder(representationName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(defaultConstructor);

        for (Field field : fields) {
            builder.addField(field.buildFieldSpec());
            builder.addMethod(field.buildGetter());
        }

        return builder.build();
    }

    public List<Field> getFields() {
        return fields;
    }
}
