import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.externalStaticFileLocation;
import spark.Session;
import java.sql.*;

public class Server {

    private static final String ERROR_PAGE=
        "<html>" +
        "<head><title>ERROR PAGE</title></head>" +
        "<body> <h1>Error</h1> %s </body>" +
        "</html>";


    public static void main(String[] args) {

       externalStaticFileLocation("static");

       get("/index", (request, response) -> {
        String user = request.session().attribute("user");     
        if (user==null) {
                Session sess = request.session(true);
                sess.attribute("user", "201461621");
                response.redirect("/index.html");
                return null;
            }
        response.redirect("/demo.html");
        return null;

        });
       post("/forget", (request, response) -> {
           String ps="";
           String n="We cannot find your info." ;
           String email=request.queryParams("email");
           System.out.println("go forget successfully");
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
    String sql = "SELECT * FROM NOTE WHERE email='"+email+"';";
    ResultSet r = stmt.executeQuery(sql);
    if(r.next()){
    pw=r.getString("password");
    n=r.getString("name");
    System.out.println("show old message successfully");
    if(sendMail(pw,mail)){
    System.out.println("send mail successfully");
    }else{
    System.out.println("send mail wrong");
    }    
    }
     r.close();
     stmt.close();
     c.close();
                                                                                                       
    }catch( Exception e ){
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }   

             return n;  

       });
       
   }
public boolean sendMail(String pw , String m){
      // Recipient's email ID needs to be mentioned.
      String to = m;
      // Sender's email ID needs to be mentioned
      String from = "@gmail.com";
      // Assuming you are sending email from localhost
      String host = "localhost";
      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);
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
         Transport.send(message);
         return true;

         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
         return false;
      }
}
}
