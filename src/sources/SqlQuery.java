package sources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.mysql.jdbc.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author sijitend
 */
public class SqlQuery {
    private int ret;

    public  int InsertData(String sQuery)
    {
        System.out.println("Qery for inserrdata :"+sQuery);
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();

            ret = stmt.executeUpdate(sQuery);
            con.close();
            return ret;
        } catch (Exception e) {
            System.out.println(e);
            return 0;

        }
    }
    
    public  void FetchData(String sQuery)
    {
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();

            stmt.executeQuery(sQuery);
             rs =  (ResultSet) stmt.executeQuery(sQuery);
                if(rs.next())
                {    
                    rs.previous();
                    while(rs.next())
                    {
                        String Mobile = rs.getString("Mobile");
                        String first_name = rs.getString("first_name");
                        String UserID = rs.getString("UserID");
                        System.out.println("\n~* Welcome "+UserID);
                    }
                }
                else
                {
                    System.out.println("\n---> No Data Found;\nNew Customer :) \n");
                     con.close();
                }
             con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



