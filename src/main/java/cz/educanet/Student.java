package cz.educanet;

public class Student {
    private int id;
    private String name;
    private String birthday;
    private int avgMark;

    public Student(int id, String name, String birthday, int avgMark) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.avgMark = avgMark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(int avgMark) {
        this.avgMark = avgMark;
    }
}
