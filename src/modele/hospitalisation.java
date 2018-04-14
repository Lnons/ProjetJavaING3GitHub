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
public class hospitalisation {
    private int no_malade;
    private String code_service;
    private int no_chambre;
    private int lit;

    public hospitalisation(int no_malade, String code_service, int no_chambre, int lit) {
        this.no_malade = no_malade;
        this.code_service = code_service;
        this.no_chambre = no_chambre;
        this.lit = lit;
    }

    public int getNo_malade() {
        return no_malade;
    }

    public void setNo_malade(int no_malade) {
        this.no_malade = no_malade;
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

    public int getLit() {
        return lit;
    }

    public void setLit(int lit) {
        this.lit = lit;
    }

    @Override
    public String toString() {
        return "hospitalisation{" + "no_malade=" + no_malade + ", code_service=" + code_service + ", no_chambre=" + no_chambre + ", lit=" + lit + '}';
    }
    
    
}
