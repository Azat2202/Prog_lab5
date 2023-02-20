package managers;

import exceptions.InvalidForm;
import models.StudyGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Collection;

public class CollectionManager {
    private final ArrayDeque<StudyGroup> collection = new ArrayDeque<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
    }

    public ArrayDeque<StudyGroup> getCollection() {
        return collection;
    }

    private String timeFormatter(LocalDateTime localDateTime){
        if (localDateTime == null) return null;
        if (localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))){
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getLastInitTime() {
        return timeFormatter(lastInitTime);
    }

    public String getLastSaveTime() {
        return timeFormatter(lastSaveTime);
    }
    /**
     * @return Имя типа коллекции.
     */
    public String collectionType() {
        return collection.getClass().getName();
    }

    /**
     * @return Размер коллекции.
     */
    public int collectionSize() {
        return collection.size();
    }
    /**
     * @return Первый элемент коллекции (null если коллекция пустая).
     */
    public StudyGroup getFirst() {
        if (collection.isEmpty()) return null;
        return collection.peek();
    }

    public void clear(){
        this.collection.clear();
        lastInitTime = LocalDateTime.now();
    }
    /**
     * @return Последний элемент коллекции (null если коллекция пустая).
     */
    public StudyGroup getLast() {
        return collection.getLast();
    }

    /**
     * @param id ID элемента.
     * @return Элемент по его ID или null, если не найдено.
     */
    public StudyGroup getById(int id) {
        for (StudyGroup element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    public void editById(int id, StudyGroup newElement, ArrayDeque<StudyGroup> collection) throws InvalidForm{
        StudyGroup pastElement = this.getById(id);
        this.removeElement(pastElement);
        newElement.setId(id);
        this.addElement(newElement);
        StudyGroup.updateId(collection);
    }

    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id) {
        return collection.stream()
                .anyMatch((x) -> x.getId() == id);
    }

    public void addElement(StudyGroup studyGroup) throws InvalidForm{
        this.lastSaveTime = LocalDateTime.now();
        if (!studyGroup.validate()) throw new InvalidForm();
        collection.add(studyGroup);
    }

    public void addElements(Collection<StudyGroup> collection) throws InvalidForm{
        if (collection == null) return;
        for (StudyGroup studyGroup:collection){
            this.addElement(studyGroup);
        }
    }

    public void removeElement(StudyGroup studyGroup){
        collection.remove(studyGroup);
    }

    public void removeElements(Collection<StudyGroup> collection){this.collection.removeAll(collection);}

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var last = getLast();

        StringBuilder info = new StringBuilder();
        for (StudyGroup studyGroup : collection) {
            info.append(studyGroup);
            if (studyGroup != last) info.append("\n\n");
        }
        return info.toString();
    }
}
