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
public class chambre {
    private String code_service;
    private String no_chambre;
    private String surveillant;
    private String nb_lits;

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
        
        
        
        Connexion co=new Connexion("hopital","root","root");
        co.executeUpdate(requeteChambre);
        
    }
    public void suppChambre(String suppNum,String suppService) throws SQLException, ClassNotFoundException
    { 
        String numero=suppNum;
        String service=suppService;
        
        String requeteChambre="DELETE FROM chambre WHERE no_chambre="+numero+" AND code_service='"+service+"';";
        
        Connexion co=new Connexion("hopital","root","root");
        co.executeUpdate(requeteChambre);
        
        
    }
    
    
}
