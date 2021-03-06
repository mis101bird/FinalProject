import java.sql.*;

public class BookReserve{

public static String error="<!DOCTYPE html><html>"+
  "<head>"+
  " <link href='../css/bootstrap.min.css' rel='stylesheet'>"+
  "</head>"+
  "<body background='../img/back.png'>"+
    "<br><br><br><div class='container'>"+
    "<h2>Booking Details</h2>"+
    "<div class='panel panel-info'>"+
    "<div class='panel-heading'>Status of Book</div>"+
    "<div class='panel-body' id='status'><p><span class='glyphicon glyphicon-user'></span>  If you want to book, Please log in first!</p></div>"+
    "</div></body></html>";

  
  public static String loadBook(String status, String borrower , ResultSet re ,int bid){
  String word="<!DOCTYPE html>"+
"<html><head>"+
   "<link href='../css/bootstrap.min.css' rel='stylesheet'>"+
  "</head><body background='../img/back.png'>"+
    "<br><br><br><div class='container'>"+
      "<h2>Booking Details</h2>"+
	 "<div class='panel panel-info'>"+
	 "<div class='panel-heading'>Status of Book</div>"+
      "<div class='panel-body' id='status'>"+status+" : "+borrower+" already borrows.</div>"+
    "</div><h2>Who is waiting for this book?</h2>"+                                                                                     
      "<div class='table-responsive'>"+         
      "<table class='table'>"+
       "<thead>"+
          "<tr>"+
           "<th>Order</th>"+
            "<th>Name</th>"+
         "</tr>"+
       " </thead> <tbody id='wait'>";
    int i=1;
    try{
    while(re.next()){  
    String n=re.getString("name");
    word+= "<tr>"+
           "<th>"+i+"</th>"+                                                                                                                                                                          
            "<th>"+n+"</th>"+
         "</tr>";
    i++;
    }
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   
    word+="</tbody>"+
     " </table>"+
     " </div></div>"+
   " <div class='btn-group btn-group-justified'>"+
	"<div class='btn-group'>";
    if(status.equals("free")){
     word+="<a href='../loadbook/"+bid+"'><button type='button' id='b' class='btn btn-default'>Book now</button></a>"+
	"</div>"+
	"<div class='btn-group'>"+
        "<button type='button' id='r' class='btn btn-default' disabled >Reserve now</button>"+
	"</div>";    
    }else{
     word+="<a href='../loadbook/"+bid+"'><button type='button' id='b' class='btn btn-default' disabled >Book now</button></a>"+                                                                                       
        "</div>"+
        "<div class='btn-group'>"+                                                                                                                                                                    
        "<a href='../loadreserve/"+bid+"'><button type='button' id='r' class='btn btn-default'>Reserve now</button></a>"+                                                                                    
        "</div>";  
    }            
   word+="</div></body></html>";    
   return word;       
  }
  static public String getError(){
  return error;
  }
}
