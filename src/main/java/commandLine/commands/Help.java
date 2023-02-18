package commandLine.commands;

import commandLine.*;
import exceptions.IllegalArguments;
import managers.CommandManager;

public class Help extends Command{
    private CommandManager commandManager;
    private Console console;
    public Help(Console console, CommandManager commandManager) {
        super("help", ": вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        commandManager.getCommands()
                .forEach(command -> console.println(ConsoleColors.toColor(command.getName(), ConsoleColors.CYAN) + command.getDescription()));
    }
}
