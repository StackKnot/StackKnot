USE stackknot_db;

INSERT INTO progression (status) VALUES ("Mastered"), ("Satisfactory"), ("Needs Work");

INSERT INTO users (email, first_name, is_admin, last_name, password, username) VALUES ("admin@stackknot.xyz", "admin", true, "adminton", "codeup", "admin");

INSERT INTO sets (description, title, subject_id, user_id) VALUES ("test java set", "test java set", 1, 1), ("test javascript set", "test javascript set", 2 , 1);

INSERT INTO cards (answer, question, set_id) VALUES ("test java answer", "test java question", 1), ("test javascript answer", "test javascript question", 2);

INSERT INTO whiteboards (javaurl, jsurl, question) VALUES ("https://res.cloudinary.com/smith-gary/image/upload/v1647287764/Screen_Shot_2022-03-14_at_2.55.31_PM_ma6rkd.png", "https://res.cloudinary.com/smith-gary/image/upload/v1647286602/Screen_Shot_2022-03-14_at_2.35.12_PM_lsu5ou.png", "Create function to check whether or not the input string is a Palindrome.");