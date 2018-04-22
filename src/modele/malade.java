/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Connexion;
import controleur.MaConnexion;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author victo
 */
public class malade {
    private String numero;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String mutuelle;
    private static Connexion co=MaConnexion.get();

    public malade()
    {
        
    }
    
    public malade(String numero, String nom, String prenom, String tel, String adresse, String mutuelle) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.mutuelle = mutuelle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    @Override
    public String toString() {
        return "malade{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", mutuelle=" + mutuelle + '}';
    }
    
    public void addMalade() throws SQLException, ClassNotFoundException
    { 
      
        String requeteMalade="insert into malade values ("+numero+",'"+nom+"','"+prenom+"','"+adresse+"','"+tel+"','"+mutuelle+"');";

        co.executeUpdate(requeteMalade);
        
    }
    public void suppMalade(String suppNum) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        
        String requeteMalade="DELETE FROM malade WHERE numero="+numero+";";
        
        co.executeUpdate(requeteMalade);
        
        
    }
    
        public void modifierMalade(String champ_maj, String attribut_choisi, String primarykey) throws SQLException, ClassNotFoundException
    { 
        String mod;
        String Anciennumero=primarykey;
        String requeteMalade;       
        
        switch (attribut_choisi) {
            case "NUMERO":
                requeteMalade="UPDATE malade SET numero="+champ_maj+" WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteMalade);
                break;
            case "NOM":
                requeteMalade="UPDATE malade SET nom='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteMalade);
                break;
            case "PRENOM":
                requeteMalade="UPDATE malade SET prenom='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteMalade);
                break;
            case "ADRESSE":
                requeteMalade="UPDATE malade SET adresse='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteMalade);
                break;
            case "TEL":
                requeteMalade="UPDATE malade SET tel='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteMalade);
                break;
            case "MUTUELLE":
                requeteMalade="UPDATE malade SET mutuelle='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteMalade);
                break;
            default:
                break;
        }
        
    }
    
}
