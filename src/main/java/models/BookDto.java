package models;

import javax.validation.constraints.Size;

public class BookDto {

    @Size(min = 5)
    public  String b_title;
    
    @Size(min = 5)
    public  String b_author;
    
    
    public  int b_copies;
    
    public int b_id;
    
    public BookDto() {}

}
