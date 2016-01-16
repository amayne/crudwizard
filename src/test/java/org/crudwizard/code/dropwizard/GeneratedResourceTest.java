package org.crudwizard.code.dropwizard;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GeneratedResourceTest {

    @Test
    public void testGeneratedCode() {
        GeneratedResource subject = new GeneratedResource("HelloWorld", "com.example");
        assertThat(subject.getContent(), equalTo("package com.example;\n"
                + "\n"
                + "import com.codahale.metrics.annotation.Timed;\n"
                + "import java.lang.Integer;\n"
                + "import java.lang.String;\n"
                + "import java.util.List;\n"
                + "import javax.ws.rs.DELETE;\n"
                + "import javax.ws.rs.GET;\n"
                + "import javax.ws.rs.POST;\n"
                + "import javax.ws.rs.PUT;\n"
                + "import javax.ws.rs.Path;\n"
                + "import javax.ws.rs.PathParam;\n"
                + "import javax.ws.rs.Produces;\n"
                + "import javax.ws.rs.core.MediaType;\n"
                + "\n"
                + "@Path(\"/helloworld\")\n"
                + "@Produces(MediaType.APPLICATION_JSON)\n"
                + "public class HelloWorldResource {\n"
                + "  @GET\n"
                + "  @Timed\n"
                + "  public List<HelloWorld> index() {\n"
                + "    //return Tweet.findAll();\n"
                + "    return null;\n"
                + "  }\n"
                + "\n"
                + "  @GET\n"
                + "  @Path(\"/new\")\n"
                + "  @Timed\n"
                + "  public String newObject() {\n"
                + "    //return HTML form for creating a new tweet;\n"
                + "    return null;\n"
                + "  }\n"
                + "\n"
                + "  @POST\n"
                + "  @Timed\n"
                + "  public void create() {\n"
                + "    //save the tweet in the database;\n"
                + "  }\n"
                + "\n"
                + "  @GET\n"
                + "  @Path(\"/{id}\")\n"
                + "  @Timed\n"
                + "  public HelloWorld show(@PathParam(\"id\") Integer id) {\n"
                + "    //return Tweet.findById(id);\n"
                + "    return null;\n"
                + "  }\n"
                + "\n"
                + "  @GET\n"
                + "  @Path(\"/{id}/edit\")\n"
                + "  @Timed\n"
                + "  public String edit(@PathParam(\"id\") Integer id) {\n"
                + "    //return HTML form for editing the message;\n"
                + "    return null;\n"
                + "  }\n"
                + "\n"
                + "  @PUT\n"
                + "  @Path(\"/{id}\")\n"
                + "  @Timed\n"
                + "  public HelloWorld put(@PathParam(\"id\") Integer id) {\n"
                + "    //update in database and return the tweet;\n"
                + "    return null;\n"
                + "  }\n"
                + "\n"
                + "  @DELETE\n"
                + "  @Path(\"/{id}\")\n"
                + "  @Timed\n"
                + "  public void destroy(@PathParam(\"id\") Integer id) {\n"
                + "    //delete the tweet in database;\n"
                + "    return null;\n"
                + "  }\n"
                + "}\n"
                + ""));
    }
}