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
public class service {
    private String code;
    private String nom;
    private char batiment;
    private int directeur;

    public service(String code, String nom, char batiment, int directeur) {
        this.code = code;
        this.nom = nom;
        this.batiment = batiment;
        this.directeur = directeur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getBatiment() {
        return batiment;
    }

    public void setBatiment(char batiment) {
        this.batiment = batiment;
    }

    public int getDirecteur() {
        return directeur;
    }

    public void setDirecteur(int directeur) {
        this.directeur = directeur;
    }

    @Override
    public String toString() {
        return "service{" + "code=" + code + ", nom=" + nom + ", batiment=" + batiment + ", directeur=" + directeur + '}';
    }
    
    
}
