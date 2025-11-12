package dto;

public class Cart {
	private int cartCode;
	private int goodsCode;
	private int customerCode;
	private int goodsQuantity;
	@Override
	public String toString() {
		return "Cart [cartCode=" + cartCode + ", goodsCode=" + goodsCode + ", customerCode=" + customerCode
				+ ", goodsQuantity=" + goodsQuantity + "]";
	}
	public int getCartCode() {
		return cartCode;
	}
	public void setCartCode(int cartCode) {
		this.cartCode = cartCode;
	}
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public int getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}
	public int getGoodsQuantity() {
		return goodsQuantity;
	}
	public void setGoodsQuantity(int goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	
}
