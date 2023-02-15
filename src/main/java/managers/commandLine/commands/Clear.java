package managers.commandLine.commands;

import managers.CollectionManager;
import managers.commandLine.Console;
import managers.commandLine.ExitCode;
import managers.commandLine.ScannerManager;

import java.util.Scanner;

public class Clear extends Command{
    private CollectionManager collectionManager;
    private Console console;

    private final Scanner scanner = ScannerManager.getUserScanner();

    public Clear(Console console, CollectionManager collectionManager) {
        super("update", "id {element} : обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public ExitCode execute(String[] args) {
        collectionManager.clear();
        return ExitCode.OK;
    }
}
