package managers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.security.AnyTypePermission;
import commandLine.Console;
import commandLine.ConsoleColors;
import commandLine.Printable;
import exceptions.ExitObliged;
import exceptions.InvalidForm;
import models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Date;

public class FileManager {
    private String text;
    private Printable console;
    private CollectionManager collectionManager;
    private XStream xStream = new XStream();

    public FileManager(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.collectionManager = collectionManager;

        this.xStream.alias("StudyGroup", StudyGroup.class);
        this.xStream.alias("Array", CollectionManager.class);
        this.xStream.addPermission(AnyTypePermission.ANY);
        this.xStream.addImplicitCollection(CollectionManager.class, "collection");
    }

    public void findFile() throws ExitObliged{
        String file_path = System.getenv("file_path");
        if (file_path == null || file_path.isEmpty())
            console.printError("Путь должен быть в переменных окружения в перменной 'file_path'");
        else console.println(ConsoleColors.toColor("Путь получен успешно", ConsoleColors.PURPLE));

        File file = new File(file_path);
        BufferedInputStream bis;
        FileInputStream fis;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            while (bis.available() > 0) {
                stringBuilder.append((char) bis.read());
            }
            fis.close();
            bis.close();
            if (stringBuilder.isEmpty()) {
                console.printError("Файл пустой");
                throw new ExitObliged();
            }
            this.text = stringBuilder.toString();
        } catch (FileNotFoundException fnfe) {
            console.printError("Такого файла не найдено");
            throw new ExitObliged();
        } catch (IOException ioe) {
            console.printError("Ошибка ввода/вывода" + ioe);
            throw new ExitObliged();
        }
    }

    public void createObjects() throws ExitObliged{
        XStream xstream = new XStream();
        xstream.alias("StudyGroup", StudyGroup.class);
        xstream.alias("Array", CollectionManager.class);
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.addImplicitCollection(CollectionManager.class, "collection");
        CollectionManager collectionManagerWithObjects = (CollectionManager) xstream.fromXML(this.text);
        try{
            this.collectionManager.addElements(collectionManagerWithObjects.getCollection());
        } catch (InvalidForm e) {
            console.printError("Объекты в файле не валидны");
            throw new ExitObliged();
        }
        StudyGroup.updateId(collectionManager.getCollection());
    }

    public void saveObjects(){
        String file_path = System.getenv("file_path");
        if (file_path == null || file_path.isEmpty())
            console.printError("Путь должен быть в переменных окружения в перменной 'file_path'");
        else console.println(ConsoleColors.toColor("Путь получен успешно", ConsoleColors.PURPLE));
        try{
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file_path));
            out.write(this.xStream.toXML(collectionManager)
                    .getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (FileNotFoundException e) {
            console.printError("Файл не существует");
        }catch (IOException e){
            console.printError("Ошибка ввода вывода");
        }
    }

    private Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
