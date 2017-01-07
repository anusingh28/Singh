package sources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.mysql.jdbc.ResultSet;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
/**
 *
 * @author sijitend
 */
public class Customer {
    
static String First_Name;

static String Last_Name ;
static String FatherName;
static String Mobile	;	
static String Vill	;	
static String Town	;	
static String Dist	;	
static String Landmark	;
static int     PINCODE	;	
static String Address 	;
static String City 	;	
static String Birth_Date;
static String UserID;

static  Scanner in = new Scanner(System.in); 
static int flag;

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getVill() {
        return Vill;
    }

    public String getTown() {
        return Town;
    }

    public String getDist() {
        return Dist;
    }

    public String getLandmark() {
        return Landmark;
    }

    public int getPINCODE() {
        return PINCODE;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

  

    public String getBirth_Date() {
        return Birth_Date;
    }

    public void setFirst_Name(String First_Name) {
        this.First_Name = First_Name;
    }

    public void setLast_Name(String Last_Name) {
        this.Last_Name = Last_Name;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setVill(String Vill) {
        this.Vill = Vill;
    }

    public void setTown(String Town) {
        this.Town = Town;
    }

    public void setDist(String Dist) {
        this.Dist = Dist;
    }

    public void setLandmark(String Landmark) {
        this.Landmark = Landmark;
    }

    public void setPINCODE(int PINCODE) {
        this.PINCODE = PINCODE;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

 

    public void setBirth_Date(String Birth_Date) {
        this.Birth_Date = Birth_Date;
    }
        public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    private static final Logger LOG = Logger.getLogger(Customer.class.getName());

     
    
   
public String toMyString() {
  StringBuilder result = new StringBuilder();
  String newLine = System.getProperty("line.separator");

  result.append( this.getClass().getName() );
  result.append( " Object {" );
  result.append(newLine);

  //determine fields declared in this class only (no fields of superclass)
  Field[] fields = this.getClass().getDeclaredFields();

  //print field names paired with their values
  for ( Field field : fields  ) {
    result.append("  ");
    try {
      result.append( field.getName() );
      result.append(": ");
      //requires access to private field:
      result.append( field.get(this) );
    } catch ( IllegalAccessException ex ) {
      System.out.println(ex);
    }
    result.append(newLine);
  }
  result.append("}");

  return result.toString();
}


    public  static  String CreateNewCustomer() {
        
        System.out.print("First_Name : ");
        First_Name = in.nextLine();
        
        System.out.print("\nLast_Name : ");
         Last_Name = in.nextLine();

        System.out.print("\nFatherName : ");
         FatherName = in.nextLine();
        
        System.out.print("\nMobile : ");
         Mobile = in.nextLine();
        
        System.out.print("\nVill : ");
         Vill = in.nextLine();
        
        System.out.print("\nPINCODE : ");
         PINCODE = in.nextInt();
        in.nextLine();

        System.out.print("\nTown : ");
         Town = in.nextLine();
        
        System.out.print("\nDistrict : ");
         Dist = in.nextLine();
        
        System.out.print("\nCity : ");
         City = in.nextLine();
        
        System.out.print("\nAddress : ");
        String Address = in.nextLine();
        
       
        
        System.out.print("\nLandmark : ");
         Landmark = in.nextLine();
        
        System.out.print("\nBirth_Date : ");
         Birth_Date = in.nextLine();
      
        String UserID = First_Name+Mobile.substring(Mobile.length()-5, Mobile.length());
        
        Customer c = new Customer();
        c.setFirst_Name(First_Name);
        c.setLast_Name(Last_Name);
        c.setFatherName(FatherName);
        c.setMobile(Mobile);
        c.setPINCODE(PINCODE);
        c.setVill(Vill);
        c.setTown(Town);
        c.setDist(Dist);
        c.setCity(City);
        c.setAddress(Address);
        c.setLandmark(Landmark);
        c.setBirth_Date(Birth_Date);
        c.setUserID(UserID);
        
        System.out.println(c.toMyString());
        
        c.SetVal(c);
        System.out.println("\nHello "+c.getFirst_Name()+"  Your Customer ID : "+c.getUserID()+"\n");
        return UserID;
    }

    public static void SetVal(Customer c )
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();
            String tmp = "INSERT INTO customer (`First_Name`, `Last_Name`, `FatherName`, `Mobile`, `Vill`, `Town`, "
                    + "`Dist`, `Landmark`, `PINCODE`, `Address`, `City`, `Birth_Date`, `UserID`) "
                    + "VALUES ("+"'"+c.getFirst_Name()+"'"+","+"'"+c.getLast_Name()+"'"+","+"'"+c.getFatherName()+"'"+","+"'"+c.getMobile()+"'"+","+"'"+c.getVill()+"'"+","+"'"+c.getTown()+"'"+","+"'"+c.getDist()+"'"+","+"'"+c.getLandmark()+"'"+","+"'"+c.getPINCODE()+"'"+","
                    +"'"+c.getAddress()+"'"+","+"'"+c.getCity()+"'"+", "+"'"+c.getBirth_Date()+"'"+","+"'"+c.getUserID()+"'"+");";
            
            System.out.println("==>"+tmp);
            stmt.executeUpdate(tmp);
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void getSQLOutput(String sQuery) {
        ResultSet rs;
                try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();
            System.out.println("==>"+sQuery);
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
    
    
    
    public static String CheckQuery(String sVal, int opt) {
        String sQuery = null;
        ResultSet rs;
        
                try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();
            
            if (opt == 2)
            {
                sQuery = "select UserID,first_name,last_name,Mobile from customer where Mobile="+"'"+sVal+"'";
                rs =  (ResultSet) stmt.executeQuery(sQuery);
                if(rs.next())
                {    
                    rs.previous();
                    while(rs.next())
                    {
                        String Mobile = rs.getString("Mobile");
                        String first_name = rs.getString("first_name");
                        String last_name = rs.getString("last_name");
                        String UserID = rs.getString("UserID");
                        
                        return (UserID+":"+first_name+":"+last_name);
                       // return UserID;

                    }
                }
                else
                {
                    System.out.println("\n---> No Data Found;\nNew Customer :) \n");
                    flag =1;
                     con.close();
                    return "No Data Found";
                    
                }
            }
            else if(opt ==1)
            {
                sQuery = "select first_name,last_name from customer where UserID="+"'"+sVal+"'";
                rs =  (ResultSet) stmt.executeQuery(sQuery);
                if(rs.next())
                {
                   rs.previous();
                   while (rs.next())
                   {
                      
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    System.out.println("\n~* Welcome "+UserID);
                    return (sVal+":"+first_name+":"+last_name);
                    //return ("~* Welcome: "+UserID);
                    }
                }
                else
                { 
                    System.out.println("\n---> No Data Found;\nNew Customer :) \n");
                    flag =1;
                    con.close();
                    return "No Data Found";
                    
                }
            }
            else
            {
                sQuery = "select Mobile,Address,Vill,Town,Dist,Landmark,City,PINCODE from customer where UserID="+"'"+sVal+"'";
                rs =  (ResultSet) stmt.executeQuery(sQuery);
                 while (rs.next())
                   {
                    MyMain.sCustomer_More_Details = rs.getString("Mobile")+"Address : "+rs.getString("Address")+"@"+"Village : "+rs.getString("Vill")+", "+"Town : "+rs.getString("Town")+"@"+"District : "+rs.getString("Dist")+"@"+"Landmark : "+rs.getString("Landmark")+"@"+"City : "+rs.getString("City")+" - "+rs.getString("PINCODE");
                    System.out.println("\nMyMain.sCustomer_More_Details : "+MyMain.sCustomer_More_Details);
                    return "OK";
                    }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
                return null;
    }
}
