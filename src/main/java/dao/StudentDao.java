package dao;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import models.Student;
import models.StudentsDto;
import ninja.jpa.UnitOfWork;

public class StudentDao {
	@Inject
	Provider<EntityManager> entitiyManagerProvider;
	public static PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);

	@ElementCollection
	java.util.ListIterator<Student> student;

	@UnitOfWork
	public StudentsDto ShowAllstudents() {
		EntityManager entityManager = entitiyManagerProvider.get();
		TypedQuery<Student> q = entityManager.createQuery("SELECT x FROM Student x", Student.class);
		List<Student> students = q.getResultList();
		StudentsDto studentsDto = new StudentsDto();
		studentsDto.students = students;
		return studentsDto;

	}


}
