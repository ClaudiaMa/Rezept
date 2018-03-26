package rezept.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import rezept.ejb.AllergieBean;
import rezept.ejb.AnlassBean;
import rezept.ejb.GrundzutatBean;
import rezept.ejb.RezeptBean;
import rezept.jpa.Allergie;
import rezept.jpa.Anlass;
import rezept.jpa.Grundzutat;
import rezept.jpa.Rezept;


@WebServlet(urlPatterns = {"/index.html"})
public class StartServlet extends HttpServlet {
    
 
    
    @EJB
    private RezeptBean rezeptBean;
    
   
    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;
    
    AnlassBean anlassBean;
    AllergieBean allergieBean;
    GrundzutatBean grundzutatBean;
    
    
    //<editor-fold defaultstate="collapsed" desc="init-Methode um Filter in die DB zu schreiben, falls noch nicht vorhanden">
    @Override
    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
       
        //Anlass Tabelle befüllen/ prüfen
        List<Anlass> anlässe = em.createQuery("SELECT a FROM Anlass a ").getResultList();
        
        if (anlässe.isEmpty()) {
            try {
                utx.begin();
                Anlass anlass;
                anlass = new Anlass("Frühstück");
                em.persist(anlass);
                anlass = new Anlass("Brunch");
                em.persist(anlass);
                anlass = new Anlass("Mittagessen");
                em.persist(anlass);
                anlass = new Anlass("Abendessen");
                em.persist(anlass);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (IllegalStateException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        //Zutaten Tabelle befüllen/ prüfen
        List<Grundzutat> zutaten = em.createQuery("SELECT z FROM Grundzutat z ").getResultList();
        
        if (zutaten.isEmpty()) {
            try {
                utx.begin();
                Grundzutat zutat;
                zutat = new Grundzutat("Kartoffeln");
                em.persist(zutat);
                zutat = new Grundzutat("Tomaten");
                em.persist(zutat);
                zutat = new Grundzutat("Nudeln");
                em.persist(zutat);
                zutat = new Grundzutat("Eier");
                em.persist(zutat);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (IllegalStateException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
        
        //Allergien Tabelle befüllen/ prüfen
        List<Allergie> allergien = em.createQuery("SELECT a FROM Allergie a ").getResultList();
        
        if (allergien.isEmpty()) {
            try {
                utx.begin();
                Allergie allergie;
                allergie = new Allergie("Weizen");
                em.persist(allergie);
                allergie = new Allergie("Laktose");
                em.persist(allergie);
                allergie = new Allergie("Gluten");
                em.persist(allergie);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (IllegalStateException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
        
        
        //Rezept Tabelle prüfen 
         List<Rezept> rezepte = em.createQuery("SELECT r FROM Rezept r ").getResultList();
        
        if(rezepte.isEmpty()){
             try {
                utx.begin();
                Rezept rezept;
                rezept = new Rezept("Lasagne", "Man nehme...", "mittel", 30,  "Tolles Bild");
                Anlass abendessen = this.anlassBean.findByName("Abendessen");
                rezept.getAnlässe().add(abendessen);
                Grundzutat eier = this.grundzutatBean.findByName("Eier");
                rezept.getGrundzutaten().add(eier);
                Grundzutat nudeln = this.grundzutatBean.findByName("Nudeln");
                rezept.getGrundzutaten().add(nudeln);
                em.persist(rezept);
                utx.commit();
            } catch (Exception e) {
                try {
                    utx.rollback();
                } catch (IllegalStateException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }
    //</editor-fold>
    
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        
       
        
       
        //Zuerst Fall-Unterscheidung ob der "Rezepte filtern" oder "Rezepte suchen"-Button gedrückt wurde oder keines von beidem
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            action="";
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/startseite.jsp");
            dispatcher.forward(request, response);
        
            
        }
        
        
        //Button "Rezepte suchen" wurde gedrückt
        if (action.equals("suchen")) {
            
            //<editor-fold defaultstate="collapsed" desc="Inhalt wenn Button "suchen" gedrückt wurde">
            //Suchparameter aus der URL lesen und gesetzte Filter identifizieren!
            String searchText = request.getParameter("search_text");
            
            List<Rezept> rezepte = this.rezeptBean.search(searchText);
            request.setAttribute("rezept", rezepte);
            
            //muss noch angeglichen werden in der JSP
            
            //An die JSP weiterleiten:
            request.getRequestDispatcher("/WEB-INF/app/startseite.jsp").forward(request, response);
            
            
        }
//</editor-fold>
        
        
        //Button "Rezepte filtern" wurde gedrückt
        if (action.equals("filtern")) {
            //<editor-fold defaultstate="collapsed" desc="Inhalt wenn Button "filtern" gedrückt wurd">
            //Boolean-Werte für einzelne Filterelemente erstellen
            
            
            // auf anderen Servlet verweisen
            response.sendRedirect("/Rezept/suche");
            /*
            //Anlass:
            Anlass frühstück = null;
            Anlass brunch = null;
            Anlass mittagessen = null;
            Anlass abendessen = null;
            
            //Zutaten:
            Grundzutat eier = null;
            Grundzutat nudeln = null;
            Grundzutat kartoffeln = null;
            Grundzutat tomaten = null;
            
            //Allergien:
            Allergie weizen = null;
            Allergie gluten = null;
            Allergie laktose = null;
            
            
            //Anlass, Zuzat, Allergie Objekte erstellen, sind vorerst noch leer
            // Dann die Listen holen und durchgehen und schauen ob Element != null ist 
            // wenn Element != null ist wird das Element.name geprüft und mit Strings wie z.B. Frühstück verglichen
            //wenn Element dann z.B. Frühstück ist wird dem Frühstück Anlass Objekt, dass wir oben erstellt haben, die Werte dieses 
            // Checkbox-Element gegeben
            //Die Parameter werden dann der suche-Methode übergeben...
            
            //Anlässe prüfen
            List anlässe = (List) request.getSession().getAttribute("filterAnlässe");
            
            Iterator<Anlass> iter = anlässe.iterator();
                  if (iter.hasNext()) {
                    Anlass anlass = iter.next();
                        if(anlass != null) {
                            if (anlass.getName() == "Frühstück") {
                                frühstück = anlass;
                            }
                            
                             if (anlass.getName() =="Brunch") {
                                brunch = anlass;
                            }
                             
                             if (anlass.getName() =="Frühstück") {
                                mittagessen = anlass;
                            }
                             
                              if (anlass.getName() =="Frühstück") {
                                abendessen = anlass;
                            }
                            
                        }
                        
                 }
                  
                  
                
                  
            //Zutaten prüfen
            List zutaten = (List) request.getSession().getAttribute("filterZutaten");
            
            Iterator<Grundzutat> iter1 = zutaten.iterator();
                  if (iter1.hasNext()) {
                    Grundzutat zutat = iter1.next();
                        if(zutat != null) {
                            if (zutat.getName() == "Tomaten") {
                                tomaten = zutat;
                            }
                            
                             if (zutat.getName() =="Kartoffeln") {
                                kartoffeln = zutat;
                            }
                             
                             if (zutat.getName() =="Eier") {
                                eier = zutat;
                            }
                             
                              if (zutat.getName() =="Nudeln") {
                                nudeln = zutat;
                            }
                            
                        }
                        
                 }

                 //Anlässe prüfen
            List allergien = (List) request.getSession().getAttribute("filterAllergien");
            
            Iterator<Allergie> iter2 = allergien.iterator();
                  if (iter2.hasNext()) {
                    Allergie allergie = iter2.next();
                        if(allergie != null) {
                            if (allergie.getName() == "Weizen") {
                                weizen = allergie;
                            }
                            
                             if (allergie.getName() =="Gluten") {
                                gluten = allergie;
                            }
                             
                             if (allergie.getName() =="Laktose") {
                                laktose = allergie;
                            }
                             
                            
                        }
                        
                 }
                  
                  
            List<Rezept> rezepte = this.rezeptBean.searchByFilters(frühstück, brunch, mittagessen, abendessen, eier, nudeln, kartoffeln, tomaten, weizen, gluten, laktose);
            request.setAttribute("rezepte", rezepte);
            */
            // Anfrage an die JSP weiterleiten
            //request.getRequestDispatcher("/WEB-INF/app/startseite.jsp").forward(request, response);
            
            
        }
//</editor-fold>
        
        
  
   
        }

 
        
    }
    

   