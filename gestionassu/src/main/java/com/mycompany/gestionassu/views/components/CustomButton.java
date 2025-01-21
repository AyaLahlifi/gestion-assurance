/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.views.components;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ayala
 */
public class CustomButton extends JButton {
    private final Color baseColor;
    
    public CustomButton(String text, Color themeColor) {
        super(text);
        this.baseColor = themeColor;
        
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setBackground(baseColor);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(baseColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(baseColor);
            }
        });
    }
    
}
