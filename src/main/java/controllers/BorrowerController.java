package controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.Inject;


import dao.BorrowedDao;
import models.BookDto;
import models.BorrowerDto;
import models.BorrowersDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;

public class BorrowerController {
	private static Logger log = LogManager.getLogger(BookController.class);
	@Inject
	BorrowedDao borrowerDao;

	// 1
	public Result showAllRecords() {
		BorrowersDto borrower =borrowerDao.ShowAllRecord();
		if (borrower != null) {
			return Results.json().render(borrower);
		}

		return Results.json().render(" Not Found");
	}
	
	// 2
	
		public Result newentry(Context context, BorrowerDto borrowerDto) {
			borrowerDao.Add_New_Entry(borrowerDto);
			context.getFlashScope().success("New Entry Added");
			return Results.ok().json().render("New Entry Added");

		}
		
		

}
