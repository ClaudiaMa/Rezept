/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import java.util.List;
import javax.ejb.Stateless;
import rezept.jpa.Anlass;

/**
 *
 * @author Claudia
 */
@Stateless
public class AnlassBean extends EntityBean<Anlass, Long> {

    public AnlassBean() {
        super(Anlass.class);
    }

    public Anlass findByName(String name) {
        if (name == null) {
            return null;
        }

        List<Anlass> ergebnis = em.createQuery("SELECT a FROM Anlass a WHERE a.name = :name").setParameter("name", name).getResultList();

        if (ergebnis.isEmpty()) {
            return null;
        } else {
            return ergebnis.get(0);
        }

    }

}
