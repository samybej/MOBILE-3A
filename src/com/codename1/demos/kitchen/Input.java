/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package com.codename1.demos.kitchen;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 * Demonstrates basic usage of input facilities, device orientation behavior as well as adapting the UI to tablets.
 * This demo shows off a typical form with user information, different keyboard types, ability to capture an 
 * avatar image and style it etc.
 *
 * @author .
 */
public class Input  extends Demo {

    public String getDisplayName() {
        return "Reservation Taxi ";
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
       
         
        Container welcome = FlowLayout.encloseCenter(
                new Label("bienvenue, ", "WelcomeWhite"),
                new Label("Reserver un Taxi", "WelcomeBlue")
        );
        
        TextComponent depart = new TextComponent().labelAndHint("Lieu de depart");
        FontImage.setMaterialIcon(depart.getField().getHintLabel(), FontImage.MATERIAL_ADD_LOCATION);
        TextComponent arrive = new TextComponent().labelAndHint("Lieu d'arrivee");
        FontImage.setMaterialIcon(arrive.getField().getHintLabel(), FontImage.MATERIAL_ADD_LOCATION);
        
        TextComponent vehicule = new TextComponent().labelAndHint("vehicule");
        FontImage.setMaterialIcon(vehicule.getField().getHintLabel(), FontImage.MATERIAL_CAR_RENTAL);          
      
      
       Validator val = new Validator();
       
       val.setValidationFailureHighlightMode(Validator.HighlightMode.UIID);
        val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(depart, new LengthConstraint(2, "saisir le dapart"));
        val.addConstraint(arrive, new LengthConstraint(2, "saisir l'arrivée"));
        
        TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), depart);
        comps.add(tl.createConstraint().widthPercentage(30), arrive);
        comps.add(tl.createConstraint().horizontalSpan(2), vehicule);
      //comps.add(tl.createConstraint().horizontalSpan(2), password);
       // comps.add(tl.createConstraint().horizontalSpan(2), bio);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
        
        Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        content.add(SOUTH, save);
         save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
                   
                        
                       // Reservation r = new Reservation(30,depart.getText(),arrive.getText(),Integer.parseInt(vehicule.getText()),4,209);
                      /*  if( ServiceReservation.getInstance().ajoutReservation(r))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));*/

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
