package org.jagruti.javaweb.dao;

import java.util.List;
import org.jagruti.javaweb.model.Student;


public interface StudentDAO {
	Student addStudent(Student Student);
	void removeStudent(long id);
	Student updateStudent(Student Student);
	List<Student> listStudents();
}
