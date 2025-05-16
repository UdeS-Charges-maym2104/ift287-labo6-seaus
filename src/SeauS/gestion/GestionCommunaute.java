package SeauS.gestion;

import SeauS.SeauSException;
import SeauS.tables.Communautes;
import SeauS.tables.Projets;
import SeauS.tuples.Communaute;

import java.sql.SQLException;

public class GestionCommunaute extends GestionTransactions {

    private final Communautes communautes;
    private final Projets projets;

    public GestionCommunaute(Communautes communautes, Projets projets) {
        super(communautes.getConnexion());

        this.communautes = communautes;
        this.projets = projets;
    }

    private void ajouterCommunaute(String nom, String nation, String chef, String coord)
            throws SQLException, SeauSException
    {
        try
        {
            // Vérifier que la communauté n'existe pas déjà
            if (communautes.existe(nom))
            {
                throw new SeauSException("Communauté existe déjà : " + nom);
            }

            // Création de la communauté
            Communaute communaute = new Communaute(nom, nation, chef, coord);
            communautes.ajouterCommunaute(communaute);

            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
    // TODO : implémenter le reste des fonctions
}
