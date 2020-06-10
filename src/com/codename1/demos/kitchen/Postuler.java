/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.kitchen;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.CN.execute;
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
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import entities.Offre;
import entities.Postulation;
import java.util.Date;
import services.ServiceFormation;
import services.ServiceOffre;
import services.ServiceType;

/**
 *
 * @author Asus
 */
public class Postuler extends Demo {
    
     private void showDemoInformation(Form back, Demo d) {
        Form f = new Form("Information", new BorderLayout());
        Button sourceCode = new Button("View Source");
        FontImage.setMaterialIcon(sourceCode, FontImage.MATERIAL_WEB);
        sourceCode.addActionListener(e -> execute(d.getSourceCodeURL()));
        f.add(CENTER, new SpanLabel(d.getDescription())).
                add(SOUTH, sourceCode);
        f.getToolbar().setBackCommand("", e -> back.showBack());
        f.show();
    }
    
     public String getDisplayName() {
        return "Postuler Chauffeur";
    }

    public Image getDemoIcon() {
        return getResources().getImage("input.png"); 
    }

    @Override
    public String getDescription() {
        return "Posuler chauffuer...";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Input.java";
    }

    
    @Override
    public Container createDemo(Form parent) {
        
        TextComponent Nom = new TextComponent().labelAndHint("Nom");
        
        TextComponent Prenom = new TextComponent().labelAndHint("Prenom");
                TextComponent Cin = new TextComponent().labelAndHint("Cin");

        TextComponent Num_Permis = new TextComponent().labelAndHint("Num_Permis");
        TextComponent Experience     = new TextComponent().labelAndHint("Experience");
        TextComponent Tel     = new TextComponent().labelAndHint("Tel");
        TextComponent Langue     = new TextComponent().labelAndHint("Langue");
        TextComponent Sexe     = new TextComponent().labelAndHint("Sexe");

       // FontImage.setMaterialIcon(name.getField().getHintLabel(), FontImage.);
       
     //  PickerComponent date = PickerComponent.createDate(null).label("Date");
      /*Picker date2 = new Picker();
       date2.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
       
       Picker time = new Picker();
       time.setType(Display.PICKER_TYPE_TIME);
       
       TextComponent tarif = new TextComponent().labelAndHint("Tarif");
    
       FontImage.setMaterialIcon(tarif.getField().getHintLabel(), FontImage.MATERIAL_MONEY);
       
       TextComponent vehicule = new TextComponent().labelAndHint("Vehicule");
       FontImage.setMaterialIcon(vehicule.getField().getHintLabel(), FontImage.MATERIAL_CAR_RENTAL);
       
       Picker bagage = new Picker();
       bagage.setType(Display.PICKER_TYPE_STRINGS);
       bagage.setSelectedString("Pas de bagage");
       bagage.setStrings("Pas de bagage","entre 1 et 5 kg","entre 5 et 10kg"," > 10kg ");
       Label errorNbPlace = new Label(" ");
       Validator val = new Validator();
       val.setValidationFailureHighlightMode(Validator.HighlightMode.UIID);
       val.addConstraint(nbPlace, new NumericConstraint(true, 1, 5, "Nombre de places"));
       */
       
       Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        
         TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), Nom);
        comps.add(tl.createConstraint().widthPercentage(30), Prenom);
       // comps.add(tl.createConstraint().horizontalSpan(2), depart);
       comps.add(tl.createConstraint().horizontalSpan(2), Num_Permis);
              comps.add(tl.createConstraint().horizontalSpan(2), Cin);

        comps.add(tl.createConstraint().horizontalSpan(2), Experience);
        comps.add(tl.createConstraint().horizontalSpan(2), Tel);
        comps.add(tl.createConstraint().horizontalSpan(2), Langue);
        comps.add(tl.createConstraint().horizontalSpan(2), Sexe);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
        
        content.add(SOUTH, save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
                     
                        Postulation o = new Postulation(Integer.parseInt(Num_Permis.getText()),Integer.parseInt(Cin.getText()),Sexe.getText(),Integer.parseInt(Tel.getText()),Integer.parseInt(Experience.getText()),
                        Langue.getText(), Nom.getText(), Prenom.getText(),209);
                       boolean id = ServiceFormation.getInstance().addTask(o);
                       if( ServiceFormation.getInstance().addTask(o))
                        {
                           Dialog.show("Success"," Votre demande est enregistres avec succes !",new Command("OK"));
                           // new ListeCovoituragesForm(new BorderLayout()).show(); 
                        }                         
                           
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       System.out.println(o);
                           
                       System.out.println(o);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "veillez remplir des donnes valides", new Command("OK"));
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
