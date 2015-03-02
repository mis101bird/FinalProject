import java.sql.*;

public class SQLiteJDBC
{

  public static void main( String args[] )
  {

    Connection c = null;

    try {

      Class.forName("org.sqlite.JDBC");

      c = DriverManager.getConnection("jdbc:sqlite:database.db");

    } catch ( Exception e ) {

      System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      System.exit(0);

    }

    System.out.println("Create database successfully");

    Statement stmt = null;

    try {

      Class.forName("org.sqlite.JDBC");
      System.out.println("Opened database successfully");
      stmt = c.createStatement();
      String sql = "CREATE TABLE ACCOUNT" +
                   "(UID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                   " name           TEXT    NOT NULL, " + 
                   " password       TEXT     NOT NULL)"; 

      stmt.executeUpdate(sql);
      String sql1 = "CREATE TABLE BOOK" +                                                                           
                   "(BID INTEGER PRIMARY KEY  AUTOINCREMENT," +                                     
                   " name         TEXT    NOT NULL, " +                                                            
                   " author       TEXT     NOT NULL,"+
                   " status         TEXT    NOT NULL,"+
                   " cover           TEXT    NOT NULL,"+
                   " description     TEXT    NOT NULL,"+
                   " category        TEXT    NOT NULL"+
                   ")";  
      stmt.executeUpdate(sql1);
      String sql2 = "CREATE TABLE BORROW" +                                                                           
                   "(UID            INT     NOT NULL," + 
                   " BID            INT     NOT NULL, " +                                                             
                   " deadline       TEXT    NOT NULL," +
                   "FOREIGN KEY(UID) REFERENCES ACCOUNT(UID),"+
                   "FOREIGN KEY(BID) REFERENCES BOOK(BID)"+
                   ")";      
      stmt.executeUpdate(sql2);
      String sql3 = "CREATE TABLE RESERVE" +
                   "(UID            INT     NOT NULL," + 
                   " BID            INT     NOT NULL, " +                                                          
                   "FOREIGN KEY(UID) REFERENCES ACCOUNT(UID),"+                                                       
                   "FOREIGN KEY(BID) REFERENCES BOOK(BID)"+                                                           
                   ")"; 
      stmt.executeUpdate(sql3); 
      System.out.println("Table created successfully");
      //insert example
      stmt.executeUpdate(SQLoperation.insertBOOK( "First Frost", "Sarah Addison Allen", "free", "2767052.jpg", "From the New York Times bestselling author of Garden Spells comes a story of the Waverley family, in a novel as sparkling as the first dusting of frost on new-fallen leaves...", "Friction"));



stmt.executeUpdate(SQLoperation.insertBOOK( "The War that saved my life", "Kimberly Brubaker Bradley", "free", "20912424.jpg", "An exceptionally moving story of triumph against all odds set during World War 2, from the acclaimed author of Jefferson Sons and for fans of Number the Stars.", "Children"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Draw what you see", "Kathleen Benson Haskins", "free", "20256580.jpg" , "Benny Andrews loved to draw. He drew his nine brothers and sisters, and his parents. He drew the red earth of the fields where they all worked, the hot sun that beat down, and the rows and rows of crops. As Benny hauled buckets of water, he made pictures in his head. And he dreamed of a better lifes something beyond the segregation, the backbreaking labor, and the limited opportunities of his world.Bennys dreams took him far from the rural Georgia of his childhood. He became one of the most important African American painters of the twentieth century, and he opened doors for other artists of color. His story will inspire budding young artists to work hard and follow their dreams." , "Art"));



stmt.executeUpdate(SQLoperation.insertBOOK( "All Fall Down", "Ally Carter", "free", "22571275.jpg", "A new series of global proportions -- from master of intrigue, NEW YORK TIMES bestselling author Ally Carter." , "culture"));



stmt.executeUpdate(SQLoperation.insertBOOK( "My Beloved", "Eden Bulter", "free", "23497749.jpg", "Keira Riley was the girl Kona Hale loved first, the woman he wants to love last. Theyve battled addiction, forgiven betrayal and healed from heartache, coming through it all bruised but hopeful that their future will be limitless.", "Drama"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Hell hole", "Gina Damico", "free", "17997245.jpg", "There was a time when geeky, squeaky-clean Max Kilgore would never lie or steal or even think about murder. Then he accidentally unearths a devil, and Maxs choices are no longer his own. The big red guy has a penchant for couch surfing and junk food and you should never underestimate evil on a sugar high", "Humor"));



stmt.executeUpdate(SQLoperation.insertBOOK( "How to Hook a Bookworm", "Cassie Mae", "free", "18711456.jpg", "Getting kissed for a birthday present should not be a big deal. Especially for Brea Mason, who doesnt think of her best friend, Adam Silver, in any way other  well, a friend. But after the liplock sannot seem to get him off her mind. And se has to, because Adam is a senior while is stuck in high school for another two years." ," Humor"));



stmt.executeUpdate(SQLoperation.insertBOOK( "A Cold Legacy", "Megan Shephered", "free", "16182308.jpg", "After killing the men who tried to steal her fathers research, Julie along with Montgomery, Lucy, Balthazar, and a deathly ill Edwa has escaped to a remote estate on the Scottish moors. Owned by the enigmatic Elizabeth von Stein, the mansion is full of mysteries and unexplained oddities: dead bodies in the basement, secret passages, and fortune-tellers who seem to know Juliets secrets. Though it appears to be a safe haven, Juliet fears new dangers may be present within the manors own walls.", "Horror"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Fire Fight", "Brandon Sanderson", "free", "15704459.jpg", "From the #1 New York Times bestselling author of Words of Radiance coauthor of Robert Jordans Wheel of Time series, and creator of the internationally bestselling Mistborn Trilogy, Brandon Sanderson presents the second book in the Reckoners series: Firefight, the sequel to the #1 bestseller Steelheart.", "Friction"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Before I go", "Collen Oakley", "free", "20871704.jpg" , "A heart-wrenching debut novel in the bestselling tradition of P.S. I Love You about a young woman with breast cancer who undertakes a mission to find a new wife for her husband before she passes away.", "Classic"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Country Heaven", "Vicki Green", "free", "23166974.jpg", "Heaven is in the eyes of the beholder,To help others see that beauty,Makes the heavens open its loving arms", "Drama"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Frog", "Nic Bishob", "free", "2805987.jpg" , "For the first- to third-grade set, frogs are an endless source of fascination, especially when looked at VERY close up. See tiny poison dart frogs and mammoth bullfrogs, as Nic Bishops amazing images show the beauty and diversity of frogs from around the globe. And simple, engaging text conveys basic information about frogs -- as well as cool and quirky facts. Nic Bishop Frogs is a fun and informative tour through an exciting amphibian world."," Children"));





stmt.executeUpdate(SQLoperation.insertBOOK( "Well-read Women", "Samatha Hahn", "free", "17557474.jpg", "A treasure of a gift for the well-read woman, this collection brings together 50 stirring portraits, in watercolor and in word, of literatures most well-read female characters. Anna Karenina, Clarissa Dalloway, Daisy Buchanan...each seems to live on the page through celebrated artist Samantha Hahns evocative portraits and hand-lettered quotations, with the pairing of art and text capturing all the spirit of the character as she was originally written. The book itself evokes vintage grace reimagined for contemporary taste, with a cloth spine silkscreened in a graphic pattern, debossed cover, and pages that turn with the tactile satisfaction of watercolor paper. In the hand and in the reading, here is a new classic for the book lovers library."," Classic"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Culture Crash", "Scott Timberg", "free", "21945086.jpg", "Change is no stranger to us in the twenty-first century. We must constantly adjust to an evolving world, to transformation and innovation. But for many thousands of creative artists, a torrent of recent changes has made it all but impossible to earn a living. A persistent economic recession, social shifts, and technological change have combined to put our artists from graphic designers to indie-rock musicians, from architects to booksel leout of work. This important book looks deeply and broadly into the roots of the crisis of the creative class in America and tells us why it matters.", "Art"));



stmt.executeUpdate(SQLoperation.insertBOOK( "The girl on the Train", "Paula Hickins", "free", "22557272.jpg", "Rachel takes the same commuter train every morning. Every day she rattles down the track, flashes past a stretch of cozy suburban homes, and stops at the signal that allows her to daily watch the same couple breakfasting on their deck. Shes even started to feel like she knows the Jess and Jas she calls them. Their  as she se is perfect. Not unlike the life she recently lost.", "Friction"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Bottom Feeders", "Adam Cesare", "free", "24663142.jpg", "The Mississippi River is notorious for holding catfish pushing triple digit weights, flesh-eating alligator gar over two-hundred pounds, water moccasins, alligators, and even bull sharks. Top to bottom, its a river teeming with top-tier predators. But there is something else in the water, something far more ancient and evil, something that has been dormant for thousands of years. Now, as Jed Wilkes, a New Orleans casino baron, prepares for the grand opening of his greatest endeavor yet a floating casino on the Mississippi these primordial river monsters are just waking up. Faced with losing his lifetime dream, Jed must team up with his ex-wifes new husband, Louisianas greatest big-game hunter, to track down and kill off the river monsters before opening night.", "Horror"));



stmt.executeUpdate(SQLoperation.insertBOOK( "If I fall, If I die", "Micheal Christie", "free", "21462154.jpg", "Will has never been to the outside, at least not since he can remember. And he has certainly never gotten to know anyone other than his mother, a fiercely loving yet wildly eccentric agoraphobe who drowns in panic at the thought of opening the front door. Their little world comprises only the rooms in their home, each named for various exotic locales and filled with Wills art projects. Soon the confines of his world close in on Will. Despite his mothers protestations, Will ventures outside clad in a protective helmet and braces himself for danger. He eventually meets and befriends Jonah, a quiet boy who introduces Will to skateboarding. Will welcomes his new world with enthusiasm, his fears fading and his body hardening with each new bump, scrape, and fall. But life quickly gets complicated. When a local boy goes missing, Will and Jonah want to uncover what happened. They embark otraordinary adventure that pulls Will far from the confines of his closed-off world and into the throes of early adulthood and the dangers that everyday life offers. If I Fall, if I Die is a remarkable debut full of dazzling prose, unforgettable characters, and a poignant and heartfelt depiction of coming of age.", "Drama"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Seeds of Freedom", "Hester Bass", "free", "22822875.jpg", "Explore a little-known story of the Civil Rights movement, in which black and white citizens in one Alabama city worked together nonviolently to end segregation.", "culture"));



stmt.executeUpdate(SQLoperation.insertBOOK( "A fine Dessert","Emily Jenkins", "free", "14823980.jpg", "In this fascinating picture book, four families, in four different cities, over four centuries, make the same delicious dessert: blackberry fool. This richly detailed book ingeniously shows how food, technology, and even families have changed throughout American history.", "Culture"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Out cast", "Robert Kirkman", "free", "23043731.jpg", "IT WATCHES FROM THE DARKNESS In the second issue of KIRKMAN & AZACETAs horror epic, Kyle Barnes is still reeling from the revelations about demonic possession last issue and what they may mean about the time his mother was possessed.", "Horror"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Woven", "Micheal Jensen", "free", "17993765.jpg", "Two unlikely allies must journey across a kingdom in the hopes of thwarting death itself.All his life, Nels has wanted to be a knight of the kingdom of Avwrnand. Tall and strong, and with a knack for helping those in need, the people of his sleepy little village have even taken to calling him the Knight of Cobblestown.", "Adventure"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Diamond Boy", "Micheal Williams", "free", "20839546.jpg", "A high-stakes, harrowing adventure set in the diamond fields of Southern Africa, from the critically acclaimed author of Now Is the Time for Running.", "Culture"));



stmt.executeUpdate(SQLoperation.insertBOOK( "Classic Love Poems", "Ritchard Armitage", "free", "24893051.jpg" , "For anyone whos in love - or hopes to be - what greater celebration could there be than to hear the worlds greatest love poetry read lovingly by Richard Armitage? With 15 poems by William Shakespeare, Edgar Allan Poe,Elizabeth Barrett Browning, and more, Classic Love Poems is a listening treat for Valentines Day - or any day.", "Classic")); 
      stmt.close();
      c.close();

    } catch ( Exception e ) {

      System.err.println( e.getClass().getName() + ": " + e.getMessage() );

      System.exit(0);

    }
    System.out.println("All operation done successfully");
  }

}
