import managers.*;
import managers.commandLine.*;
import managers.commandLine.commands.*;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Console console = new Console();
        FileWorker fileWorker = new FileWorker();
        CollectionManager collectionManager = new CollectionManager(fileWorker);
        CommandManager commandManager = new CommandManager();

        commandManager.addCommand(List.of(
                new Help(console, commandManager),
                new Info(console, collectionManager),
                new Show(console, collectionManager),
                new AddElement(console, collectionManager)
        ));
        new RuntimeManager(console, commandManager).interactiveMode();
    }
}
