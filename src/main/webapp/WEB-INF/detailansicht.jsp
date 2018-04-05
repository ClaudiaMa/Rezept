<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<template:base>

    <jsp:attribute name="content">
        <body>

            <div class="row">
                <c:forEach items="${rezepte}" var="rezept">
                    <div class="col-md-12">
                        <tr>

                        <h4>

                            <c:out value="${rezept.rezeptname}"/>

                        </h4>
                        
                                                 
                        <td>
                            <h5>
                                <p name="rezept_dauer">Dauer:</h5>
                                <c:out value="${rezept.dauer}"/> Minuten
                            </p>
                        </td>
                        <td>
                            <h5>
                                <p name="rezept_aufwand">Aufwand:</h5>
                                <c:out value="${rezept.aufwand}"/>
                            </p>
                        </td>
                        <td>
                            <h5>
                                <p name="rezept_zutaten">Zutaten:</h5>
                                <c:out value="${rezept.zutaten}"/>
                            </p>
                        </td>
                        <td>
                            <h5>
                                <p name="rezept_rezeptbeschreibung">Rezeptbeschreibung:</h5>
                                <c:out value="${rezept.rezeptbeschreibung}"/>
                            </p>
                        </td>



                        </tr>
                    </div>
                </c:forEach>
            </div>

        </body>
    </jsp:attribute>

</template:base>