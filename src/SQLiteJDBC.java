import java.sql.*;

public class SQLiteJDBC
{

  public static void main( String args[] )
  {

    Connection c = null;

    try {

      Class.forName("org.sqlite.JDBC");

      c = DriverManager.getConnection("jdbc:sqlite:database.db");

    } catch ( Exception e ) {

      System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      System.exit(0);

    }

    System.out.println("Create database successfully");

    Statement stmt = null;

    try {

      Class.forName("org.sqlite.JDBC");
      System.out.println("Opened database successfully");
      stmt = c.createStatement();
      String sql = "CREATE TABLE ACCOUNT" +
                   "(UID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                   " name           TEXT    NOT NULL, " + 
                   " password       TEXT     NOT NULL)"; 

      stmt.executeUpdate(sql);
      String sql1 = "CREATE TABLE BOOK" +                                                                           
                   "(BID INTEGER PRIMARY KEY  AUTOINCREMENT," +                                     
                   " name         TEXT    NOT NULL, " +                                                            
                   " author       TEXT     NOT NULL,"+
                   " status         TEXT    NOT NULL,"+
                   " cover           TEXT    NOT NULL,"+
                   " description     TEXT    NOT NULL,"+
                   " category        TEXT    NOT NULL"+
                   ")";  
      stmt.executeUpdate(sql1);
      String sql2 = "CREATE TABLE BORROW" +                                                                           
                   "(UID            INT     NOT NULL," + 
                   " BID            INT     NOT NULL, " +                                                             
                   " deadline       TEXT    NOT NULL," +
                   "FOREIGN KEY(UID) REFERENCES ACCOUNT(UID),"+
                   "FOREIGN KEY(BID) REFERENCES BOOK(BID)"+
                   ")";      
      stmt.executeUpdate(sql2);
      String sql3 = "CREATE TABLE RESERVE" +
                   "(UID            INT     NOT NULL," + 
                   " BID            INT     NOT NULL, " +                                                          
                   "FOREIGN KEY(UID) REFERENCES ACCOUNT(UID),"+                                                       
                   "FOREIGN KEY(BID) REFERENCES BOOK(BID)"+                                                           
                   ")"; 
      stmt.executeUpdate(sql3); 
      System.out.println("Table created successfully");
      //insert example
      stmt.executeUpdate(SQLoperation.insertACCOUNT("Grace","abcd")); 

      stmt.close();
      c.close();

    } catch ( Exception e ) {

      System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      System.exit(0);

    }
    System.out.println("All operation done successfully");
  }

}
