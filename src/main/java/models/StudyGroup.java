package models;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Objects;

public class StudyGroup implements Validator{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private long expelledStudents; //Значение поля должно быть больше 0
    private long averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null

    private static int nextId = 0;

    public StudyGroup(String name, Coordinates coordinates, Date creationDate, Long studentsCount, long expelledStudents, long averageMark, FormOfEducation formOfEducation, Person groupAdmin) {
        this.id = incNextId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
    }

    public static int incNextId(){
        return nextId++;
    }

    public static void updateId(ArrayDeque<StudyGroup> collection){
        nextId = collection.
                stream().filter(Objects::nonNull)
                .map(StudyGroup::getId)
                .mapToInt(Integer::intValue)
                .max().orElse(0) + 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public long getExpelledStudents() {
        return expelledStudents;
    }

    public void setExpelledStudents(long expelledStudents) {
        this.expelledStudents = expelledStudents;
    }

    public long getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(long averageMark) {
        this.averageMark = averageMark;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public boolean validate() {
        if (this.id == null || this.id <= 0) return false;
        if (this.name == null || this.name.isEmpty()) return false;
        if (this.coordinates == null) return false;
        if (this.creationDate == null) return false;
        if (this.studentsCount == null || this.studentsCount <= 0) return false;
        if (this.expelledStudents <= 0) return false;
        if (this.averageMark <= 0) return false;
        return this.groupAdmin != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyGroup that = (StudyGroup) o;

        if (expelledStudents != that.expelledStudents) return false;
        if (averageMark != that.averageMark) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!coordinates.equals(that.coordinates)) return false;
        if (!creationDate.equals(that.creationDate)) return false;
        if (!studentsCount.equals(that.studentsCount)) return false;
        if (formOfEducation != that.formOfEducation) return false;
        return groupAdmin.equals(that.groupAdmin);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + studentsCount.hashCode();
        result = 31 * result + (int) (expelledStudents ^ (expelledStudents >>> 32));
        result = 31 * result + (int) (averageMark ^ (averageMark >>> 32));
        result = 31 * result + formOfEducation.hashCode();
        result = 31 * result + groupAdmin.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", expelledStudents=" + expelledStudents +
                ", averageMark=" + averageMark +
                ", formOfEducation=" + formOfEducation +
                ", groupAdmin=" + groupAdmin +
                '}';
    }
}
