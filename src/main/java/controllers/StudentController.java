package controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.Inject;


import dao.StudentDao;
import dao.UserDao;
import models.Student;
import models.StudentsDto;
import models.UserEntity;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;

public class StudentController {
	@Inject
	StudentDao studentDao;
	
	//1
	public Result ShowStudents(@PathParam("s_id") Long s_id) {
		StudentsDto studnets=null;
		if( s_id != null) {
			studnets=studentDao.ShowAllstudents();
			return Results.json().render(studnets);
		}
		
		return  Results.json().render(studentDao.ShowAllstudents());
	}
    
    

}
