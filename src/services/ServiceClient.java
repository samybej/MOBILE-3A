/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import entities.Client;
import entities.Offre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author azuz
 */
public class ServiceClient {
    
     public ArrayList<Client> client;
     public int id;
    
    public static ServiceClient instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceClient() {
         req = new ConnectionRequest();
    }

    public static ServiceClient getInstance() {
        if (instance == null) {
            instance = new ServiceClient();
        }
        return instance;
    }
    
    public int loginClient(String email , String password)
    {
        String url = Statics.BASE_URL + "api/getClient/" + email + "/" + password;
        req.setUrl(url);
       //float id;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                System.out.println(resultOK);
                if (resultOK)
                {
                    String jsonText = new String(req.getResponseData());
                    
                    JSONParser j = new JSONParser();
                    
                    
                        id = Integer.parseInt(jsonText);
                  
                    System.out.println(jsonText);
                    
                }
                else 
                {
                    System.out.println("hhhh");
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return id;
    }
    
    public boolean addClient(Client c) {
        String url = Statics.BASE_URL + "api/addclient/" + c.getNom() + "/" + c.getPrenom()+ "/" + c.getTel()
                + "/" + c.getMail()+ "/" + c.getMdp()+ "/" + c.getAdresse();
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
    
    
    public ArrayList<Client> parseClient(String jsonText){
        try {
            client =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Client c = new Client();
                float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                float etatCompte = Float.parseFloat(obj.get("etatCompte").toString());
                
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
                c.setPrenom(obj.get("prenom").toString());
                c.setTel((int)tel);
                c.setMail(obj.get("mail").toString());
                c.setMdp(obj.get("mdp").toString());
                c.setAdresse(obj.get("adresse").toString());
                c.setEtatCompte((int)etatCompte);
                
                
                client.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return client;
    }
    
    
     public ArrayList<Client> getClient(){
        String url = Statics.BASE_URL+"api/client/209";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                client = parseClient(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return client;
    }
}
