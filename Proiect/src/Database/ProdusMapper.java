package Database;

import Database.CrudService;
import Model.Produs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProdusMapper implements CrudService.DbMapper<Produs> {
    @Override
    public PreparedStatement createInsertStatement(Connection conn, String table, Produs item) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + table + " (nume, pret) VALUES (?, ?)");
        stmt.setString(1, item.getNume());
        stmt.setDouble(2, item.getPret());
        return stmt;
    }

    @Override
    public Produs extractFromResultSet(ResultSet rs) throws SQLException {
        String nume = rs.getString("nume");
        double pret = rs.getDouble("pret");
        return new Produs(nume, pret);
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection conn, String table, Produs item, String identifier) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE " + table + " SET nume = ?, pret = ? WHERE id = ?");
        stmt.setString(1, item.getNume());
        stmt.setDouble(2, item.getPret());
        stmt.setInt(3, Integer.parseInt(identifier));
        return stmt;
    }
}
