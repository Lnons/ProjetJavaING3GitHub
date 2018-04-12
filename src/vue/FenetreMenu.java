/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controleur.*;

/**
 *
 * @author victo
 */

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FenetreMenu extends JFrame implements ActionListener, ItemListener {
    private final JButton recherche = new JButton("Recherche");
    private final JButton maj = new JButton("Mise à jour");
    private final JButton reporting = new JButton("Reporting");
    
    public FenetreMenu(FenetreLogin fen)
    {
        // creation par heritage de la fenetre
        super("Fenetre Menu");
        
        fen.dispose();
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 400);
        setResizable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        
        //ON MET EN PLACE LA DISPOSITION DE NOS BOUTONS
        JPanel b1 = new JPanel();
        //On définit le layout en lui indiquant qu'il travaillera en ligne
        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        b1.add(recherche);

        JPanel b2 = new JPanel();
        //Idem pour cette ligne
        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        b2.add(maj);

        JPanel b3 = new JPanel();
        //Idem pour cette ligne
        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        b3.add(reporting);

        JPanel b4 = new JPanel();
        //On positionne maintenant ces trois lignes en colonne
        b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
        b4.add(b1);
        b4.add(b2);
        b4.add(b3);

        this.getContentPane().add(b4); 
        setVisible(true);
        
        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }
    
    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param evt
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
    }

    /**
     *
     * Pour gerer les actions sur items d'une liste on utilise la methode
     * itemStateChanged
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void itemStateChanged(ItemEvent evt) { 
    }
}
