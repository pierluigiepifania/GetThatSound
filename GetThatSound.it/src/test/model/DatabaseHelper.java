package test.model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

import it.storage.DriverManagerConnectionPool;

public class DatabaseHelper {

	public static void initializeDatabase() throws SQLException {
		DriverManagerConnectionPool.setTest(true);
		Connection conn = DriverManagerConnectionPool.getConnection();

		try {
	        IDatabaseConnection connection = new DatabaseConnection(conn);
	        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
	        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
	        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("db_init.xml"));
	        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

		} catch (Exception e) {
			System.err.println("Assicurati che il server sia spento e nessun altro stia usando il db\n"+"The error is " + e.getMessage());
		}

		conn.commit();

		String resetUtente = "ALTER TABLE utente AUTO_INCREMENT=1;";
		String resetBrano = "ALTER TABLE brano AUTO_INCREMENT=1;";
		String resetSalvato = "ALTER TABLE brano_salvato AUTO_INCREMENT=1;";
		String resetStallo = "ALTER TABLE brano_stallo AUTO_INCREMENT=1;";

		conn = DriverManagerConnectionPool.getConnection();

		PreparedStatement stm1 = conn.prepareStatement(resetUtente);
		stm1.execute();

		PreparedStatement stm2 = conn.prepareStatement(resetBrano);
		stm2.execute();

		PreparedStatement stm3 = conn.prepareStatement(resetSalvato);
		stm3.execute();

		PreparedStatement stm4 = conn.prepareStatement(resetStallo);
		stm4.execute();

		stm1.close();
		stm2.close();
		stm3.close();
		stm4.close();

		conn.commit();
		DriverManagerConnectionPool.releaseConnection(conn);
	}

}
