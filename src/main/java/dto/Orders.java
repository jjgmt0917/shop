package dto;

public class Orders {
	private int orderCode;
	private int goodscode;
	private int customerCode;
	private int addressCode;
	private int orderQuantity;
	private int orderPrice;	// 상품가격 * 수량
	private String orderState;
	private String createdate;
	@Override
	public String toString() {
		return "Orders [orderCode=" + orderCode + ", goodscode=" + goodscode + ", customerCode=" + customerCode
				+ ", addressCode=" + addressCode + ", orderQuantity=" + orderQuantity + ", orderPrice=" + orderPrice
				+ ", orderState=" + orderState + ", createdate=" + createdate + "]";
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public int getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(int goodscode) {
		this.goodscode = goodscode;
	}
	public int getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}
	public int getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
