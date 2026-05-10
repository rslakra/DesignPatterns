package com.oreilly.patterns.chapter9;

public class ReceiptDTO {
	private float totalPrice;
	private String[] descriptions;
	private float[] prices;

	public ReceiptDTO(LocalItem[] items, LocalSalesRecord record) {
		descriptions = new String[items.length];
		prices = new float[items.length];

		for (int i = 0; i < items.length; i++) {
			descriptions[i] = items[i].getDescription();
			prices[i] = items[i].getPrice();
		}

		totalPrice = record.getTotalPrice();
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public int getItemCount() {
		return descriptions.length;
	}

	public String getItemDescription(int index) {
		return descriptions[index];
	}

	public float getItemPrice(int index) {
		return prices[index];
	}
}
