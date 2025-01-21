/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.models;

import org.bson.types.ObjectId;
import java.util.Date;
/**
 *
 * @author ayala
 */
public class AssuranceInfo {
    private ObjectId id;
    private AssuranceType type;
    private ObjectId clientId;
    private String numeroPolice;
    private double montantCouverture;
    private Date dateDebut;
    private Date dateFin;
    private String status;
//constructors    
    public AssuranceInfo(AssuranceType type, ObjectId clientId, String numeroPolice, 
                        double montantCouverture, Date dateDebut, Date dateFin) {
        this.type = type;
        this.clientId = clientId;
        this.numeroPolice = numeroPolice;
        this.montantCouverture = montantCouverture;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = "ACTIF";
    }
    
    // Getters et Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public AssuranceType getType() { return type; }
    public void setType(AssuranceType type) { this.type = type; }
    public ObjectId getClientId() { return clientId; }
    public void setClientId(ObjectId clientId) { this.clientId = clientId; }
    public String getNumeroPolice() { return numeroPolice; }
    public void setNumeroPolice(String numeroPolice) { this.numeroPolice = numeroPolice; }
    public double getMontantCouverture() { return montantCouverture; }
    public void setMontantCouverture(double montantCouverture) { this.montantCouverture = montantCouverture; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
    

