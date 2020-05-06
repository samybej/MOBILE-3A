/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.kitchen;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import entities.Type;
import services.ServiceType;

/**
 *
 * @author Asus
 */
public class AddTypeForm extends Demo {

    private int idCovoiturage;
    private Form f;
    AddTypeForm(int idCovoiturage) {
          
       this.idCovoiturage = idCovoiturage;
      
    }
    
     public String getDisplayName() {
        return "Covoiturage";
    }

    public Image getDemoIcon() {
        return getResources().getImage("input.png"); 
    }

    @Override
    public String getDescription() {
        return "Reservation Taxi...";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Input.java";
    }

    @Override
    public Container createDemo(Form parent) {
        
        TextComponent vitesse = new TextComponent().labelAndHint("Vitesse");
        
        TextComponent nbrArrets = new TextComponent().labelAndHint("Nombre d'arrets / Pauses");
        
        TextComponent tmpArret = new TextComponent().labelAndHint("Temps total d'arrets");
        
         Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        
        TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), vitesse);
        comps.add(tl.createConstraint().widthPercentage(30), nbrArrets);
       // comps.add(tl.createConstraint().horizontalSpan(2), depart);
        comps.add(tl.createConstraint().horizontalSpan(2), tmpArret);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
           content.add(SOUTH, save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
         
                        Type t = new Type(Integer.parseInt(vitesse.getText()),Integer.parseInt(nbrArrets.getText())
                        ,Integer.parseInt(tmpArret.getText()),idCovoiturage);
                        if( ServiceType.getInstance().addType(t))
                        {
                           Dialog.show("Success","Offre Ajoutée !",new Command("OK"));
                           // new ListeCovoituragesForm(new BorderLayout()).show(); 
                        }                         
                           
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       System.out.println(t);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
            }
        }
            
        );
        
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
