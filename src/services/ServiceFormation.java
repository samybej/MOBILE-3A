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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Formation;
import entities.Offre;
import entities.Postulation;
import entities.Reservation;
import entities.Type;
import entities.theemes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Med Aymen Barhoumi
 */
public class ServiceFormation {
   
     public ArrayList<theemes> Theme;

      public ArrayList<Formation> Formation;
     
     public Map<theemes,Formation> map = new HashMap();
    public static ServiceFormation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceFormation() {
         req = new ConnectionRequest();
    }

    public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }
    
    
    
    
     public Map<theemes,Formation> getFormation()
     {
     
        String url = Statics.BASE_URL+"api/listformation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    
                    map = parseOffres(new String(req.getResponseData()));
                    
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.out.println("ahja");
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return map;
     }
    
     
     
     public Map<theemes,Formation> parseOffres(String jsonText) throws ParseException{
        try {
            Theme=new ArrayList<>();
            Formation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> offresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)offresListJson.get("root");
            for(Map<String,Object> obj : list){
                Formation o = new Formation();
                
                theemes t = new theemes();
                
                //Les donnees mte3 el formation
                float id = Float.parseFloat(obj.get("id").toString());
                float nbr = Float.parseFloat(obj.get("nbrPlace").toString());
               float idtheme = Float.parseFloat(((Map)obj.get("typeTheme")).get("id").toString());
               
               
                String date_debut = obj.get("dateDebut").toString();
                String date_fin = obj.get("dateFin").toString();
                String time = obj.get("time").toString();
                String titre = obj.get("lieu").toString();
                Date date_new = new SimpleDateFormat("yyyy-MM-dd").parse(date_debut);
                 Date date_new1 = new SimpleDateFormat("yyyy-MM-dd").parse(date_fin);
                
            
                
                
                
                o.setId((int)id);
                o.setDate_debut(date_new);
                o.setDate_fin(date_new1);
               
                o.setNbr_place((int)nbr);
                o.setTime(time);
                o.setTitre(titre);
                o.setType((int)idtheme);
                
                
                //les donnes mte3 el theme
                
                t.setId((int)idtheme);
                t.setTitre(((Map)obj.get("typeTheme")).get("titre").toString());
                t.setCibles(((Map)obj.get("typeTheme")).get("cibles").toString());
                t.setFormateur(((Map)obj.get("typeTheme")).get("formateur").toString());
                
                
             
                
               // System.out.println(t);
                
                Theme.add(t);
                Formation.add(o);
                
                map.put(t, o);
                
            }
            
            
        } catch (IOException ex) {
            
        }
     
        return map;
    }


     public boolean addTask(Postulation o) {
        String url = Statics.BASE_URL + "api/postuler/" + o.getNom()+ "/" + o.getPrenom()+ "/" + String.valueOf(o.getCin())+ "/"+ o.getNum_permis()+ "/" + String.valueOf(o.getExperience())+ "/" + String.valueOf(o.getTel())+ "/" + o.getLangue()+ "/" + String.valueOf(o.getId_client())+ "/" + o.getSexe();
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
     
     
     
      
     
     public boolean inscrireFormation(int idFormation,int idChauffeur)
    {
        String url = Statics.BASE_URL + "api/participer/" + idChauffeur + "/" + idFormation;
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
     
     
    
 public Map<theemes,Formation> getFormationbyClient()
     {
     
        String url = Statics.BASE_URL+"api/formation/168";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    
                    map = parseOffres(new String(req.getResponseData()));
                    
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.out.println("ahja");
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return map;
     }
      
     
     
     
     
    
}
