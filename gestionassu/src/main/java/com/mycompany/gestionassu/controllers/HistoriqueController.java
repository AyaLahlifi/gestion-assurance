/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.gestionassu.database.MongoDBConnection;
import com.mycompany.gestionassu.models.AssuranceType;
import com.mycompany.gestionassu.models.HistoriqueEntry;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoriqueController {
    private final MongoCollection<Document> historiqueCollection;
    
    public HistoriqueController() {
        historiqueCollection = MongoDBConnection.getCollection("historique");
    }
    
    public void addHistoriqueEntry(String cni, String nomClient, String prenomClient,
                                 AssuranceType type, String numeroPolice,
                                 Date dateDebut, Date dateFin, String status) {
        Document entry = new Document()
            .append("cni", cni)
            .append("nomClient", nomClient)
            .append("prenomClient", prenomClient)
            .append("type", type.toString())
            .append("numeroPolice", numeroPolice)
            .append("dateDebut", dateDebut)
            .append("dateFin", dateFin)
            .append("status", status)
            .append("dateCreation", new Date());
            
        historiqueCollection.insertOne(entry);
    }
    
    public List<HistoriqueEntry> findHistoriqueByCNI(String cni) {
        List<HistoriqueEntry> historique = new ArrayList<>();
        historiqueCollection.find(Filters.eq("cni", cni))
                          .sort(new Document("dateDebut", -1))
                          .forEach(doc -> {
            HistoriqueEntry entry = new HistoriqueEntry(
                doc.getString("cni"),
                doc.getString("nomClient"),
                doc.getString("prenomClient"),
                AssuranceType.valueOf(doc.getString("type")),
                doc.getString("numeroPolice"),
                doc.getDate("dateDebut"),
                doc.getDate("dateFin"),
                doc.getString("status")
            );
            entry.setId(doc.getObjectId("_id"));
            historique.add(entry);
        });
        return historique;
    }
}