/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.gestionassu.database.MongoDBConnection;
import com.mycompany.gestionassu.models.Client;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ayala
 */
public class ClientController {
    private final MongoCollection<Document> clientsCollection;
    
    public ClientController() {
        clientsCollection = MongoDBConnection.getCollection("clients");
    }
    
    public ObjectId saveClient(Client client) {
        Document clientDoc = new Document()
            .append("nom", client.getNom())
            .append("prenom", client.getPrenom())
            .append("cni", client.getCni())
            .append("email", client.getEmail())
            .append("telephone", client.getTelephone())
            .append("adresse", client.getAdresse());
            
        clientsCollection.insertOne(clientDoc);
        return clientDoc.getObjectId("_id");
    }
    
    public Client findClientByCNI(String cni) {
        Document doc = clientsCollection.find(Filters.eq("cni", cni)).first();
        if (doc == null) return null;
        
        Client client = new Client(
            doc.getString("nom"),
            doc.getString("prenom"),
            doc.getString("cni"),
            doc.getString("email"),
            doc.getString("telephone"),
            doc.getString("adresse")
        );
        client.setId(doc.getObjectId("_id"));
        return client;
    }
    
    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        clientsCollection.find().forEach(doc -> {
            Client client = new Client(
                doc.getString("nom"),
                doc.getString("prenom"),
                doc.getString("cni"),
                doc.getString("email"),
                doc.getString("telephone"),
                doc.getString("adresse")
            );
            client.setId(doc.getObjectId("_id"));
            clients.add(client);
        });
        return clients;
    }
 
}
