package com.javafx.database;

import com.javafx.entity.Subject;
import com.javafx.entity.SubjectName;
import com.javafx.entity.exception.DatabaseException;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class SubjectRepository implements Repository<Subject> {

    private static final String SELECT_ALL =
            "SELECT name, ects, date_time, location, duration FROM SUBJECT";

    @Override
    public List<Subject> getAll() throws DatabaseException, IOException {
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<Subject> out = new ArrayList<>();
            while (rs.next()){
                out.add(mapRow(rs));
            }
            return out;

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Subject> getOneById(Integer id) throws DatabaseException, IOException {
        String sql = "SELECT name, ects, date_time, location, duration FROM SUBJECT WHERE id = ?";
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

    public List<Subject> search(SubjectName name, Integer ects, String location, Integer duration) throws DatabaseException, IOException {
        String sql = SELECT_ALL + " WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (name != null) {
            sql += " AND name = ? ";
            params.add(name.name());
        }
        if (ects != null) {
            sql += " AND ects = ? ";
            params.add(ects);
        }
        if (location != null && !location.isBlank()) {
            sql += " AND LOWER(location) LIKE ? ";
            params.add("%" + location.toLowerCase() + "%");
        }
        if (duration != null) {
            sql += " AND duration = ? ";
            params.add(duration);
        }

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++){
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<Subject> out = new ArrayList<>();
                while (rs.next()){
                    out.add(mapRow(rs));
                }
                return out;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Subject mapRow(ResultSet rs) throws SQLException {
        SubjectName name = SubjectName.valueOf(rs.getString("name"));
        Integer ects = rs.getInt("ects");

        Timestamp ts = rs.getTimestamp("date_time");
        LocalDateTime dt = (ts == null) ? null : ts.toLocalDateTime();

        String location = rs.getString("location");

        Integer duration = rs.getInt("duration");
        if (rs.wasNull()) duration = null;

        return new Subject(name, ects, dt, location, duration);
    }
}