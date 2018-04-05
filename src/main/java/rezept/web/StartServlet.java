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

    @EJB
    AnlassBean anlassBean;

    @EJB
    AllergieBean allergieBean;

    @EJB
    GrundzutatBean grundzutatBean;

    //<editor-fold defaultstate="collapsed" desc="init-Methode um Filter und Rezepte in die DB zu schreiben, falls noch nicht vorhanden">
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        //<editor-fold defaultstate="collapsed" desc="Filter in DB speichern">
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
//</editor-fold>
        

        //Rezept Tabelle prüfen 
        List<Rezept> rezepte = em.createQuery("SELECT r FROM Rezept r ").getResultList();

        if (rezepte.isEmpty()) {

            try {
                utx.begin();
                Rezept rezept;
                
                //Lasagne
               
                rezept = new Rezept("Lasagne", "100g Lasagneplatten, 5 Tomaten, 500g Hackfleisch, 1 Packung passierte Tomaten, 2 Paprikas, 2 Zwiebeln",
                        "In einer gebutterten, feuerfesten Form etwas Ragout Bolognese verteilen, eine Schicht Lasagnenudeln darauf legen, die Nudelschicht wieder mit Ragu und dann mit einer Bechamelsaucenschicht bedecken.Die letzte Schicht sollte die Bechamelsauce bilden. Dick mit geriebenem Käse bestreuen, Butterflöckchen darauf setzen. Die Lasagne bei 180°C im Ofen ca. 30 - 40 Minuten überbacken, bis die Kruste goldbrau",
                        "mittel", 30, "Tolles Bild");
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
                rezept = new Rezept("Spätzle","5 Eier, 500g Dinkelmehl, 100 ml Wasser", "Mehl, Eier und 100 ml Wasser in einer Schüssel mit 1 Prise Salz vermengen und mit der Hand oder einem Kochlöffel so lange schlagen, bis der Teig Blasen wirft. Den Teig durch eine Spätzlepresse portionsweise in kochendes Salzwasser drücken.", "gering", 45, "Tolles Bild");
                Anlass abendessen1 = this.anlassBean.findByName("Abendessen");
                rezept.getAnlässe().add(abendessen1);
                abendessen1.getRezepten().add(rezept);
                Grundzutat eier1 = this.grundzutatBean.findByName("Eier");
                rezept.getGrundzutaten().add(eier1);
                eier1.getRezepten().add(rezept);
                Grundzutat kartoffeln = this.grundzutatBean.findByName("Kartoffeln");
                rezept.getGrundzutaten().add(kartoffeln);
                Allergie weizen = this.allergieBean.findByName("Weizen");
                rezept.getAllergien().add(weizen);
                weizen.getRezepten().add(rezept);
                
                
                //Tortilla
                rezept = new Rezept("Tortilla","2 Packungen Tortilla Chips, 1 Avocado, 3 Tomaten, 200g Mais, 1 Paprika, 200g Hackfleisch", 
                        "Kartoffeln schälen, waschen, in dünne Scheiben schneiden. Zwiebeln pellen, sehr fein würfeln. Eier mit Petersilie, Salz, Pfeffer und Milch in einer Schüssel kräftig verquirlen, über die Kartoffeln gießen. Mit einem Holzspatel gut mischen. Im heißen Ofen bei 180 Grad auf der 2. Schiene von unten 15-20 Min.",
                        "hoch", 20, "Tolles Bild");
                Anlass abendessen2 = this.anlassBean.findByName("Abendessen");
                rezept.getAnlässe().add(abendessen2);
                abendessen2.getRezepten().add(rezept);
                Grundzutat tomate = this.grundzutatBean.findByName("Tomaten");
                rezept.getGrundzutaten().add(tomate);
                tomate.getRezepten().add(rezept);
                Grundzutat eier2 = this.grundzutatBean.findByName("Eier");
                rezept.getGrundzutaten().add(eier2);
                Allergie gluten = this.allergieBean.findByName("Gluten");
                rezept.getAllergien().add(gluten);
                gluten.getRezepten().add(rezept);
                
                //Spaghetti Bolognese
                rezept = new Rezept ("Spaghetti Bolognese", "500g Hackfleisch, 6 Tomaten, 2 Zwiebeln, 1 Knolle Knoblauch, 50ml Olivenöl, 1 TubeTomatenmark, 500g Spaghetti",
                                     "In einem großen Topf den Speck und den Rosmarin in Olivenöl goldgelb anbraten. Zwiebeln und Knoblauch zugeben und 3 Minuten unter Rühren anschmoren. Dann das Hackfleisch zugeben und anbraten. Danach den Wein zugeben und die Flüssigkeit etwas reduzieren lassen. Dann den Oregano, die Möhren und alle Tomaten und n.B. etwas Tomatenmark zugeben. Mit den Gewürzen, außer dem Basilikum, gut abschmecken, nochmals aufkochen, die Hitze fast ganz runter nehmen, Deckel drauf und 1,5 bis 2 Stunden leise köcheln lassen.",
                                    "gering", 30, "Kein Bild");
                Anlass mittagessen = this.anlassBean.findByName("Mittagessen");
                rezept.getAnlässe().add(mittagessen);
                mittagessen.getRezepten().add(rezept);
                Grundzutat tomate1 = this.grundzutatBean.findByName("Tomaten");
                rezept.getGrundzutaten().add(tomate1);
                tomate1.getRezepten().add(rezept);
                Grundzutat nudeln2 = this.grundzutatBean.findByName("Nudeln");
                rezept.getGrundzutaten().add(nudeln2);
                nudeln2.getRezepten().add(rezept);
                Allergie laktose1 = this.allergieBean.findByName("Laktose");
                rezept.getAllergien().add(laktose1);
                laktose1.getRezepten().add(rezept);
                
                
                
                
                
                
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

        //Die Startseite laden lassen falls der Button "action" nicht gedrückt wurde & leer ist
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            action = "";
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/startseite.jsp");
            dispatcher.forward(request, response);

        }

        

    }

}
