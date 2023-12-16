package mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.text.AbstractDocument.BranchElement;

import bean.DealEntity;
import dao.CurrencyLookUp;
import dao.DealDAO;
import util.Message;

@ManagedBean
@RequestScoped
public class MbDeal {

	private DealEntity dealEntity;
	private List<DealEntity> dealEntityOldRecords;
	private DealEntity dealEntityOldReq;
	private CurrencyLookUp currencyLookUp;
	private List<String> currencyCodes;
	private DealDAO dealDAO;

	@PostConstruct
	public void init() {
		dealEntity = new DealEntity();
		dealEntityOldReq = new DealEntity();
		dealDAO = new DealDAO();
		dealEntityOldRecords = dealDAO.selectAll();

		currencyLookUp = new CurrencyLookUp();
		currencyCodes = currencyLookUp.selectAllCurrencyCode();
	}
//_______________For Testing__________________	
//	if (checkIfFromCurrencyNotNull() == false) {
//	System.out.println("from currency is null");
////	Message.addMessage("ERROR", "Missing Data", "from currency is null");
//} else if (checkIfToCurrencyNotNull() == false) {
//	System.out.println("to currency is null");
////	Message.addMessage("ERROR", "Missing Data", "to currency is null");
//} else if (IfAmountNotNull() == false) {
//	System.out.println("amount is null or equal zero");
////	Message.addMessage("ERROR", "Missing Data", "amount is null or equal zero");
//} else {
//	dealDAO.insert(dealEntity);
//	Message.addMessage("INFO", "Deal Added", "Program Added Successfully");
//}
//System.out.println("checkFromCurrencyNotNull -> " + checkIfFromCurrencyNotNull());
//System.out.println("checkToCurrencyNotNull -> " + checkIfToCurrencyNotNull());
//System.out.println("amountNotNull -> " + IfAmountNotNull());

	public String save() {
		dealDAO = new DealDAO();
		if (dealEntity.getFromCurrency() == null && dealEntity.getToCurrency() == null
				&& dealEntity.getDealAmount() == 0) {
			System.out.println("dealEntity is null");
			Message.addMessage("ERROR", "Missing Data", "Your deal is empty");

		} else if (dealEntity.getFromCurrency() == null || dealEntity.getToCurrency() == null
				|| dealEntity.getDealAmount() == 0) {
			System.out.println("some Fields is null");
			Message.addMessage("ERROR", "Missing Data", "Some Fields empty");
			if (!checkIfFromCurrencyNotNull()) {
				System.out.println("From currency is null");
				Message.addMessage("ERROR", "Missing Data", "from currency is null");
			}
			if (!checkIfToCurrencyNotNull()) {
				System.out.println("To currency is null");
				Message.addMessage("ERROR", "Missing Data", "to currency is null");
			}
			if (!IfAmountNotNull()) {
				System.out.println("Amount is null or equal zero");
				Message.addMessage("ERROR", "Missing Data", "amount is null or equal zero");
			}

		} else {

			try {

				if (!checkIfDealExistBefor()) {
					System.out.println("The same deal is exist");
					Message.addMessage("ERROR", "Repeated", "The same deal is exist");
				}

				if (checkIfFromCurrencyNotNull() && checkIfToCurrencyNotNull() && IfAmountNotNull()
						&& checkIfDealExistBefor()) {
					dealDAO.insert(dealEntity);
					Message.addMessage("INFO", "Deal Added", "Program Added Successfully");
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
				Message.addMessage("ERROR", "Error", "Backend Error");
			}
		}
		return null;
	}

	public boolean checkIfDealExistBefor() {
		int length = dealEntityOldRecords.size();
		int i = 0;
		while (i < length) {
			dealEntityOldReq = dealDAO.selectAll().get(i);
//			____________for testing_________
//			System.out.println(dealEntity.getFromCurrency() + " " + dealEntityOldReq.getFromCurrency());
			if (dealEntity.getFromCurrency().equals(dealEntityOldReq.getFromCurrency())
					&& dealEntity.getToCurrency().equals(dealEntityOldReq.getToCurrency())
					&& dealEntity.getDealAmount() == dealEntityOldReq.getDealAmount()) {
				return false;
			}
			i++;
		}
		return true;

	}

	public boolean checkIfFromCurrencyNotNull() {
		return dealEntity.getFromCurrency() != null;
	}

	public boolean checkIfToCurrencyNotNull() {
		return dealEntity.getToCurrency() != null;
	}

	public boolean IfAmountNotNull() {
		return dealEntity.getDealAmount() != 0 && String.valueOf(dealEntity.getDealAmount()) != null;
	}

	public List<String> getCurrencyCodes() {
		return currencyCodes;
	}

	public void setCurrencyCodes(List<String> currencyCodes) {
		this.currencyCodes = currencyCodes;
	}

	public DealEntity getDealEntity() {
		return dealEntity;
	}

	public void setDealEntity(DealEntity dealEntity) {
		this.dealEntity = dealEntity;
	}

	public CurrencyLookUp getCurrencyLookUp() {
		return currencyLookUp;
	}

	public void setCurrencyLookUp(CurrencyLookUp currencyLookUp) {
		this.currencyLookUp = currencyLookUp;
	}

	public List<DealEntity> getDealEntityOldRecords() {
		return dealEntityOldRecords;
	}

	public void setDealEntityOldRecords(List<DealEntity> dealEntityOldRecords) {
		this.dealEntityOldRecords = dealEntityOldRecords;
	}

	public DealEntity getDealEntityOldReq() {
		return dealEntityOldReq;
	}

	public void setDealEntityOldReq(DealEntity dealEntityOldReq) {
		this.dealEntityOldReq = dealEntityOldReq;
	}

	public DealDAO getDealDAO() {
		return dealDAO;
	}

	public void setDealDAO(DealDAO dealDAO) {
		this.dealDAO = dealDAO;
	}

}
