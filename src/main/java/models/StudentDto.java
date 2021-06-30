package models;


import javax.validation.constraints.Size;

public class StudentDto {
	
	public int s_id;
	
	@Size(min=5)
	public String s_name;
	
	@Size(min=5)
	public String s_email;
	
	@Size(min=5)
	public String s_password;
    
    public  StudentDto() {}

}
