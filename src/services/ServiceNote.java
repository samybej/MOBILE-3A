/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Client;
import entities.Note;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceNote {
    
     public static ServiceNote instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceNote() {
         req = new ConnectionRequest();
    }

    public static ServiceNote getInstance() {
        if (instance == null) {
            instance = new ServiceNote();
        }
        return instance;
    }
    
      public boolean addNote(Note n) {
        String url = Statics.BASE_URL + "api/noter/" + n.getNote()+ "/" + n.getIdClient()+ "/" + n.getIdChauffeur();
                
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
}
