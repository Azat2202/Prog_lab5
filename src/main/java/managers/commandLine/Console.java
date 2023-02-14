package managers.commandLine;

import managers.commandLine.Printable;

/**
 * Класс для вывода в консоль
 */
public class Console implements Printable {
    public static final String ANSI_ERROR_COLOR = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    @Override
    public void println(String a) {
        System.out.println(a);
    }

    @Override
    public void print(String a) {
        System.out.print(a);
    }

    @Override
    public void printError(String a) {
        System.out.println(ANSI_ERROR_COLOR + a + ANSI_RESET);
    }
}
