package commandLine.commands;

import commandLine.Console;
import commandLine.ConsoleColors;
import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;
import exceptions.NoSuchCommand;
import managers.CommandManager;
import managers.FileManager;
import managers.RuntimeManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

/**
 * Команда 'execute_script'
 * Считатывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class Execute extends Command{
    private FileManager fileManager;
    private Console console;
    private CommandManager commandManager;
    public Execute(Console console, FileManager fileManager, CommandManager commandManager) {
        super("execute_script", " file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.fileManager = fileManager;
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     * @throws CommandRuntimeError команда вызваола ошибку при исполнении
     * @throws ExitObliged требуется выход из программы
     */
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
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(bis, StandardCharsets.UTF_8));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                try{
                    commandManager.addToHistory(line);
                    String[] cmd = (line + " ").split(" ", 2);
                    if (cmd[0].isBlank() || cmd[0].equals("execute_script")) return;
                    console.println(ConsoleColors.toColor("Выполнение команды " + cmd[0], ConsoleColors.YELLOW));
                    commandManager.execute(cmd[0], cmd[1]);
                } catch (NoSuchElementException exception) {
                    console.printError("Пользовательский ввод не обнаружен!");
                } catch (NoSuchCommand noSuchCommand) {
                    console.printError("Такой команды нет в списке");
                } catch (IllegalArguments e) {
                    console.printError("Введены неправильные аргументы команды");
                } catch (CommandRuntimeError e) {
                    console.printError("Ошибка при исполнении команды");
                }
            }
            fis.close();
            bis.close();
            br.close();
        }  catch (NoSuchCommand noSuchCommand){
            console.printError("Такой команды не существует");
        } catch (FileNotFoundException fileNotFoundException){
            console.printError("Такого файла не существует");
        } catch (IOException e) {
            console.printError("Ошибка ввода вывода");
        }
    }
}
