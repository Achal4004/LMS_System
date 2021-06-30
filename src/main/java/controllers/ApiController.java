/**
  * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;



import ninja.Result;
import ninja.Results;

import ninja.params.PathParam;

import com.google.inject.Inject;

import dao.BookDao;
import dao.StudentDao;
import models.BookDto;
import models.BooksDto;
import models.StudentsDto;


public class ApiController {
	
	@Inject
	BookDao bookDao;
	StudentDao studentDao;
	
	public Result getbooksJson() {
		BooksDto booksDto = bookDao.showAllbooks();
		return Results.json().render(booksDto);
	}
	
	public Result getstudentsJson() {
		StudentsDto studentsDto = studentDao.ShowAllstudents();
		return Results.json().render(studentsDto);
	}

	public Result newBook(BookDto bookDto) {
		System.out.println("Detail" + bookDto);
		System.out.println("Title" + bookDto.b_title);
		System.out.println("Author" + bookDto.b_author);
		boolean succeeded = bookDao.addBook(bookDto);

		if (!succeeded) {
			return Results.notFound();
		} else {
			return Results.json();
		}

	}
	
	public Result updateBook(@PathParam("b_id") Long b_id) {
		
		return Results.noContent();		
	}
	
	public Result DeleteBook(@PathParam("b_id") Long b_id) {
		bookDao.deleteBook(b_id);
		return Results.redirect("/delete");
	}
	
	
   
}
