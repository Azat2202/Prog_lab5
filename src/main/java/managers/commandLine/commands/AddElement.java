package managers.commandLine.commands;

import managers.CollectionManager;
import managers.commandLine.Console;
import managers.commandLine.ConsoleColors;
import managers.commandLine.ExitCode;
import models.forms.InvalidForm;
import models.forms.StudyGroupForm;

public class AddElement extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public AddElement(Console console, CollectionManager collectionManager) {
        super("add", "{element} добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public ExitCode execute(String[] args) {
        while (true) {
            try {
                console.println(ConsoleColors.toColor("Создание объекта StudyGroup", ConsoleColors.PURPLE));
                collectionManager.addElement(new StudyGroupForm(console, collectionManager).build());
                console.println(ConsoleColors.toColor("Создание объекта StudyGroup окончено успешно!", ConsoleColors.PURPLE));
                return ExitCode.OK;
            } catch (InvalidForm invalidForm) {
                console.printError("Поля продукта не валидны! Продукт не создан!");
            } catch (Throwable throwable){
                console.printError("Непредвиденная ошибка");
            }
        }
    }
}
