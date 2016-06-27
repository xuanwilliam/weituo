package stock.db;

/**
 *需要抛出异常的地方，两处
 *1、29
 *2、40
 */
import java.sql.DriverManager;
import java.sql.SQLException;

import stock.constants.ConstantStrings;
import base.db.impl.BaseDatabaseUtil;

public class DatabaseUtil extends BaseDatabaseUtil {
	static DatabaseUtil dbo;

	@Override
	public void getConnected() throws ClassNotFoundException, SQLException {
		Class.forName(ConstantStrings.DRIVER);
		// conn = DriverManager.getConnection(ConstantStrings.SQLURL
		// + ConstantStrings.DATABASENAME, ConstantStrings.USERNAME,
		// ConstantStrings.PASSWORD);

		conn = DriverManager.getConnection(ConstantStrings.SQLURL,
				ConstantStrings.USERNAME, ConstantStrings.PASSWORD);
	}

	private DatabaseUtil() {
		// if (Config.entityTableMap.isEmpty()) {
		// Config.initMap();
		// }
	}

	public static DatabaseUtil getInstance() {
		if (dbo == null) {
			dbo = new DatabaseUtil();
		}
		return dbo;
	}

}
