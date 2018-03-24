/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import java.util.List;
import javax.ejb.Stateless;
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
    //</editor-fold>
     
     public List<Rezept> searchByFilters(Anlass frühstück, Anlass brunch, Anlass mittagessen, Anlass abendessen, Grundzutat eier, Grundzutat nudeln, Grundzutat kartoffeln, Grundzutat tomaten, Allergie weizen, Allergie gluten, Allergie laktose) {
         
         // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
         // SELECT r FROM Rezept r
        CriteriaQuery<Rezept> query = cb.createQuery(Rezept.class);
        Root<Rezept> from = query.from(Rezept.class);
        query.select(from);
        
        //WHERE-Abfragen bauen, je nachdem ob boolean true oder false
        
        //Anlässe
        if (frühstück != null) {
            query.where(cb.equal(from.get("rezept.anlässe.name"), "frühstück"));
        }
        
       
        //SELECT r FROM Rezept AS r WHERE : frühstück MEMBER OF r.anlässe
        
        

        
        
        
        //Zutaten
        
        //Allergien
        
        
        
        
        
        
         
         return em.createQuery(query).getResultList();
     }
             
             
    
}
