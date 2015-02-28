import static spark.Spark.get;
import static spark.Spark.post;
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
     
   }
}
