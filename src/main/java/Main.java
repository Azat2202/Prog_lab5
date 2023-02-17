import commandLine.ConsoleColors;
import exceptions.ExitObliged;
import managers.*;
import commandLine.Console;
import commandLine.commands.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Console console = new Console();
        FileManager fileWorker = new FileManager(console);
        CollectionManager collectionManager = new CollectionManager(fileWorker);
        CommandManager commandManager = new CommandManager();
        try{
            fileWorker.findfile();
            fileWorker.createObjects();
        } catch (ExitObliged e){
            console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
        }

        commandManager.addCommand(List.of(
                new Help(console, commandManager),
                new Info(console, collectionManager),
                new Show(console, collectionManager),
                new AddElement(console, collectionManager),
                new Update(console, collectionManager),
                new RemoveById(console, collectionManager),
                new Clear(console, collectionManager)
                //new Save(console, collectionManager)
        ));
        new RuntimeManager(console, commandManager).interactiveMode();
    }
}
