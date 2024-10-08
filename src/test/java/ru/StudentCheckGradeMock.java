package ru;

import java.util.ArrayList;
import java.util.List;

public class StudentCheckGradeMock implements StudentCheckGrade {


    @Override
    public boolean studentCheckGrade(int grade) {
        return (grade < 2|| grade > 5);
    }
}
