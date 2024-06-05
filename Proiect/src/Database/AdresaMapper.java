package Database;

import Model.Adresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdresaMapper implements CrudService.DbMapper<Adresa> {
    @Override
    public PreparedStatement createInsertStatement(Connection conn, String table, Adresa item) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + table + " (strada, oras, codPostal) VALUES (?, ?, ?)");
        stmt.setString(1, item.getStrada());
        stmt.setString(2, item.getOras());
        stmt.setString(3, item.getCodPostal());
        return stmt;
    }

    @Override
    public Adresa extractFromResultSet(ResultSet rs) throws SQLException {
        String strada = rs.getString("strada");
        String oras = rs.getString("oras");
        String codPostal = rs.getString("codPostal");
        return new Adresa(strada, oras, codPostal);
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection conn, String table, Adresa item, String identifier) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE " + table + " SET strada = ?, oras = ?, codPostal = ? WHERE id = ?");
        stmt.setString(1, item.getStrada());
        stmt.setString(2, item.getOras());
        stmt.setString(3, item.getCodPostal());
        stmt.setInt(4, Integer.parseInt(identifier));
        return stmt;
    }
}
