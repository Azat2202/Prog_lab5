package commandLine.commands;

import exceptions.IllegalArguments;
import managers.CollectionManager;
import commandLine.Console;
import commandLine.ConsoleColors;
import commandLine.ScannerManager;

import java.util.Scanner;

public class RemoveById extends Command {
    private CollectionManager collectionManager;
    private Console console;

    private final Scanner scanner = ScannerManager.getUserScanner();

    public RemoveById(Console console, CollectionManager collectionManager) {
        super("remove_by_id", "id : удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments{
        if (args.isBlank()) throw new IllegalArguments();
        class NoSuchId extends RuntimeException {
        }
        console.println(ConsoleColors.toColor("Введите id объекта", ConsoleColors.GREEN));
        String input = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(input.trim());
            if (!collectionManager.checkExist(id)) throw new NoSuchId();
            collectionManager.removeElement(collectionManager.getById(id));
            console.println(ConsoleColors.toColor("Объект удален успешно", ConsoleColors.GREEN));
        } catch (NoSuchId err) {
            console.printError("В коллекции нет элемента с таким id");
        } catch (NumberFormatException exception) {
            console.printError("id должно быть числом типа int");
        }
        ;
    }
}