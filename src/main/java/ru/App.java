package ru;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Student st = new Student("Vasya");
        st.addGrade(5);
        System.out.println(st);
    }
}
