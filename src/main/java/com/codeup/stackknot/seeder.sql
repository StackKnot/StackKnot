USE stackknot_db;

INSERT INTO progression (status) VALUES ("Mastered"), ("Satisfactory"), ("Needs Work");

INSERT INTO users (email, first_name, is_admin, last_name, password, username) VALUES ("admin@stackknot.xyz", "admin", true, "adminton", "codeup", "admin");

INSERT INTO sets (description, title, subject_id, user_id) VALUES ("test java set", "test java set", 1, 1), ("test javascript set", "test javascript set", 2 , 1);

INSERT INTO cards (answer, question, set_id) VALUES ("test java answer", "test java question", 1), ("test javascript answer", "test javascript question", 2);

INSERT INTO whiteboards (javaurl, jsurl, question) VALUES ("https://res.cloudinary.com/smith-gary/image/upload/v1647287764/Screen_Shot_2022-03-14_at_2.55.31_PM_ma6rkd.png", "https://res.cloudinary.com/smith-gary/image/upload/v1647286602/Screen_Shot_2022-03-14_at_2.35.12_PM_lsu5ou.png", "Create function to check whether or not the input string is a Palindrome."),
("https://res.cloudinary.com/smith-gary/image/upload/v1647287764/Screen_Shot_2022-03-14_at_2.55.31_PM_ma6rkd.png", "https://res.cloudinary.com/smith-gary/image/upload/v1647286602/Screen_Shot_2022-03-14_at_2.35.12_PM_lsu5ou.png", "Create function to check whether or not the input string is a Palindrome."),
                                                          ("https://res.cloudinary.com/smith-gary/image/upload/v1647291293/Screen_Shot_2022-03-14_at_3.54.31_PM_uod4fb.png", "https://res.cloudinary.com/smith-gary/image/upload/v1647289820/Screen_Shot_2022-03-14_at_3.29.54_PM_rsltna.png", "Create a function that returns the nth entry in the Fibonacci sequence, where n is the number you pass in as an argument .");



# ("https://res.cloudinary.com/smith-gary/image/upload/v1647289188/Screen_Shot_2022-03-14_at_3.19.08_PM_hxyctz.png","https://res.cloudinary.com/smith-gary/image/upload/v1647288249/Screen_Shot_2022-03-14_at_3.03.40_PM_usvzlg.png
# # ", "Console logs the numbers from 1 to n, where n is the integer the function takes as its parameter
# # logs /fizz/ instead of the number for multiples of 3
# # logs /buzz/ instead of the number for multiples of 5
# # logs /fizzbuzz/ for numbers that are multiples of both 3 and 5");
#