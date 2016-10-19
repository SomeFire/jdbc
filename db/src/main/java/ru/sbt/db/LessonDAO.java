package ru.sbt.db;

import ru.sbt.domain.Lesson;
import ru.sbt.domain.Student;

import java.util.List;

public interface LessonDAO {

	Lesson findById(int id);

	List<Lesson> findByObject(String object);

	List<Student> findStudentsByDate(String date);

	/**
	 *
	 * @param l
	 * @return true if lesson was added, false if anything went wrong.
	 */
	boolean add(Lesson l);
}
