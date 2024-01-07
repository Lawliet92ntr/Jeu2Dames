import static org.junit.Assert.*;
import org.junit.Test;

public class TestFonctions {

    @Test
    public void testPeutPrendreDameVersHaut() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 1,3 , 1, 4,2);

        assertTrue(resultat);
    }

    @Test
    public void testPeutPrendreDameVersBas() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 2, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 2, 4, 2, 3, 1);

        assertTrue(resultat);
    }

    @Test
    public void testPeutPrendreDameVersGauche() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 2, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 1, 3, 2, 2, 3);

        assertTrue(resultat);
    }

    @Test
    public void testPeutPrendreDameVersDroite() {
        int[][] plateau = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        boolean resultat = Fonctions.peutPrendre(plateau, 1, 2, 2, 3, 1);

        assertTrue(resultat);
    }

    // Ajoutez d'autres méthodes de test pour couvrir différents cas

}
