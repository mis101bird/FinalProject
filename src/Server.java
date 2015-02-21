import static spark.Spark.get;
import static spark.Spark.post;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static spark.Spark.externalStaticFileLocation;
import spark.Session;

public class Server {

    private static final String ERROR_PAGE=
        "<html>" +
        "<head><title>ERROR PAGE</title></head>" +
        "<body> <h1>Error</h1> %s </body>" +
        "</html>";


    public static void main(String[] args) {

       externalStaticFileLocation("static");

     
   }
}
