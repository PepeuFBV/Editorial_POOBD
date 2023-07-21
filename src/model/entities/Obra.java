package model.entities;

public class Obra implements Identify {
	private int id;
	private String title;
	private String gender;
	private int year;
	private Autor author;
	
	public Obra() {
	}

	public Obra(int id, String title, String gender, int year, Autor author) {
		this.id = id;
		this.title = title;
		this.gender = gender;
		this.year = year;
		this.author = author;
	}


	@Override
	public int getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public Autor getAuthor() {
		return author;
	}


	public void setAuthor(Autor author) {
		this.author = author;
	}
	
}
