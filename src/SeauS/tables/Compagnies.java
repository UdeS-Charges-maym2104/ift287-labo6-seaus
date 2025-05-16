package SeauS.tables;

import SeauS.bdd.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Compagnies extends GestionTables {
    private final PreparedStatement stmtExisteCompagnie;
    private final PreparedStatement stmtExisteCompagnie_id;
    private final PreparedStatement stmtInsertCompagnie;
    private final PreparedStatement stmtUpdateCompagnie;
    private final PreparedStatement stmtDeleteCompagnie;

    public Compagnies(Connexion cx) throws SQLException {
        super(cx);

        stmtExisteCompagnie = cx.getConnection().prepareStatement(
                "select idcompagnie, nom_compagnie, adresse from compagnie where nom_compagnie = ?");
        stmtExisteCompagnie_id = cx.getConnection().prepareStatement(
                "select idcompagnie, nom_compagnie, adresse from compagnie where idcompagnie = ?");
        stmtInsertCompagnie = cx.getConnection().prepareStatement(
                "insert into compagnie (nom_compagnie, adresse) values (?,?)");
        stmtUpdateCompagnie = cx.getConnection().prepareStatement(
                "update compagnie set nom_compagnie = ?, adresse = ? where nom_compagnie = ?");
        stmtDeleteCompagnie = cx.getConnection().prepareStatement(
                "delete from compagnie where nom_compagnie = ?");
    }

    public boolean existe(String nom) throws SQLException {
        stmtExisteCompagnie.setString(1, nom);
        ResultSet rs = stmtExisteCompagnie.executeQuery();
        return rs.next();
    }
}
