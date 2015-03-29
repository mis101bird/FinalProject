import javax.mail.PasswordAuthentication;
import java.util.*;
import java.io.*;
import javax.mail.internet.*;
import javax.activation.*;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.externalStaticFileLocation;
import spark.Session;
import java.sql.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.Date;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Server {

    private static final String ERROR_PAGE=
        "<html>" +
        "<head><title>ERROR PAGE</title></head>" +
        "<body> <h1>Error</h1> %s </body>" +
        "</html>";
   

    public static void main(String[] args) {
       
       	String head =
                "<html>\n" +
                "<body background='../img/back.png'>\n";
            String tail =
                "</body>\n" +
                "</html>\n";

       externalStaticFileLocation("static");
       
       get("/", (request, response) -> {
       response.redirect("/login.html");
       return null;
       });
       get("/index", (request, response) -> {
        String user = request.session().attribute("user");     
        if (user==null) {
               // Session sess = request.session(true);
               // sess.attribute("user", "1");
                response.redirect("/index.html");
                return null;
            }
   ResultSet re=null;
   int uid = Integer.parseInt(request.session().attribute("user"));
   System.out.println("In user: "+uid);
   ResultSet bo=null; 
   Connection c = null;
   String word="";
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:database.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }   
    System.out.println("Indexlog: Open database successfully");
    try{
    Statement stmt = null;
    Statement stmt2=null;
    Class.forName("org.sqlite.JDBC");
    stmt = c.createStatement();
    stmt2 = c.createStatement();
    String sql = "SELECT A1.name name FROM BOOK A1,BORROW A2 WHERE A1.BID = A2.BID AND A2.UID ='"+uid+"';";
    bo = stmt.executeQuery(sql);

    String sql2 = "SELECT A1.name name FROM BOOK A1,RESERVE A2 WHERE A1.BID = A2.BID AND A2.UID = '"+uid+"';";
    re = stmt2.executeQuery(sql2);
    word=Index.afterLogin(bo,re);
     bo.close(); 
     re.close(); 
     stmt.close();
    stmt2.close();
     c.close();
   }catch(Exception e){
   System.out.println("In indexlog database wrong: "+e.getMessage());
   }
        return word;

        });
       post("/forget", (request, response) -> {
           String pw="";
           String mes="We cannot find your info. Please submit again." ;
           String email=request.queryParams("email");
           System.out.println("go forget successfully:email="+email);
             Connection c = null;

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:database.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );                                                      
      System.exit(0);
    }                                                                                                                            
    System.out.println("forget: Open database successfully");                                                                          
    try{
    Statement stmt = null;           
    Class.forName("org.sqlite.JDBC");                                                                                            
    stmt = c.createStatement();                                                                          
    String sql = "SELECT * FROM ACCOUNT WHERE email='"+email+"';";
    ResultSet r = stmt.executeQuery(sql);
    if(r.next()){
    pw=r.getString("password");
    mes="Here is your password: "+pw ;
    System.out.println("show old message successfully");
   /* if(sendMail(pw,email)){
    System.out.println("send mail successfully");
    }else{
    System.out.println("send mail wrong");
    }*/    
    }
     r.close();
     stmt.close();
     c.close();
                                                                                                       
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   

             return mes;  

       });
       
    get("loadbook/:bid", (request, response) -> {
             System.out.println("go loadbook successfully");
             int bid = Integer.parseInt( request.params(":bid") );
             Date date = new Date();
             int uid = Integer.parseInt(request.session().attribute("user"));
             Connection c = null;

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:database.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );                                                      
      System.exit(0); 
    }                                                                                                                            
    System.out.println("loadbook: Open database successfully");                                                                          
    try{
    Statement stmt = null;    
    Class.forName("org.sqlite.JDBC");                                                                                            
    stmt = c.createStatement();                                                                          
    stmt.executeUpdate(SQLoperation.insertBORROW(uid,bid,date.toString()));
    System.out.println("insert BORROW successfully");
     stmt.close();
     c.commit();
     c.close();
                                                                                                       
    }catch( Exception e ){
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   
    response.redirect("/");
    return null;
    });
    get("loadreserve/:bid", (request, response) -> {
              System.out.println("go loadserver successfully");
             int bid = Integer.parseInt( request.params(":bid") );
             Date date = new Date();
             int uid = Integer.parseInt(request.session().attribute("uid"));
             Connection c = null;

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:database.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );                                                      
      System.exit(0); 
    }                                                                                                                            
    System.out.println("loadreserver: Open database successfully");                                                                          
    try{
    Statement stmt = null;    
    Class.forName("org.sqlite.JDBC");                                                                                            
    stmt = c.createStatement();                                                                          
    stmt.executeUpdate(SQLoperation.insertRESERVE(uid,bid));
    System.out.println("insert RESERVE successfully");
     stmt.close();
     c.commit();
     c.close();
                                                                                                       
    }catch( Exception e ){
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   
    response.redirect("/");
    return null;
   
    });
   
   get("/detail/:bid", (request, response) -> {
   if( request.session().attribute("user") != null ){
   int bid = Integer.parseInt( request.params(":bid") );
   String status="";
   String borrower="no one";
   Connection c = null;
   String word=null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:database.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("detail: Open database successfully");
    try{
    Statement stmt = null;
    Class.forName("org.sqlite.JDBC");
    stmt = c.createStatement();
    String sql = "SELECT * FROM BOOK WHERE BID='"+bid+"';";
    ResultSet re2 = stmt.executeQuery(sql);
    if(re2.next()){
    status=re2.getString("status");
    }
    sql = "SELECT A1.name NAME FROM ACCOUNT A1, BORROW A2 WHERE A1.UID = A2.UID AND A2.BID = '"+bid+"';"; 
    ResultSet re1 = stmt.executeQuery(sql);
    if(re1.next()){
    borrower=re1.getString("NAME");
    }
    sql = "SELECT A1.* FROM ACCOUNT A1, RESERVE A2 WHERE A1.UID = A2.UID AND A2.BID = '"+bid+"';";   
    ResultSet re = stmt.executeQuery(sql);  
    word=BookReserve.loadBook( status ,borrower , re , bid);
     stmt.close();
     c.close();
     return word;
   }catch(Exception e){
   System.out.println("In bookdetail database wrong: "+e.getMessage());
   return null;
   }
}else{
   return BookReserve.getError();
   }
});
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

 			String mainPage = "<br/><br/><br/><br/><br/><br/><table border='2'>";
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
	      String mainPage = "<br/><br/><br/><br/><br/><table border='2'>";
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
		mainPage = "<br><br><br><br><br><P>Hi There,<br> The name of the book you entered does not match any book name in the library.<br> Please check the book name 			and try again.<br> Thank you.</P>";
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

			

                        String mainPage = "<br/><br/><br/><br/><br/><table border = '1'>";
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
	
				mainPage=mainPage+"</table>"+"<P>IF you wish to reserve this book, click on Go To The Reservation page button.</P> 					"+"<a href='../detail/"+id+"'><button>Go To The Reservation Page</button></a>";


				 r.close();
	    			 stmt.close();
	    			 c.close();


     			    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		   
			       }




			return head + mainPage + tail;


			});
}
static public boolean sendMail(String pw , String m){
// Recipient's email ID needs to be mentioned.

      String to = m;

      // Sender's email ID needs to be mentioned
      String from = "onlinelibrary@server.com";
      // Assuming you are sending email from localhost
      String host = "mail.smtp.localhost";
      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.port", "465");
      // Get the default Session object.
      javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
         // Set Subject: header field
         message.setSubject("[Auto] Password from OnlineLibrary");
         // Now set the actual message
         message.setText("Your password is "+pw);

         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect("smtp.gmail.com", "mis101bird", "abcd4723ccc");
         transport.send(message);
         System.out.println("Sent message successfully....");
         return true;
      }catch (MessagingException mex) {
         mex.printStackTrace();
         return false;
      }
}
}
