/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Med Aymen Barhoumi
 */
public class theemes {
   private int id ;
   private String titre ;
   private String Formateur;
   private String Cibles;

    public theemes() {
    }

    public theemes(String titre, String Formateur, String Cibles) {
        this.titre = titre;
        this.Formateur = Formateur;
        this.Cibles = Cibles;
    }

    public theemes(int id, String titre, String Formateur, String Cibles) {
        this.id = id;
        this.titre = titre;
        this.Formateur = Formateur;
        this.Cibles = Cibles;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFormateur() {
        return Formateur;
    }

    public void setFormateur(String Formateur) {
        this.Formateur = Formateur;
    }

    public String getCibles() {
        return Cibles;
    }

    public void setCibles(String Cibles) {
        this.Cibles = Cibles;
    }

    @Override
    public String toString() {
        return "theemes{" + "id=" + id + ", titre=" + titre + ", Formateur=" + Formateur + ", Cibles=" + Cibles + '}';
    }
   
   
   
    
}
