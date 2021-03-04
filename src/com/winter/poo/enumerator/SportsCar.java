package com.winter.poo.enumerator;

public class SportsCar {

	private CarType make; 
	private CarColor color;
	private double price;
	
	public SportsCar(CarType aMake, CarColor aColor, double aPrice) {
		make = aMake;
		color = aColor;
		price = aPrice;
	}
	
	public CarType getMake() {
		return make;
	}
	
	public CarColor getColor() {
		return color;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		// Create a string representing the object.
		String str = String.format("Make: %s\nColor: %s\nPrice: $%,.2f", make, color, price);
		return str;
	}

}
