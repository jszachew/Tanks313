package Tanks313;

import java.sql.*;

public class DBConnect {

    public Connection con;
    public  Statement st;
    public ResultSet rs;
    private static int nQuery=0;
    //private Controller controller;

    public DBConnect ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanks313","root","");
            st = con.createStatement();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR: "+ ex);
        }
    }

    public ResultSet getData(String table)
    {
        try
        {
            String query = "select * from "+ table;
            rs= st.executeQuery(query);
            nQuery++;
            System.out.println(nQuery + " query made");
            return rs;
        }
        catch (Exception ex)
        {
            System.out.println("err:" + ex);
        }
        return rs;
    }

    public void addResult(int Points) throws SQLException {
       String query=  "INSERT INTO Stats (Points) VALUES (\"" +Points +"\");";
       System.out.println(query);
       addToDB(query);
    }


    private void addToDB(String query) throws SQLException
    {
        st.executeUpdate(query);
    }

}
