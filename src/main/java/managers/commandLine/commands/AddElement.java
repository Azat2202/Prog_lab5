package managers.commandLine.commands;

import managers.CollectionManager;
import managers.commandLine.Console;
import managers.commandLine.ExitCode;

public class AddElement extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public AddElement(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public ExitCode execute(String[] args) {

        return ExitCode.OK;
    }
}
