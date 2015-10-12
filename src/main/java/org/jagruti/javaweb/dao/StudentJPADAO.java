package org.jagruti.javaweb.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.jagruti.javaweb.database.EMF;
import org.jagruti.javaweb.model.Student;

public class StudentJPADAO implements StudentDAO {

	String className = this.getClass().getSimpleName();

	public StudentJPADAO() {
		// TODO Auto-generated constructor stub
		System.out.println("StudentccJDODAO  666 --Constructor()");
		// System.out.println("Classname.Methodname"+className+"."+methodName);
	}

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		System.out.println(className + "." + methodName + "() ");
		student.setCreated(new Date());
		EntityManager pm = EMF.get().createEntityManager();
		try {
			// System.out.println("Classname.Methodname"+className+"."+methodName);
			pm.getTransaction().begin();
			pm.persist(student);
			pm.getTransaction().commit();

		} catch (Exception ex) {
			pm.getTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();

			return student;
		}
	}

	@Override
	public void removeStudent(long id) {
		// TODO Auto-generated method stub
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		System.out.println(className + "." + methodName + "() ");
		// Todo todo = em.find(Todo.class, id);
		// em.remove(todo);
		EntityManager pm = EMF.get().createEntityManager();
		try {
			 pm.getTransaction().begin();

			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			Student student = pm.find(Student.class, id); // student.getId());
			pm.remove(student);

			pm.getTransaction().commit();
		} catch (Exception ex) {
			 pm.getTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}

	}

	@Override
	public Student updateStudent(Student student) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		System.out.println(className + "." + methodName + "() ");

		EntityManager pm = EMF.get().createEntityManager();
		String name = student.getName();
		Date created = student.getCreated();

		try {
			 pm.getTransaction().begin();
			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			student = pm.find(Student.class, student.getId());
			student.setName(name);
			student.setCreated(created);
			pm.persist(student);
			 pm.getTransaction().commit();
		} catch (Exception ex) {
			pm.getTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();

			return student;
		}
	}

	// @SuppressWarnings("finally")
	@Override
	public List<Student> listStudents() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(className + "." + methodName + "() ");

		EntityManager pm = EMF.get().createEntityManager();
		String query = "";
		List<Student> ls = null;
		try {
			// TODO Auto-generated method stub
			// Query q = em.createQuery("select m from Todo m");
			// List<Todo> todos = q.getResultList();

			pm.getTransaction().begin();
			/*
			 * query = "select from " + Student.class.getName(); ls =
			 * (List<Student>) pm.newQuery(query).execute();
			 */
			query = "select from " + Student.class.getName();

			Query q = pm.createQuery(query);
			ls = q.getResultList();
			// sop is for lazy reading
			System.out.println(className + "." + methodName + "() list size is " + ls.size());
			pm.getTransaction().commit();
		} catch (Exception ex) {
			 pm.getTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			/// @SuppressWarnings("unchecked")
			pm.close();
			return ls;
		}

	}

}
