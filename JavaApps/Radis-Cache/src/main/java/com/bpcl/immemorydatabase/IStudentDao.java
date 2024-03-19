package com.bpcl.immemorydatabase;

import java.util.Map;

import com.bpcl.dto.Student;

public interface IStudentDao {

	void addStudent(Student student);

	void modifyStudent(Student student);

	Student getOneStudent(String id);

	Map<String, Student> getAllStudents();

	void removeStudent(String id);
}
