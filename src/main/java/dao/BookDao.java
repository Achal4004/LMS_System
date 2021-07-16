package dao;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import controllers.BookController;
import models.Book;
import models.BookDto;
import models.BooksDto;
import ninja.jpa.UnitOfWork;

public class BookDao {
	private static Logger log = LogManager.getLogger(BookDao.class);
	@Inject
	Provider<EntityManager> entitiyManagerProvider;
	public static PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);

	@ElementCollection
	java.util.ListIterator<Book> books;
	
	public BooksDto showAllbooks() {
		EntityManager entityManager = entitiyManagerProvider.get();
		TypedQuery<Book> q = entityManager.createQuery("SELECT x FROM Book x", Book.class);
		List<Book> books = q.getResultList();
		BooksDto booksDto = new BooksDto();
		booksDto.books = books;
		return booksDto;
	}
	
	@Transactional
	public boolean addBook(BookDto bookDto) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Book book1 = new Book(bookDto.b_id, bookDto.b_title, bookDto.b_author,bookDto.b_copies);
		entityManager.persist(book1);
		return true;

	}


	@Transactional
	public boolean deleteBook(int b_id) {
		try {
		EntityManager entityManager = entitiyManagerProvider.get();
		Book book = entityManager.find(Book.class, b_id);
		entityManager.remove(book);	
		return true;
		}
		catch(Exception e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Book getBookbyId(int b_id){
		EntityManager entityManager = entitiyManagerProvider.get();
		Book book=null;
		try {
		Query query = entityManager.createQuery("Select X FROM Book X where X.b_id=:id");
		query.setParameter("id", b_id);
		book=(Book)query.getSingleResult();
		return book;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			book=null;
			return book;
		}
	 }
	@Transactional
	public Book getBookbyTitle(String b_title){
		EntityManager entityManager = entitiyManagerProvider.get();
		Book book=null;
		try {
		Query query = entityManager.createQuery("Select X FROM Book X where X.b_title=:title");
		query.setParameter("title", b_title);
		book=(Book)query.getSingleResult();
		return book;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			book=null;
			return book;
		}
	 }
	
	@Transactional
	public Book getBookbyAuthor(String b_author){
		EntityManager entityManager = entitiyManagerProvider.get();
		Book book=null;
		try {
		Query query = entityManager.createQuery("Select X FROM Book X where X.b_author=:author");
		query.setParameter("author", b_author);
		book=(Book)query.getSingleResult();
		return book;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			book=null;
			return book;
		}
	 }

	@Transactional
	public boolean updateBookA(Book book) {
		try {
		EntityManager entityManager = entitiyManagerProvider.get();
		Book book2 = entityManager.find(Book.class, book.b_id);
		Query query = entityManager.createQuery("update  Book  set b_title =:title, b_author=:author, b_copies=:copies where b_id=:id");
		query.setParameter("id", book.b_id);
		query.setParameter("title", book.b_title);
		query.setParameter("author", book.b_author);
		query.setParameter("copies", book.b_copies);
		query.executeUpdate();
		if(book2!=null) {
			return true;
		}
		return false;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}	

}
