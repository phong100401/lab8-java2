package L8j2;

import java.sql.*;

public class VD3 {
    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/ebookshop","root","");
            Statement stmt = conn.createStatement();
            //Step 3 4: Execute query and process query result
            ResultSet rset = stmt.executeQuery("select * from books");
            //Get the metadata of the ResultSet
            ResultSetMetaData rsetMD = rset.getMetaData();
            //Get the number of column from metadata
            int numcolumn = rsetMD.getColumnCount();

            //Print column names - column Index begins at 1 (instead of 0)
            for(int i = 1; i<=numcolumn;++i){
                System.out.printf("%-30s","("+rsetMD.getColumnClassName(i)+")");

            }
            System.out.println();

            //Print column contents for all the rows
            while (rset.next()){
                for(int i=1;i<=numcolumn;++i){
                    //getString() can be used for all column types
                    System.out.printf("%-30s",rset.getString(i));
                }
                System.out.println();
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
