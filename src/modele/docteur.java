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
public class docteur extends employe{
    private String specialite;

    public docteur(int numero, String specialite, String nom, String prenom, String adresse, String tel) {
        super(numero, nom, prenom, adresse, tel);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "docteur{" + "numero=" + numero + ", specialite=" + specialite + '}';
    }   
}
