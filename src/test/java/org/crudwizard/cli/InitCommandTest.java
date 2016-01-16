package org.crudwizard.cli;

import com.beust.jcommander.JCommander;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class InitCommandTest extends JCommanderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void testParsesAppName() {
        String[] args = {"init", "Sample"};

        InitCommand subject = new InitCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("init"));
        assertThat(subject.getAppName(), equalTo("Sample"));
    }

    @Test
    public void testReadsPackage() {
        String[] args = {"init", "Sample", "-p", "com.example"};

        InitCommand subject = new InitCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("init"));
        assertThat(subject.getPackageName(), equalTo("com.example"));
    }

    @Test
    public void testEnablesIdea() {
        String[] args = {"init", "Sample", "-idea"};

        InitCommand subject = new InitCommand();
        JCommander jCommander = buildAndParse(args, subject);

        assertThat(jCommander.getParsedCommand(), equalTo("init"));
        assertTrue(subject.isIntellijIdea());
    }

    @Test
    public void testThrowsOnMultipleAppNames() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Please provide one app name");

        String[] args = {"init", "Sample", "Sample2"};

        InitCommand subject = new InitCommand();

        buildAndParse(args, subject);

        subject.getAppName();
    }

}