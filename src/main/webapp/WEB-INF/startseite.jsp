<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<template:base>
    
    <jsp:attribute name="content">
        <body>
            <c:choose>
            <c:when test="${empty rezepte}">
                <p>
                    Es wurden noch keine Rezepte gefunden. 
                </p>
            </c:when>
            <c:otherwise>
             
                <div class="row">
                    <c:forEach items="${rezepte}" var="rezept">
                        <div class="col-md-6">
                        <tr>
                            
                            <h4>
                                <a href="<c:url value="/detailansicht/${rezept.id}/"/>">
                                    <c:out value="${rezept.rezeptname}"/>
                                </a>
                            </h4>
                        
                        <img id="Rezeptbild" src="Bild.jpg"/>
                        
                            <td>
                            <p name="rezept_dauer">Dauer:
                                <c:out value="${rezept.dauer}"/> 
                            </p>
                            </td>
                            <td>
                            <p name="rezept_aufwand">Aufwand:
                                <c:out value="${rezept.aufwand}"/>
                            </p>
                            </td>
                            
                        </tr>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
        </body>

    </jsp:attribute>

</template:base>