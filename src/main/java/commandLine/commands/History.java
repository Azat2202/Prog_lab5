package commandLine.commands;

import commandLine.Console;
import commandLine.ConsoleColors;
import exceptions.IllegalArguments;
import exceptions.InvalidForm;
import managers.CollectionManager;
import managers.CommandManager;
import models.forms.StudyGroupForm;

import java.util.List;

public class History extends Command{
    private CommandManager commandManager;
    private Console console;

    public History(Console console, CommandManager commandManager) {
        super("history", " вывести последние 5 команд (без их аргументов)");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        List<String> history= commandManager.getCommandHistory();
        for (String command:history.subList(Math.max(history.size() - 5, 0), history.size())){
            console.println(ConsoleColors.toColor(command, ConsoleColors.CYAN));
        }
    }
}
