package managers;

import models.StudyGroup;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public class CollectionManager {
    private final ArrayDeque<StudyGroup> collection = new ArrayDeque<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final FileWorker fileWorker;

    public CollectionManager(FileWorker fileWorker) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileWorker = fileWorker;
    }

    public ArrayDeque<StudyGroup> getCollection() {
        return collection;
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
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

    /**
     * @return Последний элемент коллекции (null если коллекция пустая).
     */
    public StudyGroup getLast() {
        if (collection.isEmpty()) return null;
        return Iterables.getLast(collection);
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

    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id) {
        return collection.stream()
                .anyMatch((x) -> x.getId() == id);
    }

    public void addElement(StudyGroup studyGroup){
        collection.add(studyGroup);
        StudyGroup.incNextId();
    }
    public void removeElement(StudyGroup studyGroup){
        collection.remove(studyGroup);
    }

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
