package models.forms;

import commandLine.*;
import models.Country;

import java.util.Locale;
import java.util.Scanner;

public class NationalityForm extends Form<Country>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public NationalityForm(Console console) {
        this.console = console;
    }

    @Override
    public Country build() {
        console.println("Возможные страны: ");
        console.println(Country.names());
        while (true){
            console.println(ConsoleColors.toColor("Введите страну: ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try{
                return Country.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printError("Такой страны нет в списке");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка");
            }
        }
    }
}
