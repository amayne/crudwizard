package org.crudwizard.code.dropwizard;

import com.codahale.metrics.annotation.Timed;
import com.squareup.javapoet.*;
import org.crudwizard.code.GeneratedJavaCode;

import javax.lang.model.element.Modifier;
import javax.ws.rs.*;

public class GeneratedResource extends GeneratedJavaCode {
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
        AnnotationSpec produceAnnotation = AnnotationSpec.builder(Produces.class)
                .addMember("value", "$T", mediaType.nestedClass("APPLICATION_JSON"))
                .build();

        MethodSpec index = MethodSpec.methodBuilder("index")
                .addAnnotation(GET.class)
                .addAnnotation(Timed.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(listOfReps)
                .addStatement("//return Tweet.findAll()")
                .addStatement("return null")
                .build();

        AnnotationSpec newPathAnnotation = buildPathAnnotation("new");
        MethodSpec newObject = MethodSpec.methodBuilder("newObject")
                .addAnnotation(GET.class)
                .addAnnotation(newPathAnnotation)
                .addAnnotation(Timed.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("//return HTML form for creating a new tweet")
                .addStatement("return null")
                .build();

        MethodSpec create = MethodSpec.methodBuilder("create")
                .addAnnotation(POST.class)
                .addAnnotation(Timed.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addStatement("//save the tweet in the database")
                .build();

        ParameterSpec idParameter = ParameterSpec.builder(Integer.class, "id")
                .addAnnotation(AnnotationSpec.builder(PathParam.class).addMember("value", "$S", "id").build())
                .build();

        AnnotationSpec showPathAnnotation = buildPathAnnotation("{id}");
        MethodSpec show = MethodSpec.methodBuilder("show")
                .addAnnotation(GET.class)
                .addAnnotation(showPathAnnotation)
                .addAnnotation(Timed.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(className)
                .addStatement("//return Tweet.findById(id)")
                .addStatement("return null")
                .build();

        AnnotationSpec editPathAnnotation = buildPathAnnotation("{id}/edit");
        MethodSpec edit = MethodSpec.methodBuilder("edit")
                .addAnnotation(GET.class)
                .addAnnotation(editPathAnnotation)
                .addAnnotation(Timed.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(String.class)
                .addStatement("//return HTML form for editing the message")
                .addStatement("return null")
                .build();

        AnnotationSpec putPathAnnotation = buildPathAnnotation("{id}");
        MethodSpec put = MethodSpec.methodBuilder("put")
                .addAnnotation(PUT.class)
                .addAnnotation(putPathAnnotation)
                .addAnnotation(Timed.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(idParameter)
                .returns(className)
                .addStatement("//update in database and return the tweet")
                .addStatement("return null")
                .build();

        AnnotationSpec deletePathAnnotation = buildPathAnnotation("{id}");
        MethodSpec delete = MethodSpec.methodBuilder("destroy")
                .addAnnotation(DELETE.class)
                .addAnnotation(deletePathAnnotation)
                .addAnnotation(Timed.class)
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
        return AnnotationSpec.builder(Path.class)
                .addMember("value", "$S", String.format("/%s", endPoint.toLowerCase()))
                .build();
    }
}
