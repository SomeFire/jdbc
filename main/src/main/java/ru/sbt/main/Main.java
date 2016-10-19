package ru.sbt.main;

import ru.sbt.db.StudentDAO;
import ru.sbt.h2.StudentDAOimpl;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Main {
	public static void main(String[] args) {
		dropTables();
		try (Connection connection = getConnection("jdbc:h2:~/test", "admin", "secret")) {
			createDB(connection);
			insertStudents(connection);
			insertLessons(connection);
			insertVisits(connection);

			StudentDAO s = new StudentDAOimpl(connection);
			System.out.println(s.findById(1));
			System.out.println(s.findByName("Alex"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createDB(Connection c) throws SQLException {
		c.prepareStatement("CREATE TABLE students (" +
				"id int," +
				"name varchar2(20)," +
				"surname varchar2(20)," +
				"PRIMARY KEY (id)" +
				");").execute();

		c.prepareStatement("CREATE TABLE lessons (" +
				"id int," +
				"date varchar2(20)," +
				"object varchar2(20)," +
				"PRIMARY KEY (id)" +
				");").execute();

		c.prepareStatement("CREATE TABLE visits (" +
				"student_id int," +
				"lesson_id int," +
				"FOREIGN KEY (student_id) REFERENCES students(id)," +
				"FOREIGN KEY (lesson_id) REFERENCES lessons(id)" +
				");").execute();
	}

	private static void insertStudents(Connection c) throws SQLException {
		c.prepareStatement("INSERT INTO students VALUES (1, 'Alex', 'First');").executeUpdate();
		c.prepareStatement("INSERT INTO students VALUES (2, 'Alex', 'Second');").execute();
		c.prepareStatement("INSERT INTO students VALUES (3, 'Tom', 'Third');").execute();
		c.prepareStatement("INSERT INTO students VALUES (4, 'Jerry', 'Fourth');").execute();
		c.prepareStatement("INSERT INTO students VALUES (5, 'Bob', 'Fifth');").execute();
		c.prepareStatement("INSERT INTO students VALUES (6, 'Katy', 'Sixth');").execute();
		c.prepareStatement("INSERT INTO students VALUES (7, 'Jane', 'Seventh');").execute();
	}

	private static void insertLessons(Connection c) throws SQLException {
		c.prepareStatement("INSERT INTO lessons VALUES (1, '01.09', 'Java');").execute();
		c.prepareStatement("INSERT INTO lessons VALUES (2, '01.09', 'C++');").execute();
		c.prepareStatement("INSERT INTO lessons VALUES (3, '02.09', 'Java');").execute();
		c.prepareStatement("INSERT INTO lessons VALUES (4, '02.09', 'Python');").execute();
		c.prepareStatement("INSERT INTO lessons VALUES (5, '02.09', 'C++');").execute();
	}

	private static void insertVisits(Connection c) throws SQLException {
		c.prepareStatement("INSERT INTO visits VALUES (1, 1);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (1, 2);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (1, 3);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (1, 4);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (1, 5);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (2, 1);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (2, 2);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (2, 3);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (2, 4);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (3, 1);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (3, 2);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (3, 3);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (3, 4);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (3, 5);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (4, 3);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (4, 4);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (4, 5);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (5, 1);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (5, 2);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (6, 1);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (6, 2);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (6, 5);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (7, 3);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (7, 4);").execute();
		c.prepareStatement("INSERT INTO visits VALUES (7, 5);").execute();
	}

	private static void dropTables() {
		try (Connection connection = getConnection("jdbc:h2:~/test", "admin", "secret")) {
			connection.prepareStatement("DROP TABLE students;" +
					"DROP TABLE lessons;" +
					"DROP TABLE visits;")
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
