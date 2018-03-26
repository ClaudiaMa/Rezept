package rezept.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rezept.ejb.AllergieBean;
import rezept.ejb.AnlassBean;
import rezept.ejb.GrundzutatBean;
import rezept.ejb.RezeptBean;
import rezept.jpa.Allergie;
import rezept.jpa.Anlass;
import rezept.jpa.Grundzutat;
import rezept.jpa.Rezept;

/**
 * Suche nach Rezepten
 */
@WebServlet(urlPatterns={"/suche"})
public class SuchServlet extends HttpServlet {
    
    @EJB
    AnlassBean anlassBean;
    
    @EJB
    AllergieBean allergieBean;
    
    @EJB
    GrundzutatBean grundzutatBean;
    
    @EJB
    RezeptBean rezeptBean;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse repsonse)
            throws IOException, ServletException {
    
        List<Anlass> anlaesse = new ArrayList<>();
        List<Grundzutat> grundzutaten = new ArrayList<>();
        List<Allergie> allergien = new ArrayList<>();
        
        String[] anlassCheckboxen = request.getParameterValues("anlass");
        String[] grundzutatCheckboxen = request.getParameterValues("grundzutat");
        String[] allergieCheckboxen = request.getParameterValues("allergie");
        
        // Angekreuzte Anlässe ermitteln
        if (anlassCheckboxen != null) {
            for (String anlassCheckbox : anlassCheckboxen) {
                long id;
                
                try {
                    id = Long.parseLong(anlassCheckbox);
                } catch (NumberFormatException ex) {
                    continue;
                }
                
                Anlass anlass = this.anlassBean.findById(id);
                anlaesse.add(anlass);
            }
        }
        
        // Angekreuzte Allergien ermitteln
          if (allergieCheckboxen != null) {
            for (String allergieCheckbox : allergieCheckboxen) {
                long id;
                
                try {
                    id = Long.parseLong(allergieCheckbox);
                } catch (NumberFormatException ex) {
                    continue;
                }
                
                Allergie allergie = this.allergieBean.findById(id);
                allergien.add(allergie);
            }
        }
        
        
        // Angekreuzte Grundzutaten ermitteln
          if (grundzutatCheckboxen != null) {
            for (String grundzutatCheckbox : grundzutatCheckboxen) {
                long id;
                
                try {
                    id = Long.parseLong(grundzutatCheckbox);
                } catch (NumberFormatException ex) {
                    continue;
                }
                
                Grundzutat grundzutat = this.grundzutatBean.findById(id);
                grundzutaten.add(grundzutat);
            }
        }
        
        
        // Suche ausführen
        List<Rezept> rezepte = this.rezeptBean.searchByFilters(anlaesse, grundzutaten, allergien);
        
        // Suchergebnis anzeigen
        request.setAttribute("rezepte", rezepte);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/startseite.jsp");
        //war vorher suchseite.jsp
    }
}