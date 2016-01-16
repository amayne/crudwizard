package org.crudwizard;

import com.codahale.metrics.annotation.Timed;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import javax.ws.rs.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        /////////////////////////////////////////////////////////
        String appName = "HelloWorld";
        String representationName = "Tweet";
        String paramName = "args";
        String packageName = "com.example";

        List<ClassProperty> propertyList = new ArrayList<ClassProperty>();
        propertyList.add(new ClassProperty("id", Integer.class));
        propertyList.add(new ClassProperty("content", String.class));
        propertyList.add(new ClassProperty("yetAnotherProperty", Long.class));
        propertyList.add(new ClassProperty("again", String[].class));
        /////////////////////////////////////////////////////////

        TypeSpec cnfClass = buildConfiguration(appName);
        TypeSpec appClass = buildApplication(appName, paramName, packageName);
        TypeSpec repClass = buildRepresentation(representationName, propertyList);

        TypeSpec resClass = buildResource(representationName, packageName);
        JavaFile javaFile = JavaFile.builder(packageName, resClass)
                .build();

        javaFile.writeTo(System.out);
    }

    private static TypeSpec buildConfiguration(String appName) {

        return TypeSpec.classBuilder(appName + "Configuration")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ClassName.get("io.dropwizard", "Configuration"))
                .build();
    }

    private static TypeSpec buildApplication(String appName, String paramName, String packageName) {

        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addException(Exception.class)
                .addParameter(String[].class, paramName)
                .addStatement("new $NApplication().run($L)", appName, paramName)
                .build();

        MethodSpec run = MethodSpec.methodBuilder("run")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(ClassName.get(packageName, appName + "Configuration"), "configuration")
                .addParameter(ClassName.get("io.dropwizard", "Environment"), "environment")
                .build();

        MethodSpec initialize = MethodSpec.methodBuilder("initialize")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Bootstrap"),
                        ClassName.get(packageName, appName + "Configuration")), "bootstrap")
                .build();

        return TypeSpec.classBuilder(appName + "Application")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get("io.dropwizard", "Application"),
                        ClassName.get(packageName, appName + "Configuration")))
                .addMethod(main)
                .addMethod(initialize)
                .addMethod(run)
                .build();
    }

    private static TypeSpec buildRepresentation(String representationName, List<ClassProperty> fieldsList) {

        MethodSpec defaultConstructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build();

        TypeSpec.Builder builder = TypeSpec.classBuilder(representationName)
                .addModifiers(Modifier.PUBLIC)
                .addField(Integer.class, "id", Modifier.PRIVATE)
                .addMethod(defaultConstructor);

        for (ClassProperty classProperty: fieldsList) {
            builder.addField(classProperty.buildFieldSpec());
            builder.addMethod(classProperty.buildGetter());
        }

        return builder.build();
    }

    private static AnnotationSpec buildPathAnnotation(String endPoint) {
        return AnnotationSpec.builder(Path.class)
                .addMember("value", "$S", String.format("/%s", endPoint.toLowerCase()))
                .build();
    }

    private static TypeSpec buildResource(String representationName, String packageName) {

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

        return TypeSpec.classBuilder(representationName + "Resource")
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
}
