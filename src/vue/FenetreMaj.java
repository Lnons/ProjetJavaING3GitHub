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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FenetreMaj extends JFrame implements ActionListener, ItemListener {
    private final JButton retour = new JButton("Retour");
    private final JLabel titre;
    private final String[] table = {"Choisissez une table...","chambre", "docteur", "employe","hospitalisation", "infirmier",
            "malade","service", "soigne"};
    private final JComboBox combo_table = new JComboBox(table);
    private JPanel b1;
    private JPanel b2 = new JPanel();
    private String title[];
    private JTable tableau;
    private final JButton ajouter = new JButton("Ajouter un objet à la table");
    private final JButton afficher = new JButton("Afficher la table");
    
    public FenetreMaj(FenetreMenu fen) throws ClassNotFoundException
    {
        // creation par heritage de la fenetre
        super("Fenetre Mise à jour");
        
        fen.dispose();
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on cadrille notre fenetre
        this.setLayout(new GridLayout(5,1));
        
        // creation des labels
        titre = new JLabel("Mise à jour", JLabel.CENTER);
        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,30);
        titre.setFont(font);    
        
        //on crée notre bouton retour
        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(retour);
        retour.addActionListener(this);
        
        //on crée notre panel 1 associé a la table 1
        JPanel b1 = new JPanel();
        b1.add(combo_table);
        b1.add(afficher);
        b1.add(ajouter);
        ajouter.addActionListener(this);
        afficher.addActionListener(this);
        combo_table.addActionListener(this);
        
        this.getContentPane().add(b0);
        
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS
        this.getContentPane().add(titre);
        this.getContentPane().add(b1);
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
        if (source == ajouter) {
                    FenetreMaj2 fen = new FenetreMaj2(combo_table.getSelectedItem());
        }
        if (source == afficher) {  
            try {
                affiche_table(combo_table.getSelectedItem());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMaj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == combo_table) {
                    System.out.println("ActionListener : action sur " + combo_table.getSelectedItem());
            
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
    
    public void affiche_table(Object source) throws ClassNotFoundException
    {
        int taille_tableau=taille_table(source);
        title= new String[taille_tableau];
        try {
            int i=0;
            ArrayList<String> liste;
            Connexion maconnexion = new Connexion("hopital","root","");

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + source + ";";
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);
            Object[][]data = new Object[liste.size()][taille_tableau];
            
            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                //resultat.addItem(liste1);
                for(int j=0 ; j<taille_table(source) ; j++)
                {
                    data[i][j]=separe_colonne(source,liste1,taille_tableau)[j]; 
                }   
                i++;
            } 
            title=entete(source);                    
            tableau = new JTable(data, title);
            
            b2.removeAll();
            b2.setLayout(new BorderLayout());
            b2.add(new JScrollPane(tableau));
            this.getContentPane().add(b2);
            this.setVisible(true);
        } catch (SQLException e) {
        }
    }
    
    public String[] separe_colonne(Object source, String chaine, int taille)
    {
        int i=0;
        String tableau[]=new String[taille];
        tableau[0]="";
        int cpt=0;
        if(source=="malade" || source=="employe")
        {
            for(int k=0 ; k<taille ; k++)
            {
                tableau[k]="";
            }
            for(int j=0 ; j<chaine.length() ; j++)
            {
                if(chaine.charAt(j) != ',')
                {
                    tableau[i]+=chaine.charAt(j);
                }
                if(chaine.charAt(j) == ',')
                {
                    cpt++;
                    if (cpt==4)
                    {   
                        tableau[i]+=chaine.charAt(j);   
                    }
                    else
                        i++;
                }
            }
        }
        else{
        for(int k=0 ; k<taille ; k++)
        {
            tableau[k]="";
        }
        for(int j=0 ; j<chaine.length() ; j++)
        {
            if(chaine.charAt(j) != ',')
            {
                tableau[i]+=chaine.charAt(j);
            }
            if(chaine.charAt(j) == ',')
            {
                i++;
            }
        } 
        }
        return tableau;
    }
    
    public int taille_table (Object source)
    {
        int taille=0;
        if(source=="chambre")
        {
            taille=4;
        }
        if(source=="docteur")
        {
            taille=2;
        }
        if(source=="employe")
        {
            taille=5;
        }
        if(source=="hospitalisation")
        {
            taille=4;
        }
        if(source=="infirmier")
        {
            taille=4;
        }
        if(source=="malade")
        {
            taille=6;
        }
        if(source=="service")
        {
            taille=4;
        }
        if(source=="soigne")
        {
            taille=4;
        }
        return taille;
    }
    
    public String[] entete(Object source)
    {      
        String defaut[]={"",""};
        if(source=="chambre")
        {   
            String entete[]={"NO_CHAMBRE","CODE_SERVICE","SURVEILLANT","NB_LITS"};
            return entete;
        }
        if(source=="docteur")
        {
            String entete[]={"numero","specialite"};
            return entete;
        }
        if(source=="employe")
        {
            String entete[]={"numero","NOM","PRENOM","ADRESSE","TEL"};
            return entete;
        }
        if(source=="hospitalisation")
        {
            String entete[]={"NO_MALADE","CODE_SERVICE","NO_CHAMBRE","LIT"};
            return entete;
        }
        if(source=="infirmier")
        {
            String entete[]={"NUMERO","CODE_SERVICE","ROTATION","SALAIRE"};
            return entete;
        }
        if(source=="malade")
        {
            String entete[]={"numero","NOM","PRENOM","ADRESSE","TEL","MUTUELLE"};
            return entete;
        }
        if(source=="service")
        {
            String entete[]={"CODE","NOM","BATIMENT","DIRECTEUR"};
            return entete;
        }
        if(source=="soigne")
        {
            String entete[]={"NO_DOCTEUR","NO_MALADE"};
            return entete;
        }
        return defaut;
    }
}
