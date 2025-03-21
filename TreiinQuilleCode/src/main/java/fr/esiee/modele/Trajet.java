package fr.esiee.modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trajet {
    private String code;
    private LocalDateTime tempsDepart;
    private LocalDateTime tempsArrivee;
    private Arret arretDepart;
    private Arret arretArrivee;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    ////////////////// Les Constructeurs //////////////
    public Trajet() {
    }

    public Trajet(String code, LocalDateTime tempsDepart, LocalDateTime tempsArrivee, Arret arretDepart, Arret arretArrivee) {
        this.code = code;
        this.tempsDepart = tempsDepart;
        this.tempsArrivee = tempsArrivee;
        this.arretDepart = arretDepart;
        this.arretArrivee = arretArrivee;
    }

    /////////////////// getters/ setters /////////////////
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTempsDepart() {
        return tempsDepart;
    }

    public void setTempsDepart(LocalDateTime tempsDepart) {
        this.tempsDepart = tempsDepart;
    }

    public LocalDateTime getTempsArrivee() {
        return tempsArrivee;
    }

    public void setTempsArrivee(LocalDateTime tempsArrivee) {
        this.tempsArrivee = tempsArrivee;
    }

    public Arret getArretDepart() {
        return arretDepart;
    }

    public void setArretDepart(Arret arretDepart) {
        this.arretDepart = arretDepart;
    }

    public Arret getArretArrivee() {
        return arretArrivee;
    }

    public void setArretArrivee(Arret arretArrivee) {
        this.arretArrivee = arretArrivee;
    }

    public void afficherTrajet() {
        System.out.println("=============================");
        System.out.println("Code Trajet: " + code);
        System.out.println("Temps de départ: " + tempsDepart.format(FORMATTER));
        System.out.println("Temps d'arrivée: " + tempsArrivee.format(FORMATTER));
        System.out.println("Arrêt de départ: ");
        arretDepart.afficherArret();
        System.out.println("Arrêt d'arrivée: ");
        arretArrivee.afficherArret();
        System.out.println("=============================");
    }

    @Override
    public String toString() {
        return "Trajet{" +
                "code='" + code + '\'' +
                ", tempsDepart=" + tempsDepart.format(FORMATTER) +
                ", tempsArrivee=" + tempsArrivee.format(FORMATTER) +
                ", arretDepart=" + arretDepart +
                ", arretArrivee=" + arretArrivee +
                '}';
    }

}
