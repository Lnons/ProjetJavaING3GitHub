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
public class soigne {
    private int no_docteur;
    private int no_malade;

    public soigne(int no_docteur, int no_malade) {
        this.no_docteur = no_docteur;
        this.no_malade = no_malade;
    }

    public int getNo_docteur() {
        return no_docteur;
    }

    public void setNo_docteur(int no_docteur) {
        this.no_docteur = no_docteur;
    }

    public int getNo_malade() {
        return no_malade;
    }

    public void setNo_malade(int no_malade) {
        this.no_malade = no_malade;
    }

    @Override
    public String toString() {
        return "soigne{" + "no_docteur=" + no_docteur + ", no_malade=" + no_malade + '}';
    }
    
    
}
