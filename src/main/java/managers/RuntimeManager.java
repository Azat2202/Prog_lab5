package managers;

import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;
import exceptions.NoSuchCommand;
import commandLine.*;

import java.util.*;

public class RuntimeManager {
    private final Printable console;
    private final CommandManager commandManager;

    public RuntimeManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    public void interactiveMode(){
        Scanner userScanner = ScannerManager.getUserScanner();
        while (true) {
            try{
                if (!userScanner.hasNext()) throw new ExitObliged();
                String userCommand = userScanner.nextLine().trim() + " "; // прибавляем пробел, чтобы split выдал два элемента в массиве
                this.launch(userCommand.split(" ", 2));
                commandManager.addToHistory(userCommand);
            } catch (NoSuchElementException exception) {
                console.printError("Пользовательский ввод не обнаружен!");
            } catch (NoSuchCommand noSuchCommand) {
                console.printError("Такой команды нет в списке");
            } catch (IllegalArguments e) {
                console.printError("Введены неправильные аргументы команды");
            } catch (CommandRuntimeError e) {
                console.printError("Ошибка при исполнении команды");
            } catch (ExitObliged exitObliged){
                console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
                return;
            }
        }
    }

    public void launch(String[] userCommand) throws NoSuchCommand, ExitObliged, IllegalArguments, CommandRuntimeError {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }
}
