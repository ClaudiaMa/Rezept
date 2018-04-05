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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;


@Entity
public class Rezept implements Serializable {

    @Id
    @GeneratedValue(generator="rezept_id")
    @TableGenerator(name="rezept_id", initialValue = 0, allocationSize = 50)
    private Long id;
    
    @ManyToMany(mappedBy="rezepten", cascade = {CascadeType.PERSIST}, fetch=FetchType.EAGER)
    List<Allergie> allergien = new ArrayList<>();
    
    @ManyToMany(mappedBy="rezepten", cascade = {CascadeType.PERSIST}, fetch=FetchType.EAGER)
    List<Anlass> anlässe = new ArrayList<>();
    
    @ManyToMany(mappedBy="rezepten", cascade = {CascadeType.PERSIST}, fetch=FetchType.EAGER)
    List<Grundzutat> grundzutaten = new ArrayList<>();
    
    
    private String rezeptname = "";
    
    @Lob
    private String rezeptbeschreibung = "";
    
    @Lob
    private String zutaten ="";
    
    private String aufwand = "";
    private int dauer = 0;
    private String bild = ""; 
   
    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Rezept() {
        
    }
    
    public Rezept(String rezeptname,String zutaten, String rezeptbeschreibung, String aufwand, int dauer, String bild){
       
        this.rezeptname = rezeptname;
        this.zutaten = zutaten;
        this.rezeptbeschreibung = rezeptbeschreibung;
        this.aufwand = aufwand;
        this.dauer = dauer;
        this.bild = bild;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter und Setter">
    public Long getId() {
        return id;
    }
    
    public List<Allergie> getAllergien() {
        return allergien;
    }
    
    public List<Anlass> getAnlässe() {
        return anlässe;
    }
    
    public List<Grundzutat> getGrundzutaten() {
        return grundzutaten;
    }
    
    public String getZutaten() {
        return zutaten;
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
        this.id = id;
    }
    
    public void setAllergien(List<Allergie> allergien) {
        this.allergien = allergien;
    }
    
    public void setAnlässe(List<Anlass> anlässe) {
        this.anlässe = anlässe;
    }
    
    public void setGrundzutaten(List<Grundzutat> grundzutaten) {
        this.grundzutaten = grundzutaten;
    }
    
    public void setRezeptname(String rezeptname) {
        this.rezeptname = rezeptname;
    }
    
    public void setRezeptbeschreibung(String rezeptbeschreibung) {
        this.rezeptbeschreibung = rezeptbeschreibung;
    }
    
    public void setZutaten(String zutaten){
        this.zutaten = zutaten;
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

