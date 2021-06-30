package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="b_id")
	public Long b_id;
	
	@Column(name="b_title")
	public String b_title;
	
	@Column(name="b_author")
	public String b_author;
	
	@Column(name="b_copies")
	public int b_copies;


	public Long getB_id() {
		return b_id;
	}

	public void setB_id(Long b_id) {
		this.b_id = b_id;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_author() {
		return b_author;
	}

	public void setB_author(String b_author) {
		this.b_author = b_author;
	}

	public int getB_copies() {
		return b_copies;
	}

	public void setB_copies(int b_copies) {
		this.b_copies = b_copies;
	}
	public Book() {
		
	}
	public Book( String b_title, String b_author, int b_copies) {
		super();
		this.b_title = b_title;
		this.b_author = b_author;
		this.b_copies = b_copies;
	}

	

	
}
