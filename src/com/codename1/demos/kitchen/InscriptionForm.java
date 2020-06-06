/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.kitchen;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import entities.Client;
import java.io.IOException;
import services.ServiceClient;

/**
 *
 * @author azuz
 */
public class InscriptionForm extends Demo {
    
    private Image img;
    private String imgPath;
    private ConnectionRequest connectionRequest;
    private ImageViewer iv;
    public String getDisplayName() {
        return "";
    }

    public Image getDemoIcon() {
        return getResources().getImage("input.png"); 
    }

    @Override
    public String getDescription() {
        return "Inscription en cours...";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Input.java";
    }
    
    @Override
    public Container createDemo(Form parent) {
                    TextField nom = new TextField(null, "Nom", 20, TextField.ANY) ;
            TextField prenom = new TextField(null, "Prenom", 20, TextField.ANY) ;
            TextField tel = new TextField(null, "Téléphone", 20, TextField.DECIMAL) ;
            TextField mail = new TextField(null, "Email", 20, TextField.EMAILADDR) ;
            TextField mdp = new TextField(null, "Mot de passe", 20, TextField.PASSWORD) ;
            TextField adresse = new TextField(null, "Adresse", 20, TextField.ANY) ;
            
                  // ImageViewer iv ;
   

            //Image red = Image.createImage(100, 100, 0xffff0000);
            iv = new ImageViewer(Image.createImage(250,250));
     
            
             iv.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        //
                        
                        imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);                      
                        img = Image.createImage(imgPath);
                        iv.setImage(img);
                    } catch (IOException ex) {
                        
                    }
                }
            });
            
   
            
            Button save = new Button("Save");
            save.setUIID("InputAvatarImage");
            
            TextModeLayout tl = new TextModeLayout(4, 2);
            Container comps = new Container(tl);
            comps.add(tl.createConstraint().widthPercentage(70), nom);
            comps.add(tl.createConstraint().widthPercentage(30), prenom);
            comps.add(tl.createConstraint().horizontalSpan(2), tel);
            comps.add(tl.createConstraint().horizontalSpan(2), mail);
            comps.add(tl.createConstraint().horizontalSpan(2), mdp);
            comps.add(tl.createConstraint().horizontalSpan(2), adresse);
            comps.add(tl.createConstraint().widthPercentage(70),iv);
            
            comps.setScrollableY(true);
            comps.setUIID("PaddedContainer");
            
            Container content = BorderLayout.center(comps);
            
            content.add(SOUTH, save);
            save.addActionListener(new com.codename1.ui.events.ActionListener() {
                @Override
                public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                    Client c = new Client( nom.getText(), prenom.getText(), Integer.parseInt(tel.getText()), mail.getText(), mdp.getText(), adresse.getText(),1 );  
                    connectionRequest = new ConnectionRequest();
                    connectionRequest.setUrl("http://localhost/untitled2/insert.php?path=" + imgPath);
                    NetworkManager.getInstance().addToQueue(connectionRequest);
                    
                    if (ServiceClient.getInstance().addClient(c))
                    {
                        Dialog.show("Bienvenue", "Inscription effectuée avec succès", new Command("OK"));
                        KitchenSink ks = new KitchenSink();
                        ks.init(CENTER);
                        ks.start();
                    }
                    else
                    {
                        Dialog.show("Erreur", "vérifier les champs", new Command("OK"));
                        
                    }
                }
                
            });
            
                  
   
        
            content.setUIID("InputContainerForeground");
            
            Button avatar = new Button("");
            avatar.setUIID("InputAvatar");
            
            Container actualContent = LayeredLayout.encloseIn(content,
                    FlowLayout.encloseCenter(avatar));
            
            Container input;
            if(!isTablet()) {
                Label placeholder = new Label(" ");
                
                Component.setSameHeight(actualContent, placeholder);
                Component.setSameWidth(actualContent, placeholder);
                
                input = BorderLayout.center(placeholder);
                
                parent.addShowListener(e -> {
                    if(placeholder.getParent() != null) {
                        input.replace(placeholder, actualContent, CommonTransitions.createFade(1500));
                    }
                });
            } else {
                input = BorderLayout.center(actualContent);
            }
            input.setUIID("InputContainerBackground");
      
        
            return input;
      
                
    
   
    }
    
}
