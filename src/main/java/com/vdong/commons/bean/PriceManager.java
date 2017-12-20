package com.vdong.commons.bean;

import java.io.Serializable;

public class PriceManager implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PriceManager() {
	}
//	buyNumMax":"1","priceMarket":"1","buyNumMin":"1","price":"1","djbprice":"1","priceSettlement":"1","priceRetail":"1","status":"1","payType":"1"
	private String priceRetail;   // 度假币限制
	private String djbprice;    //度假币价格
	private String price;       // 指导价格
	private String stock;     //库存
	private String status;    //状态【生效状态】
	private String buyNumMin;  // 最打购买量
	private String buyNumMax;   // 最大购买量
	private String priceSettlement;  //结算价
 	private String paytype;  // 付款方式
	private String priceMarket;  //  门市价格
	private String date;  //当前时间
    private String roomId;  //房型ID
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPriceRetail() {
		return priceRetail;
	}

	public void setPriceRetail(String priceRetail) {
		this.priceRetail = priceRetail;
	}

	public String getDjbprice() {
		return djbprice;
	}

	public void setDjbprice(String djbprice) {
		this.djbprice = djbprice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyNumMin() {
		return buyNumMin;
	}

	public void setBuyNumMin(String buyNumMin) {
		this.buyNumMin = buyNumMin;
	}

	public String getPriceSettlement() {
		return priceSettlement;
	}

	public void setPriceSettlement(String priceSettlement) {
		this.priceSettlement = priceSettlement;
	}


	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPriceMarket() {
		return priceMarket;
	}

	public void setPriceMarket(String priceMarket) {
		this.priceMarket = priceMarket;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBuyNumMax() {
		return buyNumMax;
	}

	public void setBuyNumMax(String buyNumMax) {
		this.buyNumMax = buyNumMax;
	}

}
