package models.forms;

import managers.commandLine.*;
import models.Coordinates;

import java.util.Scanner;

public class CoordinatesForm extends Form<Coordinates>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public CoordinatesForm(Console console) {
        this.console = console;
    }
    @Override
    public Coordinates build() throws InvalidForm{
        Coordinates coordinates = new Coordinates(askX(), askY());
        if (!coordinates.validate()) {
            console.printError("Невалидные координаты попробуйте снова");
            throw new InvalidForm();
        };
        return coordinates;
    }

    private Float askX(){
        while (true) {
            console.print(ConsoleColors.toColor("Введите координату X", ConsoleColors.GREEN));
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
            console.print(ConsoleColors.toColor("Введите координату Y", ConsoleColors.GREEN));
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
