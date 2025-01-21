/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionassu.utils;

import com.mycompany.gestionassu.models.AssuranceType;


/**
 *
 * @author ayala
 */
public class IconManager {
    public static String getIconPathForType(AssuranceType type){
        switch (type){
            case AUTOMOBILE:
                return "/com/mycompany/gestionassu/resources/icons/automobile.png";
            case HABITATION :
                return "/com/mycompany/gestionassu/resources/icons/habitation.png";
            case SANTE : 
                return "/com/mycompany/gestionassu/resources/icons/sante.png";
            case EPARGNE: 
                return "/com/mycompany/gestionassu/resources/icons/epargne.png";
            case LOISIRS :
                return "/com/mycompany/gestionassu/resources/icons/loisirs.png";
            case PREVOYANCE:
                return "/com/mycompany/gestionassu/resources/icons/prevoyance.png";
            default :
                return "/com/mycompany/gestionassu/resources/icons/default.png";
        }
    }   
}
