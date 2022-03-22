USE stackknot_db;
## DROP ALL TABLES
DROP TABLE IF EXISTS cards;

DROP TABLE IF EXISTS user_set_prog;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS sets;

DROP TABLE IF EXISTS subjects;

DROP TABLE IF EXISTS whiteboards;

DROP TABLE IF EXISTS progression;

## CREATE ALL TABLES

## USERS
create table users
(
    id         bigint auto_increment
        primary key,
    email      varchar(255)     not null,
    first_name varchar(50)      null,
    is_admin   bit default b'0' null,
    last_name  varchar(50)      null,
    password   varchar(255)     not null,
    username   varchar(30)      not null,
    unique (email),
    unique (username)
);

## USER SET PROGRESSION
create table user_set_prog
(
    id             bigint auto_increment
        primary key,
    progression_id bigint null,
    set_id         bigint null,
    user_id        bigint null,
    foreign key (user_id) references users (id),
    foreign key (progression_id) references progression (id),
    foreign key (set_id) references sets (id)
);

## SETS
create table sets
(
    id          bigint auto_increment
        primary key,
    description varchar(255) not null,
    title       varchar(255) not null,
    subject_id  bigint       null,
    user_id     bigint       null,
    likes       int          not null,
    foreign key (user_id) references users (id),
    foreign key (subject_id) references subjects (id)
);
## CREATE FULLTEXT INDEX FOR SET SEARCH
create fulltext index title
    on sets (title, description);

## SUBJECTS
create table subjects
(
    id    bigint auto_increment
        primary key,
    title varchar(75) not null
);

## CARDS
create table cards
(
    id       bigint auto_increment
        primary key,
    answer   text not null,
    question varchar(255) not null,
    set_id   bigint       null,
    foreign key (set_id) references sets (id)
);

## WHITEBOARDS
create table whiteboards
(
    id       bigint auto_increment
        primary key,
    javaurl  text         not null,
    jsurl    text         not null,
    question varchar(255) not null
);

## PROGRESSION
create table progression
(
    id     bigint auto_increment
        primary key,
    status varchar(50) null
);

## INSERT BASE INFO INTO ALL TABLES

INSERT INTO progression (status)
VALUES ("Mastered"),
       ("Satisfactory"),
       ("Needs Work");

## NEED TO ADD ADMIN FROM SITE, THEN CHANGE IS_ADMIN TO TRUE

INSERT INTO subjects (title)
VALUES ("HTML"), ("CSS"), ("Bootstrap"), ("JavaScript"), ("jQuery"), ("Java"), ("Spring"), ("MySQL"), ("Thymeleaf");

INSERT INTO sets (description, title, subject_id, user_id, likes)
VALUES ("Basics of HTML", "HTML", 1, null, 0),
       ("Basics of CSS", "CSS I", 2, null, 0),
       ("Front-end framework that applies CSS", "BootStrap", 3, null, 0),
       ("Basics of javascript", "Javascript I", 4, null, 0),
       ("Javascript library for easier scripting of HTML", "Jquery", 5, null, 0),
       ("More in-depth javascript", "Javascript II", 4, null, 0),
       ("Basics of java", "Java I", 6, null, 0),
       ("Java and Object oriented programming", "Java II", 6, null, 0),
       ("Java and the web", "Java III", 6, null, 0),
       ("Understanding the Spring framework", "Spring", 7, null, 0),
       ("Understanding MySQL", "MySQL", 8, null, 0),
       ("Understanding Thymeleaf", "Thymeleaf", 9, null, 0);


