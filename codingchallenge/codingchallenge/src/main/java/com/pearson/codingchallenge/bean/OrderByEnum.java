package com.pearson.codingchallenge.bean;

/**
 * Enum to order stores by allowed store fields
 * @author swaonkar
 *
 */
public enum OrderByEnum {
	CITY("CITY"), OPENED_DATE("OPENED_DATE");

	private final String code;

	private OrderByEnum(String code) {
		this.code = code;
	}

	public static OrderByEnum getOrderByEnumFromCode(String code) {
		if (code == null) {
			return null;
		}
		for (OrderByEnum e : OrderByEnum.values()) {
			if (e.code.equals(code)) {
				return e;
			}
		}
		return null;

	}
}
