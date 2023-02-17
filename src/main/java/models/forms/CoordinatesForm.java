package models.forms;

import exceptions.InvalidForm;
import commandLine.*;
import models.Coordinates;

import java.util.Scanner;

public class CoordinatesForm extends Form<Coordinates>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public CoordinatesForm(Console console) {
        this.console = console;
    }
    @Override
    public Coordinates build(){
        return new Coordinates(askX(), askY());
    }

    private Float askX(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату X", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException exception) {
                console.printError("X должно быть числом типа float");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }
    private Double askY(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату Y", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("Y должно быть числом типа double");
            } catch (Throwable throwable){
                console.printError("Непредвиденная ошибка!");
            }
        }
    }
}
