package models.forms;

import managers.commandLine.*;
import models.Coordinates;
import models.Location;

import java.util.Scanner;

public class LocationForm extends Form<Location>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public LocationForm(Console console) {
        this.console = console;
    }

    @Override
    public Location build() throws InvalidForm{
        Location location = new Location(
                askX(),
                askY(),
                askName());
        if (!location.validate()) {
            console.printError("Невалидная локация попробуйте снова");
            throw new InvalidForm();
        };
        return location;
    }

    private double askX(){

        while (true) {
            console.print(ConsoleColors.toColor("Введите координату X", ConsoleColors.GREEN));
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
            console.print(ConsoleColors.toColor("Введите координату Y", ConsoleColors.GREEN));
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
        String name;
        while (true){
            console.println(ConsoleColors.toColor("Введите имя", ConsoleColors.GREEN));
            name = scanner.nextLine().trim();
            if (name.isBlank()){
                console.printError("Имя не может быть пустым");
            }
            else{
                return name;
            }
        }
    }
}
