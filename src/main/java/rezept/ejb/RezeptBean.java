/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import javax.ejb.Stateless;
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
    
}