INSERT INTO cards (answer, question, set_id)
VALUES
#        ("Semantic HTML means using the most appropriate tag for the task at hand. It means using meaningful elements such as <form>, <article>, and <table> instead of only using <div> and <span>.", "What is semantic HTML?", 1),
#        ("Web Accessibility means making sure the web is usable by people with a wide range of disabilities. It includes making sure keyboard-only users can navigate your site while also making certain people who have difficulties hearing or seeing can use it as well.", "What is web accessibility?
# ", 1),
       ("HTML tags are elements. Think <a>, <button>, and <div>. HTML attributes describe characteristics of elements. Think src, class, and id.", "What is the difference between a tag and an attribute?", 1),
       ("The CSS box model refers to the way CSS handles layout. Each element is composed of its content, padding, border, and margin.
", "What is the box model?", 2),
       ("!important is highest, #idSelector next, .classSelector next, *selectAll least", "What is the priority of selectors?", 2),
       ("What are the different type of selectors?", "Class selectors use .notation, Id selectors use #notation, Selecting siblings div + selector(p), Selecting descendants div selector(p), Selecting children div > selector(p)", 2),
       ("Bootstrap is based on a 12 columen layout", "What kind of grid system does Bootstrap utilize?", 3),
       ("A class is added onto the HTML element followed by the bootstrap naming convention.", "How do you implement Bootstrap in HTML?", 3),
       ("Yes, by putting d-flex into the HTML element class.", "Does Bootstrap support flex-box?", 3),
       ("Originally, var was the only option JavaScript had for defining variables. In ES6, we got const and let as additional options. The important takeaways are: Variables defined with const cannot be reassigned. Const and let variables are block-scoped. Var variables are function scoped. Variables defined with var are hoisted.", "What is the difference between let, const, and var?", 4),
       ("== checks for value only, before checking, it does any necessary type coercion. === checks for value and type", "What is the difference between == and ===?", 4),
       ("By using the square brackets [] and each index is separated by a comma", "How do declare an array?", 4),
       ("ID selector: #id. Class Selector: .class. Element Selector: element. Multi-selector: (selector1 , selector 2). All: *", "What is the syntax for common selectors in jQuery?", 5),
       ("$", "What is the common shorthand identifier for jQuery?", 5),
       ("An object of 0 or more HTML elements.", "What does the jQuery selector return?", 5),
       ("No, they return a new copy of the original array.", "Do the functions Map, Filter, and reduce modify the original array?", 6),
       ("pending: the event has not fired yet. resolved: event succeeded. rejected: an error occured.", "What are the three states of promises?", 6),
       ("${variableName}", "What is the syntax for template strings?", 6),
       ("Java is compiled while JavaScript is interpreted. Java is statically typed while JavaScript has dynamic typing. While JavaScript does have objects, there is much more focus on them in Java, and the way they are defined and used is quite different. JavaScript runs (mostly) in the browser, while Java runs almost everywhere. Java is a statically typed Object-oriented language and is ran almost everywhere.", "What are some differences between Java and Javascript?", 7),
       ("Java is statically typed, therefore each variable has to be explicitly tagged with a data-type. By putting the data-type on the variable it lets the program know what values that variable can actually contain.", "What is the purpose of Java having multiple number data types?", 7),
       ("Implicit casting is taking smaller data types and making them larger data-types (widening). Explicit is the opposite and needs to have the smaller data type in () after assigning it ie… int i = (byte) 2", "Differences of explicit and implicit casting?", 7),
       ("Abstraction, Polymorphism, Inheritance, Encapsulation", "What are the four pillars of Object Oriented Programming (OOP)?", 8),
       ("The method calls itself continuously to simplify problems in code. Must have a way to stop itself or will create an infinite loop.", "What is a recursive method?", 8),
       ("Method overriding requires the same parameter, return type and name as the parent it inherited it from. Overriding is an example of run time polymorphism. Overriding does not involve static methods. Method overloading the parameter must be different. Overloading is an example of compile time polymorphism. Allows for not having to define functions over and over. Static methods can be overloaded.", "Difference between method overloading and overriding?", 8),
       ("Model which refers to the data of the application. View which refers to the what the user sees. Controller which refers to the logic happening based on user actions.", "What does MVC stand for?", 9),
       ("Data Access Objects are the layer of protection between the application and the database. A Java class that handles all interactions with the database.", "What is a DAO?", 9),
       ("", "", 9),
       ("", "", 10),
       ("", "", 10),
       ("", "", 10),
       ("", "", 11),
       ("", "", 11),
       ("", "", 11),
       ("", "", 12),
       ("", "", 12),
       ("", "", 12),


INSERT INTO whiteboards (javaurl, jsurl, question)
VALUES ("https://res.cloudinary.com/smith-gary/image/upload/v1647969304/r2bbcwccmmovsxjjpuwo.png",
        "https://res.cloudinary.com/smith-gary/image/upload/v1647969298/efzy8b7llirn89lp8siz.png",
        "Create function to check whether or not the input string is a Palindrome."),
       ("https://res.cloudinary.com/smith-gary/image/upload/v1647976845/brupkqn7vrxkrltfvnoo.png",
        "https://res.cloudinary.com/smith-gary/image/upload/v1647977026/mrgnuv6q5wd91fn2w27w.png",
        "Write in the console the numbers from 1 to 100, where multiples of 3 are fizz, multiples of 5 are buzz, and multiples of both 3 and 5 are fizzbuzz."),
       ("https://res.cloudinary.com/smith-gary/image/upload/v1647976673/ugyrugtqmizooo4zr6io.png",
        "https://res.cloudinary.com/smith-gary/image/upload/v1647976752/rdrknjrq0vzgd0qs9p5b.png",
        "Create a function that returns the nth entry in the Fibonacci sequence, where n is the number you pass in as an argument .");





# ("https://res.cloudinary.com/smith-gary/image/upload/v1647289188/Screen_Shot_2022-03-14_at_3.19.08_PM_hxyctz.png","https://res.cloudinary.com/smith-gary/image/upload/v1647288249/Screen_Shot_2022-03-14_at_3.03.40_PM_usvzlg.png
# # ", "Console logs the numbers from 1 to n, where n is the integer the function takes as its parameter
# # logs /fizz/ instead of the number for multiples of 3
# # logs /buzz/ instead of the number for multiples of 5
# # logs /fizzbuzz/ for numbers that are multiples of both 3 and 5");
#