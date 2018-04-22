/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.*;
import java.sql.SQLException;

/**
 *
 * @author victo
 */
public class chambre {
    private String code_service;
    private String no_chambre;
    private String surveillant;
    private String nb_lits;
    //recuperer la connexion à la base, lors du login
    private static Connexion co=MaConnexion.get(); 

    public chambre()
    {
        
    }
    
    public chambre( String no_chambre, String code_service, String surveillant, String nb_lits) {
        this.code_service = code_service;
        this.no_chambre = no_chambre;
        this.surveillant = surveillant;
        this.nb_lits = nb_lits;
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

    public String getSurveillant() {
        return surveillant;
    }

    public void setSurveillant(String surveillant) {
        this.surveillant = surveillant;
    }

    public String getNb_lits() {
        return nb_lits;
    }

    public void setNb_lits(String nb_lits) {
        this.nb_lits = nb_lits;
    }

    @Override
    public String toString() {
        return "chambre{" + "code_service=" + code_service + ", no_chambre=" + no_chambre + ", surveillant=" + surveillant + ", nb_lits=" + nb_lits + '}';
    }
    
    public void addChambre() throws SQLException, ClassNotFoundException
    { 
      
        String requeteChambre="insert into chambre values ('"+code_service+"',"+no_chambre+","+surveillant+","+nb_lits+");";
        co.executeUpdate(requeteChambre);
        
    }
    public void suppChambre(String suppNum,String suppService) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        String service=suppService;
        
        String requeteChambre="DELETE FROM chambre WHERE no_chambre="+numero+" AND code_service='"+service+"';";
        co.executeUpdate(requeteChambre);
        
    }
    
    public void modifierChambre(String champ_maj, String attribut_choisi, String primarykey) throws SQLException, ClassNotFoundException
    { 
        String mod;
        String Anciennumero=primarykey;
        String requeteChambre;
        
        switch (attribut_choisi) {
            case "CODE_SERVICE":
                requeteChambre="UPDATE chambre SET code_service='"+champ_maj+"' WHERE no_chambre="+Anciennumero+";";
                co.executeUpdate(requeteChambre);
                break;
            case "NO_CHAMBRE":
                requeteChambre="UPDATE chambre SET no_chambre="+champ_maj+" WHERE no_chambre="+Anciennumero+";";
                co.executeUpdate(requeteChambre);
                break;
            case "SURVEILLANT":
                requeteChambre="UPDATE chambre SET surveillant="+champ_maj+" WHERE no_chambre="+Anciennumero+";";
                co.executeUpdate(requeteChambre);
                break;
            case "NB_LITS":
                requeteChambre="UPDATE chambre SET nb_lits="+champ_maj+" WHERE no_chambre="+Anciennumero+";";
                co.executeUpdate(requeteChambre);
                break;
            default:
                break;
        }
        
    }
    
    
}
