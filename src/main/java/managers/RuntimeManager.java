package managers;

import managers.commandLine.*;
import managers.commandLine.commands.Command;

import java.util.*;

public class RuntimeManager {
    private final Printable console;
    private final CommandManager commandManager;
    private final List<String> scriptStack = new ArrayList<>();

    public RuntimeManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    public void interactiveMode(){
        Scanner userScanner = ScannerManager.getUserScanner();
        try{
            ExitCode commandStatus;
            String userCommand = "";
            do{
                userCommand = userScanner.nextLine().trim();
                commandManager.addToHistory(userCommand);
                commandStatus = this.launch(userCommand
                        .split(" ", 2));
            }
            while (commandStatus != ExitCode.EXIT);
        } catch (NoSuchElementException exception) {
            console.printError("Пользовательский ввод не обнаружен!");
        } finally {
            console.printError("Непредвиденная ошибка!");
        }
    }

    private ExitCode launch(String[] userCommand){
        if (userCommand[0].equals("")) return ExitCode.OK;
        Command command = commandManager.getCommands()
                .stream().filter(s -> s.getName().equals(userCommand[0]))
                .findFirst().orElse(null);
        if (command == null) {
            console.printError("Команда '" + userCommand[0] + "' не найдена. Напишите 'help' для справки");
            return ExitCode.ERROR;
        }

        return command.execute(userCommand);
    }
}
