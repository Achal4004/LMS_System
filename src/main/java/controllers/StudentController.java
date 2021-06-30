package controllers;

import com.google.inject.Inject;


import dao.StudentDao;
import models.StudentsDto;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;

public class StudentController {
	@Inject
	StudentDao studentDao;
	
	//1
	public Result ShowStudents(@PathParam("s_id") Long s_id) {
		StudentsDto studnets=null;
		if( s_id != null) {
			studnets=studentDao.ShowAllstudents();
			return Results.ok().json().render(studnets);
		}
		
		return  Results.unauthorized().json().render(studentDao.ShowAllstudents());
	}

}
