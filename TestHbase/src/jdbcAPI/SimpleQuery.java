package jdbcAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.hbql.client.HBqlException;

/*@authors Mohamed MEDARHRI
 * 
 */

public class SimpleQuery {

	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		Class.forName("org.apache.hadoop.hbase.jdbc.Driver");

        // Get a connection with an HTablePool size of 10
        Connection conn = DriverManager.getConnection("jdbc:hbql;maxtablerefs=10");

        // or
        Connection conn2 = DriverManager.getConnection("jdbc:hbql;maxtablerefs=10;hbase.master=192.168.1.90:60000");

        // or if you want to connect with a HBaseConfiguration object, then you would call:
        HBaseConfiguration config = new HBaseConfiguration();
        Connection conn3 = org.apache.hadoop.hbase.jdbc.Driver.getConnection("jdbc:hbql;maxtablerefs=10", config);

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE table12 (f1(), f3()) IF NOT tableexists('table12')");

        stmt.execute("CREATE TEMP MAPPING sch9 FOR TABLE table12"
                     + "("
                     + "keyval key, "
                     + "f1 ("
                     + "    val1 string alias val1, "
                     + "    val2 string alias val2 "
                     + "), "
                     + "f3 ("
                     + "    val1 int alias val5, "
                     + "    val2 int alias val6 "
                     + "))");

        ResultSet rs = stmt.executeQuery("select * from sch9");

        while (rs.next()) {
            int val5 = rs.getInt("val5");
            int val6 = rs.getInt("val6");
            String val1 = rs.getString("val1");
            String val2 = rs.getString("val2");

            System.out.print("val5: " + val5);
            System.out.print(", val6: " + val6);
            System.out.print(", val1: " + val1);
            System.out.println(", val2: " + val2);
        }

        rs.close();

     //   stmt.execute("DISABLE TABLE table12");
      //  stmt.execute("DROP TABLE table12");
        stmt.close();

        conn.close();

	}
}
