import java.util.Date;

public class Item 
{
	private String checkoutCode;
	private String itemName;
	private String modelNumber;
	private String serialNumber;
	private String decalNumber;
	private String cost;
	private String borrowerName;
	private boolean isIn;
	private Date dueDate;
	
	public Item(String checkoutCode, String itemName, String modelNumber,
			String serialNumber, String decalNumber, String cost)
	{
		this.checkoutCode = checkoutCode;
		this.itemName = itemName;
		this.modelNumber = modelNumber;
		this.serialNumber = serialNumber;
		this.decalNumber = decalNumber;
		this.cost = cost;
		this.borrowerName = "";
		isIn = true;
	}

	public String getCheckoutCode() {
		return checkoutCode;
	}

	public void setCheckoutCode(String checkoutCode) {
		this.checkoutCode = checkoutCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDecalNumber() {
		return decalNumber;
	}

	public void setDecalNumber(String decalNumber) {
		this.decalNumber = decalNumber;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public void setBorrowerName(String name)
	{
		this.borrowerName = name;
	}
	
	public String getBorrowerName()
	{
		return this.borrowerName;
	}
	
	public boolean isItemIn()
	{
		return this.isIn;
	}
	
	
	public Date getDueDate()
	{
		return this.dueDate;
	}
	
	public void checkingOut(String name, Date dueDate, String other)
	{
		this.borrowerName = name;
		this.dueDate = dueDate;
		this.isIn = false;
	}
	
	
}
