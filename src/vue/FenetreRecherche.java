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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FenetreRecherche extends JFrame implements ActionListener, ItemListener {
    private final JLabel titre;
    private final JButton retour = new JButton("Retour");
    private final JButton rechercher = new JButton("Rechercher");
    private JPanel b0,b1,b2,b3,b4;
    private final String[] table = {"chambre", "docteur", "employe","hospitalisation", "infirmier",
            "malade","service", "soigne"};
    private final JComboBox combo_table1 = new JComboBox(table);
    private final JComboBox combo_table2 = new JComboBox(table);
    private final JComboBox combo_table3 = new JComboBox(table);
    
    private JComboBox combo_attribut1 = new JComboBox();
    private JComboBox combo_attribut2 = new JComboBox();
    private JComboBox combo_attribut3 = new JComboBox();
    
    public FenetreRecherche(FenetreMenu fen)
    {
        // creation par heritage de la fenetre
        super("Recherche");
        
        //on ferme la precedente fenetre
        fen.dispose();
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on cadrille notre fenetre
        this.setLayout(new GridLayout(6,1));
        
        // creation des labels
        titre = new JLabel("Menu de Recherche", JLabel.CENTER);
        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,50);
        titre.setFont(font);   
        
        //on crée notre bouton retour
        b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(retour);
        retour.addActionListener(this);
        
        //on crée notre bouton rechercher
        b4 = new JPanel();
        b4.add(rechercher);
        rechercher.addActionListener(this);
        
        //--------REQUETTE--------------
        //combo.setPreferredSize(new Dimension(100, 20));
        //combo.setForeground(Color.blue);
     
        combo_attribut1.setPreferredSize(new Dimension(100, 25));
        combo_attribut2.setPreferredSize(new Dimension(100, 25));
        combo_attribut3.setPreferredSize(new Dimension(100, 25));
        
        //on crée notre panel 1 associé a la table 1
        b1 = new JPanel();
        b1.add(combo_table1);
        b1.add(combo_attribut1);
        combo_table1.addActionListener(this);
        combo_attribut1.addActionListener(this);
        
        //on crée notre panel 2 associé a la table 2
        b2 = new JPanel();
        b2.add(combo_table2);
        b2.add(combo_attribut2);
        combo_table2.addActionListener(this);
        
        //on crée notre panel 3 associé a la table 3
        b3 = new JPanel();
        b3.add(combo_table3);
        b3.add(combo_attribut3);
        combo_table3.addActionListener(this);
        
        //on affiche le tout sur la fenetre
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.getContentPane().add(b3);
        this.getContentPane().add(b4);
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
        if (source == combo_table1) {
                    System.out.println("ActionListener : action sur " + combo_table1.getSelectedItem());
                    affiche_comboBox(combo_table1.getSelectedItem(),combo_attribut1);
                    //System.out.println("ActionListener : action sur " + combo_attribut1.getSelectedItem());
        }
        if (source == combo_table2) {
                    System.out.println("ActionListener : action sur " + combo_table2.getSelectedItem());
                    affiche_comboBox(combo_table2.getSelectedItem(),combo_attribut2);
        }
        if (source == combo_table3) {
                    System.out.println("ActionListener : action sur " + combo_table3.getSelectedItem());
                    affiche_comboBox(combo_table3.getSelectedItem(),combo_attribut3);
        }
        if (source == rechercher) {
                    System.out.println("rechercher");
                    System.out.println("ComboBox table 1 " + combo_table1.getSelectedItem());
                    System.out.println("ComboBox attribut 1 " + combo_attribut1.getSelectedItem());
                    System.out.println("ComboBox table 2 " + combo_table2.getSelectedItem());
                    System.out.println("ComboBox attribut 2 " + combo_attribut2.getSelectedItem());
                    System.out.println("ComboBox table 3 " + combo_table3.getSelectedItem());
                    System.out.println("ComboBox attribut 3 " + combo_attribut3.getSelectedItem());
                    
            try {
                FenetreRes resultat_recherche = new FenetreRes(combo_table1.getSelectedItem());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRecherche.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void affiche_comboBox(Object source, JComboBox combo) { 
        combo.removeAllItems();
        
        if(source=="chambre")
        {
            combo.addItem("NO_CHAMBRE");
            combo.addItem("CODE_SERVICE");
            combo.addItem("SURVEILLANT");
            combo.addItem("NB_LITS");
        }
        if(source=="docteur")
        {
            combo.addItem("numero");
            combo.addItem("specialite");
        }
        if(source=="employe")
        {
            combo.addItem("numero");
            combo.addItem("NOM");
            combo.addItem("PRENOM");
            combo.addItem("TEL");
            combo.addItem("ADRESSE");
        }
        if(source=="hospitalisation")
        {
            combo.addItem("NO_MALADE");
            combo.addItem("CODE_SERVICE");
            combo.addItem("NO_CHAMBRE");
            combo.addItem("LIT");
        }
        if(source=="infirmier")
        {
            combo.addItem("NUMERO");
            combo.addItem("CODE_SERVICE");
            combo.addItem("ROTATION");
            combo.addItem("SALAIRE");
        }
        if(source=="malade")
        {
            combo.addItem("NUMERO");
            combo.addItem("NOM");
            combo.addItem("PRENOM");
            combo.addItem("TEL");
            combo.addItem("ADRESSE");
            combo.addItem("MUTUELLE");
        }
        if(source=="service")
        {
            combo.addItem("CODE");
            combo.addItem("NOM");
            combo.addItem("BATIMENT");
            combo.addItem("DIRECTEUR");
        }
        if(source=="soigne")
        {
            combo.addItem("NO_DOCTEUR");
            combo.addItem("NO_MALADE");
        }
    }
}
