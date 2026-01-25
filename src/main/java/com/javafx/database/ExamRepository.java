package com.javafx.database;

import com.javafx.entity.Exam;
import com.javafx.entity.SubjectName;
import com.javafx.entity.exception.DatabaseException;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ExamRepository implements Repository<Exam> {

    private static final String SELECT_ALL =
            """
            SELECT s.name AS subject_name, e.date_time, e.location, e.duration
            FROM EXAM e
            JOIN SUBJECT s ON s.id = e.subject_id
            """;


    @Override
    public List<Exam> getAll() throws DatabaseException, IOException {
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<Exam> out = new ArrayList<>();
            while (rs.next()) out.add(mapRow(rs));
            return out;

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Exam> getOneById(Integer id) throws DatabaseException, IOException {
        String sql = SELECT_ALL + " WHERE e.id = ?";
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<Exam> search(SubjectName subject, String location, Integer duration) throws DatabaseException, IOException {
        String sql = SELECT_ALL + " WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (subject != null) {
            sql += " AND s.name = ? ";
            params.add(subject.name());
        }
        if (location != null && !location.isBlank()) {
            sql += " AND LOWER(e.location) LIKE ? ";
            params.add("%" + location.toLowerCase() + "%");
        }
        if (duration != null) {
            sql += " AND e.duration = ? ";
            params.add(duration);
        }

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<Exam> out = new ArrayList<>();
                while (rs.next()){
                    out.add(mapRow(rs));
                }
                return out;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Exam mapRow(ResultSet rs) throws SQLException {
        SubjectName subject = SubjectName.valueOf(rs.getString("subject_name"));
        LocalDateTime dt = rs.getTimestamp("date_time").toLocalDateTime();
        String location = rs.getString("location");
        Integer duration = rs.getInt("duration");
        return new Exam(subject, dt, location, duration);
    }
}