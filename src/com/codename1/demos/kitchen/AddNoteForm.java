/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.kitchen;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.isTablet;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
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
import com.codename1.ui.spinner.Picker;
import entities.Note;
import entities.Offre;
import java.util.Date;
import services.ServiceNote;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class AddNoteForm extends Demo{
    
    public String getDisplayName() {
        return "Note";
    }

    public Image getDemoIcon() {
        return getResources().getImage("input.png"); 
    }

    @Override
    public String getDescription() {
        return "Ajout note...";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Input.java";
    }

    
    @Override
    public Container createDemo(Form parent) {
       
        TextComponent messageText = new TextComponent().labelAndHint("Note");
        //FontImage.setMaterialIcon(depart.getField().getHintLabel(), FontImage.MATERIAL_ADD_LOCATION);
        //TextComponent typeText = new TextComponent().labelAndHint("A Propos");
        //FontImage.setMaterialIcon(arrive.getField().getHintLabel(), FontImage.MATERIAL_ADD_LOCATION);
        
        Picker nomChauffeurText = new Picker();
       nomChauffeurText.setType(Display.PICKER_TYPE_STRINGS);
       nomChauffeurText.setSelectedString("Nom du chauffeur");
       nomChauffeurText.setStrings("Aziz","Hechmi","Mohamed","Alaa");
       
        TextModeLayout tl = new TextModeLayout(4, 2);
       Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), messageText);
     //   comps.add(tl.createConstraint().widthPercentage(30), typeText);
        comps.add(tl.createConstraint().horizontalSpan(2), nomChauffeurText);
      //comps.add(tl.createConstraint().horizontalSpan(2), );
       // comps.add(tl.createConstraint().horizontalSpan(2), bio);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        
        Container content = BorderLayout.center(comps);
        
        content.add(SOUTH, save);
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
                     Integer note = Integer.parseInt(messageText.getText());
                    // String type = typeText.getText();
                     int idChauffeur = 0;
                     if (nomChauffeurText.getText().equals("Aziz"))
                     {
                         idChauffeur=161;
                     }
                     else if (nomChauffeurText.getText().equals("hechmi"))
                     {
                         idChauffeur=163;
                     }
                     else if (nomChauffeurText.getText().equals("Mohamed"))
                     {
                         idChauffeur=167;
                     }
                      else if (nomChauffeurText.getText().equals("Alaa"))
                     {
                         idChauffeur=168;
                     }
                       
                     Note n = new Note(note,KitchenSink.getIdClientLogged(),idChauffeur);
                        if( ServiceNote.getInstance().addNote(n))
                        {
                            Demo d = new addReclamationForm();
                                if (note < 3)
                                {
                                     Form previous = getCurrentForm();
                                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                                f.getToolbar().setBackCommand(" ", ee -> {
                                    if(d.onBack()){
                                        previous.showBack();
                                    }
                                });
                              
                                f.show();
                                }
                             
                                Form previous = getCurrentForm();
                                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                                f.getToolbar().setBackCommand(" ", ee -> {
                                    if(d.onBack()){
                                        previous.showBack();
                                    }
                                });
                               
                                        }
                            
                        else
                        {
                  
                             Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                           
                      // System.out.println(o);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
              
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
