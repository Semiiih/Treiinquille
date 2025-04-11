package fr.esiee.modele;

import java.time.LocalDate;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;

    ////////////////// Les Constructeurs //////////////
    public Utilisateur() {
    }

    public Utilisateur(int id, String password, String nom, String prenom, String email, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /////////////////// getters/ setters /////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public void afficherUtilisateur() {
        System.out.println("-------------------------");
        System.out.println("ID: " + id);
        System.out.println("Mot de passe: " + password);
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("email: " + email);
        System.out.println("Rôle: " + role);
        System.out.println("-------------------------");
    }
}
