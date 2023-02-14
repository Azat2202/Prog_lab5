package models.forms;

import managers.commandLine.*;
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

    private
}
