package models.forms;

import managers.*;
import managers.commandLine.*;
import models.Coordinates;
import models.FormOfEducation;
import models.Person;
import models.StudyGroup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class StudyGroupForm extends Form<StudyGroup>{
    private final Console console;
    private final CollectionManager collectionManager;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public StudyGroupForm(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public StudyGroup build() {
        return new StudyGroup(
                askName(),
                askCoordinates(),
                new Date(),
                askStudentsCount(),
                askExpelledStudents(),
                askAverageMark(),
                askFormOfEducation(),
                askGroupAdmin()
        );
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

    private Coordinates askCoordinates(){
        return new CoordinatesForm(console).build();
    }

    private Long askStudentsCount(){

        while (true) {
            console.println(ConsoleColors.toColor("Введите количество студентов", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Число студентов должно быть числом типа long");
            } catch (Throwable throwable) {
                console.printError("Непридвиденная ошибка!");
            }
        }
    }

    private long askExpelledStudents(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите количество отчисленных студентов", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Число студентов должно быть числом типа long");
            } catch (Throwable throwable) {
                console.printError("Непридвиденная ошибка!");
            }
        }
    }

    private long askAverageMark(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите среднюю оценку", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Оценка должна быть числом типа long");
            } catch (Throwable throwable) {
                console.printError("Непридвиденная ошибка!");
            }
        }
    }

    private FormOfEducation askFormOfEducation(){
        return new FormOfEducationForm(console).build();
    }

    private Person askGroupAdmin(){
        return new PersonForm(console).build();
    }
}
