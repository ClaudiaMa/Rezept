/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;


@Entity
public class Allergie implements Serializable {

    @Id
    @GeneratedValue(generator="allergie_id")
    @TableGenerator(name="allergie_id", initialValue = 0, allocationSize = 50)
    private Long id;
    
    @ManyToMany(cascade = {CascadeType.PERSIST})
    List<Rezept> rezepten = new ArrayList<>();
    
    private String name = "";

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Allergie() {
    }
    
    public Allergie(String name) {
        
        this.name = name;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public Long getId() {
        return id;
    }
    
    public List<Rezept> getRezepten() {
        return rezepten;
    }
    
    public String getName() {
        return name;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setRezepten(List<Rezept> rezepten) {
        this.rezepten = rezepten;
    }
    
    public void setName(String name) {
        this.name = name;
    }
//</editor-fold>    
}
