import static spark.Spark.*;
import java.io.*;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import spark.Session;
import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class Project {



	public static void main(String[] args) {
           


	String head =
                "<html>\n" +
                "<body background='../img/back.png'>\n";
            String tail =
                "</body>\n" +
                "</html>\n";





        
       
		get("/project", (request, response) -> {
		
	           String mainPage = "";
 		
			
		 	
			try {
			    BufferedReader br = new BufferedReader(new FileReader("ProjectH.html"));
			    String line;
			    while ((line = br.readLine()) != null) {
			    	mainPage = mainPage + line + '\n';
			    }
			}   
			catch (IOException ex) {
			    System.out.println("Opps! There is an error: " + ex);
			}
		
			return mainPage;

		} );
		

                       
                  get("/project/:genre", (request, response) -> {

 			String mainPage = "<table border='2'>";
			String genre=request.params(":genre");




             Connection c = null; // set up a communication with the db
            
              try {
      		Class.forName("org.sqlite.JDBC");
    		 c = DriverManager.getConnection("jdbc:sqlite:database.db");
  
     
   		 System.out.println("Open database successfully");




                         Statement stmt = null;           
                                                                                               
    			stmt = c.createStatement(); 
			String cmd = "SELECT * FROM BOOK WHERE category='"+genre+"';";
			 ResultSet r = stmt.executeQuery(cmd);

			String rs="";
			String img="";
			String bookid = "";
			




	while(r.next()){  

    	rs=r.getString("name");
	img=r.getString("cover");
	bookid = r.getString("BID");
	mainPage=mainPage+"<tr><td><a href='/BookDetails/"+bookid+"'>"+rs+"</a></td><td><a href='/BookDetails/"+bookid+"'><img src='/img/"+img+"' 		height='100' width='100'></a></td></tr>";
	System.out.println(rs);
	}

	mainPage=mainPage+"</table>";



	     r.close();
	     stmt.close();
	     c.close();





             	  } catch ( Exception e ) {
     		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		System.out.println("Open database not successful");
     		System.exit(0);
              			 }



			return head+mainPage+tail;
			
		


		});






	      post("/search", (request, response) -> {
	      String bname = request.queryParams("search");
	      String mainPage = "<table border='2'>";
              Connection c = null; // set up a communication with the db
            
              try {
      		 Class.forName("org.sqlite.JDBC");
    		 c = DriverManager.getConnection("jdbc:sqlite:database.db");
  		System.out.println(bname);
   		 System.out.println("Open database successfully");
                 Statement stmt = null;           
                                         	                                             
    		stmt = c.createStatement(); 
		String cmd = "SELECT * FROM BOOK WHERE name LIKE '"+ bname +"';";
		ResultSet r = stmt.executeQuery(cmd);

		
			String rs="";
			String img="";
			String bookid = "";

			
		
		int count = 0;
		while(r.next()){  
			count ++;
    			mainPage= mainPage + "<tr><th>Book ID</th><th>Name</th><th>Author</th><th>Status</th><th>Cover</th><th>Description</th><th>Category</th></tr><tr><td>" + r.getString("BID")+"</td><td>"+r.getString("name")+"</td><td>"+r.getString("author")+"</td><td>"+r.getString("status")+"</td><td><img src='/img/"+r.getString("cover")+"' height='100' width='100'/></td><td>"+r.getString("description")+"</td><td>"+r.getString("category")+"</td></tr>";
			System.out.println(rs);
		}
	     if ( count == 0 ) {
		mainPage = "<P>Hi There,<br> The name of the book you entered does not match any book name in the library.<br> Please check the book name 			and try again.<br> Thank you.</P>";
		}
		else {
	    	 mainPage=mainPage+"</table>";
		
		}
	     r.close();
	     stmt.close();
	     c.close();
		
                 } catch ( Exception e ) {
      		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    		System.out.println("Open database not successful");
      		System.exit(0);
               }
                
              
          	return head+mainPage+tail;
		
	
 		     		
		});


		get("/search", (request, response) -> {

 		String mainPage = "<table border='2'>";
			

		
   		System.out.println("1111");



			return head+mainPage+tail;
			
		


			});


			get("/img/:file", (request, response) -> {

			String file = request.params(":file");
			file = "img/"+file;
			byte[] bytes = Files.readAllBytes(Paths.get(file));         
			HttpServletResponse raw = response.raw();

			raw.getOutputStream().write(bytes);
			raw.getOutputStream().flush();
			raw.getOutputStream().close();


			return response.raw();


			});


                 get("/BookDetails/:id", (request, response) -> {

			

                        String mainPage = "<table border = '1'>";
			String id = request.params(":id");




             Connection c = null; // set up a communication with the db
            
              try {
      		Class.forName("org.sqlite.JDBC");
    		 c = DriverManager.getConnection("jdbc:sqlite:database.db");
  
     
   		 System.out.println("Open database successfully");




                         Statement stmt = null;           
                                                                                               
    			stmt = c.createStatement(); 
			String cmd = "SELECT * FROM BOOK WHERE BID='"+id+"';";
			 ResultSet r = stmt.executeQuery(cmd);
				








			mainPage= mainPage + "<tr><th>Book ID</th><th>Name</th><th>Author</th><th>Status</th><th>Cover</th><th>Description</		th><th>Category</th></tr><tr><td>" + r.getString("BID")+"</td><td>"+r.getString("name")+"</td><td>"+r.getString("author")+"</td><td>"+r.getString("status")+"</td><td><img src='/img/"+r.getString("cover")+"' height='100' width='100'/></td><td>"+r.getString("description")+"</td><td>"+r.getString("category")+"</td></tr>";
	
				mainPage=mainPage+"</table>"+"<P>IF you wish to reserve this book, click on Go To The Reservation page button.</P> 					"+"<button onclick='location.href='detail/"+r.getString("BID")+"'>Go To The Reservation Page</button>";


				 r.close();
	    			 stmt.close();
	    			 c.close();


     			    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		   
			       }




			return head + mainPage + tail;


			});



			}
			}
           


		
