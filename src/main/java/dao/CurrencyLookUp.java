package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class CurrencyLookUp {

	private Database db = null;
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public List<String> selectAllCurrencyCode() {
		List<String> currencyCodeList = new ArrayList<String>();
		try {
			db = new Database();
			connection = db.getConnection();
			ps = connection.prepareStatement("select currency from currency");
			rs = ps.executeQuery();
			while (rs.next()) {
				currencyCodeList.add(rs.getString("currency"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(rs);
			Database.close(ps);
			Database.close(connection);
		}

		return currencyCodeList;
	}
}
