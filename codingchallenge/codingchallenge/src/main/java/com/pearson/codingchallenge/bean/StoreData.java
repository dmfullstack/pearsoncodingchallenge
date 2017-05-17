package com.pearson.codingchallenge.bean;

import java.util.Date;

/**
 * This class holds store structure retrieved from stores data csv file
 * 
 * @author swaonkar
 *
 */
public class StoreData {
	private String storeId;
	private String postCode;
	private String city;
	private String address;
	private Date openedDate;
	private int openedDays;
	
	public StoreData(String storeId, String postCode, String city, String address, Date openedDate,int openedDays) {
		super();
		this.storeId = storeId;
		this.postCode = postCode;
		this.city = city;
		this.address = address;
		this.openedDate = openedDate;
		this.openedDays = openedDays;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public int getOpenedDays() {
		return openedDays;
	}

	public void setOpenedDays(int openedDays) {
		this.openedDays = openedDays;
	}
}
