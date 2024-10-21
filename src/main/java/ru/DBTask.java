package ru;

import java.sql.*;

public class DBTask {
    void action() {
        try(Connection con = DriverManager.getConnection("jdbc:h2:C:\\Users\\pps_r\\OneDrive\\Документы\\java\\jdbc testing\\Office (2)\\Office")){
            PreparedStatement stm = con.prepareStatement(
                    "Select ID, NAME as txt from Department where name like ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            String str="A%";
            //ResultSet rs= stm.executeQuery("Select ID, NAME as txt from Department");
            stm.setString(1,str);
            ResultSet rs=stm.executeQuery();
            System.out.println("------------------------------------");
            while(rs.next()){
                System.out.println(rs.getInt("ID")+"\t"+rs.getString("name"));
            }
            System.out.println("------------------------------------");
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void myaction() throws ClassNotFoundException {
        try (Connection con = DriverManager.getConnection("jdbc:h2:.\\StudentTesting")) {
            if (con != null) {
                String txt = "Select * from Employee Where name like ?";
                PreparedStatement pStatement = con.prepareStatement(txt);
                pStatement.setString(1, "Ann");
                ResultSet rs = pStatement.executeQuery();
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("EmployeeID") + "\t"
                                    + rs.getString("Name")
                    );
                }
            } else {
                System.out.println("Failad to make connection");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
