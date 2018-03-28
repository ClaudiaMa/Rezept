<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<template:base>
    
    <jsp:attribute name="content">
        <body>
           
             
                
                    
                    <!-- kann man Schleife auch weglassen??-->
                    
                    <c:forEach items="${rezepte}" var="rezept">
                        <div class="col-md-12">
                        <tr>
                            
                            <h4>
                                <!-- eventuelle a href weglassen und nur c:out --> 
                                <a href="<c:url value="/detailansicht/${rezept.id}/"/>">
                                    <c:out value="${rezept.rezeptname}"/>
                                </a>
                            </h4>
                            <td>
                            <p name="rezept_dauer">Dauer:
                                <c:out value="${rezept.dauer}"/> 
                            </p>
                            </td>
                            <td>
                            <p name="rezept_aufwand">Aufwand:
                                <c:out value="${rezept.aufwand}}"/>
                            </p>
                            </td>
                             <td>
                            <p name="rezept_rezeptbeschreibung">Rezeptbeschreibung:
                                <c:out value="${rezept.rezeptbeschreibung}}"/>
                            </p>
                            </td>
                            
                            
                            
                        </tr>
                        </div>
                    </c:forEach>
                
           
        </body>
    </jsp:attribute>

</template:base>