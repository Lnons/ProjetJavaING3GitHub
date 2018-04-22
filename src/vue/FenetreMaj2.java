/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controleur.*;
import modele.*;

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

public class FenetreMaj2 extends JFrame implements ActionListener, ItemListener {
    private Object source_prec=null;
    private JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8;
    private final JLabel titre;
    private final JButton fermer = new JButton("fermer");
    private final JButton terminer = new JButton("Valider");
    private final JButton supprimer = new JButton("Supprimer");
    private final JButton rechercher = new JButton("Rechercher un objet");
    private final JButton modifier = new JButton("Mettre à jour");
    private final JPanel b0;
    private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,b1,b2,b3;
    public String champ_rempli[] = new String[30];
    private JTextField champ1 = new JTextField(8);
    private JTextField champ2 = new JTextField(8);
    private JTextField champ3 = new JTextField(8);
    private JTextField champ4 = new JTextField(8);
    private JTextField champ5 = new JTextField(8);
    private JTextField champ6 = new JTextField(8);
    private JTextField champ7 = new JTextField(8);
    private JTextField champ8 = new JTextField(8);
    private JTextField texte = new JTextField(10);
    private final String[] string_attributs={"Attributs..."};
    private JComboBox combo_attribut = new JComboBox(string_attributs);  
    
    public FenetreMaj2(Object source, int choix)
    {
        // creation par heritage de la fenetre
        super("Fenetre Maj");
        
        //on sauvegarde le choix de la table précédent
        source_prec=source;
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 500);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on quadrille notre fenetre
        this.setLayout(new GridLayout(10,1));
        
