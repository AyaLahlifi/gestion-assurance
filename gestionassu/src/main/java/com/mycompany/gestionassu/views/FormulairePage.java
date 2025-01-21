/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.views;

import com.mycompany.gestionassu.controllers.AssuranceController;
import com.mycompany.gestionassu.models.*;
import static com.mycompany.gestionassu.models.AssuranceType.AUTOMOBILE;
import static com.mycompany.gestionassu.models.AssuranceType.EPARGNE;
import static com.mycompany.gestionassu.models.AssuranceType.HABITATION;
import static com.mycompany.gestionassu.models.AssuranceType.LOISIRS;
import static com.mycompany.gestionassu.models.AssuranceType.PREVOYANCE;
import static com.mycompany.gestionassu.models.AssuranceType.SANTE;
import com.mycompany.gestionassu.utils.*;
import com.mycompany.gestionassu.views.components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FormulairePage extends JFrame {
    private final AssuranceType type;
    private final Color themeColor;
    
    private JTextField nomField, prenomField, cniField, emailField, telephoneField, adresseField;
    private JTextField numeroPoliceField, montantField;
    private JTextField dateDebutField, dateFinField;
    
    public FormulairePage(AssuranceType type) {
        this.type = type;
        this.themeColor = getThemeColorForType(type);
        
        setupFrame();
        initializeComponents();
    }
    
    private void setupFrame() {
        setTitle("Formulaire - " + type.getDisplayName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 200);
        setLocationRelativeTo(null);
        
        // Retour à la page principale lors de la fermeture
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Section Informations Personnelles
        addSection(mainPanel, "Informations Personnelles");
        nomField = addFormField(mainPanel, "Nom *:", "");
        prenomField = addFormField(mainPanel, "Prénom *:", "");
        cniField = addFormField(mainPanel, "CNI *:", "Format: XX12345");
        emailField = addFormField(mainPanel, "Email *:", "exemple@email.com");
        telephoneField = addFormField(mainPanel, "Téléphone *:", "+2126-XXXXXXX");
        adresseField = addFormField(mainPanel, "Adresse *:", "");
        
        // Section Informations Assurance
        addSection(mainPanel, "Informations d'Assurance");
        numeroPoliceField = addFormField(mainPanel, "Numéro de Police *:", "");
        montantField = addFormField(mainPanel, "Montant de Couverture (MAD) *:", "");
        dateDebutField = addFormField(mainPanel, "Date de Début *:", "YYYY-MM-DD");
        dateFinField = addFormField(mainPanel, "Date de Fin *:", "YYYY-MM-DD");
        
        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        CustomButton submitButton = new CustomButton("Soumettre", themeColor);
        CustomButton cancelButton = new CustomButton("Annuler", themeColor);
        
        submitButton.addActionListener(e -> submitForm());
        cancelButton.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });
        
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        
        add(new JScrollPane(mainPanel));
    }
    
    private void addSection(JPanel panel, String title) {
        panel.add(Box.createVerticalStrut(10));
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(themeColor);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
    }
    
    private JTextField addFormField(JPanel panel, String labelText, String placeholder) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(200, 25));
        
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(300, 25));
        field.setToolTipText(placeholder);
        
        fieldPanel.add(label);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
        
        return field;
    }
    
    private void submitForm() {
        // Validation des champs
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez remplir correctement tous les champs obligatoires",
                "Erreur de validation",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Créer le client
            Client client = new Client(
                nomField.getText(),
                prenomField.getText(),
                cniField.getText(),
                emailField.getText(),
                telephoneField.getText(),
                adresseField.getText()
            );
            
            // Créer l'assurance
            AssuranceInfo assurance = new AssuranceInfo(
                type,
                null, // clientId sera défini par le controller
                numeroPoliceField.getText(),
                Double.parseDouble(montantField.getText()),
                DateUtils.parseDate(dateDebutField.getText()),
                DateUtils.parseDate(dateFinField.getText())
            );
            
            // Sauvegarder
            new AssuranceController().saveAssurance(client, assurance);
            
            JOptionPane.showMessageDialog(this,
                "Assurance enregistrée avec succès!",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
                
            new MainFrame().setVisible(true);
            this.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erreur lors de l'enregistrement: " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean validateFields() {
        return !nomField.getText().isEmpty() &&
               !prenomField.getText().isEmpty() &&
               ValidationUtils.isValidCNI(cniField.getText()) &&
               ValidationUtils.isValidEmail(emailField.getText()) &&
               ValidationUtils.isValidPhone(telephoneField.getText()) &&
               !adresseField.getText().isEmpty() &&
               !numeroPoliceField.getText().isEmpty() &&
               ValidationUtils.isValidAmount(montantField.getText()) &&
               ValidationUtils.isValidDate(dateDebutField.getText()) &&
               ValidationUtils.isValidDate(dateFinField.getText()) &&
               ValidationUtils.isDateRangeValid(
                   DateUtils.parseDate(dateDebutField.getText()),
                   DateUtils.parseDate(dateFinField.getText())
               );
    }
    
    private Color getThemeColorForType(AssuranceType type) {
        switch(type) {
            case AUTOMOBILE:
                return new Color(70, 130, 180); // Steel Blue
            case HABITATION:
                return new Color(60, 179, 113); // Medium Sea Green
            case SANTE:
                return new Color(221, 160, 221); // Plum
            case EPARGNE:
                return new Color(255, 140, 0);   // Dark Orange
            case LOISIRS:
                return new Color(106, 90, 205);  // Slate Blue
            case PREVOYANCE:
                return new Color(178, 34, 34);   // Fire Brick
            default:
                return new Color(100, 100, 100); // Default Gray
        }
    }
}
