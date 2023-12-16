package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import bean.DealEntity;
import database.Database;

public class DealDAO {
	private Database db = null;
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private int row = 0;
//	List<DealEntity> dealEntityTable;

	public static String generateUniqueId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 8);
	}

	public static Timestamp timeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	public List<DealEntity> selectAll() {
		List<DealEntity> dealEntityTable = new ArrayList<>();

		try {
			db = new Database();
			connection = db.getConnection();
			ps = connection
					.prepareStatement("select from_currency_iso_code,to_currency_iso_code,deal_amount from fx_deals order by deal_id");
			rs = ps.executeQuery();

			while (rs.next()) {
				DealEntity dealEntity = new DealEntity();
				dealEntity.setFromCurrency(rs.getString("from_currency_iso_code"));
				dealEntity.setToCurrency(rs.getString("to_currency_iso_code"));
				dealEntity.setDealAmount(rs.getDouble("deal_amount"));

				dealEntityTable.add(dealEntity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(rs);
			Database.close(ps);
			Database.close(connection);
		}
		return dealEntityTable;
	}

	private long selectMaxId() {
		try {
			db = new Database();
			connection = db.getConnection();
			ps = connection.prepareStatement("SELECT IFNULL(MAX(deal_id), 0) AS DEAL_ID FROM fx_deals");
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("deal_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
			Database.close(rs);
			Database.close(ps);
			Database.close(connection);
		}

		return 0;

	}

	public int insert(DealEntity dealEntity) {
		long maxId = selectMaxId();
		try {
			db = new Database();
			connection = db.getConnection();
			ps = connection.prepareStatement(
					"insert into fx_deals(deal_id,deal_unique_id,from_currency_iso_code,to_currency_iso_code,deal_timestamp,deal_amount) values(?,?,?,?,?,?)");
			int counter = 1;
			ps.setLong(counter++, maxId + 1);
			ps.setString(counter++, generateUniqueId());
			ps.setString(counter++, dealEntity.getFromCurrency());
			ps.setString(counter++, dealEntity.getToCurrency());
			ps.setTimestamp(counter++, timeStamp());
			ps.setDouble(counter++, dealEntity.getDealAmount());

			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(ps);
			Database.close(connection);
		}

		return row;

	}
}
