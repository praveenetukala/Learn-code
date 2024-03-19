package com.demo.util;

import com.demo.bean.SendData;

public class TestTheMap {

	public static void main(String[] args) throws CloneNotSupportedException {

		SendData originalObject = new SendData();
		SendData clonedObject = (SendData) originalObject.clone();

		System.out.println(clonedObject);

	}
}