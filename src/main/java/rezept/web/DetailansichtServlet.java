/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import rezept.ejb.RezeptBean;
import rezept.jpa.Rezept;

/**
 *
 * @author Alina
 */
@WebServlet(urlPatterns = "/detailansicht/*")
public class DetailansichtServlet extends HttpServlet {
    
    @EJB
    RezeptBean rezeptBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        
        //Buttons abfragen, falls Benutzer nach Detailansicht neu filtern möchte
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");
        
        //Wenn kein Button gedrückt wurde, sondern über die Überschrift die Seite aufgerufen wird
        if (action == null) {
            action = "";
            
            // Zu lesendes Rezept einlesen
            //HttpSession session = request.getSession();

            Rezept rezept = this.getRequestedRezept(request);
            List<Rezept> rezepte = new ArrayList<Rezept>();
            rezepte.add(rezept);
            request.setAttribute("rezepte", rezepte);
        
        
            // Anfrage an eine JSP weiterleiten, um damit den HTML-Code
            // der Seite zu generieren
             RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detailansicht.jsp");
             dispatcher.forward(request, response);
    
        }
        
        
        //Falls ein Button nach Erscheinen der Detailansicht gedrückt wurde, an das Startseiteservlet weiterleiten
        else {
            
            response.sendRedirect(request.getContextPath() + "/index.html"); 
        }
        
       
       
         
         
        
        
    }
    
    
     private Rezept getRequestedRezept(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Rezept rezept = new Rezept();
      
        // ID aus der URL herausschneiden
        String rezeptId = request.getPathInfo();

        if (rezeptId == null) {
            rezeptId = "";
        }

        rezeptId = rezeptId.substring(1);

        if (rezeptId.endsWith("/")) {
            rezeptId = rezeptId.substring(0, rezeptId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            rezept = this.rezeptBean.findById(Long.parseLong(rezeptId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return rezept;
    }
    
}
