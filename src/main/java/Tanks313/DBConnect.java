package Tanks313;

import java.net.InetAddress;
import java.sql.*;

/*
* By this class we can get acces to our scores
* MySQL database.
* Server- localhost (easy to change)
* Database- tanks313
 */

/*
* ______________________________________
*|IdStats| Name | Points | Level | Date |
*|---------------------------------------
*|       |      |        |       |      |
 */

public class DBConnect {

    public Connection con;
    public Statement st;
    public ResultSet rs;

/*
* By counting queries we can avoid DDoS attacks
 */
    private static int nQuery=0;

/*
* Player name is the player's computer name
*/
    private String computerName;


/*
* Constructors only try get connection with
* our database
 */
    public DBConnect ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanks313","root","");
            st = con.createStatement();
            computerName= InetAddress.getLocalHost().getHostName();
            System.out.println(computerName);
        }
        catch (Exception ex)
        {
            System.out.println("ERROR: "+ ex);
        }
    }

    /*
    * At the end of game main App class
    * call addResult with game restult.
    * Name of player is set in constructor class
    * and it is the computer name
    * Date is added by database trigger to avoid time zones
     */

    public void addResult(int Points, int Level) throws SQLException {

       String query=  "INSERT INTO Stats (Name,Points, Level) VALUES ("+ "\"" + computerName+ "\"," +Points + "," +Level +  ");";
       System.out.println(query);
       addToDB(query);
    }

/*
* It is hard to make SQL injectrion.
* Just in one case it is possibile, but I don't
* tell you when! ;-)
 */
    private void addToDB(String query) throws SQLException
    {
        st.executeUpdate(query);
    }

}
