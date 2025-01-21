/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.views;

import com.mycompany.gestionassu.models.AssuranceType;
import com.mycompany.gestionassu.views.components.AssuranceCard;
import com.mycompany.gestionassu.views.components.SearchPanel;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SearchPanel searchPanel;
    
    public MainFrame() {
        setTitle("Gestion des Assurances");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Panel principal avec BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panneau de recherche en haut
        searchPanel = new SearchPanel(this);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Panneau des cartes d'assurance
        JPanel cardsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Création des cartes pour chaque type d'assurance
        for (AssuranceType type : AssuranceType.values()) {
            AssuranceCard card = new AssuranceCard(type, this);
            cardsPanel.add(card);
        }
        
        // Ajouter le panneau des cartes dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    public void openFormulaire(AssuranceType type) {
        new FormulairePage(type).setVisible(true);
        this.dispose();
    }
    
    public void openHistorique(String cni) {
        new HistoriquePage(cni).setVisible(true);
        this.dispose();
    }
}