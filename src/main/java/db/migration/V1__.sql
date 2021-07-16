-- the first script for migration

CREATE TABLE Book(
	b_id int PRIMARY KEY,
	b_title VARCHAR(50) NOT NULL,
	b_author VARCHAR(50),
	b_copies INT
);

CREATE TABLE Student(
	s_id SERIAL PRIMARY KEY,
	s_name VARCHAR(50) NOT NULL,
	s_email VARCHAR(50),
	s_pass VARCHAR(50)
	
);

CREATE TABLE "user"(
    id SERIAL primary key,
    fullname varchar(50),
    isAdmin boolean ,
    password varchar(50),
    username varchar(50)
);

CREATE TABLE Borrower 
(  
    Borrower_Id int PRIMARY KEY,  
    Book_Id int,  
    Borrowed_From date,  
    Borrowed_TO date,  
    Actual_Return_Date date  
);
ALTER TABLE Borrower ADD CONSTRAINT Book_Id_FK FOREIGN KEY(Book_Id) REFERENCES Book(b_id);
ALTER TABLE Borrower ADD CONSTRAINT Borrower_Id_FK FOREIGN KEY(Borrower_Id) REFERENCES Student(s_id);