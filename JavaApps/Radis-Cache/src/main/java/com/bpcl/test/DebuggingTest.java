package com.bpcl.test;

public class DebuggingTest {

	public static void main(String[] args) {

		System.out.println("start");

		show();

		int a = 10;
		a = a++ + ++a - ++a + a++;

		if (a > 55) {
			System.out.println("valid");
		} else {
			System.out.println("invalid");
		}
		System.out.println("done");
	}

	public static void show() {

		System.out.println("From show method");
		int k = 8;
		k = k-- + ++k + k++;
		System.out.println("Data is " + k);
		System.out.println("About to exist");
	}
}
