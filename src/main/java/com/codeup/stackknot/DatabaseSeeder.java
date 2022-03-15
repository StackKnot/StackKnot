package com.codeup.stackknot;

import com.codeup.stackknot.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import com.codeup.stackknot.repositories.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class DatabaseSeeder {
    private UserRepository usersDao;
    private SubjectRepository subjectsDao;
    private WhiteboardRepository whiteboardsDao;
    private SetRepository setsDao;
    private CardRepository cardsDao;
    private ProgressionRepository progDao;
    private JdbcTemplate jdbcTemplate;

    public DatabaseSeeder(UserRepository usersDao, SubjectRepository subjectsDao, WhiteboardRepository whiteboardsDao, SetRepository setsDao, CardRepository cardsDao, ProgressionRepository progDao, JdbcTemplate jdbcTemplate) {
        this.usersDao = usersDao;
        this.subjectsDao = subjectsDao;
        this.whiteboardsDao = whiteboardsDao;
        this.setsDao = setsDao;
        this.cardsDao = cardsDao;
        this.progDao = progDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedSubjectsTable();
        seedProgressionTable();
        seedUsersTable();
        seedSetsTable();
        seedCardsTable();
    }

    public void seedSubjectsTable() {
        String sub1 = "Java", sub2 = "JavaScript";
        String sql = "SELECT * FROM subjects WHERE title IN (\"" + sub1 + "\", \"" + sub2 + "\")";
        List<Subject> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (rs == null || rs.size() <= 0) {
            Subject s1 = new Subject("Java");
            Subject s2 = new Subject("JavaScript");
            subjectsDao.save(s1);
            subjectsDao.save(s2);
        }
    }

    public void seedProgressionTable() {
        String prog1 = "Mastered", prog2 = "Satisfactory", prog3 = "Needs Work";
        String sql = "SELECT * FROM progression WHERE status IN (\"" + prog1 + "\", \"" + prog2 + "\", \"" + prog3 + "\")";
        List<Progression> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (rs == null || rs.size() <= 0) {
            Progression p1 = new Progression("Mastered");
            Progression p2 = new Progression("Satisfactory");
            Progression p3 = new Progression("Need Work");
            progDao.save(p1);
            progDao.save(p2);
            progDao.save(p3);
        }

    }


    public void seedUsersTable() {
        String user1 = "Admin";
        String sql = "SELECT * FROM users WHERE username = (\"" + user1 + "\")";
        List<User> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (rs == null || rs.size() <= 0) {
            User user = new User("admin", "admin@stacknot.xyz", "gocodeup", "", "", true);
            usersDao.save(user);
        }

    }

    public void seedSetsTable() {
        String setTitle = "Test Set";
        String sql = "SELECT * FROM sets WHERE title = (\"" + setTitle + "\")";
        List<Set> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (rs == null || rs.size() <= 0) {
            Set set1 = new Set("Test Set", "Test Java Set", usersDao.findByUsername("admin"), subjectsDao.findByTitle("Java"));
            Set set2 = new Set("Test Set", "Test JavaScript Set", usersDao.findByUsername("admin"), subjectsDao.findByTitle("JavaScript"));

            setsDao.save(set1);
            setsDao.save(set2);
        }
    }

    public void seedCardsTable() {
        String cardQuestion1 = "Test Java Question", cardQuestion2 = "Test JavaScript Question";

        String sql = "SELECT * FROM cards WHERE question IN (\"" + cardQuestion1 + "\", \"" + cardQuestion2 + "\")";
        List<Set> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (rs == null || rs.size() <= 0) {


            Card card1 = new Card("Test Java Question","Test Java Answer", setsDao.getByDescription("Test Java Set"));
            Card card2 = new Card("Test JavaScript Question","Test JavaScript Answer", setsDao.getByDescription("Test JavaScript Set"));

            cardsDao.save(card1);
            cardsDao.save(card2);

        }
    }



}





