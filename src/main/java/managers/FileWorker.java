package managers;

import managers.commandLine.Console;
import managers.commandLine.ConsoleColors;
import managers.commandLine.ExitCode;

import java.io.*;

public class FileWorker {
    private String text;
    private Console console;

    public FileWorker(Console console) {
        this.console = console;
    }

    public ExitCode findfile() {
        String file_path = System.getenv("file_path");
        if (file_path == null || file_path.isEmpty())
            console.printError("Путь должен быть в переменных окружения в перменной 'file_path'");
        else console.println(Console.toColor("Путь получен успешно", ConsoleColors.GREEN));

        File file = new File(file_path);
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            while (bis.available() > 0) {
                stringBuilder.append((char) bis.read());
            }

        } catch (FileNotFoundException fnfe) {
            console.printError("Такого файла не найдено");
            return ExitCode.ERROR;
        } catch (IOException ioe) {
            console.printError("Ошибка ввода/вывода" + ioe);
            return ExitCode.ERROR;
        } finally {
            try {
                if (bis != null) {
                    fis.close();
                    bis.close();
                    console.println(Console.toColor("Файл получен успешно", ConsoleColors.GREEN));
                }
            } catch (IOException ioe) {
                console.printError("Ошибка в InputStream ");
                return ExitCode.ERROR;
            }
        }
        if (stringBuilder.isEmpty()) {
            console.printError("Файл пустой");
            return ExitCode.ERROR;
        }
        this.text = stringBuilder.toString();
        return ExitCode.OK;
    }
}
