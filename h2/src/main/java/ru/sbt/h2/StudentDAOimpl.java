package ru.sbt.h2;

import ru.sbt.db.StudentDAO;
import ru.sbt.domain.Lesson;
import ru.sbt.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOimpl implements StudentDAO {

	private final Connection connection;

	public StudentDAOimpl(Connection connection) {
		this.connection = connection;
	}

	public Student findById(int id) throws SQLException {
		PreparedStatement s = connection.prepareStatement(
				"SELECT * FROM students " +
				"WHERE id = ?");
		s.setInt(1, id);
		ResultSet rs = s.executeQuery();
		if (rs.first())
			return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname"));
		return null;
	}

	public List<Student> findByName(String name) throws SQLException {
		PreparedStatement s = connection.prepareStatement(
				"SELECT * FROM students " +
				"WHERE name = ?");
		s.setString(1, name);
		ResultSet rs = s.executeQuery();
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname")));
		}
		return list;
	}

	public List<Student> findBySurname(String surname) throws SQLException {
		PreparedStatement s = connection.prepareStatement(
				"SELECT * FROM students " +
				"WHERE name = ?");
		s.setString(1, surname);
		ResultSet rs = s.executeQuery();
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname")));
		}
		return list;
	}

	public List<Lesson> findLessonsByDate(String date) throws SQLException {
		PreparedStatement s = connection.prepareStatement(
				"SELECT lessons.id, lessons.object, lessons.date " +
				"FROM lessons as l, visits as v, students as s " +
				"WHERE s.id = v.student_id AND v.lesson_id = l.id AND l.date = ?");
		s.setString(1, date);
		ResultSet rs = s.executeQuery();
		List<Lesson> list = new ArrayList<>();
		while (rs.next()) {
			list.add(new Lesson(rs.getInt("id"), rs.getString("date"), rs.getString("object")));
		}
		return list;
	}

	public boolean add(Student s) throws SQLException {
		PreparedStatement st = connection.prepareStatement(
				"INSERT INTO students VALUES (?, ?, ?);");
		st.setInt(1, s.getId());
		st.setString(2, s.getName());
		st.setString(2, s.getSurname());
		return st.execute();
	}
}
