/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Connexion;
import controleur.MaConnexion;
import java.sql.SQLException;

/**
 *
 * @author victo
 */
public class hospitalisation {
    private String no_malade;
    private String code_service;
    private String no_chambre;
    private String lit;
    private static Connexion co=MaConnexion.get();

    public hospitalisation()
    {
        
    }
    
    public hospitalisation(String no_malade, String code_service, String no_chambre, String lit) {
        this.no_malade = no_malade;
        this.code_service = code_service;
        this.no_chambre = no_chambre;
        this.lit = lit;
    }

    public String getNo_malade() {
        return no_malade;
    }

    public void setNo_malade(String no_malade) {
        this.no_malade = no_malade;
    }

    public String getCode_service() {
        return code_service;
    }

    public void setCode_service(String code_service) {
        this.code_service = code_service;
    }

    public String getNo_chambre() {
        return no_chambre;
    }

    public void setNo_chambre(String no_chambre) {
        this.no_chambre = no_chambre;
    }

    public String getLit() {
        return lit;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }

    @Override
    public String toString() {
        return "hospitalisation{" + "no_malade=" + no_malade + ", code_service=" + code_service + ", no_chambre=" + no_chambre + ", lit=" + lit + '}';
    }
    
    public void addHospitalisation() throws SQLException, ClassNotFoundException
    { 
      
        String requeteHospitalisation="insert into hospitalisation values ("+no_malade+",'"+code_service+"',"+no_chambre+","+lit+");";

        co.executeUpdate(requeteHospitalisation);
        
    }
    
    public void suppHospitalisation(String suppNum) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        
        String requeteHospitalisation="DELETE FROM hospitalisation WHERE no_malade="+numero+";";
        
        co.executeUpdate(requeteHospitalisation);
        
        
    }
    
    public void modifierHospitalisation(String champ_maj, String attribut_choisi, String primarykey) throws SQLException, ClassNotFoundException
    { 
        String mod;
        String Anciennumero=primarykey;
        String requeteHospitalisation;
  
        switch (attribut_choisi) {
            case "NO_MALADE":
                requeteHospitalisation="UPDATE hospitalisation SET no_malade="+champ_maj+" WHERE no_malade="+Anciennumero+";";
                co.executeUpdate(requeteHospitalisation);
                break;
            case "CODE_SERVICE":
                requeteHospitalisation="UPDATE hospitalisation SET code_service='"+champ_maj+"' WHERE no_malade="+Anciennumero+";";
                co.executeUpdate(requeteHospitalisation);
                break;
            case "NO_CHAMBRE":
                requeteHospitalisation="UPDATE hospitalisation SET no_chambre="+champ_maj+" WHERE no_malade="+Anciennumero+";";
                co.executeUpdate(requeteHospitalisation);
                break;
            case "LIT":
                requeteHospitalisation="UPDATE hospitalisation SET lit="+champ_maj+" WHERE no_malade="+Anciennumero+";";
                co.executeUpdate(requeteHospitalisation);
                break;
            default:
                break;
        }
        
    }
    
}
