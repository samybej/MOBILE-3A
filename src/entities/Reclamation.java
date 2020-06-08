/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Reclamation {
    private int id;
    private String message;
    private String type;
    //private Date date_rec;
    private int id_client;
    private int id_chauffeur;

    public Reclamation(String message, String type, int id_client, int id_chauffeur) {
        this.message = message;
        this.type = type;
        this.id_client = id_client;
        this.id_chauffeur = id_chauffeur;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }
    
    
    
}
