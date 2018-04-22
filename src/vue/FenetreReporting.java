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

//JFreechart
import java.awt.*; 
import java.awt.event.*; 
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*; 
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.*; 

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class FenetreReporting extends JFrame implements ActionListener, ItemListener {
    private int choix;
    private final JLabel titre, texte;
    private final JButton retour = new JButton("Retour");
    private final JButton spec_docteur = new JButton("Spécialité des Docteurs");
    private final JButton infirmier_service = new JButton("Répartitions des Infirmiers par service");
    private final JButton malade_service = new JButton("Nombre de malades par service");
    private final JButton chambre_service = new JButton("Nombre de chambres par service");
    private final JButton infirmier_rotation = new JButton("Répartitions Jour/Nuit des Infirmiers");
    private final Connexion maconnexion = new Connexion("hopital","root","");
    private final String[] cam_bat = {"Diagramme Camembert","Diagramme Bâton"};
    private final JComboBox choix_diagramme = new JComboBox(cam_bat);
    
    public FenetreReporting(FenetreMenu fen) throws SQLException, ClassNotFoundException
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
        this.setLayout(new GridLayout(9,1));
        
        // creation des labels
        titre = new JLabel("Reporting", JLabel.CENTER);
        texte = new JLabel("Sélectionnez votre type de diagramme, puis choisissez une requete à afficher : ");
        
        //taille des labels et boutons
        Font font = new Font("Arial",Font.BOLD,20);
        titre.setFont(font);
        spec_docteur.setPreferredSize(new Dimension(250, 25));
        infirmier_service.setPreferredSize(new Dimension(250, 25));
        malade_service.setPreferredSize(new Dimension(250, 25));
        chambre_service.setPreferredSize(new Dimension(250, 25));
        infirmier_rotation.setPreferredSize(new Dimension(250, 25));
        
        //on crée notre bouton retour
        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(retour);
        retour.addActionListener(this);
        
        JPanel b4 = new JPanel();
        b4.add(texte);
        b4.add(choix_diagramme);
        
        JPanel b1 = new JPanel();
        b1.add(spec_docteur);

        JPanel b2 = new JPanel();
        b2.add(infirmier_service);

        JPanel b3 = new JPanel();
        b3.add(malade_service);
        
        JPanel b5 = new JPanel();
        b5.add(chambre_service);
        
        JPanel b6 = new JPanel();
        b6.add(infirmier_rotation);
        
        // ajout des listeners
        spec_docteur.addActionListener(this);
        infirmier_service.addActionListener(this);
        malade_service.addActionListener(this);
        chambre_service.addActionListener(this);
        infirmier_rotation.addActionListener(this);
        choix_diagramme.addActionListener(this);
                
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
        this.getContentPane().add(b4);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.getContentPane().add(b3);
        this.getContentPane().add(b5);
        this.getContentPane().add(b6);
        
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
        if (source == spec_docteur) {
            try {
                specialiteDocteur(choix);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == infirmier_service) {
            try {
                serviceInfirmier(choix);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == malade_service) {
            try {
                serviceMalade(choix);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == chambre_service) {
            try {
                serviceChambre(choix);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == infirmier_rotation) {
            try {
                rotationInfirmier(choix);
            } catch (SQLException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreReporting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source == choix_diagramme) {
                    System.out.println(choix_diagramme.getSelectedItem());
                    if(choix_diagramme.getSelectedItem().equals("Diagramme Bâton"))
                        choix = 1;
                    else
                        choix = 0;
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
    
    public void specialiteDocteur(int choix) throws SQLException, ClassNotFoundException
    {       
        // requete pour le nombre d'anesthesiste
        String requeteAnesthesiste = "select * from docteur where docteur.specialite = 'Anesthesiste';";
        ArrayList<String> res_anesthesiste = maconnexion.remplirChampsRequete(requeteAnesthesiste);
        int nb_anesthesiste=res_anesthesiste.size();
        
        // requete cardiologue
        String requeteCardiologue = "select * from docteur where docteur.specialite = 'Cardiologue';";
        ArrayList<String> res_cardiologue = maconnexion.remplirChampsRequete(requeteCardiologue);
        int nb_cardiologue=res_cardiologue.size();
        
        // requete orthopediste
        String requeteOrthopediste = "select * from docteur where docteur.specialite = 'Orthopediste';";
        ArrayList<String> res_orthopediste = maconnexion.remplirChampsRequete(requeteOrthopediste);
        int nb_orthopediste=res_orthopediste.size();
        
        // requete pneumologue
        String requetePneumologue = "select * from docteur where docteur.specialite = 'Pneumologue';";
        ArrayList<String> res_pneumologue = maconnexion.remplirChampsRequete(requetePneumologue);
        int nb_pneumologue=res_pneumologue.size();
        
        // requete radiologue
        String requeteRadiologue = "select * from docteur where docteur.specialite = 'Radiologue';";
        ArrayList<String> res_radiologue = maconnexion.remplirChampsRequete(requeteRadiologue);
        int nb_radiologue=res_radiologue.size();
        
        // requete traumatologue
        String requeteTraumatologue = "select * from docteur where docteur.specialite = 'Traumatologue';";
        ArrayList<String> res_traumatologue = maconnexion.remplirChampsRequete(requeteTraumatologue);
        int nb_traumatologue=res_traumatologue.size();
        
        //-------- diagramme camembert -----------
        if(choix==0)
        {
            JDialog specialiteDocteur_cam = new JDialog();
            specialiteDocteur_cam.setTitle("Répartition des docteurs selon leur spécialité");
            final DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Anesthesiste : " + nb_anesthesiste, nb_anesthesiste);
            pieDataset.setValue("Cardiologue : " + nb_cardiologue, nb_cardiologue);
            pieDataset.setValue("Orthopediste : " + nb_orthopediste, nb_orthopediste);
            pieDataset.setValue("Pneumologue : " + nb_pneumologue, nb_pneumologue);
            pieDataset.setValue("Radiologue : " + nb_radiologue, nb_radiologue);
            pieDataset.setValue("Traumatologue : " + nb_traumatologue, nb_traumatologue);

            final JFreeChart cam_chart = ChartFactory.createPieChart("Répartitions des spécialités", pieDataset, true, false, false);
            final ChartPanel panel = new ChartPanel(cam_chart);

            specialiteDocteur_cam.getContentPane().add(panel);
            specialiteDocteur_cam.pack();
            specialiteDocteur_cam.setVisible(true);
        }
        //--------- diagramme baton -------
        else if(choix==1)
        {
            JDialog specialiteDocteur_bat = new JDialog();
            specialiteDocteur_bat.setTitle("Répartition des docteurs selon leur spécialité");
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(nb_anesthesiste, "Anesthesiste : " + nb_anesthesiste, "Anesthesiste");
            dataset.addValue(nb_cardiologue, "Cardiologue : " + nb_cardiologue, "Cardiologue");
            dataset.addValue(nb_orthopediste, "Orthopediste : " + nb_orthopediste, "Orthopediste");
            dataset.addValue(nb_pneumologue, "Pneumologue : " + nb_anesthesiste, "Pneumologue");
            dataset.addValue(nb_radiologue, "Radiologue : " + nb_radiologue, "Radiologue");
            dataset.addValue(nb_traumatologue, "Traumatologue : " + nb_traumatologue, "Traumatologue");

            final JFreeChart barChart = ChartFactory.createBarChart("Répartition des docteurs selon leur spécialité", "Spécialité docteur", "Nombre", 
                                    dataset, PlotOrientation.VERTICAL, true, true, false);

            final ChartPanel panel2 = new ChartPanel(barChart);

            specialiteDocteur_bat.getContentPane().add(panel2);
            specialiteDocteur_bat.pack();
            specialiteDocteur_bat.setVisible(true);
        }        
    }  
    
    public void serviceInfirmier(int choix) throws SQLException, ClassNotFoundException
    {       
        // requete pour le nombre d'infirmiers dans le service CAR 
        String requeteCAR = "select * from infirmier where infirmier.code_service = 'CAR';";
        ArrayList<String> res_CAR = maconnexion.remplirChampsRequete(requeteCAR);
        int nb_CAR=res_CAR.size();
        
        // requete pour le nombre d'infirmier dans le service CHG 
        String requeteCHG = "select * from infirmier where infirmier.code_service = 'CHG';";
        ArrayList<String> res_CHG = maconnexion.remplirChampsRequete(requeteCHG);
        int nb_CHG=res_CHG.size();
                
        // requete pour le nombre d'infirmier dans le service REA 
        String requeteREA = "select * from infirmier where infirmier.code_service = 'REA';";
        ArrayList<String> res_REA = maconnexion.remplirChampsRequete(requeteREA);
        int nb_REA=res_REA.size();
        
        //-------- diagramme camembert -----------
        if(choix==0)
        {
            JDialog serviceInfirmier_cam = new JDialog();
            serviceInfirmier_cam.setTitle("Répartition des infirmiers selon leur service");
            final DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Service CAR : " + nb_CAR, nb_CAR);
            pieDataset.setValue("Service CHG : " + nb_CHG, nb_CHG);
            pieDataset.setValue("Service REA : " + nb_REA, nb_REA);

            final JFreeChart cam_chart = ChartFactory.createPieChart("Répartition des infirmiers selon leur service", pieDataset, true, false, false);
            final ChartPanel panel = new ChartPanel(cam_chart);

            serviceInfirmier_cam.getContentPane().add(panel);
            serviceInfirmier_cam.pack();
            serviceInfirmier_cam.setVisible(true);
        }
        //--------- diagramme baton -------
        else if(choix==1)
        {
            JDialog serviceInfirmier_bat = new JDialog();
            serviceInfirmier_bat.setTitle("Répartition des infirmiers selon leur service");
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(nb_CAR, "Service CAR : " + nb_CAR,"CAR");
            dataset.addValue(nb_CHG, "Service CHG : " + nb_CHG, "CHG");
            dataset.addValue(nb_REA, "Service REA : " + nb_REA, "REA");

            final JFreeChart barChart = ChartFactory.createBarChart("Répartition des infirmiers selon leur service", "Nom du service", "Nombre d'infirmiers", 
                                    dataset, PlotOrientation.VERTICAL, true, true, false);

            final ChartPanel panel2 = new ChartPanel(barChart);

            serviceInfirmier_bat.getContentPane().add(panel2);
            serviceInfirmier_bat.pack();
            serviceInfirmier_bat.setVisible(true);
        }        
    }
    
    public void serviceMalade(int choix) throws SQLException, ClassNotFoundException
    {       
        // requete pour le nombre d'infirmiers dans le service CAR 
        String requeteCAR = "select * from hospitalisation where hospitalisation.code_service = 'CAR';";
        ArrayList<String> res_CAR = maconnexion.remplirChampsRequete(requeteCAR);
        int nb_CAR=res_CAR.size();
        
        // requete pour le nombre d'infirmier dans le service CHG 
        String requeteCHG = "select * from hospitalisation where hospitalisation.code_service = 'CHG';";
        ArrayList<String> res_CHG = maconnexion.remplirChampsRequete(requeteCHG);
        int nb_CHG=res_CHG.size();
                
        // requete pour le nombre d'infirmier dans le service REA 
        String requeteREA = "select * from hospitalisation where hospitalisation.code_service = 'REA';";
        ArrayList<String> res_REA = maconnexion.remplirChampsRequete(requeteREA);
        int nb_REA=res_REA.size();
        
        //-------- diagramme camembert -----------
        if(choix==0)
        {
            JDialog serviceMalade_cam = new JDialog();
            serviceMalade_cam.setTitle("Répartition des malades selon le service");
            final DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Service CAR : " + nb_CAR, nb_CAR);
            pieDataset.setValue("Service CHG : " + nb_CHG, nb_CHG);
            pieDataset.setValue("Service REA : " + nb_REA, nb_REA);

            final JFreeChart cam_chart = ChartFactory.createPieChart("Répartition des malades selon le service", pieDataset, true, false, false);
            final ChartPanel panel = new ChartPanel(cam_chart);

            serviceMalade_cam.getContentPane().add(panel);
            serviceMalade_cam.pack();
            serviceMalade_cam.setVisible(true);
        }
        //--------- diagramme baton -------
        else if(choix==1)
        {
            JDialog serviceMalade_bat = new JDialog();
            serviceMalade_bat.setTitle("Répartition des malades selon le service");
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(nb_CAR, "Service CAR : " + nb_CAR, "CAR");
            dataset.addValue(nb_CHG, "Service CHG : " + nb_CHG, "CHG");
            dataset.addValue(nb_REA, "Service REA : " + nb_REA, "REA");

            final JFreeChart barChart = ChartFactory.createBarChart("Répartition des malades selon le service", "Nom du service", "Nombre de malades", 
                                    dataset, PlotOrientation.VERTICAL, true, true, false);

            final ChartPanel panel2 = new ChartPanel(barChart);

            serviceMalade_bat.getContentPane().add(panel2);
            serviceMalade_bat.pack();
            serviceMalade_bat.setVisible(true);
        }        
    }
    
    public void serviceChambre(int choix) throws SQLException, ClassNotFoundException
    {       
        // requete pour le nombre de chambres dans le service CAR 
        String requeteCAR = "select * from chambre where chambre.code_service = 'CAR';";
        ArrayList<String> res_CAR = maconnexion.remplirChampsRequete(requeteCAR);
        int nb_CAR=res_CAR.size();
        
        // requete pour le nombre de chambres dans le service CHG 
        String requeteCHG = "select * from chambre where chambre.code_service = 'CHG';";
        ArrayList<String> res_CHG = maconnexion.remplirChampsRequete(requeteCHG);
        int nb_CHG=res_CHG.size();
                
        // requete pour le nombre de chambres dans le service REA 
        String requeteREA = "select * from chambre where chambre.code_service = 'REA';";
        ArrayList<String> res_REA = maconnexion.remplirChampsRequete(requeteREA);
        int nb_REA=res_REA.size();
        
        //-------- diagramme camembert -----------
        if(choix==0)
        {
            JDialog serviceChambre_cam = new JDialog();
            serviceChambre_cam.setTitle("Répartition des chambres selon le service");
            final DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Service CAR : " + nb_CAR, nb_CAR);
            pieDataset.setValue("Service CHG : " + nb_CHG, nb_CHG);
            pieDataset.setValue("Service REA : " + nb_REA, nb_REA);

            final JFreeChart cam_chart = ChartFactory.createPieChart("Répartition des chambres selon le service", pieDataset, true, false, false);
            final ChartPanel panel = new ChartPanel(cam_chart);

            serviceChambre_cam.getContentPane().add(panel);
            serviceChambre_cam.pack();
            serviceChambre_cam.setVisible(true);
        }
        //--------- diagramme baton -------
        else if(choix==1)
        {
            JDialog serviceChambre_bat = new JDialog();
            serviceChambre_bat.setTitle("Répartition des chambres selon le service");
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(nb_CAR, "Service CAR : " + nb_CAR, "CAR");
            dataset.addValue(nb_CHG, "Service CHG : " + nb_CHG, "CHG");
            dataset.addValue(nb_REA, "Service REA : " + nb_REA, "REA");

            final JFreeChart barChart = ChartFactory.createBarChart("Répartition des chambres selon le service", "Nom du service", "Nombre de chambres", 
                                    dataset, PlotOrientation.VERTICAL, true, true, false);

            final ChartPanel panel2 = new ChartPanel(barChart);

            serviceChambre_bat.getContentPane().add(panel2);
            serviceChambre_bat.pack();
            serviceChambre_bat.setVisible(true);
        }        
    }
    
    public void rotationInfirmier(int choix) throws SQLException, ClassNotFoundException
    {       
        // requete pour le nombre d'infirmiers travaillant durant le jour 
        String requeteJour = "select * from  infirmier where infirmier.rotation = 'JOUR';";
        ArrayList<String> res_Jour = maconnexion.remplirChampsRequete(requeteJour);
        int nb_Jour=res_Jour.size();
        
        // requete pour le nombre d'infirmier travaillant durant la nuit
        String requeteNuit = "select * from infirmier where infirmier.rotation = 'NUIT';";
        ArrayList<String> res_Nuit = maconnexion.remplirChampsRequete(requeteNuit);
        int nb_Nuit=res_Nuit.size();
                
        //-------- diagramme camembert -----------
        if(choix==0)
        {
            JDialog rotationInfirmier_cam = new JDialog();
            rotationInfirmier_cam.setTitle("Répartition Jour/Nuit des infirmiers");
            final DefaultPieDataset pieDataset = new DefaultPieDataset();

            pieDataset.setValue("Infirmier travaillant le jour : " + nb_Jour, nb_Jour);
            pieDataset.setValue("Infirmier travaillant la nuit : " + nb_Nuit, nb_Nuit);

            final JFreeChart cam_chart = ChartFactory.createPieChart("Répartition Jour/Nuit des infirmiers", pieDataset, true, false, false);
            final ChartPanel panel = new ChartPanel(cam_chart);

            rotationInfirmier_cam.getContentPane().add(panel);
            rotationInfirmier_cam.pack();
            rotationInfirmier_cam.setVisible(true);
        }
        //--------- diagramme baton -------
        else if(choix==1)
        {
            JDialog rotationInfirmier_bat = new JDialog();
            rotationInfirmier_bat.setTitle("Répartition Jour/Nuit des infirmiers");
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(nb_Jour, "Rotation Jour : " + nb_Jour, "Jour");
            dataset.addValue(nb_Nuit, "Rotation Nuit : " + nb_Nuit, "Nuit");

            final JFreeChart barChart = ChartFactory.createBarChart("Répartition Jour/Nuit des infirmiers", "Rotation Jour/Nuit", "Nombre d'infirmiers", 
                                    dataset, PlotOrientation.VERTICAL, true, true, false);

            final ChartPanel panel2 = new ChartPanel(barChart);

            rotationInfirmier_bat.getContentPane().add(panel2);
            rotationInfirmier_bat.pack();
            rotationInfirmier_bat.setVisible(true);
        }        
    }
}
