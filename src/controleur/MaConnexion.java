/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

/**
 *
 * @author victo
 */
public class MaConnexion {
    private static Connexion co;

    public MaConnexion(Connexion co) {
        this.co = co;
    }

    public static Connexion get() {
        return co;
    }

    public void set(Connexion co) {
        this.co = co;
    }
}
