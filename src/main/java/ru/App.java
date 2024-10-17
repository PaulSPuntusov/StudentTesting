package ru;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public App() throws SQLException {
    }

    public static void main(String[] args )
    {

        Student st = new Student("Vasya");
        st.addGrade(5);
        System.out.println(st);
        DBTask dbt = new DBTask();
        dbt.action();
    }

}
