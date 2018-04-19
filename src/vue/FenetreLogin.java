/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controleur.*;
import static controleur.Connexion.requetes;

/**
 *
 * @author victo
 */

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FenetreLogin extends JFrame implements ActionListener, ItemListener {
    private Connexion maconnexion;
    private final JLabel titre, nomBDD, loginBDD, passwdBDD;
    private final JTextField nomBDDTexte, loginBDDTexte;
    private final JPasswordField passwdBDDTexte;
    private final JButton connect;
    private final JPanel p0, p1, p11, p12, p13, p2;
    
    public FenetreLogin()
    {
        // creation par heritage de la fenetre
        super("Fenetre Login");
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        // creation des labels
        titre = new JLabel("Bienvenue dans la base de donnée Hopital", JLabel.CENTER);
        nomBDD = new JLabel("Nom de la Base :", JLabel.CENTER);
        loginBDD = new JLabel("Login :", JLabel.CENTER);
        passwdBDD = new JLabel("Password :", JLabel.CENTER);
                        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,30);
        titre.setFont(font);
        
        // creation des textes
        nomBDDTexte = new JTextField(8);
        loginBDDTexte = new JTextField(8);
        passwdBDDTexte = new JPasswordField(8);   
        
        // creation des boutons
        connect = new JButton("Ok");
        
        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        p11 = new JPanel();
        p12 = new JPanel();
        p13 = new JPanel();
        p2 = new JPanel();
        
        // mise en page des panneaux
       /* p0.setLayout(new GridLayout(1, 10));
        p1.setLayout(new GridLayout(2, 5));
        p2.setLayout(new GridLayout(3, 15));*/
        
        // ajout des objets graphqiues dans les panneaux
        p0.add(titre);
        p11.add(nomBDD);
        p11.add(nomBDDTexte);
        p12.add(loginBDD);
        p12.add(loginBDDTexte);
        p13.add(passwdBDD);
        p13.add(passwdBDDTexte);
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
        p1.add(p11);
        p1.add(p12);
        p1.add(p13);
        p2.add(connect);

        // ajout des listeners
        nomBDDTexte.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passwdBDDTexte.addActionListener(this);
        connect.addActionListener(this);
        
        // disposition geographique des panneaux
        add("North", p0);
        add("Center",p1);
        add("South",p2);

        this.setVisible(true);      

        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }
    
    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param evt
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();

        // tester cas de la commande evenementielle
        if (source == connect) {
            String nomBDDString = new String(nomBDDTexte.getText());
            String loginBDDString = new String(loginBDDTexte.getText());
            String passwdBDDString = new String(passwdBDDTexte.getPassword());
            System.out.println("nom BDD = " + nomBDDString);
            System.out.println("lgin BDD = " + loginBDDString);
            System.out.println("passwd BDD = " + passwdBDDString);
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    //maconnexion = new Connexion(nomBDDString, loginBDDString, passwdBDDString);
                    maconnexion = new Connexion("hopital", "root", "");
                    //debugg
                    System.out.println("connexion ok");
                    //on cache la premiere fenetre
                    //this.setVisible(false);
                    FenetreMenu fen = new FenetreMenu(this);
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        } 

    }

    /**
     *
     * Pour gerer les actions sur items d'une liste on utilise la methode
     * itemStateChanged
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void itemStateChanged(ItemEvent evt) { 
    }
}
