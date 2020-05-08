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
import entities.Client;
import entities.Offre;
import java.util.Map;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class ResultatCovoiturageForm extends Demo {
    
    private String date;
    private String depart;
    private String arrive;
    public ResultatCovoiturageForm(String date,String depart,String arrive)
    {
        this.date = date;
        this.depart = depart;
        this.arrive = arrive;
    }
    
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
        
        Map<Offre,Client> mapResultat = ServiceOffre.getInstance().rechercherCovoiturage(date, depart, arrive);
         
         Container c1 = new Container(BoxLayout.y());
         
         for (Map.Entry<Offre,Client> entry : mapResultat.entrySet())
         {
             Offre o = entry.getKey();
             Client c = entry.getValue();
             
             Label lbDepart = new Label(o.getDepart());
             Label lbArrive = new Label(o.getArrive());
             Label lbTarif = new Label(String.valueOf(o.getTarif()));
             Label lbVehicule = new Label(o.getVehicule());
             Label lbBagage = new Label(o.getBagage());
             
             Container c2 = new Container(BoxLayout.y());
             c2.add("Depart : " + lbDepart.getText() + " , Arrivee : " + lbArrive.getText());
             c2.add("Tarif : "+ lbTarif.getText());
             c2.add("Vehicule : "+ lbVehicule.getText());
             c2.add("Bagage : " + lbBagage.getText());
             
             Button btn = new Button("S'inscrire");
             c2.add(btn);
             
             
             
             btn.addPointerPressedListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                      
                 if (ServiceOffre.getInstance().inscriOffre(o.getId(), 209, o.getIdOffreur()))  
                    {
                       Dialog.show(" Success "," Inscription terminée ! ",new Command("OK"));
                    }
                 else 
                 {
                     Dialog.show(" Failed "," Erreur ! ",new Command("OK"));
                     
                 }
                 
                 }
             });
             
             
             c1.add(c2);
         }
         
         c1.setScrollableY(true);
         
         return c1;
    }
}
