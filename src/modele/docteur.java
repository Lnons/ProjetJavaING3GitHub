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
    private static Connexion co=MaConnexion.get();
    
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
        String requeteEmploye="insert into employe values ("+numero+",'"+nom+"','"+prenom+"','"+adresse+"','"+tel+"');";
        String requeteDocteur="insert into docteur values ("+numero+",'"+specialite+"');";

        co.executeUpdate(requeteEmploye);
        co.executeUpdate(requeteDocteur);       
    }
    
    public void suppDocteur(String suppNum) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        
        String requeteDocteur="DELETE FROM docteur WHERE numero="+numero+";";
        String requeteEmploye="DELETE FROM employe WHERE numero="+numero+";";
        
        co.executeUpdate(requeteDocteur);
        co.executeUpdate(requeteEmploye);
        
        
    }
    
    public void modifierDocteur(String champ_maj, String attribut_choisi, String primarykey) throws SQLException, ClassNotFoundException
    { 
        String mod;
        String Anciennumero=primarykey;
        String requeteDocteur;
        String requeteEmploye;        
        
        switch (attribut_choisi) {
            case "NUMERO":
                requeteDocteur="UPDATE docteur SET numero="+champ_maj+" WHERE numero="+Anciennumero+";";
                requeteEmploye="UPDATE employe SET numero="+champ_maj+" WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteEmploye);
                co.executeUpdate(requeteDocteur);
                break;
            case "NOM":
                requeteEmploye="UPDATE employe SET nom='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteEmploye);
                break;
            case "PRENOM":
                requeteEmploye="UPDATE employe SET prenom='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteEmploye);
                break;
            case "ADRESSE":
                requeteEmploye="UPDATE employe SET adresse='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteEmploye);
                break;
            case "TEL":
                requeteEmploye="UPDATE employe SET tel='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteEmploye);
                break;
            case "SPECIALITE":
                requeteDocteur="UPDATE docteur SET specialite='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteDocteur);
                break;
            default:
                break;
        }
        
    }
}
