package managers.commandLine.commands;

import managers.CollectionManager;
import managers.commandLine.Console;
import managers.commandLine.Executable;
import managers.commandLine.ExitCode;
import models.StudyGroup;

import java.util.Collection;
import java.util.Objects;

public class Show extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public ExitCode execute(String[] args) {
        Collection<StudyGroup> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            console.printError("Коллекцмя еще не инициализирована");
            return ExitCode.OK;
        }
        console.println(collection.toString());
        return ExitCode.OK;
    }
}
