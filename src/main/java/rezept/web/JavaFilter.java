/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.web;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import rezept.jpa.Allergie;
import rezept.jpa.Anlass;
import rezept.jpa.Grundzutat;

/**
 *
 * Hier werden die Häkchen für die Filter in dem base.tag initialisiert
 * 
 */
public class JavaFilter implements Filter {

    @PersistenceContext
    EntityManager em; 
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
                
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        List<Anlass> anlässe = em.createQuery("SELECT a FROM Anlass a ORDER BY a.id ASC").getResultList();
        List<Grundzutat> zutaten = em.createQuery("SELECT z FROM Grundzutat z ORDER BY z.id ASC").getResultList();
        List<Allergie> allergien = em.createQuery("SELECT a FROM Allergie a ORDER BY a.id ASC").getResultList();
        
        request.setAttribute("filterAnlässe", anlässe);
        request.setAttribute("filterZutaten", zutaten);
        request.setAttribute("filterAllergien", allergien);
        
        chain.doFilter(request, response);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        
    }

    @Override
    public void destroy() {
        
    }
    
    
    
}
