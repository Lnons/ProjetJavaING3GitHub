/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Connexion;
import java.sql.SQLException;

/**
 *
 * @author victo
 */
public class soigne {
    private String no_docteur;
    private String no_malade;

    public soigne()
    {
        
    }
    
    public soigne(String no_docteur, String no_malade) {
        this.no_docteur = no_docteur;
        this.no_malade = no_malade;
    }

    public String getNo_docteur() {
        return no_docteur;
    }

    public void setNo_docteur(String no_docteur) {
        this.no_docteur = no_docteur;
    }

    public String getNo_malade() {
        return no_malade;
    }

    public void setNo_malade(String no_malade) {
        this.no_malade = no_malade;
    }

    @Override
    public String toString() {
        return "soigne{" + "no_docteur=" + no_docteur + ", no_malade=" + no_malade + '}';
    }
    
    public void addSoigne() throws SQLException, ClassNotFoundException
    { 
      
        String requeteSoigne="insert into soigne values ("+no_docteur+","+no_malade+");";
        
        
        
        Connexion co=new Connexion("hopital","root","root");
        co.executeUpdate(requeteSoigne);
        
    }
    public void suppSoigne(String suppNum) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        
        
        String requeteSoigne="DELETE FROM soigne WHERE no_docteur="+numero+";";
        
        Connexion co=new Connexion("hopital","root","root");
        co.executeUpdate(requeteSoigne);
        
        
    }
    
}
