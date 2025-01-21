/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.views.components;

import com.mycompany.gestionassu.models.AssuranceType;
import com.mycompany.gestionassu.views.MainFrame;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;

public class AssuranceCard extends JPanel {
    private final AssuranceType type;
    private final MainFrame mainFrame;
    private final Color themeColor;
    
    public AssuranceCard(AssuranceType type, MainFrame mainFrame) {
        this.type = type;
        this.mainFrame = mainFrame;
        this.themeColor = getThemeColorForType(type);
        
        setupCard();
    }
    
    private void setupCard() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(themeColor, 2));
        setBackground(Color.WHITE);
        
        // Icon Panel
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(Color.WHITE);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icons/" + 
                type.toString().toLowerCase() + ".png"));
            Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
            iconPanel.add(iconLabel);
        } catch (Exception e) {
            System.err.println("Erreur de chargement de l'icône: " + e.getMessage());
        }
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel(type.getDisplayName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(themeColor);
        titlePanel.add(titleLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        CustomButton button = new CustomButton("En savoir plus", themeColor);
        button.addActionListener(e -> mainFrame.openFormulaire(type));
        buttonPanel.add(button);
        
        // Add components
        add(iconPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(245, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.WHITE);
            }
        });
    }
    
    private Color getThemeColorForType(AssuranceType type) {
        // Même méthode que dans FormulairePage
        switch(type) {
            case AUTOMOBILE:
                return new Color(70, 130, 180);
            case HABITATION:
                return new Color(60, 179, 113);
            case SANTE:
                return new Color(221, 160, 221);
            case EPARGNE:
                return new Color(255, 140, 0);
            case LOISIRS:
                return new Color(106, 90, 205);
            case PREVOYANCE:
                return new Color(178, 34, 34);
            default:
                return new Color(100, 100, 100);
        }
    }
}