        //on crée des labels (le titre) en fonction du choix dans la précédente fenetre
        if(choix==1)
        {
            titre = new JLabel("Ajout dans " + source, JLabel.CENTER);
        }   
        else if(choix==2)
        {
            titre = new JLabel("Suppression dans " + source, JLabel.CENTER);
        }
        else if(choix==3)
        {
            titre = new JLabel("Recherche de " + source + " existant", JLabel.CENTER);
        }
        else
            titre = new JLabel("Erreur ! Veuillez réessayer", JLabel.CENTER);
               
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,15);
        titre.setFont(font);
        
        //on crée notre bouton retour
        b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(fermer);
        fermer.addActionListener(this);
                       
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS et des labels
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
        
        //on ajoute, modifie ou supprime suivant le choix dans la précédente fenetre
        if(choix==1)
        {
            ajout(source);
        }   
        else if(choix==2)
        {
            supprimer(source);
        }
        else if(choix==3)
        {
            modifier(source);
        }
            
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
        if (source == combo_attribut) {
            //on definit une ComboBox en fonction de la table choisie
            if(source_prec.equals("chambre") || source_prec.equals("hospitalisation"))
                    champ2.setText((String)combo_attribut.getSelectedItem());
            if(source_prec=="infirmier" || source_prec=="docteur")
                    champ6.setText((String)combo_attribut.getSelectedItem());
        }
        if (source == terminer) {
            try {
                //on verifie puis on crée un objet dans la base de donnée
                verification_ajout(source_prec);
                creation_objet(source_prec);
                JOptionPane.showMessageDialog(this,"Ajout effectué ! ","Ajout",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,"Ajout impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,"Ajout impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
            }
            //on ferme la fenetre
            this.dispose();
        }
        if (source == supprimer) {
            try {
                //on vérifie puis on supprime un objet de la table
                verification_supprimer(source_prec);
                supprimer_objet(source_prec);
                JOptionPane.showMessageDialog(this,"Suppression effectuée ! ","Suppression",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,"Suppression impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,"Suppression impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
            }
            //on ferme la fenetre
            this.dispose();
        }
        if (source == rechercher) {
            try {
                //on vérifie si l'objet est existant et si on peut le modifier
                verification_modifier(source_prec);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == modifier) {
            //on définit l'attribut à modifier et sa nouvelle valeur
            String texte_maj = new String(texte.getText());
            String attribut_choisi = new String((String) combo_attribut.getSelectedItem());
            //il faut que le champ soit non vide
            if(texte_maj.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Attention à bien remplir les champs au bon format ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);                   
            }
            else
            {
                try {
                    if(modifier_objet(source_prec, texte_maj,attribut_choisi)==true)
                    {
                        JOptionPane.showMessageDialog(this,"Modification effectuée ! ","Modification",JOptionPane.INFORMATION_MESSAGE);
                        //on ferme la fenetre
                        this.dispose();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
                }
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
    
    //on vérifie si les champs tapés par l'utilisateur sont corrects
    public void verification_ajout(Object source)
    {   
        boolean blindage=false;
        
        //les champs doivent etre différents de vide
        if (!champ1.getText().equals("null"))
           champ_rempli[0]=champ1.getText();
        if (!champ2.getText().equals("null"))
           champ_rempli[1]=champ2.getText(); 
        if (!champ3.getText().equals("null"))
           champ_rempli[2]=champ3.getText(); 
        if (!champ4.getText().equals("null"))
           champ_rempli[3]=champ4.getText(); 
        if (!champ5.getText().equals("null"))
           champ_rempli[4]=champ5.getText(); 
        if (!champ6.getText().equals("null"))
           champ_rempli[5]=champ6.getText(); 
        if (!champ7.getText().equals("null"))
           champ_rempli[6]=champ7.getText(); 
        if (!champ8.getText().equals("null"))
           champ_rempli[7]=champ8.getText(); 
        
        if(source=="chambre")
        {
            for(int i=0 ; i<4 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
            }
            if(valid_int(champ_rempli[0])==true || valid_int(champ_rempli[3]))
                blindage=true;
        }
        else if(source=="docteur")
        {
            for(int i=0 ; i<6 ; i++)
            {
                if(champ_rempli[i].equals(""))
                {
                    blindage=true;
                }
                if(valid_int(champ_rempli[0])==true)
                    blindage=true;
            }
        }
        else if(source=="hospitalisation")
        {
            for(int i=0 ; i<4 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
            }
            if(valid_int(champ_rempli[0])==true || valid_int(champ_rempli[2])==true || valid_int(champ_rempli[3])==true)
                    blindage=true;
        }
        else if(source=="infirmier")
        {;
            for(int i=0 ; i<8 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
            if(valid_int(champ_rempli[0])==true || valid_int(champ_rempli[5])==true)
                    blindage=true;
            if(valid_float(champ_rempli[7])==true)
                    blindage=true;
            if(valid_bool(champ_rempli[6])==true)
                    blindage=true;
        }
        else if(source=="malade")
        {
            for(int i=0 ; i<6 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
            if(valid_int(champ_rempli[0])==true)
                    blindage=true;
        }
        else if(source=="soigne")
        {
            for(int i=0 ; i<2 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
                if(valid_int(champ_rempli[i])==true)
                    blindage=true;
            }
        }
        else
            blindage=true;
        
        if(blindage==true)
            JOptionPane.showMessageDialog(this,"Attention à bien remplir tous les champs et au bon format ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);        
    }
    
    //on crée notre objet que l'on va rentrer ensuite dans la base de donnée
    public void creation_objet(Object source) throws SQLException, ClassNotFoundException
    {
        if(source=="chambre")
        {
            chambre cha = new chambre (champ_rempli[0], champ_rempli[1],champ_rempli[2],
                    champ_rempli[3]);
            cha.addChambre();
        }
        else if(source=="docteur")
        {
            docteur doc = new docteur (champ_rempli[0], champ_rempli[1],champ_rempli[2],
                    champ_rempli[3],champ_rempli[4],champ_rempli[5]);
            doc.addDocteur();
        }
        else if(source=="hospitalisation")
        {
            hospitalisation hos = new hospitalisation (champ_rempli[0], champ_rempli[1],champ_rempli[2],
                    champ_rempli[3]);
            hos.addHospitalisation();
        }
        else if(source=="infirmier")
        {
            infirmier inf = new infirmier (champ_rempli[0], champ_rempli[1],champ_rempli[2],
                    champ_rempli[3],champ_rempli[4],champ_rempli[5],champ_rempli[6],champ_rempli[7]);
            inf.addInfirmier();
        }
        else if(source=="malade")
        {
            malade mal = new malade (champ_rempli[0], champ_rempli[1],champ_rempli[2],
                    champ_rempli[3],champ_rempli[4],champ_rempli[5]);
            mal.addMalade();
        }
        else if(source=="soigne")
        {
            soigne soi = new soigne (champ_rempli[0], champ_rempli[1]);
            soi.addSoigne();
        }
        else
            JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
    
    }
    
    //on vérifie si les champs tapés par l'utilisateur sont corrects
    public void verification_supprimer(Object source)
    {   
        boolean blindage=false;
        
        if (!champ1.getText().equals("null"))
           champ_rempli[0]=champ1.getText();
        if (!champ2.getText().equals("null"))
           champ_rempli[1]=champ2.getText(); 
        
        if(source=="chambre")
        {
            for(int i=0 ; i<2 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
            }
            if(valid_int(champ_rempli[0])==true)
                blindage=true;
        }
        else if(source=="docteur")
        {
            if(champ_rempli[0].equals(""))
            {
                blindage=true;
            }
            if(valid_int(champ_rempli[0])==true)
                blindage=true;
            
        }
        else if(source=="hospitalisation")
        {
            if(champ_rempli[0]=="")
                blindage=true;
            if(valid_int(champ_rempli[0])==true)
                blindage=true;
            
        }
        else if(source=="infirmier")
        {
            if(champ_rempli[0]=="")
            {
                blindage=true;
            }
            
            if(valid_int(champ_rempli[0])==true)
                    blindage=true;
        }
        else if(source=="malade")
        {
            if(champ_rempli[0]=="")
            {
                blindage=true;
            }            
            if(valid_int(champ_rempli[0])==true)
                    blindage=true;
        }
        else if(source=="soigne")
        {
            for(int i=0 ; i<2 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
                if(valid_int(champ_rempli[i])==true)
                    blindage=true;
            }
        }
        else
            blindage=true;
        
        if(blindage==true)
            JOptionPane.showMessageDialog(this,"Attention à bien remplir les champs et au bon format ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);        
    }
    
    //on vérifie si les champs tapés par l'utilisateur sont corrects
    public void verification_modifier(Object source) throws SQLException, ClassNotFoundException
    {   
        boolean blindage=false;
        
        if (!champ1.getText().equals("null"))
           champ_rempli[0]=champ1.getText();
        if (!champ2.getText().equals("null"))
           champ_rempli[1]=champ2.getText(); 
        
        if(source=="chambre")
        {
            for(int i=0 ; i<2 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
            }
            if(valid_int(champ_rempli[0])==true)
                blindage=true;
        }
        else if(source=="docteur")
        {
            if(champ_rempli[0].equals(""))
            {
                blindage=true;
            }
            if(valid_int(champ_rempli[0])==true)
                blindage=true;
            
        }
        else if(source=="hospitalisation")
        {
            if(champ_rempli[0]=="")
                blindage=true;
            if(valid_int(champ_rempli[0])==true)
                blindage=true;
            
        }
        else if(source=="infirmier")
        {
            if(champ_rempli[0]=="")
            {
                blindage=true;
            }
            
            if(valid_int(champ_rempli[0])==true)
                    blindage=true;
        }
        else if(source=="malade")
        {
            if(champ_rempli[0]=="")
            {
                blindage=true;
            }            
            if(valid_int(champ_rempli[0])==true)
                    blindage=true;
        }
        else if(source=="soigne")
        {
            for(int i=0 ; i<2 ; i++)
            {
                if(champ_rempli[i]=="")
                    blindage=true;
                if(valid_int(champ_rempli[i])==true)
                    blindage=true;
            }
        }
        else
            blindage=true;
               
        if(blindage==false)
        {
            champ_modifier_objet(source);
        }
        else
            JOptionPane.showMessageDialog(this,"Attention à bien remplir les champs et au bon format ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);        
    }
    
    //on affiche une fenetre qui va permettre à l'utilisateur de modifier un objet dans une table
    public void champ_modifier_objet(Object source)throws SQLException, ClassNotFoundException
    {    
        //on definit la taille de la combobox
        combo_attribut.setPreferredSize(new Dimension(100, 25));
        
        //on définit un label et sa taille
        JLabel modif_titre = new JLabel("\nObjet trouvé ! Choisissez l'attribut à mettre à jour ");
        Font font = new Font("Arial",Font.BOLD,15);
        modif_titre.setFont(font);
        
        //on crée nos panels avec nos boutons et notre combobox
        b3 = new JPanel();
        b1 = new JPanel();
        b2 = new JPanel();
        b1.add(combo_attribut);
        b1.add(texte);
        b2.add(modifier);
        b3.add(modif_titre);
        affiche_comboBox(source,combo_attribut);
        combo_attribut.addActionListener(this);
        modifier.addActionListener(this);   
        
        //on affiche sur notre fenetre
        this.getContentPane().add(b3);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.setVisible(true);
    }
    
    //on crée par defaut un objet puis on le modifie dans la base de donnée
    public boolean modifier_objet(Object source, String champ_maj, String attribut_choisi) throws SQLException, ClassNotFoundException
    {        
        boolean valide=true;
        
        if(source=="chambre")
        {
            chambre cha=new chambre();
            cha.modifierChambre(champ_maj,attribut_choisi,champ_rempli[0]);
        }
        else if(source=="docteur")
        {
            docteur doc = new docteur();
            doc.modifierDocteur(champ_maj,attribut_choisi,champ_rempli[0]);
        }
        else if(source=="hospitalisation")
        {
            hospitalisation hos=new hospitalisation();
            hos.modifierHospitalisation(champ_maj, attribut_choisi, champ_rempli[0]);
        }
        else if(source=="infirmier")
        {
            infirmier inf=new infirmier();
            inf.modifierInfirmier(champ_maj, attribut_choisi, champ_rempli[0]);
        }
        else if(source=="malade")
        {
            malade mal=new malade();
            mal.modifierMalade(champ_maj, attribut_choisi, champ_rempli[0]);
        }
        else if(source=="soigne")
        {
            soigne soi=new soigne();
            soi.modifierSoigne(champ_maj, attribut_choisi, champ_rempli[0]);
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
            valide=false;
        }
        return valide; 
    }
    
    //affiche les attributs d'une table dans une combobox, selon la table choisie
    public void affiche_comboBox(Object source, JComboBox combo) { 
        //on la vide avant de la remplir a chaque fois
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
            combo.addItem("NUMERO");
            combo.addItem("NOM");
            combo.addItem("PRENOM");
            combo.addItem("TEL");
            combo.addItem("ADRESSE");
            combo.addItem("SPECIALITE");
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
            combo.addItem("NOM");
            combo.addItem("PRENOM");
            combo.addItem("TEL");
            combo.addItem("ADRESSE");
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
    
    //on supprime un objet de la table à partir de sa primary key, envoyée ici en paramètre dans chaque if
    public void supprimer_objet(Object source) throws SQLException, ClassNotFoundException
    {
        if(source=="chambre")
        {
            chambre cha =new chambre();
            cha.suppChambre(champ_rempli[0],champ_rempli[1]);
        }
        else if(source=="docteur")
        {
            docteur doc = new docteur();
            doc.suppDocteur(champ_rempli[0]);
        }
        else if(source=="hospitalisation")
        {
            hospitalisation hos = new hospitalisation();
            hos.suppHospitalisation(champ_rempli[0]);
        }
        else if(source=="infirmier")
        {
            infirmier inf= new infirmier();
            inf.suppInfirmier(champ_rempli[0]);
        }
        else if(source=="malade")
        {
            malade mal = new malade();
            mal.suppMalade(champ_rempli[0]);
        }
        else if(source=="soigne")
        {
            soigne soi = new soigne();
            soi.suppSoigne(champ_rempli[0]);
        }
        else
            JOptionPane.showMessageDialog(this,"Suppression impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
    }
    
    //vérifie si la valeur en parametre est bien un float
    public boolean valid_float(String s)
    {
        try{
            float a = new Float(s);
            return false;
        }
        catch(NumberFormatException a){
           return true;
        }
    }
    
    //vérifie si la valeur en parametre est bien un int
    public boolean valid_int(String s)
    {
        try{
            int a = new Integer(s);
            return false;
        }
        catch(NumberFormatException a){
            //JOptionPane.showMessageDialog(this,"Attention à remplir les champs au bon format","Warning",JOptionPane.WARNING_MESSAGE);
            return true;
        }
    }
    
    //vérifie si la valeur en parametre est bien JOUR ou NUIT (pour infirmier)
    public boolean valid_bool(String s)
    {
        if(s.equals("JOUR") || s.equals("NUIT"))
            return false;
        return true;
    }
    
    //graphique qui s'affiche lorsque l'on veut ajouter un objet à la bdd
    //on place tous nos panels et boutons selon la table choisie dans la précédente fenetre
    public void ajout(Object source)
    {
        if(source=="chambre")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero chambre", JLabel.CENTER);
            lab2 = new JLabel("Code service", JLabel.CENTER);
            lab3 = new JLabel("Surveillant", JLabel.CENTER);
            lab4 = new JLabel("Nombre lits", JLabel.CENTER);  

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p7 = new JPanel();
            
            //on initialise notre comboBox
            combo_attribut.removeAllItems();
            String [] chambre = {"REA","CHG","CAR"};
            combo_attribut = new JComboBox(chambre);

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(combo_attribut);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p7.add(terminer);
            combo_attribut.addActionListener(this);
           
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p7);
        }
        else if(source=="docteur")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);
            lab2 = new JLabel("Nom", JLabel.CENTER);
            lab3 = new JLabel("Prenom", JLabel.CENTER);
            lab4 = new JLabel("Adresse", JLabel.CENTER);
            lab5 = new JLabel("Tel", JLabel.CENTER);
            lab6 = new JLabel("Specialite", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();
            p6 = new JPanel();
            p7 = new JPanel();
            
            //on initialise notre comboBox
            combo_attribut.removeAllItems();
            String [] docteur = {"Anesthesiste","Cardiologue","Orthopediste","Pneumologue","Radiologue","Traumatologue"};
            combo_attribut = new JComboBox(docteur);

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p5.add(lab5);
            p5.add(champ5);
            p6.add(lab6);
            p6.add(combo_attribut);
            p7.add(terminer);
            combo_attribut.addActionListener(this);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p5);
            this.add(p6);
            this.add(p7);
        }
        else if(source=="employe")
        {
            JOptionPane.showMessageDialog(this,"Impossible d'ajouter un Employé ! Veuillez créer un Employé à partir de la table Docteur ou Infirmier ","Warning",JOptionPane.WARNING_MESSAGE);        
        }
        else if(source=="hospitalisation")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero malade", JLabel.CENTER);
            lab2 = new JLabel("Code service", JLabel.CENTER);
            lab3 = new JLabel("Numero chambre", JLabel.CENTER);
            lab4 = new JLabel("Lit", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p7 = new JPanel();
            
            //on initialise notre comboBox
            combo_attribut.removeAllItems();
            String [] chambre = {"REA","CHG","CAR"};
            combo_attribut = new JComboBox(chambre);
            
            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(combo_attribut);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p7.add(terminer);
            combo_attribut.addActionListener(this);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p7);
        }
        else if(source=="infirmier")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);
            lab2 = new JLabel("Nom", JLabel.CENTER);
            lab3 = new JLabel("Prenom", JLabel.CENTER);
            lab4 = new JLabel("Tel", JLabel.CENTER);
            lab5 = new JLabel("Adresse", JLabel.CENTER);
            lab6 = new JLabel("Code service", JLabel.CENTER);  
            lab7 = new JLabel("Rotation", JLabel.CENTER); 
            lab8 = new JLabel("Salaire", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();
            p6 = new JPanel();
            p7 = new JPanel();
            p8 = new JPanel();
            p9 = new JPanel();
            
            //on initialise notre comboBox
            combo_attribut.removeAllItems();
            String [] chambre = {"REA","CHG","CAR"};
            combo_attribut = new JComboBox(chambre);

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p5.add(lab5);
            p5.add(champ5);
            p6.add(lab6);
            p6.add(combo_attribut);
            p7.add(lab7);
            p7.add(champ7);
            p8.add(lab8);
            p8.add(champ8);
            p9.add(terminer);
            combo_attribut.addActionListener(this);
            
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p5);
            this.add(p6);
            this.add(p7);
            this.add(p8);
            this.add(p9);
        }
        else if(source=="malade")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);
            lab2 = new JLabel("Nom", JLabel.CENTER);
            lab3 = new JLabel("Prenom", JLabel.CENTER);
            lab4 = new JLabel("Tel", JLabel.CENTER);
            lab5 = new JLabel("Adresse", JLabel.CENTER);
            lab6 = new JLabel("Mutuelle", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();
            p6 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p5.add(lab5);
            p5.add(champ5);
            p6.add(lab6);
            p6.add(champ6);
            p7.add(terminer);
            
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p5);
            this.add(p6);
            this.add(p7);
        }
        else if(source=="service")
        {
            JOptionPane.showMessageDialog(this,"Donnée privée ! Impossible d'ajouter un Service  ","Warning",JOptionPane.WARNING_MESSAGE);        
        }
        else if(source=="soigne")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero docteur", JLabel.CENTER);
            lab2 = new JLabel("Numero malade", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(terminer);
            
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
        }
        else
            JOptionPane.showMessageDialog(this,"Ajout impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
        
        terminer.addActionListener(this);
    }
    
    //graphique qui s'affiche lorsque l'on veut supprimer un objet à la bdd
    //on place tous nos panels et boutons selon la table choisie dans la précédente fenetre
    public void supprimer(Object source)
    {
        if(source=="chambre")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero chambre", JLabel.CENTER);
            lab2 = new JLabel("Code service", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p7.add(supprimer);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p7);
        }
        else if(source=="docteur")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p7.add(supprimer);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p7);
        }
        else if(source=="employe")
        {
            JOptionPane.showMessageDialog(this,"Impossible de supprimer un Employé ! Veuillez supprimer un Employé à partir de la table Docteur ou Infirmier ","Warning",JOptionPane.WARNING_MESSAGE);        
        }
        else if(source=="hospitalisation")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero malade", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p7 = new JPanel();
         
            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p7.add(supprimer);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p7);
        }
        else if(source=="infirmier")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p9 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p9.add(supprimer);
           
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p9);
        }
        else if(source=="malade")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p7.add(supprimer);
          
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p7);
        }
        else if(source=="service")
        {
            JOptionPane.showMessageDialog(this,"Donnée privée ! Impossible de supprimer un Service  ","Warning",JOptionPane.WARNING_MESSAGE);        
        }
        else if(source=="soigne")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero docteur", JLabel.CENTER);
            lab2 = new JLabel("Numero malade", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(supprimer);
         
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
        }
        else
            JOptionPane.showMessageDialog(this,"Suppression impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
        
        supprimer.addActionListener(this);
    }
    
    //graphique qui s'affiche lorsque l'on veut modifier un objet à la bdd
    //on place tous nos panels et boutons selon la table choisie dans la précédente fenetre
    public void modifier(Object source)
    {
        if(source=="chambre")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero chambre", JLabel.CENTER);
            lab2 = new JLabel("Code service", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p7.add(rechercher);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p7);
        }
        else if(source=="docteur")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);
            
            // creation des panneaux
            p1 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p7.add(rechercher);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p7);
        }
        else if(source=="employe")
        {
            JOptionPane.showMessageDialog(this,"Impossible de modifier un Employé ! Veuillez modifier un Employé à partir de la table Docteur ou Infirmier ","Warning",JOptionPane.WARNING_MESSAGE);        
        }
        else if(source=="hospitalisation")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero malade", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p7 = new JPanel();
   
            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p7.add(rechercher);

            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p7);
        }
        else if(source=="infirmier")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p9 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p9.add(rechercher);
          
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p9);
        }
        else if(source=="malade")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p7 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p7.add(rechercher);
         
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p7);
        }
        else if(source=="service")
        {
            JOptionPane.showMessageDialog(this,"Donnée privée ! Impossible de modifier un Service  ","Warning",JOptionPane.WARNING_MESSAGE);        
        }
        else if(source=="soigne")
        {
            //on crée nos labels
            lab1 = new JLabel("Numero docteur", JLabel.CENTER);
            lab2 = new JLabel("Numero malade", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();

            //on place nos labels sur nos panels
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(rechercher);
          
            //on place nos panels sur la fenetre
            this.add(p1);
            this.add(p2);
            this.add(p3);
        }
        else
            JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
        
        rechercher.addActionListener(this);
    }   
}
