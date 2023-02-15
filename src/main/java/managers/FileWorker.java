package managers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.*;
import managers.commandLine.Console;
import managers.commandLine.ConsoleColors;
import managers.commandLine.ExitCode;
import models.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileWorker {
    private String text;
    private Console console;

    @XStreamImplicit
    private ArrayDeque<StudyGroup> collection = new ArrayDeque<StudyGroup>();

    public FileWorker(Console console) {
        this.console = console;
    }

    public ExitCode findfile() {
        String file_path = System.getenv("file_path");
        if (file_path == null || file_path.isEmpty())
            console.printError("Путь должен быть в переменных окружения в перменной 'file_path'");
        else console.println(Console.toColor("Путь получен успешно", ConsoleColors.GREEN));

        File file = new File(file_path);
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            while (bis.available() > 0) {
                stringBuilder.append((char) bis.read());
            }

        } catch (FileNotFoundException fnfe) {
            console.printError("Такого файла не найдено");
            return ExitCode.ERROR;
        } catch (IOException ioe) {
            console.printError("Ошибка ввода/вывода" + ioe);
            return ExitCode.ERROR;
        } finally {
            try {
                if (bis != null) {
                    fis.close();
                    bis.close();
                    console.println(Console.toColor("Файл получен успешно", ConsoleColors.GREEN));
                }
            } catch (IOException ioe) {
                console.printError("Ошибка в InputStream ");
                return ExitCode.ERROR;
            }
        }
        if (stringBuilder.isEmpty()) {
            console.printError("Файл пустой");
            return ExitCode.ERROR;
        }
        this.text = stringBuilder.toString();
        return ExitCode.OK;
    }

    public ExitCode createObjects(){
        //XStream xstream = new XStream();
//        xstream.processAnnotations();
//        xstream.addPermission(AnyTypePermission.ANY);
//        xstream.addImplicitCollection(this.getClass(), "collection");
//        this.collection = (ArrayDeque<StudyGroup>) xstream.fromXML(this.text);
//        for (StudyGroup studyGroup : collection){
//            console.println(studyGroup.toString());
//        }
//        console.println(convertedCustomer.toString());
        collection.add(new StudyGroup(
                "Azat",
                new Coordinates((float) 10.1, (Double) 10.5),
                convertToDateViaSqlTimestamp(LocalDateTime.now()),
                10L,
                6,
                4L,
                FormOfEducation.FULL_TIME_EDUCATION,
                new Person(
                        "admin",
                        55,
                        Color.WHITE,
                        Color.BROWN,
                        Country.FRANCE,
                        new Location(15, 10, "Home")
                )
        ));
        collection.add(new StudyGroup(
                "Azat2",
                new Coordinates((float) 10.2, (Double) 10.5),
                convertToDateViaSqlTimestamp(LocalDateTime.now()),
                15L,
                6,
                4L,
                FormOfEducation.EVENING_CLASSES,
                new Person(
                        "admin",
                        55,
                        Color.BROWN,
                        Color.RED,
                        Country.FRANCE,
                        new Location(15, 10, "Home3")
                )
        ));
        collection.add(new StudyGroup(
                "Azat3",
                new Coordinates((float) 15.5, (Double) 10.5),
                convertToDateViaSqlTimestamp(LocalDateTime.now()),
                10L,
                6,
                4L,
                FormOfEducation.EVENING_CLASSES,
                new Person(
                        "admin",
                        55,
                        Color.RED,
                        Color.BROWN,
                        Country.FRANCE,
                        new Location(15, 10, "Home2")
                )
        ));
        XStream xstream = new XStream();
//        xstream.alias("StudyGroup", StudyGroup.class);
//        xstream.alias("Groups", FileWorker.class);
        xstream.addImplicitCollection(FileWorker.class, "collection");

         String xml = xstream.toXML(this);
         console.println(xml);
        return ExitCode.OK;
    }
    private Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
