package org.crudwizard.code.dropwizard;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.crudwizard.code.GeneratedJavaCode;

import javax.lang.model.element.Modifier;

import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

public class GeneratedResource extends GeneratedJavaCode {

    private static final String REST_PACKAGE_NAME = "javax.ws.rs";
    private static final String METRICS_PACKAGE_NAME = "com.codahale.metrics.annotation";

    private final String representationName;
    private final String packageName;

    public GeneratedResource(String representationName, String packageName) {
        super("src/main/java", packageName, getClassName(representationName));
        this.representationName = representationName;
        this.packageName = packageName;
    }

    @Override
    protected TypeSpec build() {

        ClassName className = ClassName.get(packageName, representationName);

        TypeName listOfReps = ParameterizedTypeName.get(
                ClassName.get("java.util", "List"),
                className
        );

        AnnotationSpec rootPathAnnotation = buildPathAnnotation(representationName);

        ClassName mediaType = ClassName.get("javax.ws.rs.core", "MediaType");
        AnnotationSpec produceAnnotation = AnnotationSpec.builder(ClassName.get(REST_PACKAGE_NAME, "Produces"))
                .addMember("value", "$T", mediaType.nestedClass("APPLICATION_JSON"))
                .build();

        MethodSpec index = MethodSpec.methodBuilder("index")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "GET"))
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .returns(listOfReps)
                .addStatement("//return Tweet.findAll()")
                .addStatement("return null")
                .build();

        AnnotationSpec newPathAnnotation = buildPathAnnotation("new");
        MethodSpec newObject = MethodSpec.methodBuilder("newObject")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "GET"))
                .addAnnotation(newPathAnnotation)
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("//return HTML form for creating a new tweet")
                .addStatement("return null")
                .build();

        MethodSpec create = MethodSpec.methodBuilder("create")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "POST"))
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addStatement("//save the tweet in the database")
                .build();

        ParameterSpec idParameter = ParameterSpec.builder(Integer.class, "id")
                .addAnnotation(AnnotationSpec.builder(ClassName.get(REST_PACKAGE_NAME, "PathParam")).addMember("value", "$S", "id").build())
                .build();

        AnnotationSpec showPathAnnotation = buildPathAnnotation("{id}");
        MethodSpec show = MethodSpec.methodBuilder("show")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "GET"))
                .addAnnotation(showPathAnnotation)
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(className)
                .addStatement("//return Tweet.findById(id)")
                .addStatement("return null")
                .build();

        AnnotationSpec editPathAnnotation = buildPathAnnotation("{id}/edit");
        MethodSpec edit = MethodSpec.methodBuilder("edit")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "GET"))
                .addAnnotation(editPathAnnotation)
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(String.class)
                .addStatement("//return HTML form for editing the message")
                .addStatement("return null")
                .build();

        AnnotationSpec putPathAnnotation = buildPathAnnotation("{id}");
        MethodSpec put = MethodSpec.methodBuilder("put")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "PUT"))
                .addAnnotation(putPathAnnotation)
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(className)
                .addStatement("//update in database and return the tweet")
                .addStatement("return null")
                .build();

        AnnotationSpec deletePathAnnotation = buildPathAnnotation("{id}");
        MethodSpec delete = MethodSpec.methodBuilder("destroy")
                .addAnnotation(ClassName.get(REST_PACKAGE_NAME, "DELETE"))
                .addAnnotation(deletePathAnnotation)
                .addAnnotation(ClassName.get(METRICS_PACKAGE_NAME, "Timed"))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(void.class)
                .addStatement("//delete the tweet in database")
                .addStatement("return null")
                .build();

        return TypeSpec.classBuilder(getClassName(representationName))
                .addAnnotation(rootPathAnnotation)
                .addAnnotation(produceAnnotation)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(index)
                .addMethod(newObject)
                .addMethod(create)
                .addMethod(show)
                .addMethod(edit)
                .addMethod(put)
                .addMethod(delete)
                .build();
    }

    private static String getClassName(String representationName) {
        return representationName + "Resource";
    }


    private static AnnotationSpec buildPathAnnotation(String endPoint) {
        return AnnotationSpec.builder(ClassName.get(REST_PACKAGE_NAME, "Path"))
                .addMember("value", "$S", String.format("/%s", UPPER_CAMEL.to(LOWER_HYPHEN, endPoint)))
                .build();
    }
}
