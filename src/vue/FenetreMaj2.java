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
    private final JLabel titre;
    private JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8;
    private final JButton fermer = new JButton("fermer");
    private final JPanel b0;
    private final JButton terminer = new JButton("Valider");;
    private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9;
    public String champ_rempli[] = new String[30];
    private JTextField champ1 = new JTextField(8);
    private JTextField champ2 = new JTextField(8);
    private JTextField champ3 = new JTextField(8);
    private JTextField champ4 = new JTextField(8);
    private JTextField champ5 = new JTextField(8);
    private JTextField champ6 = new JTextField(8);
    private JTextField champ7 = new JTextField(8);
    private JTextField champ8 = new JTextField(8);
    private Object source_prec=null;
    
    public FenetreMaj2(Object source)
    {
        // creation par heritage de la fenetre
        super("Fenetre Maj");
        
        source_prec=source;
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 500);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on cadrille notre fenetre
        this.setLayout(new GridLayout(9,1));

        // creation des labels
        titre = new JLabel("Ajout", JLabel.CENTER);
               
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,15);
        titre.setFont(font);
        
        //on crée notre bouton retour
        b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(fermer);
        fermer.addActionListener(this);
                       
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
        ajout(source);
        
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
        if (source == terminer) {
           
            try {
                verification_ajout(source_prec);
                creation_objet(source_prec);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreMaj2.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public void verification_ajout(Object source)
    {   
        boolean blindage=false;
        
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
            System.out.println("oui");
            for(int i=0 ; i<4 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
        }
        else if(source=="docteur")
        {
            for(int i=0 ; i<6 ; i++)
            {
                if(champ_rempli[i].equals(""))
                {
                    blindage=true;
                }
            }
        }
        else if(source=="employe")
        {
            System.out.println("oui");
            for(int i=0 ; i<5 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
        }
        else if(source=="hospitalisation")
        {
            System.out.println("oui");
            for(int i=0 ; i<4 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
        }
        else if(source=="infirmier")
        {
            System.out.println("oui");
            for(int i=0 ; i<8 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
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
        }
        else if(source=="service")
        {
            for(int i=0 ; i<4 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
        }
        else if(source=="soigne")
        {
            for(int i=0 ; i<2 ; i++)
            {
                if(champ_rempli[i]=="")
                {
                    blindage=true;
                }
            }
        }
        else
            blindage=true;
        
        if(blindage==false)
        {
            JOptionPane.showMessageDialog(this,"Ajout effectué ! ","Enregistré",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(this,"Attention à bien remplir tous les champs ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);        
    }
    
    
    public void creation_objet(Object source) throws SQLException, ClassNotFoundException
    {
        if(source=="chambre")
        {
            System.out.println("oui");
        }
        else if(source=="docteur")
        {
            //docteur doc = new docteur (champ_rempli[0], champ_rempli[1],champ_rempli[2],
            //        champ_rempli[3],champ_rempli[4],champ_rempli[5]);
            //doc.addDocteur();
        }
        else if(source=="employe")
        {
            System.out.println("oui");
        }
        else if(source=="hospitalisation")
        {
            System.out.println("oui");
        }
        else if(source=="infirmier")
        {
            System.out.println("oui");
        }
        else if(source=="malade")
        {
            System.out.println("oui");
        }
        else if(source=="service")
        {
            System.out.println("oui");
        }
        else if(source=="soigne")
        {
            System.out.println("oui");
        }
        else
            JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
    
    }
    
    public void valid_float(String s)
    {
        
    }
    
    public void valid_int(String s)
    {
        
    }
    
    public void ajout(Object source)
    {
        if(source=="chambre")
        {
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

            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p7.add(terminer);

            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p7);
        }
        else if(source=="docteur")
        {
            lab1 = new JLabel("Numero", JLabel.CENTER);
            lab2 = new JLabel("Nom", JLabel.CENTER);
            lab3 = new JLabel("Prenom", JLabel.CENTER);
            lab4 = new JLabel("Tel", JLabel.CENTER);
            lab5 = new JLabel("Adresse", JLabel.CENTER);
            lab6 = new JLabel("Specialite", JLabel.CENTER); 

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();
            p6 = new JPanel();
            p7 = new JPanel();

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
            lab1 = new JLabel("Numero", JLabel.CENTER);
            lab2 = new JLabel("Nom", JLabel.CENTER);
            lab3 = new JLabel("Prenom", JLabel.CENTER);
            lab4 = new JLabel("Tel", JLabel.CENTER);
            lab5 = new JLabel("Adresse", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();
            p7 = new JPanel();

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
            p7.add(terminer);

            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p5);
            this.add(p7);
        }
        else if(source=="hospitalisation")
        {
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
            
            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p7.add(terminer);

            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p7);
        }
        else if(source=="infirmier")
        {
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
            p7.add(lab7);
            p7.add(champ7);
            p8.add(lab8);
            p8.add(champ8);
            p9.add(terminer);
            
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
            lab1 = new JLabel("Code", JLabel.CENTER);
            lab2 = new JLabel("Nom", JLabel.CENTER);
            lab3 = new JLabel("Batiment", JLabel.CENTER);
            lab4 = new JLabel("Directeur", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();
            p4 = new JPanel();
            p5 = new JPanel();

            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(lab3);
            p3.add(champ3);
            p4.add(lab4);
            p4.add(champ4);
            p5.add(terminer);
            
            this.add(p1);
            this.add(p2);
            this.add(p3);
            this.add(p4);
            this.add(p5);
        }
        else if(source=="soigne")
        {
            lab1 = new JLabel("Numero docteur", JLabel.CENTER);
            lab2 = new JLabel("Numero malade", JLabel.CENTER);

            // creation des panneaux
            p1 = new JPanel();
            p2 = new JPanel();
            p3 = new JPanel();

            p1.add(lab1);
            p1.add(champ1);
            p2.add(lab2);
            p2.add(champ2);
            p3.add(terminer);
            
            this.add(p1);
            this.add(p2);
            this.add(p3);
        }
        else
            JOptionPane.showMessageDialog(this,"Modification impossible ! Veuillez réessayer ","Warning",JOptionPane.WARNING_MESSAGE);
        
        terminer.addActionListener(this);
    }
}
