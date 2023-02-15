package managers.commandLine;

import models.Color;

/**
 * Класс для вывода в консоль
 */
public class Console implements Printable {

    public static String toColor(String s, ConsoleColors color){
        return color.toString() + s + ConsoleColors.RESET;
    }

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
        System.err.println(ConsoleColors.RED + a + ConsoleColors.RESET);
    }
}
