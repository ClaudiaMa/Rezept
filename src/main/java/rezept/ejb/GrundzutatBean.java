/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import java.util.List;
import javax.ejb.Stateless;
import rezept.jpa.Grundzutat;

/**
 *
 * @author Claudia
 */
@Stateless
public class GrundzutatBean extends EntityBean<Grundzutat, Long> {

    public GrundzutatBean() {
        super(Grundzutat.class);
    }

    public Grundzutat findByName(String name) {
        if (name == null) {
            return null;
        }

        List<Grundzutat> ergebnis = em.createQuery("SELECT g FROM Grundzutat g WHERE g.name = :name").setParameter("name", name).getResultList();

        if (ergebnis.isEmpty()) {
            return null;
        } else {
            return ergebnis.get(0);
        }
    }

}
