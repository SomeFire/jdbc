package ru.sbt.db;

import ru.sbt.domain.Lesson;
import ru.sbt.domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

	Student findById(int id) throws SQLException;

	List<Student> findByName(String name) throws SQLException;

	List<Student> findBySurname(String surname) throws SQLException;

	List<Lesson> findLessonsByDate(String date) throws SQLException;

	/**
	 *
	 * @param s
	 * @return true if student was added, false if anything went wrong.
	 */
	boolean add(Student s) throws SQLException;
}
