package commandLine.commands;

import exceptions.IllegalArguments;
import managers.CollectionManager;
import commandLine.Console;
import commandLine.ConsoleColors;
import commandLine.ScannerManager;
import models.StudyGroup;
import exceptions.InvalidForm;
import models.forms.StudyGroupForm;

import java.util.Scanner;

public class Update extends Command{
    private CollectionManager collectionManager;
    private Console console;

    private final Scanner scanner = ScannerManager.getUserScanner();

    public Update(Console console, CollectionManager collectionManager) {
        super("update", " id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments{
        if (args.isBlank()) throw new IllegalArguments();
        class NoSuchId extends RuntimeException{

        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(id)) throw new NoSuchId();
            console.println(ConsoleColors.toColor("Создание нового объекта StudyGroup", ConsoleColors.PURPLE));
            StudyGroup newStudyGroup = new StudyGroupForm(console).build();
            collectionManager.editById(id, newStudyGroup, collectionManager.getCollection());
            console.println(ConsoleColors.toColor("Создание нового объекта StudyGroup окончено успешно!", ConsoleColors.PURPLE));
        } catch (NoSuchId err) {
            console.printError("В коллекции нет элемента с таким id");
        } catch (InvalidForm invalidForm) {
            console.printError("Поля объекта не валидны! Объект не создан!");
        } catch (NumberFormatException exception) {
            console.printError("id должно быть числом типа int");
        }
    }
}
