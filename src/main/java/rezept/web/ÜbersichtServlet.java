package rezept.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/uebersicht"})
public class ÜbersichtServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
      
        // Anfrage an eine JSP weiterleiten, um damit den HTML-Code
        // der Seite zu generieren
        
         RequestDispatcher dispatcher;
         dispatcher = request.getRequestDispatcher("/WEB-INF/übersichtseite.jsp");
         dispatcher.forward(request, response);
        

          }
    }

   
 


