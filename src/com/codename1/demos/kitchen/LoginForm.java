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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import services.ServiceClient;


/**
 *
 * @author Asus
 */
public class LoginForm extends Form {
   public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
             setUIID("backPic");

        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome ", "WelcomeWhite")
        );
        
        getTitleArea().setUIID("Container");
        
        /*Image profilePic = theme.getImage("driver.png");
        Image mask = theme.getImage("driver.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());*/
        
        TextField login = new TextField(null, "Login", 20, TextField.EMAILADDR) ;
        TextField password = new TextField(null, "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
       
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int id = ServiceClient.getInstance().loginClient(login.getText(), password.getText());
                if ( id != 0)
                {
                                 
                    KitchenSink ks = new KitchenSink();
                    KitchenSink.setIdClientLogged(id);
                    ks.init(CENTER);
                   
                    ks.showSplashAnimation();
                    
                }
                else 
                {
                    System.out.println(id);
                     System.out.println(login.getText());
                    System.out.println(password.getText());
                     Dialog.show("ERROR", "Login ou Mot de passe Incorrects" , new Command("OK"));
                }
            }
        });
        
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");
        createNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Demo d = new InscriptionForm(); 
                Form f = new Form(d.getDisplayName(), new BorderLayout());
                                f.add(CENTER, d.createDemo(f));
                f.show();
            }
        });
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
      
        
        Container by = BoxLayout.encloseY(
                
                welcome,
             //   profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

    
}

