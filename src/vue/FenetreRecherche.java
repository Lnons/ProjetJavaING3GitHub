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
    private final String[] table = {"Choisissez une table...","chambre", "docteur", "employe","hospitalisation", "infirmier",
            "malade","service", "soigne"};
    private final String[] requete = {"Choisissez une requete...",
        "R1. Prenom et nom des malades affilies a la mutuelle « MAAF »",
        "R2. Prenom et nom des infirmiers travaillant pendant la nuit",
        "R3. Nom et batiment pour chaque service, ainsi que le prenom, nom et specialite du directeur du service",
        "R4. Informations sur un malade affilié a une mutuelle dont le nom commence par « MN... », au batiment « B »",
        "R5. Moyenne des salaires des infirmiers par service ",
        "R6. Nombre moyen de lits par chambre pour chaque service du batiment « A » ",
        "R7. Nombre total de médecins pour chaque malade soigné, et \n" +
            "nombre correspondant de spécialités médicales concernées",
        "R8. Rapport entre le nombre d’infirmiers affectés au service\n" +
            " et le nombre de malades hospitalisés dans le service",
        "R9. Prénom et nom des docteurs ayant au moins un malade hospitalisé",
        "R10. Prénom et nom des docteurs n’ayant aucun malade hospitalisé"};
    private final String[] choix = {"Attributs..."};
    private final JComboBox combo_table1 = new JComboBox(table);
    private final JComboBox combo_requete = new JComboBox(requete);
    private JComboBox combo_attribut1 = new JComboBox(choix);
    private final JButton executer = new JButton("Executer");
    private JTextField texte = new JTextField(10);
    
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
        texte.setPreferredSize( new Dimension( 200, 24 ) );
        
        //on crée notre bouton retour
        b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(retour);
        retour.addActionListener(this);
              
        
        //--------REQUETTE--------------
     
        combo_attribut1.setPreferredSize(new Dimension(100, 25));
        //on crée notre panel 1 associé a la table 1
        b1 = new JPanel();
        b1.add(combo_table1);
        b1.add(combo_attribut1);
        b1.add(texte);
        b1.add(rechercher);
        combo_table1.addActionListener(this);
        combo_attribut1.addActionListener(this);
        rechercher.addActionListener(this);
        texte.addActionListener(this);
        
        //on crée notre panel 2 associé a la table 2
        b2 = new JPanel();
        b2.add(combo_requete);
        b2.add(executer);
        executer.addActionListener(this);
        combo_requete.addActionListener(this);
        
        //on affiche le tout sur la fenetre
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
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
        }
        if (source == executer) {
            System.out.println("executer");
            System.out.println("ActionListener : action sur " + combo_requete.getSelectedItem());
            try {
            FenetreRes resultat_recherche = new FenetreRes(combo_requete.getSelectedItem());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRecherche.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == combo_requete) {
                    System.out.println("ActionListener : action sur " + combo_requete.getSelectedItem());
        }
        if (source == rechercher) { 
            System.out.println(texte.getText());
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
