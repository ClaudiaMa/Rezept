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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="REZEPT")
public class Rezept implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Many-to-Many Beziehungen zu Anlass, Allegie und Grundzutat">
    @Id
    @Column(name="rezept_id")
    @GeneratedValue(generator="rezept_id")
    @TableGenerator(name="rezept_id", initialValue = 0, allocationSize = 50)
    private Long rezeptId;
    
    @ManyToMany
    @JoinTable(name="rezept_anlass",
            joinColumns=
                    @JoinColumn(name="rezept_id", referencedColumnName="rezept_id"),
            inverseJoinColumns=
                    @JoinColumn(name="anlass_id", referencedColumnName="anlass_id")
    )
    private List<Anlass> anlassListe;
    
    
    
    @ManyToMany
    @JoinTable(name="rezept_allergie",
            joinColumns=
                    @JoinColumn(name="rezept_id", referencedColumnName="rezept_id"),
            inverseJoinColumns=
                    @JoinColumn(name="allergie_id", referencedColumnName="allergie_id")
    )
    private List<Allergie> allergieListe;
    
    
    @ManyToMany
    @JoinTable(name="rezept_allergie",
            joinColumns=
                    @JoinColumn(name="rezept_id", referencedColumnName="rezept_id"),
            inverseJoinColumns=
                    @JoinColumn(name="grundzutat_id", referencedColumnName="grundzutat_id")
    )
    private List<Grundzutat> grundzutatListe;
    
//</editor-fold>
                  
    /*@ManyToMany(mappedBy="rezepten")
    List<Allergie> allergien = new ArrayList<>();
    
    @ManyToMany(mappedBy="rezepten")
    List<Anlass> anlässe = new ArrayList<>();
    
    @ManyToMany(mappedBy="rezepten")
    List<Grundzutat> grundzutaten = new ArrayList<>();
    */
    
    private String rezeptname = "";
    private String rezeptbeschreibung = "";
    private String aufwand = "";
    private int dauer = 0;
    private String bild = ""; // ??
   
//<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Rezept() {
        
    }
    
    public Rezept(String rezeptname, String rezeptbeschreibung, String aufwand, int dauer, String bild){
        
        this.rezeptname = rezeptname;
        this.rezeptbeschreibung = rezeptbeschreibung;
        this.aufwand = aufwand;
        this.dauer = dauer;
        this.bild = bild;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public Long getId() {
        return rezeptId;
    }
    
    public List<Allergie> getAllergien() {
        return allergieListe;
    }
    
    public List<Anlass> getAnlässe() {
        return anlassListe;
    }
    
    public List<Grundzutat> getGrundzutaten() {
        return grundzutatListe;
    }
    
    public String getRezeptname() {
        return rezeptname;
    }
    
    public String getRezeptbeschreibung() {
        return rezeptbeschreibung;
    }
    
    public String getAufwand() {
        return aufwand;
    }
    
    public int getDauer() {
        return dauer;
    }
    
    public String getBild() {
        return bild;
    }
    
    public void setId(Long id) {
        this.rezeptId = id;
    }
    
    public void setAllergien(List<Allergie> allergien) {
        this.allergieListe = allergien;
    }
    
    public void setAnlässe(List<Anlass> anlässe) {
        this.anlassListe = anlässe;
    }
    
    public void setGrundzutaten(List<Grundzutat> grundzutaten) {
        this.grundzutatListe = grundzutaten;
    }
    
    public void setRezeptname(String rezeptname) {
        this.rezeptname = rezeptname;
    }
    
    public void setRezeptbeschreibung(String rezeptbeschreibung) {
        this.rezeptbeschreibung = rezeptbeschreibung;
    }
    
    public void setAufwand(String aufwand) {
        this.aufwand = aufwand;
    }
    
    public void setDauer(int dauer) {
        this.dauer = dauer;
    }
    
    public void setBild(String bild) {
        this.bild = bild;
    }
//</editor-fold>


}
