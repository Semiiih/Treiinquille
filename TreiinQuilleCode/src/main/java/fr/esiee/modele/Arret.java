package fr.esiee.modele;

public class Arret {
    private int id;
    private String nom;
    private TypeArret typeArret;

    public enum TypeArret {
        TERMINUS,
        INTERMEDIAIRE
    }

    ////////////////// Les Constructeurs //////////////
    public Arret() {
    }

    public Arret(int id, String nom, TypeArret typeArret) {
        this.id = id;
        this.nom = nom;
        this.typeArret = typeArret;
    }

    /////////////////// getters/ setters /////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeArret getTypeArret() {
        return typeArret;
    }

    public void setTypeArret(TypeArret typeArret) {
        this.typeArret = typeArret;
    }

    public void afficherArret() {
        System.out.println("ID Arret: " + id);
        System.out.println("Nom Arret: " + nom);
        System.out.println("Type Arret: " + typeArret);
        System.out.println("--------");
    }

    @Override
    public String toString() {
        return "Arret{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeArret=" + typeArret +
                '}';
    }

}
