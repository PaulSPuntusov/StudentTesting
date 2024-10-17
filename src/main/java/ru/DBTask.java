package ru;

import java.sql.*;

public class DBTask {

    public void action() {
        try (Connection con = DriverManager.getConnection("jdbc:h2:.C:\\Users\\pps_r\\OneDrive\\Документы\\java\\jdbc testing\\Office (2)\\Office\\Office")) {
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
