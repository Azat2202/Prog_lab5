import commandLine.ConsoleColors;
import exceptions.ExitObliged;
import managers.*;
import commandLine.Console;
import commandLine.commands.*;

import java.util.List;

/*
TODO
    Изменить printError чтобы он брал текст из ошибки
    sorting
    add_if_max
    remove_greater
    history
    remove_all_by_average_mark
    count_by_average_mark
    count_less_than_expelled_students
FIXME
    remove_by_id takes args
 */

public class Main {
    public static void main(String[] args){
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(console, collectionManager);
        CommandManager commandManager = new CommandManager();
        try{
            fileManager.findFile();
            fileManager.createObjects();
        } catch (ExitObliged e){
            console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
            return;
        }

        commandManager.addCommand(List.of(
                new Help(console, commandManager),
                new Info(console, collectionManager),
                new Show(console, collectionManager),
                new AddElement(console, collectionManager),
                new Update(console, collectionManager),
                new RemoveById(console, collectionManager),
                new Clear(console, collectionManager),
                new Save(console, fileManager),
                new Execute(console, fileManager, commandManager),
                new Exit()
        ));
        new RuntimeManager(console, commandManager).interactiveMode();
    }
}
