/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package rezept.web;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 * Statische Hilfsmethoden
 */
public class WebUtils {
   
    /**
     * Stellt sicher, dass einer URL der Kontextpfad der Anwendung vorangestellt
     * ist. Denn sonst ruft man aus Versehen Seiten auf, die nicht zur eigenen
     * Webanwendung gehören.
     * 
     * @param request HttpRequest-Objekt
     * @param url Die aufzurufende URL
     * @return  Die vollständige URL
     */
    public static String appUrl(HttpServletRequest request, String url) {
        return request.getContextPath() + url;
    }
   

}
