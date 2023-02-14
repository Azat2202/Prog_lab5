package managers.commandLine.commands;

import managers.commandLine.*;

public class Help extends Command{
    private CommandManager commandManager;
    private Console console;
    public Help(Console console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public ExitCode execute(String[] args) {
        commandManager.getCommands()
                .forEach(command -> console.println(command.getName() + ": " + command.getDescription()));
        return ExitCode.OK;
    }
}
