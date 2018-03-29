/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rezept.jpa.Allergie;
import rezept.jpa.Anlass;
import rezept.jpa.Grundzutat;
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
    
    //Methode um Rezepte aus der Suchleiste zu finden
    //<editor-fold defaultstate="collapsed" desc="Suchen anhand Text in der Suchleiste">
    public List<Rezept> search(String search) {
        
        boolean stringIsValid = isValid(search);
        if (stringIsValid == true ) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        // SELECT r FROM Rezept r
        CriteriaQuery<Rezept> query = cb.createQuery(Rezept.class);
        Root<Rezept> from = query.from(Rezept.class);
        query.select(from);
        
        // WHERE r.rezeptname LIKE :search
        if (search != null && !search.trim().isEmpty()) {
            query.where(cb.equal(from.get("rezeptbeschreibung"), search));
        }
       
        return em.createQuery(query).getResultList();
        }
        
        else {
            return null;
        }
    }
    
    public boolean isValid (String search) {
        String numberRegex = ".*[0-9].*";
        String specialcharRegex = ".*[!§$%&@+#'^°].*";
        
        if ( search.matches(numberRegex) || search.matches(specialcharRegex)) {
            return false;
        }
        
        else {
            return true;
        }
    }
    
     public boolean rezeptOk (String search) {
        String stringRegex = ".*(false).*";
        
        if ( search.matches(stringRegex)) {
            return false;
        }
        
        else {
            return true;
        }
    }
     
    
    
    //</editor-fold>
     
    public List<Rezept> searchByFilters(List<Anlass> anlaesse, List<Grundzutat> grundzutaten, List<Allergie> allergien) {
        
        List<Rezept> rezepte = em.createQuery("SELECT r FROM Rezept r").getResultList();
        System.out.println("FUNKTIONIERT DER SELECT?????????????????????????????????????" + rezepte.toString());
        
        //for(int i = 0; i < rezept.size(); ++i) {}
        
            //String Array
            
           
            
            List<Rezept> ergebnisListe = new ArrayList<Rezept>();
            
            for ( Rezept rezept : rezepte) {
                System.out.println("Er geht in die erste Rezept-for Schleife rein!  Das gerade behandelte Rezept lautet: " + rezept.getRezeptname());
                String enthaeltAnlaesse  = "";
                String enthaeltAllergien = "";
                String enthaeltGrundzutaten = "";
                
                List<Allergie> allergienVonRezept = rezept.getAllergien();
                List<Anlass> anlässeVonRezept = rezept.getAnlässe();
                List<Grundzutat> grundzutatVonRezept = rezept.getGrundzutaten();
                 
                System.out.println("Das Rezept: " + rezept.getRezeptname() + " hat folgende Anlässe:   " + anlässeVonRezept );
                 System.out.println("Das Rezept:   " + rezept.getRezeptname() + "  hat folgende Allergien: " + allergienVonRezept );
                  System.out.println("Das Rezept:   " + rezept.getRezeptname() + " hat folgende Grundzutaten:   "+ grundzutatVonRezept );
               
                 
                 
                 
                //Anlässe durchsuchen
                if (!anlaesse.isEmpty()) {
                    for ( Anlass anlass : anlaesse) {
                         System.out.println(" Es wurden Anlässe angehakt und zwar die folgenden:  " + anlass.getName());
                         
                         String anlassWarEnthalten = "";
                         for ( Anlass a: anlässeVonRezept) {
                             if ( a.getName().equals(anlass.getName())) {
                                 System.out.println("Der angehakte Anlass "+ anlass.getName() + "  ist auch in der Anlassliste von Rezept enthalten!");
                                  anlassWarEnthalten = "ja";
                                  break;
                             }
                             else {
                                 System.out.println("Der angehakte Anlass  " + anlass.getName() + " ist nicht in der Anlassliste von Rezept enthalten"); 
                                 anlassWarEnthalten = "nein";
                             }
                         }                        
                         System.out.println("Der angehakte Anlass: " + anlass.getName() + " war in der AllergieListe von Rezept enthalten?: " + anlassWarEnthalten);
                         
                       if ( anlassWarEnthalten.equals("ja")) {
                           enthaeltAnlaesse = enthaeltAnlaesse + "true";
                       }
                       else {
                           enthaeltAnlaesse = enthaeltAnlaesse + "false";
                       }
                    }
                    }
                System.out.println("Der String enthaeltAnlaesse sieht so aus: " + enthaeltAnlaesse);
                
                
                //Allergien durchsuchen
                 if (!allergien.isEmpty()) {
                    for ( Allergie allergie : allergien) {
                         System.out.println(" Es wurden Allergien angehakt und zwar die folgenden:  " + allergie.getName());
                         
                         String allergieWarEnthalten = "";
                         for ( Allergie a: allergienVonRezept) {
                             if ( a.getName().equals(allergie.getName())) {
                                 System.out.println("Der angehakte Allergie "+ allergie.getName() + "  ist auch in der Allergieliste von Rezept enthalten!");
                                  allergieWarEnthalten = "ja";
                                  break;
                             }
                             else {
                                 System.out.println("Der angehakte Allergie  " + allergie.getName() + " ist nicht in der Allergieliste von Rezept enthalten"); 
                                 allergieWarEnthalten = "nein";
                             }
                         }                        
                         System.out.println("Die angehakte Allergie: " + allergie.getName() + " war in der AllergieListe von Rezept enthalten?: " + allergieWarEnthalten);
                         
                       if ( allergieWarEnthalten.equals("ja")) {
                           enthaeltAllergien = enthaeltAllergien + "true";
                       }
                       else {
                           enthaeltAllergien = enthaeltAllergien + "false";
                       }
                    }
                    }
                System.out.println("Der String enthaeltAllergie sieht so aus: " + enthaeltAllergien);
                
                //Grundzutaten untersuchen
                if (!grundzutaten.isEmpty()) {
                    for ( Grundzutat grundzutat : grundzutaten) {
                         System.out.println(" Es wurden Zutaten angehakt und zwar die folgenden:  " + grundzutat.getName());
                         
                         String grundzutatWarEnthalten = "";
                         for ( Grundzutat g: grundzutatVonRezept) {
                             if ( g.getName().equals(grundzutat.getName())) {
                                 System.out.println("Die angehakte Zutat "+ grundzutat.getName() + "  ist auch in der Zutatenliste von Rezept enthalten!");
                                  grundzutatWarEnthalten = "ja";
                                  break;
                             }
                             else {
                                 System.out.println("Die angehakte Zutat  " + grundzutat.getName() + " ist nicht in der Zutatenliste von Rezept enthalten"); 
                                 grundzutatWarEnthalten = "nein";
                             }
                         }                        
                         System.out.println("Die angehakte Zutat: " + grundzutat.getName() + " war in der Zutatenliste von Rezept enthalten?: " + grundzutatWarEnthalten);
                         
                       if ( grundzutatWarEnthalten.equals("ja")) {
                           enthaeltGrundzutaten = enthaeltGrundzutaten + "true";
                       }
                       else {
                           enthaeltGrundzutaten = enthaeltGrundzutaten + "false";
                       }
                    }
                    }
                System.out.println("Der String enthaeltGrundzutaten sieht so aus: " + enthaeltGrundzutaten);
                
                boolean rezeptOkAnlaesse = rezeptOk(enthaeltAnlaesse);
                System.out.println("Der erste boolean:  " + rezeptOkAnlaesse);
                boolean rezeptOkAllergien = rezeptOk(enthaeltAllergien);
                System.out.println("Der zweite boolean: " + rezeptOkAllergien);
                boolean rezeptOkZutate = rezeptOk(enthaeltGrundzutaten);
                System.out.println("Der dritte boolean: " + rezeptOkZutate);
              
                
                if ( rezeptOkAnlaesse == true && rezeptOkAllergien == true && rezeptOkZutate == true){
                    Long id = rezept.getId();
                    ergebnisListe.add(this.findById(id));
                    System.out.println("Alle Eigenschaften treffen auf das Rezept zu: " + rezept.getRezeptname() + "Dieses wird jz in der DB gesucht");
                }
            }
                
            
            return ergebnisListe;
       
       
    }
  
}
            
        
    
