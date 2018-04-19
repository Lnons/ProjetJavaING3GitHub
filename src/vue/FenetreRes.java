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

public final class FenetreRes extends JFrame implements ActionListener, ItemListener {
    private final JLabel titre;
    private JTextArea fenetreRes;
    private JComboBox resultat = new JComboBox();
    private final JButton fermer = new JButton("Fermer fenetre Resultat");
    private JPanel b0;
    private JTable tableau;
    private String title[];
    
    public FenetreRes(Object source) throws ClassNotFoundException
    {
        // creation par heritage de la fenetre
        super("Fenetre Resultat");
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on cadrille notre fenetre
        this.setLayout(new GridLayout(4,1));
        
        // creation des labels
        titre = new JLabel("Resultat : " + source, JLabel.CENTER);
        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,20);
        titre.setFont(font);   
        
        //on crée notre bouton
        b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(fermer);
        fermer.addActionListener(this);
        
        //on appele une methode qui va chercher notre requete dans base de donnée
        this.afficherLignes(source);
        
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
        //this.getContentPane().add(resultat);
        this.getContentPane().add(new JScrollPane(tableau));

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
        if (source == fermer) {
                    this.dispose();
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
    
    public void afficherLignes(Object source) throws ClassNotFoundException {
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
        
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec table SQL");
            e.printStackTrace();
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
