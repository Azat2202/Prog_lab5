package models.forms;

import managers.commandLine.*;
import models.Color;
import models.FormOfEducation;

import java.util.Locale;
import java.util.Scanner;

public class ColorForm extends Form<Color>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();
    private final String type;

    public ColorForm(Console console, String type) {
        this.console = console;
        this.type = type;
    }

    @Override
    public Color build() {
        console.println("Возможные цвета: ");
        console.print(Color.names());
        while (true){
            console.println(Console.toColor("Введите цвет " + type + ": ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try{
                return Color.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printError("Такого цвета нет в списке");
            } finally {
                console.printError("Непредвиденная ошибка");
            }
        }
    }
}
