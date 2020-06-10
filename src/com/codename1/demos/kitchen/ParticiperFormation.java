/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.kitchen;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Formation;
import entities.Offre;
import entities.Type;
import entities.theemes;
import java.util.List;
import java.util.Map;
import services.ServiceFormation;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class ParticiperFormation extends Demo {
    
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
        return "Mes Participations";
    }

    public Image getDemoIcon() {
        return getResources().getImage("input.png"); 
    }

    @Override
    public String getDescription() {
        return " Mes Participation...";
    }

    @Override
    public String getSourceCodeURL() {
        return "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/Input.java";
    }

    
    @Override
    public Container createDemo(Form parent) {
        
        Map<theemes,Formation> mapOffreType = ServiceFormation.getInstance().getFormation();
        
        
        
        Container c1 = new Container(BoxLayout.y());
        int i=0;
       for (Map.Entry<theemes,Formation> entry : mapOffreType.entrySet())
               {
                theemes o = entry.getKey();
             Formation t = entry.getValue();
                    
                  Label Formateur = new Label(o.getFormateur());
                    Label Titre = new Label(o.getTitre());
                    Label Cibles = new Label(o.getCibles());
                    
                    Label lbTime = new Label(t.getTime());
                    Label lbTarif = new Label(String.valueOf(t.getNbr_place()));
                    Label lbVehicule = new Label(t.getTitre());
                    Label lbDate = new Label(String.valueOf(t.getDate_debut()));
                    Label lbDate1 = new Label(String.valueOf(t.getDate_fin()));


                   
                    
                    Container c2 = new Container(BoxLayout.y());
                    
                      c2.add("titre : " + Titre.getText());
                    c2.add("formateur : " + Formateur.getText());
                  
                    c2.add("lieu : " + lbVehicule.getText());
                    c2.add("Date_debut : " + lbDate.getText());
                    c2.add("date_fin : " + lbDate1.getText());
                    c2.add("Heure : " + lbTime.getText());
                    c2.add("nbre de place : " + lbTarif.getText());                   
                    c2.add("cibles : " + Cibles.getText());
                    c2.add("***************************************************");
                            
                    c1.add(c2);
                    
                     
             
             
           
                    
                    
                    
                }
            
               
        
        
        c1.setScrollableY(true);
        
        return c1;
    }
    
    
}
