import javax.mail.PasswordAuthentication;
import java.util.*;
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

public class Server {

    private static final String ERROR_PAGE=
        "<html>" +
        "<head><title>ERROR PAGE</title></head>" +
        "<body> <h1>Error</h1> %s </body>" +
        "</html>";


    public static void main(String[] args) {

       externalStaticFileLocation("static");
       
       get("/", (request, response) -> {
       response.redirect("/login.html");
       return null;
       });
       get("/index", (request, response) -> {
        String user = request.session().attribute("user");     
        if (user==null) {
                Session sess = request.session(true);
                sess.attribute("user", "1");
                response.redirect("/index.html");
                return null;
            }
   ResultSet re=null;
   int uid = Integer.parseInt(request.session().attribute("user"));
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
    Class.forName("org.sqlite.JDBC");
    stmt = c.createStatement();
    String sql = "SELECT A1.name name FROM BOOK A1, BORROW A2 WHERE A1.BID = A2.BID AND A2.UID = '"+uid+"';";
    bo = stmt.executeQuery(sql);
    String sql2 = "SELECT A1.name name FROM BOOK A1, RESERVE A2 WHERE A1.BID = A2.BID AND A2.UID = '"+uid+"';";
    re = stmt.executeQuery(sql2);
    word=Index.afterLogin(bo,re);
     bo.close(); 
     re.close(); 
     stmt.close();
     c.close();
   }catch(Exception e){
   System.out.println("In bookdetail database wrong: "+e.getMessage());
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
    mes="Please check your mail.";
    System.out.println("show old message successfully");
    if(sendMail(pw,email)){
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

             return mes;  

       });
       
    get("loadbook/:bid", (request, response) -> {
             System.out.println("go loadbook successfully");
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
