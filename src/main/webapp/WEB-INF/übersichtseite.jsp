<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<template:base>
    
    <jsp:attribute name="content">
        <body>
            
            <!-- Zeile mit drei gleichgroßen Spalten -->
            <div class="row">
                <div class="col-md-4">
                    <h2>Rezept 1</h2>
                    <img src="Bild.jpg" id="Repezeptbild"/>
                    <p name="rezept_dauer">Dauer:${rezept_form.values["rezept_dauer"][0]}</p>
                    <p name="rezept_aufwand">Aufwand:${rezept_form.values["rezept_aufwand"][0]}</p>
                </div>
                <div class="col-md-4">
                    <h2>Rezept 2</h2>
                    <img src="Bild.jpg" id="Repezeptbild"/>
                    <p name="rezept_dauer">Dauer:${rezept_form.values["rezept_dauer"][0]}</p>
                    <p name="rezept_aufwand">Aufwand:${rezept_form.values["rezept_aufwand"][0]}</p>
                </div>
                <div class="col-md-4">
                    <h2>Rezept 3</h2>
                    <img src="Bild.jpg" id="Repezeptbild"/>
                    <p name="rezept_dauer">Dauer:${rezept_form.values["rezept_dauer"][0]}</p>
                    <p name="rezept_aufwand">Aufwand:${rezept_form.values["rezept_aufwand"][0]}</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <h2>Rezept 4</h2>
                    <img src="Bild.jpg" id="Repezeptbild"/>
                    <p name="rezept_dauer">Dauer:${rezept_form.values["rezept_dauer"][0]}</p>
                    <p name="rezept_aufwand">Aufwand:${rezept_form.values["rezept_aufwand"][0]}</p>
                </div>
                <div class="col-md-4">
                    <h2>Rezept 5</h2>
                    <img src="Bild.jpg" id="Repezeptbild"/>
                    <p name="rezept_dauer">Dauer:${rezept_form.values["rezept_dauer"][0]}</p>
                    <p name="rezept_aufwand">Aufwand:${rezept_form.values["rezept_aufwand"][0]}</p>
                </div>
                <div class="col-md-4">
                    <h2>Rezept 6</h2>
                    <img src="Bild.jpg" id="Repezeptbild"/>
                    <p name="rezept_dauer">Dauer:${rezept_form.values["rezept_dauer"][0]}</p>
                    <p name="rezept_aufwand">Aufwand:${rezept_form.values["rezept_aufwand"][0]}</p>
                </div>
            </div>
            </div>
        </body>
        
    </jsp:attribute>

</template:base>