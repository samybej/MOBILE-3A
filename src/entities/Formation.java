/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Med Aymen Barhoumi
 */
public class Formation {
     private int id;
    private Date Date_debut;
    private Date Date_fin;
    private String Titre;
    private String time ;
    private int nbr_place ;
    private int Type;//cle etranger mtaa l theme
   

    public Formation() {
    }

    public Formation(Date Date_debut, Date Date_fin, String Titre, String time, int nbr_place, int Type) {
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.Titre = Titre;
        this.time = time;
        this.nbr_place = nbr_place;
        this.Type = Type;
    }

  

    public Formation(int id, Date Date_debut, Date Date_fin, String Titre, String time, int nbr_place, int Type) {
        this.id = id;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.Titre = Titre;
        this.time = time;
        this.nbr_place = nbr_place;
        this.Type = Type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(Date Date_debut) {
        this.Date_debut = Date_debut;
    }

    public Date getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(Date Date_fin) {
        this.Date_fin = Date_fin;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

  

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", Date_debut=" + Date_debut + ", Date_fin=" + Date_fin + ", Titre=" + Titre + ", time=" + time + ", nbr_place=" + nbr_place + ", Type=" + Type + '}';
    }
    
    
    
    
}

