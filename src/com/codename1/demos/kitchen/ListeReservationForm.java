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
import entities.Reservation;
import java.util.List;
import services.ServiceReservation;

/**
 *
 * @author Asus
 */
public class ListeReservationForm extends Demo {
    
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
        return "Reservation";
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
        
        List<Reservation> listReservation = ServiceReservation.getInstance().getReservations();
        
        Container c1 = new Container(BoxLayout.y());
        int i=0;
        for (Reservation r : listReservation)
        {
            i++;
            
            Label lbDepart = new Label(r.getDepart());
            Label lbArrive = new Label(r.getArrive());
            Label lbDate = new Label(String.valueOf(r.getDate()));
            Label lbTarif = new Label(String.valueOf(r.getTarif()));
            Label lbDistance = new Label(String.valueOf(r.getDistance()));
            
            Container c2 = new Container(BoxLayout.y());
            
            c2.add("Reservation numero :" + i );
            c2.add("Lieu de depart : " + lbDepart.getText());
            c2.add("Lieu d'arrivee : " + lbArrive.getText());
            c2.add("Date " + lbDate.getText());
            c2.add("Tarif " + lbTarif.getText());
            c2.add("Distance : " + lbDistance.getText());
            
            c1.add(c2);
        }
        
        c1.setScrollableY(true);
        
        return c1;
    }
}
