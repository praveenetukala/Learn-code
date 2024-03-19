package com.bpcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dao.CaluclatorDao;

@Service
public class CaluclatorService {

	@Autowired
	private CaluclatorDao caluclatorDao;

	public Integer add(Integer a, Integer b) {
		return a + b;
	}

	public boolean isPalindrome(String str) {

		String reverse = "";
		int length = str.length();

		for (int i = length - 1; i >= 0; i--) {
			reverse = reverse + str.charAt(i);
		}
		if (str.equals(reverse)) {
			return true;
		}
		return false;
	}

	public static Integer convertToInt(String str) {

		if (str == null || str.trim().length() == 0) {
			throw new IllegalArgumentException("String must be not null or empty");
		}
		return Integer.parseInt(str);
	}

	public String getNameById() {
		System.out.println("CaluclatorService.getNameById()");
		return caluclatorDao.getNameById();
	}

	public String getMailById() {
		return caluclatorDao.getMailById();
	}
}
