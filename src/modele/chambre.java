/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author victo
 */
public class chambre {
    private String code_service;
    private int no_chambre;
    private int surveillant;
    private int nb_lits;

    public chambre(String code_service, int no_chambre, int surveillant, int nb_lits) {
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

    public int getNo_chambre() {
        return no_chambre;
    }

    public void setNo_chambre(int no_chambre) {
        this.no_chambre = no_chambre;
    }

    public int getSurveillant() {
        return surveillant;
    }

    public void setSurveillant(int surveillant) {
        this.surveillant = surveillant;
    }

    public int getNb_lits() {
        return nb_lits;
    }

    public void setNb_lits(int nb_lits) {
        this.nb_lits = nb_lits;
    }

    @Override
    public String toString() {
        return "chambre{" + "code_service=" + code_service + ", no_chambre=" + no_chambre + ", surveillant=" + surveillant + ", nb_lits=" + nb_lits + '}';
    }
    
    
    
}
