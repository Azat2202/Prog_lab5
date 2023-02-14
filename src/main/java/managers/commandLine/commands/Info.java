package managers.commandLine.commands;

import managers.CollectionManager;
import managers.commandLine.*;

public class Info extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Info(Console console, CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public ExitCode execute(String[] args) {
        String lastInitTime = (collectionManager.getLastInitTime() == null)
                ? "В сессии коллекция не инициализирована"
                : collectionManager.getLastInitTime().toString();
        String lastSaveTime = (collectionManager.getLastSaveTime() == null)
                ? "В сессии коллекция не инициализирована "
                : collectionManager.getLastSaveTime().toString();
        console.println("Сведения о коллекции: ");
        console.println(ConsoleColors.toColor("Тип: ", ConsoleColors.GREEN) + collectionManager.collectionType());
        console.println(ConsoleColors.toColor("Количество элементов: ", ConsoleColors.GREEN) + collectionManager.collectionSize());
        console.println(ConsoleColors.toColor("Дата последней инициализации: ", ConsoleColors.GREEN) + lastInitTime);
        console.println(ConsoleColors.toColor("Дата последнего изменения: ", ConsoleColors.GREEN) + lastSaveTime);
        return ExitCode.OK;
    }
}
