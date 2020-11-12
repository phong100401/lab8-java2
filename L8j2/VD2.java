package L8j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class VD2 {
    public static void main(String[] args) throws SQLException{
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/ebookshop","root","");
                Statement stmt = conn.createStatement();
                ){
            try{
                //Disable auto - commit
                conn.setAutoCommit(false);

                //Issue two Insert statments
                stmt.executeUpdate("insert  into books values (6000,'Paul chan','Mahjong 101',4.4, 4)");
                //Duplicate primary key, which triggers a SQLException
                stmt.executeUpdate("insert into books values (6100, 'Peter Chan','Mahjong 102',4.4, 4)");
                conn.commit(); //Commit changes wonly if all statments succeed.
            }catch (SQLException ex){
                System.out.println("--Rolling back changes --");
                conn.rollback(); //Rollback to the last commit.
                ex.printStackTrace();
            }
        }
    }
}
