package Database;

import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudService<T> {
    private final String table;
    private final DbMapper<T> mapper;

    public CrudService(String table, DbMapper<T> mapper) {
        this.table = table;
        this.mapper = mapper;
    }

    public void create(T item) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = mapper.createInsertStatement(conn, table, item)) {
            stmt.executeUpdate();
        }
    }

    public List<T> read() throws SQLException {
        List<T> items = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + table);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(mapper.extractFromResultSet(rs));
            }
        }
        return items;
    }

    public void update(T item, String identifier) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = mapper.createUpdateStatement(conn, table, item, identifier)) {
            stmt.executeUpdate();
        }
    }

    public void delete(String identifier) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + table + " WHERE id = ?")) {
            stmt.setString(1, identifier);
            stmt.executeUpdate();
        }
    }

    public interface DbMapper<T> {
        PreparedStatement createInsertStatement(Connection conn, String table, T item) throws SQLException;
        T extractFromResultSet(ResultSet rs) throws SQLException;
        PreparedStatement createUpdateStatement(Connection conn, String table, T item, String identifier) throws SQLException;
    }
}
