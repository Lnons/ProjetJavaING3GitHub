/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import modele.*;
import controleur.*;
import vue.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import static controleur.Connexion.requetes;
import java.util.Scanner;

/**
 *
 * @author Louis
 */
public class HopitalApplication {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
            
            
            
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Connexion co=new Connexion("hopital","root","root");
        docteur doc=new docteur();
        
        requetes = co.remplirChampsRequete("SELECT employe.nom, employe.prenom\n" +
"FROM employe\n" +
"JOIN docteur\n" +
"ON employe.numero=docteur.numero\n" +
"JOIN soigne\n" +
"ON docteur.numero=soigne.no_docteur\n" +
"LEFT OUTER JOIN hospitalisation\n" +
"ON soigne.no_malade=hospitalisation.no_malade\n" +
"GROUP BY employe.nom\n" +
"HAVING COUNT(DISTINCT hospitalisation.no_malade)=0");
        for (String element : requetes)
        System.out.println(element);
        
        int choixMenu; //On imagine que la note maximale est 20

        System.out.println("Que voulez vous faire (tapez le numero correspondant):\n" + 
                          "1) Ajouter un docteur \n" +
                          "2) Modifier un docteur \n" +
                          "3) Supprimer un docteur \n" +
                          "autre touche) quitter le programme\n");
        
        choixMenu=sc.nextInt();
        
        switch (choixMenu)
        {
            case 1:
            doc.addDocteur();
            break;
            case 2:
            doc.modifierDocteur();
            break;
            case 3:
            doc.suppDocteur();
            break;
            default:
            System.out.println("Fin du programme");
            System.exit(0);
        }
        
        /*
        SelectionRequetes sR=new SelectionRequetes();
        sR.selectionner();
        */
        
    }  
}
