USE stackknot_db;

INSERT INTO progression (status) VALUES ("Mastered"), ("Satisfactory"), ("Needs Work");

INSERT INTO users (email, first_name, is_admin, last_name, password, username) VALUES ("admin@stackknot.xyz", "admin", true, "adminton", "codeup", "admin");

INSERT INTO sets (description, title, subject_id, user_id) VALUES ("test java set", "test java set", 1, 1), ("test javascript set", "test javascript set", 2 , 1);

INSERT INTO cards (answer, question, set_id) VALUES ("test java answer", "test java question", 1), ("test javascript answer", "test javascript question", 2);

