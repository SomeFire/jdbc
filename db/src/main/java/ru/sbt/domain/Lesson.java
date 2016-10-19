package ru.sbt.domain;

public class Lesson {
	private final int id;
	private final String date;
	private final String object;

	public Lesson(int id, String date, String object) {
		this.id = id;
		this.date = date;
		this.object = object;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getObject() {
		return object;
	}

	@Override
	public String toString() {
		return "Lesson{" +
				"id=" + id +
				", date='" + date + '\'' +
				", object='" + object + '\'' +
				'}';
	}
}
