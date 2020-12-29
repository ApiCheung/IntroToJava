package TestDB;



import java.sql.*;

/**
 * @author Esmee Zhang
 * @date 11/26/20 4:19 下午
 * @projectName IntroToJava-NYU
 */
public class Main {

    public static final String DB_NAME ="testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/esmeezhang/Documents/GitHub/IntroToJava-NYU/" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUNM_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";

    public static void main(String[] args) {
        try{
            //Class.forName("org.sqlite.JDBC");
           Connection conn = DriverManager.getConnection(CONNECTION_STRING);
           //not to autocommit
           // conn.setAutoCommit(false);
           Statement statment = conn.createStatement();

           statment.execute("DROP TABLE IS EXISTS" + TABLE_CONTACTS);

           statment.execute("CREATE TABLE IF NOT EXISTS" + TABLE_CONTACTS + "(" + COLUNM_NAME + " text, "
                           + COLUMN_PHONE + " integer," + COLUMN_EMAIL + "text" + ")");



           statment.execute("CREATE TABLE IF NOT EXISTS" +
                        " contacts(name TEXT, phone INTEGER, email TEXT)");

//           statment.execute("INSERT INTO contacts(name, phone, email)" +
//                   "VALUES('Jamee', 2239761, 'jamee@email.com')");
//
            //statment.execute("INSERT INTO contacts(name, phone, email)" +
            //       "VALUES('Joe', 221235121, 'joejoe@email.com')");

           // statment.execute("UPDATE contacts SET phone=82971 WHERE name = 'Jamee'" );
           // statment.execute("DELETE FROM contacts WHERE name = 'Joe'");

            //statment.execute("SELECT * FROM contacts");

            //只能又一个 actived resultset
            //ResultSet results = statment.getResultSet();
            //使用index要比名字更快

            ResultSet results = statment.executeQuery("SELECT * FROM contacts");
            while(results.next()){
                System.out.println(results.getString("name") + " " +
                        results.getInt("phone") + " " +
                        results.getString("email"));
            }
            //excute query 方法





           statment.close();
           conn.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
