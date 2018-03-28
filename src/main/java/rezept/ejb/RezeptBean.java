/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rezept.jpa.Allergie;
import rezept.jpa.Anlass;
import rezept.jpa.Grundzutat;
import rezept.jpa.Rezept;

/**
 *
 * @author Claudia
 */

@Stateless
public class RezeptBean extends EntityBean<Rezept, Long> {
    
    public RezeptBean() {
        super(Rezept.class);
    }
    
    //Methode um Rezepte aus der Suchleiste zu finden
    //<editor-fold defaultstate="collapsed" desc="Suchen anhand Text in der Suchleiste">
    public List<Rezept> search(String search) {
        
        boolean stringIsValid = isValid(search);
        if (stringIsValid == true ) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        // SELECT r FROM Rezept r
        CriteriaQuery<Rezept> query = cb.createQuery(Rezept.class);
        Root<Rezept> from = query.from(Rezept.class);
        query.select(from);
        
        // WHERE r.rezeptname LIKE :search
        if (search != null && !search.trim().isEmpty()) {
            //query.where(cb.like(from.get("rezeptbeschreibung"), "%" + search + "%"));
        }
       
        return em.createQuery(query).getResultList();
        }
        
        else {
            return null;
        }
    }
    
    public boolean isValid (String search) {
        String numberRegex = ".*[0-9].*";
        String specialcharRegex = ".*[!§$%&@+#'^°].*";
        
        if ( search.matches(numberRegex) || search.matches(specialcharRegex)) {
            return false;
        }
        
        else {
            return true;
        }
    }
    //</editor-fold>
     
    public List<Rezept> searchByFilters(List<Anlass> anlaesse, List<Grundzutat> grundzutaten, List<Allergie> allergien) {
    // WHERE-Bedingungen für die Filteroptionen zusammenbauen
    String select = "SELECT r FROM Rezept r";
    String where = "";

    
    Map<String, Object> parameters = new HashMap<>();
    int i = 0;

    for (Anlass anlass : anlaesse) {
        i++;
        parameters.put("" + i, anlass);

        if (!where.isEmpty()) {
            where += " AND ";
        }

        where += ":" + i + " MEMBER OF r.anlässe";
    }
    
    for (Grundzutat grundzutat : grundzutaten) {
        i++;
        parameters.put("" + i, grundzutat);

        if (!where.isEmpty()) {
            where += " AND ";
        }

        where += ":" + i + " MEMBER OF r.grundzutaten";
    }
    
    for (Allergie allergie : allergien) {
        i++;
        parameters.put("" + i, allergie);

        if (!where.isEmpty()) {
            where += " AND ";
        }

        where += ":" + i + " MEMBER OF r.allergien";
    }

    // Finalen SELECT-String zusammenbauen und Suche ausführen
    if (!where.isEmpty()) {
        select += " WHERE " + where;
    }

    Query query = em.createQuery(select);

    for (String key : parameters.keySet()) {
        query.setParameter(key, parameters.get(key));
    }

    return query.getResultList();
}
        
    
}
