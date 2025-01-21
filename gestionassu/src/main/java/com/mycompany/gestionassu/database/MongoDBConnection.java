/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javax.swing.JOptionPane;
/**
 *
 * @author ayala
 */
public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "assurance_db";
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    public static void initializeDatabase() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            
            // Créer les collections nécessaires
            createCollectionIfNotExists("clients");
            createCollectionIfNotExists("assurances");
            createCollectionIfNotExists("historique");
            
            System.out.println("Connexion à MongoDB établie avec succès!");
            
        } catch (Exception e) {
            String errorMessage = "Erreur de connexion à MongoDB: " + e.getMessage() + 
                                "\nAssurez-vous que MongoDB est en cours d'exécution sur localhost:27017";
            System.err.println(errorMessage);
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur MongoDB", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void createCollectionIfNotExists(String collectionName) {
        try {
            boolean collectionExists = database.listCollectionNames()
                .into(new java.util.ArrayList<>())
                .contains(collectionName);
            
            if (!collectionExists) {
                database.createCollection(collectionName);
                System.out.println("Collection " + collectionName + " créée avec succès!");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la collection " + collectionName + ": " + e.getMessage());
        }
    }
    
    public static MongoDatabase getDatabase() {
        if (database == null) {
            initializeDatabase();
        }
        return database;
    }
    
    public static MongoCollection<Document> getCollection(String collectionName) {
        return getDatabase().getCollection(collectionName);
    }
    
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Connexion MongoDB fermée.");
        }
    }
    
}
