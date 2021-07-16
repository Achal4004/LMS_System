package dao;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Book;
import models.BookDto;
import models.Borrower;
import models.BorrowerDto;
import models.BorrowersDto;

public class BorrowedDao {
	private static Logger log = LogManager.getLogger(BorrowedDao.class);
	@Inject
	Provider<EntityManager> entitiyManagerProvider;
	public static PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);

	@ElementCollection
	java.util.ListIterator<Borrower> borrowers;
	//Gson g = new Gson();
	
	public BorrowersDto ShowAllRecord() {
		EntityManager entityManager = entitiyManagerProvider.get();
		TypedQuery<Borrower> q = entityManager.createQuery("SELECT x FROM Borrower x", Borrower.class);
		List<Borrower> borrowers = q.getResultList();
		BorrowersDto boom = new BorrowersDto();
		boom.borrower = borrowers;
		log.info(boom);
		return boom;
	}
	
	@Transactional
	public boolean Add_New_Entry(BorrowerDto borrowerDto) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Borrower borrower = new Borrower(borrowerDto.Borrower_Id,borrowerDto.Book_Id);
		entityManager.persist(borrower);
		return true;

	}
	
	

}
