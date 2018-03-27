/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import java.util.List;
import javax.ejb.Stateless;
import rezept.jpa.Allergie;


/**
 *
 * @author Claudia
 */
@Stateless
public class AllergieBean extends EntityBean<Allergie, Long> {
    
    public AllergieBean() {
        super(Allergie.class);
    }
    
    public Allergie findByName(String name) {
        if (name == null) {
            return null;
        }
        
        List<Allergie> ergebnis = em.createQuery("SELECT a FROM Allergie a WHERE a.name = :name").setParameter("name", name).getResultList();
        
        if (ergebnis.isEmpty()) {
            return null;
        } else {
            return ergebnis.get(0);
        }
    }
    
}
