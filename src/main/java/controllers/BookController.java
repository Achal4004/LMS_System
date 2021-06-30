package controllers;

import models.Book;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.Inject;

import dao.BookDao;
import models.BookDto;
import models.BooksDto;
import ninja.Context;
import ninja.Result;
import ninja.params.PathParam;
import ninja.Results;

public class BookController {
	private static Logger log = LogManager.getLogger(BookController.class);
	@Inject
	BookDao bookDao;

	// 1
	public Result showAllBooks() {
		BooksDto book =bookDao.showAllbooks(); ;
		if (book != null) {
			return Results.json().render(book);
		}

		return Results.json().render("Book Not Found");
	}

	// 2
	public Result newBookAdd(Context context, BookDto bookDto) {
		bookDao.addBook(bookDto);
		context.getFlashScope().success("New Book Addded");
		return Results.ok().json().render("New Book Added");

	}

	// 4
	public Result findBookbyTitle(@PathParam("b_title") String b_title) {
		Book book = bookDao.getBookbyTitle(b_title);
		if(book!=null) {
			return Results.ok().json().render("Here is Your Book "+book.b_title);
		}
		return Results.json().render("Book Not Found");
	}

	// 5
	public Result findBookbyId(@PathParam("b_id") Long b_id) {
		Book book = bookDao.getBookbyId(b_id);
		if(book!=null) {
			return Results.ok().json().render("Here is Your Book "+book.b_title);
		}
		return Results.notFound().json().render("Book Not Found");
	}

	// 6
	public Result findBookbyAuthor(@PathParam("b_author") String b_author) {
		Book book = bookDao.getBookbyAuthor(b_author);
		if(book!=null) {
			return Results.ok().json().render("Here is Your Book "+book.b_title);
		}
		return Results.json().render("Book Not Found");
	}
	
	//7
	public Result deleteBook(@PathParam("b_id") Long b_id) {
		boolean succeeded = bookDao.deleteBook(b_id);
		if (succeeded == true) {	
			return Results.ok().json().render("Book is Deleted");
		}
			return Results.ok().json().render("Not Found");
	}
	
	
}
