/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controleur.*;

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

public class FenetreMenu extends JFrame implements ActionListener, ItemListener {
    private final JButton recherche = new JButton("Recherche");
    private final JButton maj = new JButton("Mise à jour");
    private final JButton reporting = new JButton("Reporting");
    private final JLabel titre;
    
    public FenetreMenu(FenetreLogin fen)
    {
        // creation par heritage de la fenetre
        super("Fenetre Menu");

        //on ferme la precedente fenetre
        fen.dispose();     
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on quadrille notre fenetre
        this.setLayout(new GridLayout(5,1));
        
        // creation des labels
        titre = new JLabel("Menu de l'Hopital", JLabel.CENTER);
        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,20);
        titre.setFont(font);    
       
        //on crée nos labels         
        JPanel b1 = new JPanel();
        b1.add(recherche);

        JPanel b2 = new JPanel();
        b2.add(maj);

        JPanel b3 = new JPanel();
        b3.add(reporting);
        
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS 
        this.getContentPane().add(titre);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.getContentPane().add(b3);
 
        this.setVisible(true);
        
        // ajout des listeners
        recherche.addActionListener(this);
        maj.addActionListener(this);
        reporting.addActionListener(this);
        
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

        //on ouvre une fenetre en fonction du bouton sélectionné
        if (source == recherche) {
                    FenetreRecherche fen = new FenetreRecherche(this);
        } 
        if (source == maj) {
            try {
                FenetreMaj fen = new FenetreMaj(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if (source == reporting) {
            try {
                FenetreReporting fen = new FenetreReporting(this);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMenu.class.getName()).log(Level.SEVERE, null, ex);
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
