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
public class infirmier extends employe{
    private String code_service;
    private String rotation;
    private String salaire;
    private static Connexion co=MaConnexion.get();

    public infirmier()
    {
        super();
    }
    
    public infirmier(String numero, String nom, String prenom, String tel, String adresse, String code_service, String rotation, String salaire) {
        super(numero, nom, prenom, adresse, tel);
        this.code_service = code_service;
        this.rotation = rotation;
        this.salaire = salaire;
    }

    public String getCode_service() {
        return code_service;
    }

    public void setCode_service(String code_service) {
        this.code_service = code_service;
    }

    public String isRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
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

    @Override
    public String toString() {
        return "infirmier{" + "code_service=" + code_service + ", rotation=" + rotation + ", salaire=" + salaire + '}';
    }    
    
    public void addInfirmier() throws SQLException, ClassNotFoundException
    { 
      
        Scanner sc = new Scanner(System.in);
        
        String requeteEmploye="insert into employe values ("+numero+",'"+nom+"','"+prenom+"','"+adresse+"','"+tel+"');";
        String requeteInfirmier="insert into infirmier values ("+numero+",'"+code_service+"','"+rotation+"',"+salaire+");";

        co.executeUpdate(requeteEmploye);
        co.executeUpdate(requeteInfirmier);
        
    }
    public void suppInfirmier(String numSupp) throws SQLException, ClassNotFoundException
    { 
        
       
        String numero=numSupp;
        
        String requeteInfirmier="DELETE FROM infirmier WHERE numero="+numero+";";
        String requeteEmploye="DELETE FROM employe WHERE numero="+numero+";";

        co.executeUpdate(requeteInfirmier);
        co.executeUpdate(requeteEmploye);
        
    }
    
        public void modifierInfirmier(String champ_maj, String attribut_choisi, String primarykey) throws SQLException, ClassNotFoundException
    { 
        String mod;
        String Anciennumero=primarykey;
        String requeteInfirmier;
        String requeteEmploye;
     
        switch (attribut_choisi) {
            case "NUMERO":
                requeteInfirmier="UPDATE infirmier SET numero="+champ_maj+" WHERE numero="+Anciennumero+";";
                requeteEmploye="UPDATE employe SET numero="+champ_maj+" WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteEmploye);
                co.executeUpdate(requeteInfirmier);
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
            case "CODE_SERVICE":
                requeteInfirmier="UPDATE infirmier SET code_service='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteInfirmier);
                break;
            case "ROTATION":
                requeteInfirmier="UPDATE infirmier SET rotation='"+champ_maj+"' WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteInfirmier);
                break;
            case "SALAIRE":
                requeteInfirmier="UPDATE infirmier SET salaire="+champ_maj+" WHERE numero="+Anciennumero+";";
                co.executeUpdate(requeteInfirmier);
                break;
            default:
                break;
        }
        
    }
} 
