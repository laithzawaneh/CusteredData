package database;

import java.sql.SQLException;

import bean.DealEntity;
import dao.CurrencyLookUp;
import dao.DealDAO;

public class DatabaseConnectionTest {
	public static void main(String[] args) {
		Database database = new Database();
		try {
			database.getConnection();
			System.out.println("success connection");
		} catch (SQLException e) {
			System.out.println("failed connection");
			e.printStackTrace();
		}

		CurrencyLookUp currencyLookUp = new CurrencyLookUp();
		System.out.println(currencyLookUp.selectAllCurrencyCode());

		DealDAO dao = new DealDAO();
		DealEntity dealEntity = new DealEntity();
//		dealEntity.setId((long) 1);
		dealEntity.setDealAmount(10);
		dealEntity.setFromCurrency("JOD");
		dealEntity.setToCurrency("USD");
//		dealEntity.setTimestamp("2023-12-15 21:06:27.289");
//		dealEntity.setDealUniqueId("250ac77c");

	    dao.insert(dealEntity);
		DealEntity dealEntity2 = new DealEntity();
		dealEntity2 = dao.selectAll().get(1);

		System.out.println(dealEntity2.getFromCurrency() + " : " + dealEntity2.getToCurrency() + " : "
				+ dealEntity2.getDealAmount());
	}

}
