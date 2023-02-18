package commandLine.commands;

import exceptions.IllegalArguments;
import managers.CollectionManager;
import commandLine.Console;
import models.StudyGroup;

import java.util.Collection;

public class Show extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        Collection<StudyGroup> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            console.printError("Коллекцмя еще не инициализирована");
            return;
        }
        console.println(collection.toString());
    }
}
