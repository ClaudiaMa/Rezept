/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import javax.ejb.Stateless;
import rezept.jpa.Grundzutat;

/**
 *
 * @author Claudia
 */
@Stateless
public class GrundzutatBean extends EntityBean<Grundzutat, Long>{
    
    public GrundzutatBean(){
        super(Grundzutat.class);
    }
    
}
