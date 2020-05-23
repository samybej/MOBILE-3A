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
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Offre;
import entities.Type;
import java.util.List;
import java.util.Map;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class ListeCovoituragesForm extends Demo {
    
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
        
        Map<Integer,List> mapOffreType = ServiceOffre.getInstance().getCovoiturages();
        
        List<Offre> listOffres = mapOffreType.get(1);
        List<Type> listTypes = mapOffreType.get(2);
        
        Container c1 = new Container(BoxLayout.y());
        int i=0;
        for (Offre o : listOffres)
        {
            for (Type t : listTypes)
            {
                if (o.getId() == t.getIdOffre())
                {
                    i++;
                    
                    Label lbNbPlace = new Label(String.valueOf(o.getNbPlace()));
                    Label lbDepart = new Label(o.getDepart());
                    Label lbArrive = new Label(o.getArrive());
                    Label lbDate = new Label(o.getDate());
                    Label lbTime = new Label(o.getTime());
                    Label lbTarif = new Label(String.valueOf(o.getTarif()));
                    Label lbVehicule = new Label(o.getVehicule());
                    Label lbBagage = new Label(o.getBagage());
                    Label lbVitesse = new Label(String.valueOf(t.getVitesse()));
                    Label lbNbrArrets = new Label(String.valueOf(t.getNbrArrets()));
                    Label lbTmpArret = new Label(String.valueOf(t.getTmpArret()));
                    
                    Container c2 = new Container(BoxLayout.y());
                    c2.add("Covoiturage numero " +i );
                    c2.add("Nombre de places : " + lbNbPlace.getText());
                    c2.add("Lieu de depart : " + lbDepart.getText());
                    c2.add("Lieu d'arrivee : " + lbArrive.getText());
                    c2.add("Date : " + lbDate.getText());
                    c2.add("Heure : " + lbTime.getText());
                    c2.add("Tarif : " + lbTarif.getText());
                    c2.add("Vehicule : " + lbVehicule.getText());
                    c2.add("Bagage : " + lbBagage.getText());
                    c2.add("Vitesse : " + lbVitesse.getText());
                    c2.add("Nombre de pauses : " + lbNbrArrets.getText());
                    c2.add("Temps total d'arrets : " + lbTmpArret.getText());
                    c2.add("");
                    c2.add("");
                    
                    c1.add(c2);
                }
            }
            
        }
        
        c1.setScrollableY(true);
        
        return c1;
    }
    
    
}
