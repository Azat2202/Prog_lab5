package managers.commandLine.commands;

import managers.CollectionManager;
import managers.commandLine.Console;
import managers.commandLine.ConsoleColors;
import managers.commandLine.ExitCode;
import managers.commandLine.ScannerManager;
import models.StudyGroup;
import models.forms.InvalidForm;
import models.forms.StudyGroupForm;

import java.util.Scanner;

public class Update extends Command{
    private CollectionManager collectionManager;
    private Console console;

    private final Scanner scanner = ScannerManager.getUserScanner();

    public Update(Console console, CollectionManager collectionManager) {
        super("update", "id {element} : обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    @Override
    public ExitCode execute(String[] args) {
        int id;
        class NoSuchId extends RuntimeException{}
        while (true) {
            console.println(ConsoleColors.toColor("Введите id объекта", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                id = Integer.parseInt(input);
                if (!collectionManager.checkExist(id)) throw new NoSuchId();
                break;
            } catch (NoSuchId err) {
                console.printError("В коллекции нет элемента с таким id");
            } catch (NumberFormatException exception) {
                console.printError("Y должно быть числом типа int");
            } catch (Throwable throwable){
                console.printError("Непредвиденная ошибка!");
            }
        }
        while (true) {
            try {
                console.println(ConsoleColors.toColor("Создание нового объекта StudyGroup", ConsoleColors.PURPLE));
                StudyGroup newStudyGroup = new StudyGroupForm(console, collectionManager).build();
                collectionManager.editById(id, newStudyGroup, collectionManager.getCollection());
                console.println(ConsoleColors.toColor("Создание нового объекта StudyGroup окончено успешно!", ConsoleColors.PURPLE));
                return ExitCode.OK;
            } catch (InvalidForm invalidForm) {
                console.printError("Поля объекта не валидны! Объект не создан!");
            } catch (Throwable throwable){
                console.printError("Непредвиденная ошибка");
            }
        }
    }
}
