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

public class FenetreReporting extends JFrame implements ActionListener, ItemListener {
    private final JLabel titre;
    private final JButton retour = new JButton("Retour");
    
    public FenetreReporting(FenetreMenu fen)
    {
        // creation par heritage de la fenetre
        super("Fenetre Recherche");
        
        fen.dispose();
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on cadrille notre fenetre
        this.setLayout(new GridLayout(4,1));
        
        // creation des labels
        titre = new JLabel("Reporting", JLabel.CENTER);
        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,20);
        titre.setFont(font);
        
        //on crée notre bouton retour
        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(retour);
        retour.addActionListener(this);

        this.getContentPane().add(b0);
        
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS
        this.getContentPane().add(titre);

        //this.getContentPane().add(b4); 
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
        if (source == retour) {
                    this.dispose();
                    FenetreMenu fen = new FenetreMenu(new FenetreLogin());
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
