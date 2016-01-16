package org.crudwizard;

import com.beust.jcommander.JCommander;
import org.crudwizard.cli.AddCommand;
import org.crudwizard.cli.InitCommand;
import org.crudwizard.generators.AddRepresentationGenerator;
import org.crudwizard.generators.ProjectInitializationGenerator;
import org.crudwizard.writer.FileManager;

import java.io.IOException;
import java.util.List;

public class CrudWizardApp {

    private final JCommander jCommander;

    public CrudWizardApp() {
        this.jCommander = buildJCommander();
    }

    private void run(String[] args) throws IOException {
        jCommander.parse(args);
        String parsedCommand = jCommander.getParsedCommand();
        List<Object> commands = jCommander.getCommands().get(parsedCommand).getObjects();
        if (commands.isEmpty()) {
            throw new IllegalArgumentException("No recognized command");
        }
        for (Object command : commands) {
            if (command instanceof AddCommand) {
                AddCommand addCommand = (AddCommand) command;
                AddRepresentationGenerator generator =
                        new AddRepresentationGenerator(addCommand.getModelName(), addCommand.getPackageName(), addCommand.getFields());
                new FileManager(generator, addCommand.isDryRun()).write();
            } else if (command instanceof InitCommand) {
                InitCommand initCommand = (InitCommand) command;
                ProjectInitializationGenerator generator =
                        new ProjectInitializationGenerator(initCommand.getAppName(), initCommand.getPackageName(), initCommand.getDropwizardLibraries());
                new FileManager(generator, initCommand.isDryRun()).write();
            }
        }
    }

    private JCommander buildJCommander() {
        JCommander jCommander = new JCommander();
        jCommander.addCommand(new InitCommand());
        jCommander.addCommand(new AddCommand());
        return jCommander;
    }


    public static void main(String[] args) throws IOException {
        CrudWizardApp crudWizardApp = new CrudWizardApp();
        crudWizardApp.run(args);
    }
}
