package ru;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    @Test
    public void addGradeTest2(){
        Student stud = new Student("Vasya");
        CloseableHttpClient httpClientMock = Mockito.mock(CloseableHttpClient.class);
        CloseableHttpResponse httpResponseMock = Mockito.mock(CloseableHttpResponse.class);
        HttpGet requestMock = Mockito.mock(HttpGet.class);
        HttpEntity entityMock = Mockito.mock(HttpEntity.class);

        Mockito.when(httpClientMock.execute(Mockito.any())).thenReturn(httpResponseMock);
        Mockito.when(httpResponseMock.getEntity()).thenReturn(entityMock);

        InputStream istr = new ByteArrayInputStream("true".getBytes());
        Mockito.when(entityMock.getContent()).thenReturn(istr);

    }

}

