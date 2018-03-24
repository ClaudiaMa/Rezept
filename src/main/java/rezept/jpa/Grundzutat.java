/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;


@Entity(name="GRUNDZUTAT")
public class Grundzutat implements Serializable {

    @Id
    @Column(name="grundzutat_id", nullable = false)
    @GeneratedValue(generator="grundzutat_id")
    @TableGenerator(name="grundzutat_id", initialValue = 0, allocationSize = 50)
    private Long grundzutatId;
    
    private String name = "";
    
    @ManyToMany(mappedBy="grundzutatListe",fetch=FetchType.EAGER)
    private List<Rezept> rezeptListe;
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Grundzutat() {
    }
    
    public Grundzutat(String name) {
        
        this.name = name;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public Long getId() {
        return grundzutatId;
    }
    
    public List<Rezept> getRezepten() {
        return rezeptListe;
    }
    
    public String getName() {
        return name;
    }
    
    public void setId(Long id) {
        this.grundzutatId = id;
    }
    
    public void setRezepten(List<Rezept> rezepten) {
        this.rezeptListe = rezepten;
    }
    
    public void setName(String name) {
        this.name = name;
    }
//</editor-fold>    
}
