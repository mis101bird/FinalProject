import java.sql.*;

public class SQLoperation {
 //INSERT
 public static String insertACCOUNT(String n,String p){
   System.out.println( "INSERT ACCOUNT:" );
   String sql = "INSERT INTO ACCOUNT (name,password) " +
                "VALUES ('"+n+"','"+p+"');";
   System.out.println( "name = " + n );
   System.out.println( "password = " + p );
   return sql;
 }

 public static String insertBOOK(String name,String author,String status,String cover,String description,String category){

   System.out.println( "INSERT BOOK:" );
   String sql = "INSERT INTO BOOK (name,author,status,cover,description,category) " +
                "VALUES ('"+name+"','"+author+"','"+status+"','"+cover+"','"+description+"','"+category+"');";
   System.out.println( "name = " + name );
   System.out.println( "author = " + author );
   System.out.println( "status = " + status );
   System.out.println( "cover = " + cover );  
   System.out.println( "des = " + description );
   System.out.println( "category = " + category );  
   return sql;
 }

 public static String insertBORROW(int uid,int bid,String deadline){
   System.out.println( "INSERT BORROW:" );
   String sql = "INSERT INTO BORROW (UID,BID,deadline) " +
                "VALUES ("+uid+","+bid+",'"+deadline+"');";
   System.out.println( "UID = " + uid );
   System.out.println( "BID = " + bid );
   System.out.println( "deadline = " + deadline );
   return sql;
 }

public static String insertRESERVE(int uid,int bid){                                            

   System.out.println( "INSERT RESERVE:" );                                                                            
   String sql = "INSERT INTO RESERVE (UID,BID,deadline) " +                                                            
                "VALUES ("+uid+","+bid+");";                                                           
   System.out.println( "UID = " + uid );                                                                              
   System.out.println( "BID = " + bid );                                                                   
   return sql; 
 }


}
