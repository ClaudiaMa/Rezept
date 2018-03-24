/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;
import static jdk.nashorn.internal.runtime.Debug.id;


@Entity(name="ALLERGIE")
public class Allergie implements Serializable {

    @Id
    @Column(name="allergie_id", nullable = false)
    @GeneratedValue(generator="allergie_id")
    @TableGenerator(name="allergie_id", initialValue = 0, allocationSize = 50)
    private Long allergieId;
    
    
    @ManyToMany(mappedBy="allergieListe",fetch=FetchType.EAGER)
    private List<Rezept> rezeptListe;
    
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
        return allergieId;
    }
    
    public List<Rezept> getRezepten() {
        return rezeptListe;
    }
    
    public String getName() {
        return name;
    }
    
    public void setId(Long id) {
        this.allergieId = id;
    }
    
    public void setRezepten(List<Rezept> rezepten) {
        this.rezeptListe = rezepten;
    }
    
    public void setName(String name) {
        this.name = name;
    }
//</editor-fold>    
}
