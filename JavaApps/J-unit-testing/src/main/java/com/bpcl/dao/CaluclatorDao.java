package com.bpcl.dao;

import org.springframework.stereotype.Service;

@Service
public class CaluclatorDao {

	public String getNameById() {
		System.out.println("CaluclatorDao.getNameById()");
		return "Praveen";
	}

	public String getMailById() {
		return "praveen.e@ctel.in";
	}

}
