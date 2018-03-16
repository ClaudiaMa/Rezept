/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

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
    
}
