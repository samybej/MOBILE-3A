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
import java.util.Date;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class AddCovoiturageForm extends Demo {
    
     private void showDemoInformation(Form back, Demo d) {
        Form f = new Form("Information", new BorderLayout());
        Button sourceCode = new Button("View Source");
        FontImage.setMaterialIcon(sourceCode, FontImage.MATERIAL_WEB);
        sourceCode.addActionListener(e -> execute(d.getSourceCodeURL()));
        f.add(CENTER, new SpanLabel(d.getDescription())).
                add(SOUTH, sourceCode);
        //f.getToolbar().setBackCommand("", e -> back.showBack());
         f.getToolbar().addCommandToSideMenu("rechercher", null, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 Demo d = new RechercherCovoiturageForm();
                 
                  Form previous = getCurrentForm();
                                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                                f.getToolbar().setBackCommand(" ", ee -> {
                                    if(d.onBack()){
                                        previous.showBack();
                                    }
                                });
                                f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_INFO, 4, ee -> {
                                    showDemoInformation(f, d);
                                });
                                f.show();
             }
         });
         f.getToolbar().addCommandToSideMenu("ajouter", null, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 Demo d =  new AddCovoiturageForm();
                 
                  Form previous = getCurrentForm();
                                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                                f.getToolbar().setBackCommand(" ", ee -> {
                                    if(d.onBack()){
                                        previous.showBack();
                                    }
                                });
                                f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_INFO, 4, ee -> {
                                    showDemoInformation(f, d);
                                });
                                f.show();
             }
         });
         f.getToolbar().addCommandToSideMenu("mes covoiturages", null, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 Demo d = new ListeCovoituragesForm();
                 
                  Form previous = getCurrentForm();
                                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                                f.getToolbar().setBackCommand(" ", ee -> {
                                    if(d.onBack()){
                                        previous.showBack();
                                    }
                                });
                                f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_INFO, 4, ee -> {
                                    showDemoInformation(f, d);
                                });
                                f.show();
             }
         });
        f.show();
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
        
        TextComponent nbPlace = new TextComponent().labelAndHint("Nombre de places");
        
        TextComponent depart = new TextComponent().labelAndHint("Lieu de depart");
        
        TextComponent arrive = new TextComponent().labelAndHint("Lieu d'arrivee");
        
       // FontImage.setMaterialIcon(name.getField().getHintLabel(), FontImage.);
       
     //  PickerComponent date = PickerComponent.createDate(null).label("Date");
       Picker date2 = new Picker();
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
       
       
       Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        
         TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), nbPlace);
        comps.add(tl.createConstraint().widthPercentage(30), depart);
       // comps.add(tl.createConstraint().horizontalSpan(2), depart);
        comps.add(tl.createConstraint().horizontalSpan(2), arrive);
        comps.add(tl.createConstraint().horizontalSpan(2), date2);
        comps.add(tl.createConstraint().horizontalSpan(2), time);
        comps.add(tl.createConstraint().horizontalSpan(2), tarif);
        comps.add(tl.createConstraint().horizontalSpan(2), vehicule);
        comps.add(tl.createConstraint().horizontalSpan(2), bagage);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
        
        content.add(SOUTH, save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
                     String date_string = date2.toString();
                        String heure =String.valueOf(time.getTime() / 60);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = format.format(date2.getDate());
                        Date date_formatted = format.parse(dateString);
                        
                        Offre o = new Offre(Integer.parseInt(nbPlace.getText()), depart.getText(),arrive.getText(),
                        dateString, heure, Float.parseFloat(tarif.getText()),KitchenSink.getIdClientLogged(),KitchenSink.getIdClientLogged(), vehicule.getText(), bagage.getText());
                       float id = ServiceOffre.getInstance().ajoutOffre(o);
                        if( id != 0)
                        {
                                 Demo d = new AddTypeForm((int)id);
                             
                                Form previous = getCurrentForm();
                                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                                f.getToolbar().setBackCommand(" ", ee -> {
                                    if(d.onBack()){
                                        previous.showBack();
                                    }
                                });
                                f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_INFO, 4, ee -> {
                                    showDemoInformation(f, d);
                                });
                                f.show();
                                        }
                            
                        else
                        {
                  
                             Dialog.show("ERROR", "Server error" + id, new Command("OK"));
                        }
                           
                       System.out.println(o);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } catch (ParseException ex) {
                    
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
