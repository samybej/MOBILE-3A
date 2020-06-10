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
public class Participation {
    private int id ;
    private int id_formation ;
    private int id_chauffeur;

    public Participation(int id, int id_formation, int id_chauffeur) {
        this.id = id;
        this.id_formation = id_formation;
        this.id_chauffeur = id_chauffeur;
    }

    public Participation(int id_formation, int id_chauffeur) {
        this.id_formation = id_formation;
        this.id_chauffeur = id_chauffeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }
    
    
    
    
    
}
