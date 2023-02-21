package models.forms;

import commandLine.*;
import models.FormOfEducation;

import java.util.Locale;
import java.util.Scanner;

/**
 * Форма для формы обучения
 */
public class FormOfEducationForm extends Form<FormOfEducation>{
    private final Console console;
    private final Scanner scanner = ScannerManager.getUserScanner();

    public FormOfEducationForm(Console console) {
        this.console = console;
    }

    /**
     * Сконструировать новый элемент Enum {@link FormOfEducation}
     * @return объект Enum {@link FormOfEducation}
     */
    @Override
    public FormOfEducation build() {
        console.println("Возможные формы обучения: ");
        console.println(FormOfEducation.names());
        while (true){
            console.println(ConsoleColors.toColor("Введите форму обучения: ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try{
                return FormOfEducation.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printError("Такой формы обучения нет в списке");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка");
            }
        }
    }
}
