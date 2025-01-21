/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.gestionassu.database.MongoDBConnection;
import com.mycompany.gestionassu.models.AssuranceInfo;
import com.mycompany.gestionassu.models.AssuranceType;
import com.mycompany.gestionassu.models.Client;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ayala
 */
public class AssuranceController {
    private final MongoCollection<Document> assurancesCollection;
    private final ClientController clientController;
    private final HistoriqueController historiqueController;
    
    public AssuranceController() {
        assurancesCollection = MongoDBConnection.getCollection("assurances");
        clientController = new ClientController();
        historiqueController = new HistoriqueController();
    }
    
    public void saveAssurance(Client client, AssuranceInfo assurance) {
        // Vérifier si le client existe déjà
        Client existingClient = clientController.findClientByCNI(client.getCni());
        ObjectId clientId;
        
        if (existingClient == null) {
            // Nouveau client
            clientId = clientController.saveClient(client);
        } else {
            clientId = existingClient.getId();
        }
        
        // Sauvegarder l'assurance
        Document assuranceDoc = new Document()
            .append("type", assurance.getType().toString())
            .append("clientId", clientId)
            .append("numeroPolice", assurance.getNumeroPolice())
            .append("montantCouverture", assurance.getMontantCouverture())
            .append("dateDebut", assurance.getDateDebut())
            .append("dateFin", assurance.getDateFin())
            .append("status", assurance.getStatus());
            
        assurancesCollection.insertOne(assuranceDoc);
        
        // Ajouter à l'historique
        historiqueController.addHistoriqueEntry(
            client.getCni(),
            client.getNom(),
            client.getPrenom(),
            assurance.getType(),
            assurance.getNumeroPolice(),
            assurance.getDateDebut(),
            assurance.getDateFin(),
            assurance.getStatus()
        );
    }
    
    public List<AssuranceInfo> findAssurancesByClientId(ObjectId clientId) {
        List<AssuranceInfo> assurances = new ArrayList<>();
        assurancesCollection.find(Filters.eq("clientId", clientId)).forEach(doc -> {
            AssuranceInfo assurance = new AssuranceInfo(
                AssuranceType.valueOf(doc.getString("type")),
                doc.getObjectId("clientId"),
                doc.getString("numeroPolice"),
                doc.getDouble("montantCouverture"),
                doc.getDate("dateDebut"),
                doc.getDate("dateFin")
            );
            assurance.setId(doc.getObjectId("_id"));
            assurance.setStatus(doc.getString("status"));
            assurances.add(assurance);
        });
        return assurances;
    }
    
}