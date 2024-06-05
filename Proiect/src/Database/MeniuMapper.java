package Database;

import Model.Meniu;
import Model.Produs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeniuMapper implements CrudService.DbMapper<Meniu> {

    @Override
    public PreparedStatement createInsertStatement(Connection conn, String table, Meniu item) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + table + " (nume) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, "Nume Meniu");
        return stmt;
    }

    @Override
    public Meniu extractFromResultSet(ResultSet rs) throws SQLException {
        int meniuId = rs.getInt("id");
        List<Produs> produse = new ArrayList<>();

        try (PreparedStatement ps = rs.getStatement().getConnection().prepareStatement("SELECT * FROM produse WHERE meniu_id = ?")) {
            ps.setInt(1, meniuId);
            ResultSet produseRS = ps.executeQuery();
            while (produseRS.next()) {
                produse.add(new Produs(produseRS.getString("nume"), produseRS.getDouble("pret")));
            }
        }

        return new Meniu(produse);
    }

    @Override
    public PreparedStatement createUpdateStatement(Connection conn, String table, Meniu item, String identifier) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE " + table + " SET nume = ? WHERE id = ?");
        stmt.setString(1, "Nou Nume Meniu");
        stmt.setInt(2, Integer.parseInt(identifier));
        return stmt;
    }
}
