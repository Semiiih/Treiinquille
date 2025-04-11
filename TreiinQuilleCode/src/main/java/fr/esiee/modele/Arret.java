package fr.esiee.modele;

public class Arret {
    private int id;
    private String localisation;

    ////////////////// Les Constructeurs //////////////
    public Arret() {
    }

    public Arret(int id, String localisation) {
        this.id = id;
        this.localisation = localisation;
    }

    /////////////////// getters/ setters /////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void afficherArret() {
        System.out.println("ID Arret: " + id);
        System.out.println("Localisation Arret: " + localisation);
        System.out.println("--------");
    }

    @Override
    public String toString() {
        return "Arret{" +
                "id=" + id +
                ", localisation='" + localisation + '\'' +
                '}';
    }

}
