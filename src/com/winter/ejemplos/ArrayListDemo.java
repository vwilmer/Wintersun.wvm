package com.winter.ejemplos;

import java.util.ArrayList;

public class ArrayListDemo {

    public static void arrayListDemo() {
        // Create an ArrayList to hold some names.
        //ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> nameList = new ArrayList<>();
        // Add some names to the ArrayList.
        nameList.add("James");
        nameList.add("Catherine");
        nameList.add("Bill");
        // Display the items in nameList and their indices.
        for (int index = 0; index < nameList.size(); index++) {
            System.out.println("index: " + index + " name: " + nameList.get(index));
        }
        // Now insert an item at index 1.
        nameList.add(1, "Mary");
        System.out.println("Mary was added at index 1. Here are the items now.");
        // Display the items in nameList and their indices.
        for (int index = 0; index < nameList.size(); index++) {
            System.out.println("index: " + index + " name: " + nameList.get(index));
        }
    }

    public static void main(String[] args) {
        arrayListDemo();
    }
}
