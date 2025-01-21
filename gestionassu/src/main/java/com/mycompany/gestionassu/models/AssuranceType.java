/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.models;

/**
 *
 * @author ayala
 */
public enum AssuranceType {
    AUTOMOBILE("Auto / Moto"),
    HABITATION("Habitation"),
    SANTE("Santé"),
    EPARGNE("Épargne"),
    LOISIRS("Loisirs"),
    PREVOYANCE("Prévoyance");
    
    private String displayName;
    
    AssuranceType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}