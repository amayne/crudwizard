package org.crudwizard;

import com.beust.jcommander.JCommander;
import com.google.common.collect.ImmutableMap;
import org.crudwizard.cli.InitCommand;

public class CrudWizardApp {

    private static final ImmutableMap<String, Object> COMMAND_MAP = ImmutableMap.of("init", new InitCommand());

    private final JCommander jCommander;

    public CrudWizardApp() {
        this.jCommander = buildJCommander();
    }

    private void run(String[] args) {
        jCommander.parse(args);
        String command = jCommander.getParsedCommand();
        switch(command) {
            case "init":

                break;
            default:
                System.out.println("Unknown command");
        }
    }

    private JCommander buildJCommander() {
        JCommander jCommander = new JCommander();
        for (String command : COMMAND_MAP.keySet()) {
            jCommander.addCommand(command, COMMAND_MAP.get(command));
        }
        return jCommander;
    }


    public static void main(String[] args) {
        CrudWizardApp crudWizardApp = new CrudWizardApp();
        crudWizardApp.run(args);
    }
}
