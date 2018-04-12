/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import controleur.*;
import vue.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import static controleur.Connexion.requetes;

/**
 *
 * @author Louis
 */
public class HopitalApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //FenetreLogin fenetre_login = new FenetreLogin();
        
        
        Connexion co=new Connexion("hopital","root","");
        String a=new String();
        requetes = co.remplirChampsRequete("SELECT nom, prenom FROM employe");
        for (String element : requetes)
        System.out.println(element);
        
    }
}
