package commandLine.commands;

import commandLine.Console;
import commandLine.ConsoleColors;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import models.StudyGroup;
import models.forms.StudyGroupForm;

import java.util.Collection;
import java.util.Objects;

public class RemoveGreater extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super("remove_greater", " {element} : удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        class NoElements extends RuntimeException{

        }
        try {
            console.println(ConsoleColors.toColor("Создание объекта StudyGroup", ConsoleColors.PURPLE));
            StudyGroup newElement = new StudyGroupForm(console).build();
            console.println(ConsoleColors.toColor("Создание объекта StudyGroup окончено успешно!", ConsoleColors.PURPLE));
            Collection<StudyGroup> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(studyGroup -> studyGroup.compareTo(newElement) >= 1)
                    .toList();
            collectionManager.removeElements(toRemove);
            console.println(ConsoleColors.toColor("Удалены элементы большие чем заданный", ConsoleColors.GREEN));
        } catch (NoElements e){
            console.printError("В коллекции нет элементов");
        }
    }
}
