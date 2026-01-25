package com.javafx.database;

import com.javafx.entity.Professor;
import com.javafx.entity.exception.DatabaseException;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ProfessorRepository implements Repository<Professor> {

    private static final String SELECT_ALL =
            """
            SELECT p.first_name, p.last_name, p.email, p.phone_number, p.oib
            FROM PROFESSOR pr
            JOIN PERSON p ON p.id = pr.id
            """;

    @Override
    public List<Professor> getAll() throws DatabaseException, IOException {
        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<Professor> out = new ArrayList<>();
            while (rs.next()) {
                out.add(mapRow(rs));
            }
            return out;

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Professor> getOneById(Integer id) throws DatabaseException, IOException {
        String sql = SELECT_ALL + " WHERE pr.id = ?";
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

    public List<Professor> search(String fn, String ln, String email, String oib) throws DatabaseException, IOException {
        String sql = SELECT_ALL + " WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (fn != null && !fn.isBlank()) {
            sql += " AND LOWER(p.first_name) LIKE ? ";
            params.add("%" + fn.toLowerCase() + "%");
        }
        if (ln != null && !ln.isBlank()) {
            sql += " AND LOWER(p.last_name) LIKE ? ";
            params.add("%" + ln.toLowerCase() + "%");
        }
        if (email != null && !email.isBlank()) {
            sql += " AND LOWER(p.email) LIKE ? ";
            params.add("%" + email.toLowerCase() + "%");
        }
        if (oib != null && !oib.isBlank()) {
            sql += " AND p.oib LIKE ? ";
            params.add("%" + oib + "%");
        }

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++){
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<Professor> out = new ArrayList<>();
                while (rs.next()){
                    out.add(mapRow(rs));
                }
                return out;
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private Professor mapRow(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName  = rs.getString("last_name");
        String email     = rs.getString("email");
        String phone     = rs.getString("phone_number");
        String oib       = rs.getString("oib");

        return new Professor(firstName, lastName, email, phone, oib, Collections.emptySet());
    }
}