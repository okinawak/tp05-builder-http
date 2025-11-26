package exo2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestNomEtudiantsBuilder {
    
    @Test
    public void testSimple() throws IOException {
        List<String> nomsAttendus = Arrays.asList("Turing", "Lovelace", "Wirth");
        ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
        StringReader reader = new StringReader(FichierUtil.dataEtudiants); // Utilise les données fournies dans FichierUtil
        LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
        lecteur.lireFichier(reader);
        List<String> resultat = builder.getNomsEtudiants();
        assertEquals(nomsAttendus, resultat);
    }

    private List<Etudiant> lireEtudiants(String contenuFichier) throws IOException {
        ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
        LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
        return lecteur.lireFichier(new StringReader(contenuFichier));
    }

    @Test
    public void testFichierAvec3Etudiants() throws IOException {
        String data = "1:Turing:Alan:10:20\n" +
                      "2:Lovelace:Ada:15:18\n" +
                      "3:Hopper:Grace:12:14";

        List<Etudiant> resultats = lireEtudiants(data);

        assertEquals("On doit trouver 3 étudiants", 3, resultats.size());
        assertEquals("Le 1er étudiant doit être Turing", "Turing", resultats.get(0).getNom());
        assertEquals("Le 2e étudiant doit être Lovelace", "Lovelace", resultats.get(1).getNom());
        assertEquals("Le 3e étudiant doit être Hopper", "Hopper", resultats.get(2).getNom());
    }

    @Test
    public void testEtudiantSansNotes() throws IOException {
        String data = "1:Doe:John"; 
        
        List<Etudiant> resultats = lireEtudiants(data);

        assertFalse("La liste ne doit pas être vide", resultats.isEmpty());
        Etudiant e = resultats.get(0);
        assertEquals("Nom correct", "Doe", e.getNom());
        assertTrue("La liste de notes doit être vide", e.getNotes().isEmpty());
    }

    @Test
    public void testEtudiantAvecEspacesDansNom() throws IOException {
        // Cas : étudiant avec espaces dans le nom
        String data = "1:Von Neumann:John:20:18";

        List<Etudiant> resultats = lireEtudiants(data);

        assertFalse(resultats.isEmpty());
        Etudiant e = resultats.get(0);
        assertEquals("Le nom doit contenir l'espace", "Von Neumann", e.getNom());
        assertEquals("Le prénom doit être correct", "John", e.getPrenom());
        assertEquals("Il doit y avoir 2 notes", 2, e.getNotes().size());
    }

    @Test
    public void testEtudiantAvecBeaucoupDeNotes() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("1:Einstein:Albert");
        int nbNotes = 100;
        for (int i = 0; i < nbNotes; i++) {
            sb.append(":").append(10 + (i % 10));
        }
        
        List<Etudiant> resultats = lireEtudiants(sb.toString());

        assertFalse(resultats.isEmpty());
        Etudiant e = resultats.get(0);
        assertEquals("Le nombre de notes doit correspondre", nbNotes, e.getNotes().size());
    }
}