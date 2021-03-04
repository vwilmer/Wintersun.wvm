package com.winter.poo.agregation;

public class Course {

    private String courseName;
    private Instructor instructor;
    private TextBook textBook;

    public Course(String courseName, Instructor instructor, TextBook textBook) {
        // Assign the courseName.
        setCourseName(courseName);
        // Create a new Instructor object
        this.instructor = new Instructor(instructor);
        setInstructor(getInstructor());
        // Create a new TextBook object
        this.textBook = new TextBook(textBook);
        setTextBook(getTextBook());
    }

    public Course() {

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public TextBook getTextBook() {
        return textBook;
    }

    public void setTextBook(TextBook textBook) {
        this.textBook = textBook;
    }

    @Override
    public String toString() {
        return "Course [courseName=" + courseName + ", instructor=" + instructor + ", textBook=" + textBook + "]";
    }

    public static void main(String[] args) {
        Instructor myInstructor = new Instructor("Kramer", "Shawn", "RH3010");
        TextBook myTextBook = new TextBook("Starting Out with Java", "Gaddis", "Pearson");
        Course myCourse = new Course("Intro to Java", myInstructor, myTextBook);
        System.out.println(myCourse);

        Course c1 = new Course();
        c1.setCourseName("intro to java");
        System.out.println(c1.getCourseName());
    }

}
