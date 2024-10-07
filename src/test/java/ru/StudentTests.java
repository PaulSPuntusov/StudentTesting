package ru;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class StudentTests {
    @Test
    public void marksInRange() {
        List<Integer> lst = List.of(2, 3, 4, 5);
        Student stud = new Student("Vasya");
        stud.addGrade(lst.get(0));
        stud.addGrade(lst.get(1));
        stud.addGrade(lst.get(2));
        stud.addGrade(lst.get(3));
        Assertions.assertEquals(stud.getGrades(),lst);
    }

    @Test
    public void marksNotInRange() {
        List<Integer> lst = List.of(0, 1, 6, 7);
        Student stud = new Student("Vasya");
        try {
            stud.addGrade(lst.get(0));
            stud.addGrade(lst.get(1));
            stud.addGrade(lst.get(2));
            stud.addGrade(lst.get(3));
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new RuntimeException("test error");
    }
    @Test
    public void setNameOk(){
        String name = "Vasya";
        Student stud = new Student("Petya");
        stud.setName(name);
        Assertions.assertEquals(stud.getName(),name);
    }
    @Test
    public void hashTest(){
        Student stud = new Student("Vasya");
        Assertions.assertEquals(stud.hashCode(),stud.hashCode());
    }
    @Test
    public void equalsTest(){
        Student stud = new Student("Vasya");
        Assertions.assertEquals(stud.equals(stud),stud.equals(stud));
    }

}

