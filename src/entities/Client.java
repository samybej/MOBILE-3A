/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class Client {
    private int id;
    private String nom;
    private String prenom;
    private int tel;
    private String adresse;
    private String mdp;
    private String mail;
    private int etatCompte;
    private int point;
    private int avertissement;
    private int cadeau; 
    private String image;

    public Client() {
    }

    public Client(int id, String nom, String prenom, int tel, String adresse, String mdp, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.mdp = mdp;
        this.mail = mail;
    }

    public Client(String nom, String prenom, int tel, String adresse, String mdp, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.mdp = mdp;
        this.mail = mail;
    }
    
    public Client(int id, String nom, String prenom, int tel, String mail, String mdp, String adresse, int etatCompte ) {
        this.id = id;
        this.nom = nom; 
        this.prenom = prenom;
        this.tel = tel; 
        this.mail = mail; 
        this.mdp = mdp; 
        this.adresse = adresse; 
        this.etatCompte = etatCompte; 
        }
    
     public Client(String nom, String prenom, int tel, String mail, String mdp, String adresse, int etatCompte ) {
    
        this.nom = nom; 
        this.prenom = prenom;
        this.tel = tel; 
        this.mail = mail; 
        this.mdp = mdp; 
        this.adresse = adresse; 
        this.etatCompte = etatCompte; 
        this.point = 0;
        this.avertissement = 0;
        this.cadeau = 0;
    }
     public Client(String nom, String prenom, int tel, String mail, String mdp, String adresse, int etatCompte ,String imgPath) {
    
        this.nom = nom; 
        this.prenom = prenom;
        this.tel = tel; 
        this.mail = mail; 
        this.mdp = mdp; 
        this.adresse = adresse; 
        this.etatCompte = etatCompte; 
        this.point = 0;
        this.avertissement = 0;
        this.cadeau = 0;
        this.image=imgPath;
    }

     
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getEtatCompte() {
        return etatCompte;
    }

    public void setEtatCompte(int etatCompte) {
        this.etatCompte = etatCompte;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse + ", mdp=" + mdp + ", mail=" + mail + '}';
    }
    
    
    
    
}
