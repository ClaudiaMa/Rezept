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
import javax.transaction.NotSupportedException;
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

    @EJB
    AnlassBean anlassBean;

    @EJB
    AllergieBean allergieBean;

    @EJB
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
                Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, e);
                
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
                Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, e);
                
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
                Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, e);
                
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

        if (rezepte.isEmpty()) {

            try {
                utx.begin();
                Rezept rezept;
                
                //Lasagne
                rezept = new Rezept("Lasagne", "Man nehme...", "mittel", 30, "Tolles Bild");
                Anlass abendessen = this.anlassBean.findByName("Abendessen");
                rezept.getAnlässe().add(abendessen);
                abendessen.getRezepten().add(rezept);
                Grundzutat eier = this.grundzutatBean.findByName("Eier");
                rezept.getGrundzutaten().add(eier);
                eier.getRezepten().add(rezept);
                Grundzutat nudeln = this.grundzutatBean.findByName("Nudeln");
                rezept.getGrundzutaten().add(nudeln);
                nudeln.getRezepten().add(rezept);
                
                //Spätzle
                rezept = new Rezept("Spätzle", "Man nehme...", "mittel", 30, "Tolles Bild");
                Anlass abendessen1 = this.anlassBean.findByName("Abendessen");
                rezept.getAnlässe().add(abendessen1);
                abendessen1.getRezepten().add(rezept);
                Grundzutat eier1 = this.grundzutatBean.findByName("Eier");
                rezept.getGrundzutaten().add(eier1);
                eier1.getRezepten().add(rezept);
                Grundzutat kartoffeln = this.grundzutatBean.findByName("Kartoffeln");
                rezept.getGrundzutaten().add(kartoffeln);
                Allergie weizen = this.allergieBean.findByName("Weizen");
                weizen.getRezepten().add(rezept);
                
                
                //Tortilla
                rezept = new Rezept("Tortilla", "Man nehme...", "mittel", 30, "Tolles Bild");
                Anlass abendessen2 = this.anlassBean.findByName("Abendessen");
                rezept.getAnlässe().add(abendessen2);
                abendessen2.getRezepten().add(rezept);
                Grundzutat tomate = this.grundzutatBean.findByName("Tomaten");
                rezept.getGrundzutaten().add(tomate);
                tomate.getRezepten().add(rezept);
                Grundzutat eier2 = this.grundzutatBean.findByName("Eier");
                rezept.getGrundzutaten().add(eier2);
                Allergie gluten = this.allergieBean.findByName("Gluten");
                gluten.getRezepten().add(rezept);
                
                
                
                
                
                
                em.persist(rezept);
                utx.commit();
            } catch (Exception e) {
                Logger.getLogger(StartServlet.class.getName()).log(Level.SEVERE, null, e);
                
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
            action = "";
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
            
            // auf anderen Servlet verweisen
           // response.sendRedirect("/suche");
            /*RequestDispatcher dispatcher = request.getRequestDispatcher("/Rezept/suche");
            dispatcher.forward(request, response);
            */
            // Anfrage an die JSP weiterleiten
            //request.getRequestDispatcher("/WEB-INF/app/startseite.jsp").forward(request, response);

        }
            //</editor-fold>

    }

}
