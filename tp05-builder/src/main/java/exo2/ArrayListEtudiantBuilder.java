package exo2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEtudiantBuilder implements ListEtudiantBuilder {
    
    private List<Etudiant> etudiants = new ArrayList<>();

    @Override
    public ListEtudiantBuilder reset() {
        this.etudiants.clear();
        return this;
    }

    @Override
    public ListEtudiantBuilder traiterLigne(String l) {
        if (l == null || l.trim().isEmpty()) {
            return this;
        }

        String[] parts = l.split(":");
        
        if (parts.length < 3) {
            return this; 
        }

        try {
            int numero = Integer.parseInt(parts[0]);
            String nom = parts[1];
            String prenom = parts[2];

            Etudiant etudiant = new Etudiant(numero, nom, prenom);

            for (int i = 3; i < parts.length; i++) {
                if (!parts[i].isEmpty()) {
                    double note = Double.parseDouble(parts[i]);
                    etudiant.addNote(note);
                }
            }

            this.etudiants.add(etudiant);

        } catch (NumberFormatException e) {
            e.printStackTrace(); 
        }

        return this;
    }

    @Override
    public List<Etudiant> getResults() {

        return new ArrayList<>(this.etudiants);
    }


    public List<String> getNomsEtudiants() {
        List<String> noms = new ArrayList<>();
        for (Etudiant e : etudiants) {
            noms.add(e.getNom());
        }
        return noms;
    }
}