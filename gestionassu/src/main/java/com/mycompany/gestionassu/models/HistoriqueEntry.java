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
public class HistoriqueEntry {
    private ObjectId id;
    private String cni;
    private String nomClient;
    private String prenomClient;
    private AssuranceType type;
    private String numeroPolice;
    private Date dateDebut;
    private Date dateFin;
    private String status;
    
    public HistoriqueEntry(String cni, String nomClient, String prenomClient, 
                          AssuranceType type, String numeroPolice, 
                          Date dateDebut, Date dateFin, String status) {
        this.cni = cni;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.type = type;
        this.numeroPolice = numeroPolice;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
    }
    
    // Getters et Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getCni() { return cni; }
    public void setCni(String cni) { this.cni = cni; }
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
    public String getPrenomClient() { return prenomClient; }
    public void setPrenomClient(String prenomClient) { this.prenomClient = prenomClient; }
    public AssuranceType getType() { return type; }
    public void setType(AssuranceType type) { this.type = type; }
    public String getNumeroPolice() { return numeroPolice; }
    public void setNumeroPolice(String numeroPolice) { this.numeroPolice = numeroPolice; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}