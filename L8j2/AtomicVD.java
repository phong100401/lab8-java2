package L8j2;

import java.sql.*;

public class AtomicVD {
    public static void main(String[] args) {
    try{
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/ebookshop","root","");
        Statement stmt = conn.createStatement();
        //step 3,4 Execute queries and process the query results
        //Disable auto-commit for the connection, which commits every SQL statement
        conn.setAutoCommit(false);

        //Before changes
//        String strQuery = "select id, qty from books where id in(1001,1002)";
//        System.out.println();
//        ResultSet rset = stmt.executeQuery(strQuery);
        ResultSet rset = stmt.executeQuery("select  id, qty from books where id in (1001,1002)");
        System.out.println("-- Before UPDATE --");
        while (rset.next()){
            System.out.println(rset.getInt("id")+", "+rset.getInt("qty"));
        }
        conn.commit(); //Commit SELECT

        //Issue two UPDATE statements thru executeUpdate()
        stmt.executeUpdate("update books set qty = qty + 1 where id = 1001");
        stmt.executeUpdate("update books set qty = qty + 1 where id = 1002");
        conn.commit(); //Commit Update

        rset = stmt.executeQuery("select  id, qty from books where id in(1001,1002)");
        System.out.println("-- After UPDATE and Commit --");
        while (rset.next()){
            System.out.println(rset.getInt("id")+", "+rset.getInt("qty"));
        }
        conn.commit(); //Commit SELECT

        //Issue two update statments thru exucuteUpdate()
        stmt.executeUpdate("update books set qty = qty - 99 where id = 1001");
        stmt.executeUpdate("update books set qty = qty - 99 where id = 1002");
        conn.rollback(); //Discard all changes since the last commit

        rset = stmt.executeQuery("select id, qty from books where id in (1001, 1002)");
        System.out.println("-- After Update and Rollback --");
        while (rset.next()){
            System.out.println(rset.getInt("id") +", "+rset.getInt("qty"));
        }
        conn.commit(); //Commit SELECT

    }catch(SQLException ex) {
        ex.printStackTrace();
    }
}
}
