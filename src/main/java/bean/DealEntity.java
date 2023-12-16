package bean;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
public class DealEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dealUniqueId;
	private String fromCurrency;
	private String toCurrency;
	private String timestamp;
	private double dealAmount;
	

	public DealEntity() {
		super();
	}
	
	

	public DealEntity(String dealUniqueId, String fromCurrency, String toCurrency, String timestamp,
			double dealAmount) {
		this.dealUniqueId = dealUniqueId;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.timestamp = timestamp;
		this.dealAmount = dealAmount;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDealUniqueId() {
		return dealUniqueId;
	}

	public void setDealUniqueId(String dealUniqueId) {
		this.dealUniqueId = dealUniqueId;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}

	@Override
	public String toString() {
		return "DealEntity [id=" + id + ", dealUniqueId=" + dealUniqueId + ", fromCurrency=" + fromCurrency
				+ ", toCurrency=" + toCurrency + ", timestamp=" + timestamp + ", dealAmount=" + dealAmount + "]";
	}

}
