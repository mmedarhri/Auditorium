package jdbcAPI;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.PooledConnection;

import org.apache.hadoop.hbase.jdbc.ConnectionPool;

/*@authors Mohamed MEDARHRI
 * 
 */

public class JDBCConnectionPool {

	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		 // For each connection in a connection pool, assign an HTablePool max size of 25 references per table
        ConnectionPool.setMaxPoolReferencesPerTablePerConnection(25);

        // Create connection pool with max of 25 connections and prime it with 5 initial connections
        ConnectionPool pool = new ConnectionPool(5, 25);

        PooledConnection pooledConnection = pool.getPooledConnection();
        Connection conn = pooledConnection.getConnection();

        // Do some work with connection

        // Release connection back to pool
        conn.close();


	
	}
}
