package com.javafx.database;

import com.javafx.entity.Course;
import com.javafx.entity.Student;
import com.javafx.entity.exception.DatabaseException;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class StudentRepository implements Repository<Student> {

    private static final String SELECT_ALL =
            """
            SELECT p.first_name, p.last_name, p.email, p.phone_number, p.oib,
                   s.jmbag, s.course, s.academic_year, s.gpa, s.ects
            FROM STUDENT s
            JOIN PERSON p ON p.id = s.id
            """;

    private static final String SELECT_LAST =
            """
            SELECT p.id, p.first_name, p.last_name, p.email, p.phone_number, p.oib,
            s.jmbag, s.course, s.academic_year, s.gpa, s.ects
            FROM STUDENT s
            JOIN PERSON p ON p.id = s.id
            ORDER BY s.id DESC
            LIMIT 1
            """;

    private static final String DROP_BACKUP = "DROP TABLE IF EXISTS STUDENT_BACKUP";
    private static final String CREATE_BACKUP = "CREATE TABLE STUDENT_BACKUP AS SELECT id, jmbag, course, academic_year, gpa, ects FROM STUDENT";

    @Override
    public List<Student> getAll() throws DatabaseException, IOException {
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<Student> out = new ArrayList<>();
            while (rs.next()){
                out.add(mapRow(rs));
            }
            return out;

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Student> getOneById(Integer id) throws DatabaseException, IOException {
        String sql = SELECT_ALL + " WHERE s.id = ? ORDER BY s.id";
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()){
                    return Optional.empty();
                }
                return Optional.of(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Student> search(String fn, String ln, String jmbag, String email, Course course, Integer year, Integer ectsMin)
            throws DatabaseException, IOException {

        String sql = SELECT_ALL + " WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (fn != null && !fn.isBlank()) {
            sql += " AND LOWER(p.first_name) LIKE ? ORDER BY s.id";
            params.add("%" + fn.toLowerCase() + "%");
        }
        if (ln != null && !ln.isBlank()) {
            sql += " AND LOWER(p.last_name)  LIKE ? ORDER BY s.id";
            params.add("%" + ln.toLowerCase() + "%");
        }
        if (jmbag != null && !jmbag.isBlank()) {
            sql += " AND s.jmbag LIKE ? ORDER BY s.id";
            params.add("%" + jmbag + "%");
        }
        if (email != null && !email.isBlank()) {
            sql += " AND LOWER(p.email) LIKE ? ORDER BY s.id";
            params.add("%" + email.toLowerCase() + "%");
        }
        if (course != null) {
            sql += " AND s.course = ? ORDER BY s.id";
            params.add(course.name());
        }
        if (year != null) {
            sql += " AND s.academic_year = ? ORDER BY s.id";
            params.add(year);
        }
        if (ectsMin != null) {
            sql += " AND s.ects >= ? ORDER BY s.id";
            params.add(ectsMin);
        }

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++){
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<Student> out = new ArrayList<>();
                while (rs.next()){
                    out.add(mapRow(rs));
                }
                return out;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Optional<Student> getLastInserted() throws DatabaseException, IOException {
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_LAST);
             ResultSet rs = ps.executeQuery()) {


            if (rs.next()){
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }


    public void backupStudentsTable() throws DatabaseException, IOException {
        try (Connection con = createConnection();
             Statement st = con.createStatement()) {


            st.execute(DROP_BACKUP);
            st.execute(CREATE_BACKUP);


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName  = rs.getString("last_name");
        String email     = rs.getString("email");
        String phone     = rs.getString("phone_number");
        String oib       = rs.getString("oib");

        String jmbag     = rs.getString("jmbag");
        Course course    = Course.valueOf(rs.getString("course"));
        Integer year     = rs.getInt("academic_year");
        BigDecimal gpa   = rs.getBigDecimal("gpa");
        Integer ects     = rs.getInt("ects");

        return new Student.StudentBuilder(firstName, lastName, email, jmbag, oib)
                .phoneNumber(phone)
                .course(course)
                .year(year)
                .gpa(gpa)
                .ects(ects)
                .build();
    }
}