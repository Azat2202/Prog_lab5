package commandLine.commands;

import commandLine.Console;
import commandLine.ConsoleColors;
import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;
import managers.CommandManager;
import managers.FileManager;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Execute extends Command{
    private FileManager fileManager;
    private Console console;
    public Execute(Console console, FileManager fileManager) {
        super("execute_script", "file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.fileManager = fileManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws CommandRuntimeError, ExitObliged, IllegalArguments {
        if (args == null || args.isEmpty()) {
            console.printError("Путь не распознан");
            return;
        }
        else console.println(ConsoleColors.toColor("Путь получен успешно", ConsoleColors.PURPLE));

        try {
            File file = new File(args);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(bis, StandardCharsets.UTF_8));
            r.lines().forEach(console::println);
            fis.close();
            bis.close();
            r.close();
        } catch (FileNotFoundException fileNotFoundException){
            console.printError("Такого файла не существует");
        }
        catch (IOException e) {
            console.printError("Ошибка ввода вывода");
        }
    }
}
