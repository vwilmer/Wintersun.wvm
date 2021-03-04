package com.winter.poo.enumerator;

public class SportsCarDemo {

    public static void main(String[] args) {
        // Create a SportsCar object.
        SportsCar yourNewCar = new SportsCar(CarType.PORSCHE, CarColor.RED, 100000);
        // Display the object's values.
        System.out.println(yourNewCar);
        // Get the car make and switch on it.
        switch (yourNewCar.getMake()) {
            case PORSCHE:
            case FERRARI:
                System.out.println("Your car was made in Germany.");
                break;
            default:
                System.out.println("I'm not sure where that car was made.");
        }
    }
}
