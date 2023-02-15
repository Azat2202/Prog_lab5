import managers.*;
import managers.commandLine.*;
import managers.commandLine.Console;
import managers.commandLine.commands.*;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Console console = new Console();
        FileWorker fileWorker = new FileWorker(console);
        CollectionManager collectionManager = new CollectionManager(fileWorker);
        CommandManager commandManager = new CommandManager();
        if (fileWorker.findfile() == ExitCode.ERROR) return;

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
