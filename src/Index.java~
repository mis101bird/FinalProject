import java.sql.*;
import java.util.ArrayList;

public class Index{

String error="<!DOCTYPE html><html>"+
  "<head>"+
  " <link href='css/bootstrap.min.css' rel='stylesheet'>"+
  "</head>"+
  "<body background='img/back.png'>"+
    "<div class='container'>"+
    "<h2>Booking Details</h2>"+
    "<div class='panel panel-info'>"+
    "<div class='panel-heading'>Status of Book</div>"+
    "<div class='panel-body' id='status'><p><span class='glyphicon glyphicon-user'></span>  If you want to book, Please log in first!</p></div>"+
    "</div></body></html>";

  
  public static String afterLogin(ResultSet bo , ResultSet re){
  String word="<html><head>"+
  " <link href='../css/bootstrap.min.css' rel='stylesheet'></head>"+
 " <body background='../img/back.png'>"+
  "  <div class='container'>"+
     " <h2>My Books Info</h2>"+
	" <div class='panel panel-danger'>"+
" <div class='panel-heading'>Books which you aren't returned</div>"+
     " <div class='panel-body' id='status'>"+
" <div class='table-responsive'>"+
      "<table class='table'>"+
       " <thead><tr>"+
          " <th>Book's Name</th>"+
          "</tr></thead>"+
"<tbody>";
    try{
    while(bo.next()){  
   String n=bo.getString("name");
   int b=bo.getInt("BID");
   System.out.println("bo book name:"+n);
    word+= "<tr>"+                                                                                                                                                        "<td><a href='return/"+b+"'><button type='button' class='btn btn-danger'>return</button></a>"+n+"</td>"+
         "</tr>";
    }
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   
    word+="</tbody>"+
     " </table>"+
     " </div> </div></div>"+
	 " <div class='panel panel-danger'>"+
	" <div class='panel-heading'>Books which you reserve</div>"+
     " <div class='panel-body' id='status'>"+
	 " <div class='table-responsive'>"+          
     " <table class='table'>"+
       " <thead>"+
         " <tr><th>Book's Name</th>"+
         " </tr></thead>"+
       " <tbody>";
    try{
    while(re.next()){  
  String n=re.getString("name");
   System.out.println("re book name:"+n);
     word+= "<tr>"+                                                                                                                                                                   
            "<td>"+n+"</td>"+
         "</tr>";
    }
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   
    word+="</tbody>"+
    "</div></table>"+
      "</div></div>"+
	 "</div><form class='navbar-form navbar-left' action='search' method='POST' >"+
"<div class='form-group'>"+
"<input type='text' class='form-control' placeholder='Search Book' name='search'></div>"+
"<button type='submit' class='btn btn-default'>Submit</button></form>"+
"<a href='project'><button type='button' class='btn btn-default navbar-btn'>Surf Books</button></a>";
  
   return word;       
  }
public static String admin(ResultSet user , ResultSet book , ResultSet admin){
ArrayList<String> administ =null;
try{
administ = Index.toArray(admin);
}catch(Exception e){
System.out.println("Index Admin Array:" +e.getMessage());
}
String word="<!DOCTYPE html><html><head><link href='css/bootstrap.min.css' rel='stylesheet'><script src='admin.js'></script></head>"+
  "<body background='img/back.png' >"+
    "<div class='container'>"+
      "<br><br><br><br><h2>Administration</h2>"+
	 "<div class='panel panel-warning'>"+
	 "<div class='panel-heading'><span class='glyphicon glyphicon-cog'></span>Account</div>"+
      "<div class='panel-body' id='status'>"+
	  "<div class='table-responsive'>"+          
      "<table class='table'>"+
        "<thead><tr>"+
            "<th>Name</th><th>Password</th><th>Email</th><th>Option</th>"+
          "</tr></thead><tbody>";
    try{
    while(user.next()){
    boolean ad=false;
    String n=user.getString("name");
    String pw=user.getString("password");
    String e=user.getString("email");
    String id=user.getString("UID");
    for(String aa : administ){
    if(id.equals(aa)){
     ad=true;
     break;
    }
    }
    if(ad){
word+= "<tr id='a"+id+"'>"+
           "<td>"+n  +"</td>"+                                                                                                                                                                          
            "<td>"+pw+"</td>"+"<td>"+e+"</td>"+"<td id='ao"+id+"'><a href='javascript:deleteAccount("+id+");' class='btn btn-primary btn-warning'>Delete</a><span class='glyphicon glyphicon-star'></span></td>"+
         "</tr>";
    }else{
    word+= "<tr id='a"+id+"'>"+
           "<td>"+n  +"</td>"+                                                                                                                                                                          
            "<td>"+pw+"</td>"+"<td>"+e+"</td>"+"<td id='ao"+id+"'><a href='javascript:deleteAccount("+id+");' class='btn btn-primary btn-warning'>Delete</a>"+
            "<a href='javascript:Admin("+id+");' class='btn btn-primary btn-danger'>Admin</a></td>"+
         "</tr>";
    }
    }
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
    }   
 word+="</tbody></table></div></div></div>"+
	  "<div class='panel panel-success'><div class='panel-heading'><span class='glyphicon glyphicon-bookmark'></span>Books</div>"+
      "<div class='panel-body' id='status'>"+
	  "<div class='table-responsive'>"+
      "<table class='table'>"+
        "<thead><tr>"+
            "<th>Category</th><th>Book's Name</th><th>Option</th>"+
          "</tr></thead><tbody>";
    try{
    while(book.next()){  
    String n=book.getString("name");
    String c=book.getString("category");
    String id=book.getString("BID");
    word+= "<tr id='b"+id+"'>"+
           "<td>"+c +"</td>"+                                                                                                                                                                
            "<td>"+n+"</td>"+"<td><a href='javascript:deleteBook("+id+");' class='btn btn-primary btn-success'>Delete</a></td>"+
         "</tr>";
   
    }
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage());
    }
   word+="</tbody></div></table>"+
      "</div></div>"+
	  "</div></body>"+
"</html>";
return word;
}
static ArrayList<String> toArray(ResultSet rs) throws Exception{
    ArrayList<String> al = new ArrayList<String>();
    while (rs.next()) {
                    String value = rs.getString("UID");
                    al.add(value);
            }
           
            
    return al;
}
}

