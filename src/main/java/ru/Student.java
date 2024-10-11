package ru;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Student {
    @Getter
    @Setter
    private StudentCheckGrade checkGrade;
    private String name;
    private List grades = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List getGrades() {
        List<Integer> copyGrades = new ArrayList<>(this.grades);
        return copyGrades;
    }
    @SneakyThrows
    public void addGrade(int grade){
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpGet request = new HttpGet ("...");
        if(checkGrade.studentCheckGrade(grade)){
            throw new IllegalArgumentException(grade + " is wrong grade");
        }
        grades.add(grade);
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.grades);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final Student other = (Student) obj;
        if(!Objects.equals(this.name, other.name)){
            return  false;
        }
        return Objects.equals(this.grades, other.grades);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }
}
