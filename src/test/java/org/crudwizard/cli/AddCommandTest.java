package org.crudwizard.cli;

import com.beust.jcommander.JCommander;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AddCommandTest extends JCommanderTest {

    @Test
    public void testParsesCommand() {
        String[] args = {"add", "Tweet"};

        AddCommand subject = new AddCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("add"));
        assertThat(subject.getModel(), equalTo("Tweet"));
    }

    @Test
    public void testReadOnly() {
        String[] args = {"add", "Tweet", "-r"};

        AddCommand subject = new AddCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("add"));
        assertTrue(subject.isReadOnly());
    }

    @Test
    public void testPackage() {
        String[] args = {"add", "Tweet", "-p", "com.example"};

        AddCommand subject = new AddCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("add"));
        assertThat(subject.getPackageName(), equalTo("com.example"));
    }
}