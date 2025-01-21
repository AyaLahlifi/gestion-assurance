/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.views;

import com.mycompany.gestionassu.controllers.HistoriqueController;
import com.mycompany.gestionassu.models.HistoriqueEntry;
import com.mycompany.gestionassu.utils.DateUtils;
import com.mycompany.gestionassu.views.components.CustomButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoriquePage extends JFrame {
    private final String cni;
    private final HistoriqueController controller;
    private JTable historiqueTable;
    
    public HistoriquePage(String cni) {
        this.cni = cni;
        this.controller = new HistoriqueController();
        
        setupFrame();
        initializeComponents();
        loadHistorique();
    }
    
    private void setupFrame() {
        setTitle("Historique des Assurances - CNI: " + cni);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tableau
        String[] columns = {
            "Type d'Assurance",
            "Numéro Police",
            "Date Début",
            "Date Fin",
            "Status",
            "Nom",
            "Prénom"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        historiqueTable = new JTable(model);
        historiqueTable.setFillsViewportHeight(true);
        
        // Style du tableau
        historiqueTable.setFont(new Font("Arial", Font.PLAIN, 12));
        historiqueTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        historiqueTable.setRowHeight(25);
        historiqueTable.setShowGrid(true);
        historiqueTable.setGridColor(Color.LIGHT_GRAY);
        
        JScrollPane scrollPane = new JScrollPane(historiqueTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bouton retour
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        CustomButton backButton = new CustomButton("Retour", new Color(51, 122, 183));
        backButton.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadHistorique() {
        List<HistoriqueEntry> historique = controller.findHistoriqueByCNI(cni);
        DefaultTableModel model = (DefaultTableModel) historiqueTable.getModel();
        
        // Vider le tableau
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        
        // Remplir avec les nouvelles données
        for (HistoriqueEntry entry : historique) {
            model.addRow(new Object[]{
                entry.getType().getDisplayName(),
                entry.getNumeroPolice(),
                DateUtils.formatDate(entry.getDateDebut()),
                DateUtils.formatDate(entry.getDateFin()),
                entry.getStatus(),
                entry.getNomClient(),
                entry.getPrenomClient()
            });
        }
        
        if (historique.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Aucun historique trouvé pour ce numéro CNI",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
    
