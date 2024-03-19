package com.bpcl.immemorydatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bpcl.dto.Student;

//@Component
public class RadisOprTest implements CommandLineRunner {

	@Autowired
	private IStudentDao studentDao;

	@Override
	public void run(String... args) throws Exception {
		studentDao.addStudent(new Student("101", "praveen", "praveen.e@ctel.in"));
		studentDao.addStudent(new Student("102", "ram", "ram.e@ctel.in"));
		studentDao.addStudent(new Student("103", "james", "james.e@ctel.in"));

		studentDao.getAllStudents().forEach((k, v) -> System.out.println(k + "----" + v.toString()));
	}
}
