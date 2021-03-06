USE stackknot_db;
## DROP ALL TABLES
DROP TABLE IF EXISTS cards;

DROP TABLE IF EXISTS user_set_prog;

DROP TABLE IF EXISTS sets;

DROP TABLE IF EXISTS users;

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

## SUBJECTS
create table subjects
(
    id    bigint auto_increment
        primary key,
    title varchar(75) not null
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

## PROGRESSION
create table progression
(
    id     bigint auto_increment
        primary key,
    status varchar(50) null
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

## INSERT BASE INFO INTO ALL TABLES

INSERT INTO progression (status)
VALUES ("Mastered"),
       ("Satisfactory"),
       ("Needs Work");

## NEED TO ADD ADMIN FROM SITE, THEN CHANGE IS_ADMIN TO TRUE

INSERT INTO subjects (title)
VALUES ("HTML"), ("CSS"), ("Bootstrap"), ("JavaScript"), ("jQuery"), ("Java"), ("Spring"), ("MySQL"), ("Thymeleaf");

INSERT INTO sets (description, title, subject_id, user_id, likes)
VALUES ("Basics of HTML", "HTML", 1, 1, 0),
       ("Basics of CSS", "CSS I", 2, 1, 0),
       ("Front-end framework that applies CSS", "BootStrap", 3, 1, 0),
       ("Basics of javascript", "Javascript I", 4, 1, 0),
       ("Javascript library for easier scripting of HTML", "Jquery", 5, 1, 0),
       ("More in-depth javascript", "Javascript II", 4, 1, 0),
       ("Basics of java", "Java I", 6, 1, 0),
       ("Java and Object oriented programming", "Java II", 6, 1, 0),
       ("Java and the web", "Java III", 6, 1, 0),
       ("Understanding the Spring framework", "Spring", 7, 1, 0),
       ("Understanding MySQL", "MySQL", 8, 1, 0),
       ("Understanding Thymeleaf", "Thymeleaf", 9, 1, 0);


INSERT INTO cards (answer, question, set_id)
VALUES
       ("Semantic HTML means using the most appropriate tag for the task at hand. It means using meaningful elements such as <form>, <article>, and <table> instead of only using <div> and <span>.", "What is semantic HTML?", 1),
       ("Web Accessibility means making sure the web is usable by people with a wide range of disabilities. It includes making sure keyboard-only users can navigate your site while also making certain people who have difficulties hearing or seeing can use it as well.", "What is web accessibility?
", 1),
       ("HTML tags are elements. Think <a>, <button>, and <div>. HTML attributes describe characteristics of elements. Think src, class, and id.", "What is the difference between a tag and an attribute?", 1),
       ("Inline elements cannot have a height or width. Examples of inline elements include span, a, and img. Block elements get their own line and take up the full width available. Examples of block elements are div, p, and h1.", "What is the difference between inline and block elements?", 1),
       ("The CSS box model refers to the way CSS handles layout. Each element is composed of its content, padding, border, and margin.
", "What is the box model?", 2),
       ("!important is highest, #idSelector next, .classSelector next, *selectAll least", "What is the priority of selectors?", 2),
       ("What are the different type of selectors?", "Class selectors use .notation, Id selectors use #notation, Selecting siblings div + selector(p), Selecting descendants div selector(p), Selecting children div > selector(p)", 2),
       ("It allows you to easily position elements inside a container even if the size of that container is dynamic.", "What is Flexbox?", 2),
       ("Bootstrap is based on a 12 columen layout", "What kind of grid system does Bootstrap utilize?", 3),
       ("A class is added onto the HTML element followed by the bootstrap naming convention.", "How do you implement Bootstrap in HTML?", 3),
       ("Yes, by putting d-flex into the HTML element class.", "Does Bootstrap support flex-box?", 3),
       ("Its simple to use if you have any knowledge of HTML and CSS. Mobile first development is built into the framework. It works on all modern browsers.", "What are the advantages of Bootstrap?", 3),
       ("Originally, var was the only option JavaScript had for defining variables. In ES6, we got const and let as additional options. The important takeaways are: Variables defined with const cannot be reassigned. Const and let variables are block-scoped. Var variables are function scoped. Variables defined with var are hoisted.", "What is the difference between let, const, and var?", 4),
       ("== checks for value only, before checking, it does any necessary type coercion. === checks for value and type", "What is the difference between == and ===?", 4),
       ("By using the square brackets [] and each index is separated by a comma", "How do declare an array?", 4),
       ("It is more restrictive with syntax as well as showing errors that would otherwise not be shown.", "What does 'use strict' mean?", 4),
       ("ID selector: #id. Class Selector: .class. Element Selector: element. Multi-selector: (selector1 , selector 2). All: *", "What is the syntax for common selectors in jQuery?", 5),
       ("$", "What is the common shorthand identifier for jQuery?", 5),
       ("An object of 0 or more HTML elements.", "What does the jQuery selector return?", 5),
       (".each() method", "What traversing method allows you to iterate over jQuery objects?", 5),
       ("No, they return a new copy of the original array.", "Do the functions Map, Filter, and reduce modify the original array?", 6),
       ("pending: the event has not fired yet. resolved: event succeeded. rejected: an error occured.", "What are the three states of promises?", 6),
       ("${variableName}", "What is the syntax for template strings?", 6),
       ("Ensures each element in a collection passes some sort of criteria or test.", "What does the filter function accomplish?", 6),
       ("Java is compiled while JavaScript is interpreted. Java is statically typed while JavaScript has dynamic typing. While JavaScript does have objects, there is much more focus on them in Java, and the way they are defined and used is quite different. JavaScript runs (mostly) in the browser, while Java runs almost everywhere. Java is a statically typed Object-oriented language and is ran almost everywhere.", "What are some differences between Java and Javascript?", 7),
       ("Java is statically typed, therefore each variable has to be explicitly tagged with a data-type. By putting the data-type on the variable it lets the program know what values that variable can actually contain.", "What is the purpose of Java having multiple number data types?", 7),
       ("Implicit casting is taking smaller data types and making them larger data-types (widening). Explicit is the opposite and needs to have the smaller data type in () after assigning it ie??? int i = (byte) 2", "Differences of explicit and implicit casting?", 7),
       ("Strings are reference types which is an object. Which is reference types are all non-primitive data types that are a subclass of java.long.object. ", "Is a string a primitive type in Java?", 7),
       ("Abstraction, Polymorphism, Inheritance, Encapsulation", "What are the four pillars of Object Oriented Programming (OOP)?", 8),
       ("The method calls itself continuously to simplify problems in code. Must have a way to stop itself or will create an infinite loop.", "What is a recursive method?", 8),
       ("Method overriding requires the same parameter, return type and name as the parent it inherited it from. Overriding is an example of run time polymorphism. Overriding does not involve static methods. Method overloading the parameter must be different. Overloading is an example of compile time polymorphism. Allows for not having to define functions over and over. Static methods can be overloaded.", "Difference between method overloading and overriding?", 8),
       ("Abstract classes contain the abstract keyword. Abstract classes need at least one abstract method. Interfaces have all abstract methods. Interfaces have only final and static variables. Interfaces do not support multi inheritance.", "What are the differences between an interface and abstract class?", 8),
       ("Model which refers to the data of the application. View which refers to the what the user sees. Controller which refers to the logic happening based on user actions.", "What does MVC stand for?", 9),
       ("Data Access Objects are the layer of protection between the application and the database. A Java class that handles all interactions with the database.", "What is a DAO?", 9),
       ("A constructor in Java is a special method that is used to initialize objects. The constructor is called when an object of a class is created. It can be used to set initial values for object attributes", "What is a constructor?", 9),
       ("Parameter is a variable used to define a particular value during a function definition. Arguments are the value passed to a function when the function is called.", "What is an argument vs a parameter?", 9),
       ("Path Variable", "What is a variable that is a part of the URI of a request called?", 10),
       ("Using a form with the post method.", "What is a simple method to take information from a view to a controller in Spring?", 10),
       ("application.properties", "The configuration of the data source to be used goes into what file?", 10),
       ("JpaRepository, which is extended off of the data type class that will be manipulated.", "What is the base/parent class in Spring for the Data Access Layer which is usually the DAO?", 10),
       ("Order by specifies the order in which the data is viewed. Group by groups results by data in columns and allows duplicates to be removed.", "Explain the differences between Order By and Group By", 11),
       ("AS", "What is the keyword needed to assign an alias to a query?", 11),
       ("Structured Query Language.", "What does SQL stand for in MySQL.", 11),
       ("Describe returns the structure of the specified table.", "What does the Describe keyword return?", 11),
       ("Server side Java template engine. Has the ability to work on both web and non-web environments.", "What is Thymeleaf?", 12),
       ("Chunks of code that will be reused. Those chunks can be included in other templates.", "What are fragments in Thymeleaf?", 12),
       ("The variable used will automatically be escaped. ", "What is a feature of using th:text?", 12),
       ("${...} : Variable expressions. *{...} : Selection expressions. #{...} : Message (i18n) expressions. @{...} : Link (URL) expressions. ~{...} : Fragment expressions.", "What are the 5 types of standard expressions in Thymeleaf?", 12);


INSERT INTO whiteboards (javaurl, jsurl, question)
VALUES ("https://res.cloudinary.com/smith-gary/image/upload/v1647969304/r2bbcwccmmovsxjjpuwo.png",
        "https://res.cloudinary.com/smith-gary/image/upload/v1647969298/efzy8b7llirn89lp8siz.png",
        "Create function to check whether or not the input string is a Palindrome."),
       ("https://res.cloudinary.com/smith-gary/image/upload/v1647976845/brupkqn7vrxkrltfvnoo.png",
        "https://res.cloudinary.com/smith-gary/image/upload/v1647977026/mrgnuv6q5wd91fn2w27w.png",
        "Write in the console the numbers from 1 to 100, where multiples of 3 are fizz, multiples of 5 are buzz, and multiples of both 3 and 5 are fizzbuzz."),
       ("https://res.cloudinary.com/smith-gary/image/upload/v1647976673/ugyrugtqmizooo4zr6io.png",
        "https://res.cloudinary.com/smith-gary/image/upload/v1647976752/rdrknjrq0vzgd0qs9p5b.png",
        "Create a function that returns the nth entry in the Fibonacci sequence, where n is the number you pass in as an argument."),
       ("https://res.cloudinary.com/smith-gary/image/upload/v1648052773/osmltxz0wv0umirg6z2p.png", "https://res.cloudinary.com/smith-gary/image/upload/v1648052621/xlasi3yuurncoxchwab7.png", "Write a function that takes a string as an argument an returns the number of vowels contained in that string."),
  ("https://res.cloudinary.com/smith-gary/image/upload/v1648056048/cgswphmyakn2zo6eqeqb.png", "https://res.cloudinary.com/smith-gary/image/upload/v1648055230/eggtgz7eju5cyu6trcis.png", "Write a function that will take a given string and reverse the order of the words. Expected output of 'The words in this string have been reversed' to be 'reversed been have string this in words The'.");

