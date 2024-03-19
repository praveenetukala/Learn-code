package com.bpcl.immemorydatabase;

import java.util.Map;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import com.bpcl.dto.Student;
import jakarta.annotation.Resource;

@Service
public class IStudentDaoImpl implements IStudentDao {

	private String KEY = "STUDENT";

	@Resource(name = "rt")
	private HashOperations<String, String, Student> opr;

	@Override
	public void addStudent(Student student) {
		opr.putIfAbsent(KEY, student.getId(), student);
	}

	@Override
	public void modifyStudent(Student student) {

		opr.put(KEY, student.getId(), student);
	}

	@Override
	public Student getOneStudent(String id) {
		return opr.get(KEY, id);
	}

	@Override
	public Map<String, Student> getAllStudents() {
		return opr.entries(KEY);
	}

	@Override
	public void removeStudent(String id) {
		opr.delete(KEY, id);
	}
}
