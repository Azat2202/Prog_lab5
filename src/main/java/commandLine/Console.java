package commandLine;

/**
 * Класс для вывода в стандартную консоль
 */
public class Console implements Printable {

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
