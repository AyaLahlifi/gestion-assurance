/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.views.components;

import com.mycompany.gestionassu.views.MainFrame;
import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private final JTextField searchField;
    private final MainFrame mainFrame;
    
    public SearchPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel label = new JLabel("Rechercher un client (CNI): ");
        searchField = new JTextField(20);
        CustomButton searchButton = new CustomButton("Rechercher", new Color(51, 122, 183));
        
        searchButton.addActionListener(e -> search());
        
        add(label);
        add(searchField);
        add(searchButton);
    }
    
    private void search() {
        String cni = searchField.getText().trim();
        if (!cni.isEmpty()) {
            mainFrame.openHistorique(cni);
        } else {
            JOptionPane.showMessageDialog(this,
                "Veuillez entrer un numéro CNI",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}