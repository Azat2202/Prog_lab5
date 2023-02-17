package commandLine.commands;

import commandLine.Console;
import commandLine.ConsoleColors;
import exceptions.IllegalArguments;
import managers.FileManager;

public class Save extends Command{
    private FileManager fileManager;
    private Console console;

    public Save(Console console, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        fileManager.saveObjects();
        console.println(ConsoleColors.toColor("Объекты сохранены успешно", ConsoleColors.GREEN));
    }
}
