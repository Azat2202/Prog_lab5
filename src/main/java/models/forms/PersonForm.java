package models.forms;

import managers.commandLine.*;
import models.Color;
import models.Country;
import models.Location;
import models.Person;

import java.util.Scanner;

public class PersonForm extends Form<Person>{

    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public PersonForm(Console console) {
        this.console = console;
    }

    @Override
    public Person build() {
        console.println(ConsoleColors.toColor("Создание объекта админа", ConsoleColors.PURPLE));
        Person person = new Person(
                askName(),
                askWeight(),
                askEyeColor(),
                askHairColor(),
                askNationality(),
                askLocation()
        );
        if (!person.validate()) {
            console.printError("Невалидные координаты попробуйте снова");
            throw new InvalidForm();
        };

        console.println(ConsoleColors.toColor("Создание объекта админа окончено успешно", ConsoleColors.PURPLE));
        return person;
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

    private int askWeight(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите количество студентов", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("Число студентов должно быть числом типа long");
            } catch (Throwable throwable) {
                console.printError("Непридвиденная ошибка!");
            }
        }
    }

    private Color askEyeColor(){
        return new ColorForm(console, "глаз").build();
    }
    private Color askHairColor(){
        return new ColorForm(console, "волсо").build();
    }

    private Country askNationality(){
        return new NationalityForm(console).build();
    }
    private Location askLocation(){
        return new LocationForm(console).build();
    }
}
