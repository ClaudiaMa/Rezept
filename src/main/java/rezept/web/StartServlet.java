package rezept.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/index.html"})
public class StartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
      
        // Anfrage an eine JSP weiterleiten, um damit den HTML-Code
        // der Seite zu generieren
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/startseite.jsp");
         dispatcher.forward(request, response);
        
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        
        // Und die Seite nochmal laden lassen
        //response.sendRedirect(request.getRequestURI());
        
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "filtern":
                response.sendRedirect(request.getContextPath() + "/uebersicht");
                break;
            case "suche":
                response.sendRedirect(request.getContextPath() + "/übersicht.html");
                break;
                
              
        
        
    }
    }
}