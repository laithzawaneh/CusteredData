package bean;

public class CurrencyEntity {

	private int idCurrency;
	private String Currency;

	public CurrencyEntity() {
		super();
	}

	public CurrencyEntity(String currency) {
		Currency = currency;
	}

	public int getIdCurrency() {
		return idCurrency;
	}

	public void setIdCurrency(int idCurrency) {
		this.idCurrency = idCurrency;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	@Override
	public String toString() {
		return "CurrencyEntity [idCurrency=" + idCurrency + ", Currency=" + Currency + "]";
	}

}
