import java.sql.*;

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
    int i=1;
    try{
    while(bo.next()){  
    String n=bo.getString("name");
    word+= "<tr>"+                                                                                                                                                                   
            "<td>"+n+"</td>"+
         "</tr>";
    i++;
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
    i=1;
    try{
    while(re.next()){  
    String n=re.getString("name");
    word+= "<tr>"+                                                                                                                                                                   
            "<td>"+n+"</td>"+
         "</tr>";
    i++;
    }
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   
    word+="</tbody>"+
    "</div></table>"+
      "</div></div>"+
	 "</div></body></html>";
  
   return word;       
  }
/*  static public String getError(){
  return error;
  }*/
}
