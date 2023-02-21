import commandLine.ConsoleColors;
import exceptions.ExitObliged;
import managers.*;
import commandLine.Console;
import commandLine.commands.*;

import java.util.List;

/*
ADDED = in this commit
TODO
    **ADDED** count_by_average_mark
    **ADDED** count_less_than_expelled_students
    **ADDED** javadoc
    **WILL NOT ADD** Изменить printError чтобы он брал текст из ошибки
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
                new Exit(),
                new AddIfMax(console, collectionManager),
                new RemoveGreater(console, collectionManager),
                new History(console, commandManager),
                new RemoveAllByAverageMark(console, collectionManager),
                new CountByAverageMark(console, collectionManager),
                new CountLessThanExpelledStudents(console, collectionManager)
        ));
        new RuntimeManager(console, commandManager).interactiveMode();
    }
}
