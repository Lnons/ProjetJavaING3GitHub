/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Scanner;
import controleur.*;
import vue.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import static controleur.Connexion.requetes;

/**
 *
 * @author victo
 */
public class docteur extends employe{
    private String specialite;
    
    public docteur()
    {
     super(); 
    }

    public docteur(String numero, String nom, String prenom, String adresse, String tel, String specialite) {
        super(numero, nom, prenom, adresse, tel);
        this.specialite = specialite;
    }
    

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "docteur{" + "numero=" + numero + ", specialite=" + specialite + '}';
    }
    
    public void addDocteur() throws SQLException, ClassNotFoundException
    { 
      
        Scanner sc = new Scanner(System.in);
        
        String requeteEmploye="insert into employe values ("+numero+",'"+nom+"','"+prenom+"','"+adresse+"','"+tel+"');";
        String requeteDocteur="insert into docteur values ("+numero+",'"+specialite+"');";
        
        
        Connexion co=new Connexion("hopital","root","root");
        co.executeUpdate(requeteEmploye);
        co.executeUpdate(requeteDocteur);
        
    }
    public void suppDocteur(String suppNum) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        
        String requeteDocteur="DELETE FROM docteur WHERE numero="+numero+";";
        String requeteEmploye="DELETE FROM employe WHERE numero="+numero+";";
        
        Connexion co=new Connexion("hopital","root","root");
        
        co.executeUpdate(requeteDocteur);
        co.executeUpdate(requeteEmploye);
        
        
    }
    
    public void modifierDocteur() throws SQLException, ClassNotFoundException
    { 
        String mod;
        String numero;
        String requeteDocteur=null;
        String requeteEmploye=null;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Vous voulez modifier un docteur");
        System.out.println("Tapez le numero du docteur que vous voulez modifier");
        numero=sc.nextLine();
        
        System.out.println("Que voulez-vous modifier parmi (tapez le chiffre correspondant) :\n" + 
                          "1) Le numero \n" +
                          "2) Le nom \n" +
                          "3) Le prenom \n" +
                          "4) L'adresse \n" +
                          "5) Le numero de telephone \n" +
                          "6) La specialite \n"
                          );
        int choix=sc.nextInt();
        
        switch(choix) {
            case 1:
            System.out.println("Entrez son nouveau numero");
            String newNum=sc.nextLine();
            requeteDocteur="UPDATE docteur SET numero="+newNum+" WHERE numero="+numero+";";
            requeteEmploye="UPDATE employe SET numero="+newNum+" WHERE numero="+numero+";";
            break;
            case 2:
            System.out.println("Entrez son nouveau nom");
            String newNom=sc.nextLine();
            requeteEmploye="UPDATE employe SET nom="+newNom+" WHERE numero="+numero+";";
            break;
            case 3:
            System.out.println("Entrez son nouveau prenom");
            String newPrenom=sc.nextLine();
            requeteEmploye="UPDATE employe SET prenom="+newPrenom+" WHERE numero="+numero+";";
            break;
            case 4:
            System.out.println("Entrez sa nouvelle adresse");
            String newAdresse=sc.nextLine();
            requeteEmploye="UPDATE employe SET adresse="+newAdresse+" WHERE numero="+numero+";";
            break;
            case 5:
            System.out.println("Entrez son nouveau numero de telephone");
            String newTel=sc.nextLine();
            requeteEmploye="UPDATE employe SET tel="+newTel+" WHERE numero="+numero+";";
            break;
            case 6:
            System.out.println("Entrez sa nouvelle specialite entre : \n" + 
                          "- Anesthesiste \n" +
                          "- Cardiologue\n" +
                          "- Generaliste\n" +
                          "- Orthopediste\n" +
                          "- Pneumologue\n" +
                          "- Radiologue\n" +
                          "- Traumatologue");
            mod=sc.nextLine();
            while((!mod.equals("Anesthesiste"))&&(!mod.equals("Cardiologue"))&&(!mod.equals("Generaliste"))&&(!mod.equals("Orthopediste"))&&(!mod.equals("Pneumologue"))&&(!mod.equals("Radiologue"))&&(!mod.equals("Traumatologue")))
            {
                System.out.println("Erreur, cette specialite n'existe pas, veuillez rentrer une specialite parmi la liste ci-dessus");
                mod=sc.nextLine();
            }
            requeteDocteur="UPDATE docteur SET specialite="+mod+" WHERE numero="+numero+";";
            break;
            default:
            System.out.println("Entrée incorrect, rien ne sera modifié");
            break;
        }
        
        
        Connexion co=new Connexion("hopital","root","root");
        if(!requeteDocteur.equals(null))
            co.executeUpdate(requeteDocteur);
        if(!requeteEmploye.equals(null))
            co.executeUpdate(requeteEmploye);
        
    }
}
