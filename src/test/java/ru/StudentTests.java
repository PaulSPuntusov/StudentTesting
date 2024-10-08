package ru;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class StudentTests {
       @Test
    public void addGradeTest(){
        List<Integer> lst = List.of(2, 3, 4, 5);
        Student stud = new Student("Vasya");
        stud.setCheckGrade(new StudentCheckGradeMock());
        stud.addGrade(lst.get(0));
        stud.addGrade(lst.get(1));
        stud.addGrade(lst.get(2));
        stud.addGrade(lst.get(3));
        Assertions.assertEquals(stud.getGrades(),lst);
    }

}

