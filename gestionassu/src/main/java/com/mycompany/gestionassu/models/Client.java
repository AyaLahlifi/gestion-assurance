/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.models;

import org.bson.types.ObjectId;
/**
 *
 * @author ayala
 */
public class Client {
    private ObjectId id;
    private String nom;
    private String prenom;
    private String cni;
    private String email;
    private String telephone;
    private String adresse;
    
    public Client(String nom, String prenom, String cni, String email, String telephone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.cni = cni;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    
    // Getters et Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getCni() { return cni; }
    public void setCni(String cni) { this.cni = cni; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

}