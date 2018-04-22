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
    private String requete;
    private int nombre_res;
    
    public FenetreRes(Object source, Object source2, String s) throws ClassNotFoundException
    {
        // creation par heritage de la fenetre
        super("Fenetre Resultat");
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //on quadrille notre fenetre
        this.setLayout(new GridLayout(4,1)); 
        
        //on crée notre bouton
        b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        b0.add(fermer);
        fermer.addActionListener(this);
        
        //si on veut afficher une requete prédéfinie
        if(choix_requete(source)==true)
        {
            //on appele une methode qui va chercher notre requete dans base de donnée
            this.afficher_requete(source,requete);
        }
        //si on veut afficher une table entière
        else if(choix_requete(source)==false && s.isEmpty())
        {
            //on appele une methode qui va chercher notre requete dans base de donnée
            this.afficherLignes(source);
        }
        //si on veut faire une recherche avancée : une table selon un attribut et un champ tapé à l'écran
        else if(source2!=null && !s.isEmpty())
        {
            afficher_avancee(source,source2,s);
        }
        
        // creation des labels
        titre = new JLabel("Resultat : " + nombre_res, JLabel.CENTER);
        
        //taille des labels
        Font font = new Font("Arial",Font.BOLD,20);
        titre.setFont(font); 
               
        //On place le tout sur notre fenetre
        this.getContentPane().add(b0);
        this.getContentPane().add(titre);
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
    
    //on veut afficher une table entière
    public void afficherLignes(Object source) throws ClassNotFoundException {
        //on définie une taille de tableau (pour les entetes) en fonction de la table choisie
        int taille_tableau=taille_table(source);
        title= new String[taille_tableau];
        try {
            int i=0;
            ArrayList<String> liste;

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + source + ";";
            liste = MaConnexion.get().remplirChampsRequete(requeteSelectionnee);
            Object[][]data = new Object[liste.size()][taille_tableau];
            nombre_res = liste.size();
            
            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                for(int j=0 ; j<taille_table(source) ; j++)
                {
                    //on sépare les resulats dans des colonnes différentes
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
    
    //on affiche l'une des 10 requetes déjà définie
    public void afficher_requete(Object source, String requete) throws ClassNotFoundException {
        int taille_tableau=taille_table(source);
        title= new String[taille_tableau];
        try {
            int i=0;
            ArrayList<String> liste;

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = requete + ";";
            liste =  MaConnexion.get().remplirChampsRequete(requeteSelectionnee);
            Object[][]data = new Object[liste.size()][taille_tableau];
            nombre_res = liste.size();
            
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
    
    //on fait une recherche avancée : on recherche un attribut spécifique dans une table
    public void afficher_avancee(Object table, Object attribut, String s) throws ClassNotFoundException {
        Object source = table;
        Object source2 = attribut;    
        try {
            int i=0;
            ArrayList<String> liste;

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " 
                + source + " where " + source + "." + source2 + " like " + "'" + s + "%'" + ";";
            
            //si c'est docteur, on cherche les attributs dans employé
            if(source.equals("docteur") && !source2.equals("SPECIALITE") && !source2.equals("NUMERO") )
            {
                source="employe";
                requeteSelectionnee = "select * from " 
                + source + " inner join docteur on docteur.NUMERO = employe.NUMERO where " 
                         + source + "." + source2 + " like " + "'" + s + "%'" + ";";
            }
            //si c'est infirmier, on cherche les attributs dans employé
            if(source.equals("infirmier") && !source2.equals("ROTATION") && !source2.equals("NUMERO") 
                    && !source2.equals("CODE_SERVICE") && !source2.equals("SALAIRE") )
            {
                source="employe";
                requeteSelectionnee = "select * from " 
                + source + " inner join infirmier on infirmier.NUMERO = employe.NUMERO where " 
                         + source + "." + source2 + " like " + "'" + s + "%'" + ";";
            }
            
            int taille_tableau=taille_table(source);
            title= new String[taille_tableau];
            liste =  MaConnexion.get().remplirChampsRequete(requeteSelectionnee);
            Object[][]data = new Object[liste.size()][taille_tableau];
            nombre_res = liste.size();
            
            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
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
            //i<taille pour les requete complexes avec jointure (ex: docteur et nom) 
            for(int j=0 ; j<chaine.length() && i<taille ; j++)
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
        //tables
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
        
        //requete
        if(source.equals("R1. Prenom et nom des malades affilies a la mutuelle « MAAF »"))
        {
            System.out.println("heheheh");
            taille=2;
        }
        else if(source.equals("R2. Prenom et nom des infirmiers travaillant pendant la nuit"))
        {
            taille=2;
        }
        else if(source.equals("R3. Nom et batiment pour chaque service, ainsi que le prenom, nom et specialite du directeur du service"))
        {
            taille=5;
        }
        else if(source.equals("R4. Informations sur un malade affilié a une mutuelle dont le nom commence par « MN... », au batiment « B »"))
        {
            taille=6;
        }
        else if(source.equals("R5. Moyenne des salaires des infirmiers par service "))
        {
            taille=2;
        }
        else if(source.equals("R6. Nombre moyen de lits par chambre pour chaque service du batiment « A » "))
        {
            taille=2;
        }
        else if(source.equals("R7. Nombre total de médecins pour chaque malade soigné, et \n" +
            "nombre correspondant de spécialités médicales concernées"))
        {
            taille=4;
        }
        else if(source.equals("R8. Rapport entre le nombre d’infirmiers affectés au service\n" +
            " et le nombre de malades hospitalisés dans le service"))
        {
            taille=2;
        }
        else if(source.equals("R9. Prénom et nom des docteurs ayant au moins un malade hospitalisé"))
        {
            taille=2;
        }
        else if(source.equals("R10. Prénom et nom des docteurs n’ayant aucun malade hospitalisé"))
        {
            taille=2;
        }
        return taille;
    }
    
    public String[] entete(Object source)
    {      
        String defaut[]={"",""};
        if(source=="chambre")
        {   
            String entete[]={"CODE_SERVICE","NO_CHAMBRE","SURVEILLANT","NB_LITS"};
            return entete;
        }
        if(source=="docteur")
        {
            String entete[]={"NUMERO","SPECIALITE"};
            return entete;
        }
        if(source=="employe")
        {
            String entete[]={"NUMERO","NOM","PRENOM","ADRESSE","TEL"};
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
            String entete[]={"NUMERO","NOM","PRENOM","ADRESSE","TEL","MUTUELLE"};
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
        
        //requete
        if(source.equals("R1. Prenom et nom des malades affilies a la mutuelle « MAAF »"))
        {
            String entete[]={"Nom","Prenom"};
            return entete;
        }
        else if(source.equals("R2. Prenom et nom des infirmiers travaillant pendant la nuit"))
        {
            String entete[]={"Nom","Prenom"};
            return entete;
        }
        else if(source.equals("R3. Nom et batiment pour chaque service, ainsi que le prenom, nom et specialite du directeur du service"))
        {
            String entete[]={"Nom du service","Batiment","Nom du docteur","Prenom du docteur", "Specialité du docteur"};
            return entete;
        }
        else if(source.equals("R4. Informations sur un malade affilié a une mutuelle dont le nom commence par « MN... », au batiment « B »"))
        {
            String entete[]={"Nom du service","Nombre de lits", "Numéro chambre","Nom du malade", "Prénom du malade", "Mutuelle"};
            return entete;
        }
        else if(source.equals("R5. Moyenne des salaires des infirmiers par service "))
        {
            String entete[]={"Nom du service","Moyenne salaires des infirmiers"};
            return entete;
        }
        else if(source.equals("R6. Nombre moyen de lits par chambre pour chaque service du batiment « A » "))
        {
            String entete[]={"Nom du service","Nombre moyen de lits par chambre"};
            return entete;
        }
        else if(source.equals("R7. Nombre total de médecins pour chaque malade soigné, et \n" +
            "nombre correspondant de spécialités médicales concernées"))
        {
            String entete[]={"Nom du malade","Prénom du malade", "Nombre médecins par malade","Nombre de spécialités concernées"};
            return entete;
        }
        else if(source.equals("R8. Rapport entre le nombre d’infirmiers affectés au service\n" +
            " et le nombre de malades hospitalisés dans le service"))
        {
            String entete[]={"Nom du service","Rapport infirmier/malade"};
            return entete;
        }
        else if(source.equals("R9. Prénom et nom des docteurs ayant au moins un malade hospitalisé"))
        {
            String entete[]={"Nom du docteur","Prénom du docteur"};
            return entete;
        }
        else if(source.equals("R10. Prénom et nom des docteurs n’ayant aucun malade hospitalisé"))
        {
            String entete[]={"Nom du docteur","Prénom du docteur"};
            return entete;
        }
        return defaut;
    }
    
    //selon notre requete choisie précedemment, on stocke une requete en SQL dans un String
    public boolean choix_requete(Object source)
    {
        boolean trouve=false;
        if(source.equals("R1. Prenom et nom des malades affilies a la mutuelle « MAAF »"))
        {
            requete="SELECT malade.nom, malade.prenom FROM malade WHERE mutuelle='MAAF' GROUP BY nom";
            trouve=true;
        }
        else if(source.equals("R2. Prenom et nom des infirmiers travaillant pendant la nuit"))
        {
            requete="SELECT employe.nom, employe.prenom\n" +
                    "FROM employe\n" +
                    "JOIN infirmier\n" +
                    "ON employe.numero=infirmier.numero\n" +
                    "WHERE rotation='NUIT'\n" +
                    "GROUP BY employe.nom\n" ;
            trouve=true;
        }
        else if(source.equals("R3. Nom et batiment pour chaque service, ainsi que le prenom, nom et specialite du directeur du service"))
        {
            requete="SELECT service.nom, service.batiment, employe.nom, employe.prenom, docteur.specialite\n" +
                    "FROM service\n" +
                    "JOIN employe\n" +
                    "ON service.directeur=employe.numero\n" +
                    "JOIN docteur\n" +
                    "ON employe.numero=docteur.numero\n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R4. Informations sur un malade affilié a une mutuelle dont le nom commence par « MN... », au batiment « B »"))
        {
            requete="SELECT service.nom, hospitalisation.lit, hospitalisation.no_chambre, malade.nom, malade.prenom, malade.mutuelle\n" +
                    "FROM hospitalisation\n" +
                    "JOIN malade\n" +
                    "ON hospitalisation.no_malade=malade.numero\n" +
                    "JOIN service\n" +
                    "ON hospitalisation.code_service=service.code\n" +
                    "WHERE service.batiment='B'\n" +
                    "AND malade.mutuelle LIKE 'MN%'\n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R5. Moyenne des salaires des infirmiers par service "))
        {
            requete="SELECT service.nom, AVG(infirmier.salaire) \n" +
                    "FROM service\n" +
                    "JOIN infirmier\n" +
                    "ON service.code=infirmier.code_service\n" +
                    "GROUP BY service.code\n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R6. Nombre moyen de lits par chambre pour chaque service du batiment « A » "))
        {
            requete="SELECT service.nom, AVG(chambre.nb_lits)\n" +
                    "FROM service\n" +
                    "JOIN chambre\n" +
                    "ON service.code=chambre.code_service\n" +
                    "WHERE service.batiment='A'\n" +
                    "GROUP BY service.code\n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R7. Nombre total de médecins pour chaque malade soigné, et \n" +
            "nombre correspondant de spécialités médicales concernées"))
        {
            requete="SELECT malade.nom, malade.prenom, COUNT(DISTINCT docteur.numero), COUNT(DISTINCT docteur.specialite)\n" +
                    "FROM malade\n" +
                    "JOIN soigne\n" +
                    "ON malade.numero=soigne.no_malade\n" +
                    "JOIN docteur\n" +
                    "ON soigne.no_docteur=docteur.numero\n" +
                    "GROUP BY malade.nom\n" +
                    "HAVING COUNT(DISTINCT docteur.numero)>3\n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R8. Rapport entre le nombre d’infirmiers affectés au service\n" +
            " et le nombre de malades hospitalisés dans le service"))
        {
            requete="SELECT service.nom, COUNT(DISTINCT infirmier.numero)/COUNT(DISTINCT hospitalisation.no_malade)\n" +
                    "FROM service\n" +
                    "JOIN infirmier\n" +
                    "ON service.code=infirmier.code_service\n" +
                    "JOIN hospitalisation\n" +
                    "ON infirmier.code_service=hospitalisation.code_service\n" +
                    "GROUP BY service.code\n" +
                    "\n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R9. Prénom et nom des docteurs ayant au moins un malade hospitalisé"))
        {
            requete="SELECT employe.nom, employe.prenom\n" +
                    "FROM employe\n" +
                    "JOIN docteur\n" +
                    "ON employe.numero=docteur.numero\n" +
                    "JOIN soigne\n" +
                    "ON docteur.numero=soigne.no_docteur\n" +
                    "JOIN hospitalisation\n" +
                    "ON soigne.no_malade=hospitalisation.no_malade\n" +
                    "GROUP BY employe.nom\n" +
                    "HAVING COUNT(DISTINCT hospitalisation.no_malade)>0\n" +
                    " \n" +
                    "";
            trouve=true;
        }
        else if(source.equals("R10. Prénom et nom des docteurs n’ayant aucun malade hospitalisé"))
        {
            requete="SELECT employe.nom, employe.prenom\n" +
                    "FROM employe\n" +
                    "JOIN docteur\n" +
                    "ON employe.numero=docteur.numero\n" +
                    "JOIN soigne\n" +
                    "ON docteur.numero=soigne.no_docteur\n" +
                    "LEFT OUTER JOIN hospitalisation\n" +
                    "ON soigne.no_malade=hospitalisation.no_malade\n" +
                    "GROUP BY employe.nom\n" +
                    "HAVING COUNT(DISTINCT hospitalisation.no_malade)=0\n" +
                    "";
            trouve=true;
        }
        else
            trouve=false;
        return trouve;
    }
}
