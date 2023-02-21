package models.forms;

import exceptions.InvalidForm;
import commandLine.*;
import models.Location;

import java.util.Scanner;

/**
 * Форма для локации
 */
public class LocationForm extends Form<Location>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public LocationForm(Console console) {
        this.console = console;
    }

    /**
     * Сконструировать новый элемент класса {@link Location}
     * @return объект класса {@link Location}
     */
    @Override
    public Location build(){
        return new Location(
                askX(),
                askY(),
                askName());
    }

    private double askX(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату X", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("X должно быть числом типа double");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }

    private long askY(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату Y", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Y должно быть числом типа long");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }

    private String askName(){
        while (true){
            console.println(ConsoleColors.toColor("Введите название локации", ConsoleColors.GREEN));
            String name = scanner.nextLine().trim();
            if (name.isBlank()){
                console.printError("Имя не может быть пустым");
            }
            else{
                return name;
            }
        }
    }
}
