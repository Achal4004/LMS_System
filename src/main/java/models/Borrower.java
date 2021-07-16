package models;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Borrower",schema="public")
public class Borrower {
	
    @Id
    @Column(name="borrower_id")
    public int Borrower_Id;
    
    @Column(name="book_id")
    public int Book_Id;
    
    @Column(name="borrowed_from")
    public Date Borrowed_From;
    
    @Column(name="borrowed_tO")
    public Date Borrowed_TO;
    
    @Column(name="actual_return_date")
    public Date Actual_Return_Date;

	public int getBorrower_Id() {
		return Borrower_Id;
	}

	public void setBorrower_Id(int borrower_Id) {
		Borrower_Id = borrower_Id;
	}

	public int getBook_Id() {
		return Book_Id;
	}

	public void setBook_Id(int book_Id) {
		Book_Id = book_Id;
	}

	public Date getBorrowed_From() {
		return Borrowed_From;
	}

	public void setBorrowed_From(Date borrowed_From) {
		Borrowed_From = borrowed_From;
	}

	public Date getBorrowed_TO() {
		return Borrowed_TO;
	}

	public void setBorrowed_TO(Date borrowed_TO) {
		Borrowed_TO = borrowed_TO;
	}

	public Date getActual_Return_Date() {
		return Actual_Return_Date;
	}

	public void setActual_Return_Date(Date actual_Return_Date) {
		Actual_Return_Date = actual_Return_Date;
	}
	

	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Borrower(int borrower_Id, int book_Id) {
		super();
		this.Borrower_Id = borrower_Id;
		this.Book_Id = book_Id;
		this.Borrowed_From = new Date();
		this.Borrowed_TO = new Date();
		this.Actual_Return_Date = new Date();
	}
    
	

}
