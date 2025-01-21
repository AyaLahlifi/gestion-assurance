/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionassu;

import com.mycompany.gestionassu.database.MongoDBConnection;
import com.mycompany.gestionassu.views.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Gestionassu {
    public static void main(String[] args) {
        try {
            // Set Sys 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Mongo connectionn
            MongoDBConnection.initializeDatabase();
            
            //  GUI
            SwingUtilities.invokeLater(() -> {
                new MainFrame().setVisible(true);
            });
            
            // close MongoDB connectionn
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                MongoDBConnection.closeConnection();
            }));
            
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
