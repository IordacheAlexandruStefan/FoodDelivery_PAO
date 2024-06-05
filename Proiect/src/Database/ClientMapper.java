package Database;

import Model.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientMapper implements CrudService.DbMapper<Client> {
    @Override
    public PreparedStatement createInsertStatement(Connection conn, String table, Client item) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + table + " (nume, adresa, telefon) VALUES (?, ?, ?)");
        stmt.setString(1, item.getNume());
        stmt.setString(2, item.getAdresa());
        stmt.setString(3, item.getTelefon());
        return stmt;
    }

    @Override
    public Client extractFromResultSet(ResultSet rs) throws SQLException {
        String nume = rs.getString("nume");
        String adresa = rs.getString("adresa");
        String telefon = rs.getString("telefon");
        return new Client(nume, adresa, telefon);
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection conn, String table, Client item, String identifier) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE " + table + " SET nume = ?, adresa = ?, telefon = ? WHERE id = ?");
        stmt.setString(1, item.getNume());
        stmt.setString(2, item.getAdresa());
        stmt.setString(3, item.getTelefon());
        stmt.setInt(4, Integer.parseInt(identifier));
        return stmt;
    }
}
