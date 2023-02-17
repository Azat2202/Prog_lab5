package managers;

import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;
import exceptions.NoSuchCommand;
import commandLine.*;

import java.util.*;

/* TODO:
1. Изменить printError чтобы он брал текст из ошибки
2. Проверить как вообще работает id
 */
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
        while (true) {
            try{
                String userCommand = userScanner.nextLine().trim() + " "; // прибавляем пробел, чтобы split выдал два элемента в массиве
                commandManager.addToHistory(userCommand);
                this.launch(userCommand.split(" ", 2));
            } catch (NoSuchElementException exception) {
                console.printError("Пользовательский ввод не обнаружен!");
            } catch (NoSuchCommand noSuchCommand) {
                console.printError("Такой команды нет в списке");
            } catch (IllegalArguments e) {
                console.printError("Введены неправильные агрументы команды");
            } catch (CommandRuntimeError e) {
                console.printError("Ошибка при исполнении команды");
            }catch (ExitObliged exitObliged){
                console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
                return;
            }
        }
    }

    private void launch(String[] userCommand) throws NoSuchCommand, ExitObliged, IllegalArguments, CommandRuntimeError {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }
}
