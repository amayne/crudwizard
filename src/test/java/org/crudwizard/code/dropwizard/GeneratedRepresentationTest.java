package org.crudwizard.code.dropwizard;

import com.google.common.collect.ImmutableList;
import org.crudwizard.code.Field;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GeneratedRepresentationTest {

    @Test
    public void testGeneratedCodeNoFields() {
        GeneratedRepresentation subject = new GeneratedRepresentation("Saying", "com.example", emptyList());
        String returnValue = subject.getContent();
        assertThat(returnValue, equalTo("package com.example;\n"
                + "\n"
                + "import java.lang.Integer;\n"
                + "\n"
                + "public class Saying {\n"
                + "  private Integer id;\n"
                + "\n"
                + "  public Saying() {\n"
                + "  }\n"
                + "}\n"
                + ""));
    }

    @Test
    public void testGeneratedCodeWithFields() {
        List<Field> fields = ImmutableList.of(new Field("id", Integer.class), new Field("message", String.class));
        GeneratedRepresentation subject = new GeneratedRepresentation("Saying", "com.example", fields);
        String returnValue = subject.getContent();
        assertThat(returnValue, equalTo("package com.example;\n"
                + "\n"
                + "import com.fasterxml.jackson.annotation.JsonProperty;\n"
                + "import java.lang.Integer;\n"
                + "import java.lang.String;\n"
                + "\n"
                + "public class Saying {\n"
                + "  private Integer id;\n"
                + "\n"
                + "  private Integer id;\n"
                + "\n"
                + "  private String message;\n"
                + "\n"
                + "  public Saying() {\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public Integer getId() {\n"
                + "    return id;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public String getMessage() {\n"
                + "    return message;\n"
                + "  }\n"
                + "}\n"
                + ""));
    }

    @Test
    public void testGeneratedWithAllFields() {
        List<Field> fields = new ArrayList<Field>();
        fields.add(new Field("id", Long.class));
        fields.add(new Field("number", Integer.class));
        fields.add(new Field("message", String.class));
        fields.add(new Field("price", BigDecimal.class));
        fields.add(new Field("total", Double.class));
        fields.add(new Field("calculation", Float.class));
        fields.add(new Field("timeStamp", Date.class));

        GeneratedRepresentation subject = new GeneratedRepresentation("Example", "com.example", fields);
        assertThat(subject.getContent(), equalTo("package com.example;\n"
                + "\n"
                + "import com.fasterxml.jackson.annotation.JsonProperty;\n"
                + "import java.lang.Double;\n"
                + "import java.lang.Float;\n"
                + "import java.lang.Integer;\n"
                + "import java.lang.Long;\n"
                + "import java.lang.String;\n"
                + "import java.math.BigDecimal;\n"
                + "import java.util.Date;\n"
                + "\n"
                + "public class Example {\n"
                + "  private Integer id;\n"
                + "\n"
                + "  private Long id;\n"
                + "\n"
                + "  private Integer number;\n"
                + "\n"
                + "  private String message;\n"
                + "\n"
                + "  private BigDecimal price;\n"
                + "\n"
                + "  private Double total;\n"
                + "\n"
                + "  private Float calculation;\n"
                + "\n"
                + "  private Date timeStamp;\n"
                + "\n"
                + "  public Example() {\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public Long getId() {\n"
                + "    return id;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public Integer getNumber() {\n"
                + "    return number;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public String getMessage() {\n"
                + "    return message;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public BigDecimal getPrice() {\n"
                + "    return price;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public Double getTotal() {\n"
                + "    return total;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public Float getCalculation() {\n"
                + "    return calculation;\n"
                + "  }\n"
                + "\n"
                + "  @JsonProperty\n"
                + "  public Date getTimeStamp() {\n"
                + "    return timeStamp;\n"
                + "  }\n"
                + "}\n"
                + ""));
    }
}