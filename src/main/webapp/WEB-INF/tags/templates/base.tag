<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@attribute name="content" fragment="true"%>


<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8" />
        <title>Topfschlag</title>

        <!-- Bootstrap-->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">


        <link rel="stylesheet" href="css/style.css"/>


    </head>
    <body>
        <div class="row">

            <div class="col-3 left">
                <div class="container" id="container2" > 
                    <h3 id="Titel">Topfschlag</h3
                    
                   <form method="GET" class="horizontal" id="search" > 
                    </form> 
                   
                    
                   <form method="GET" class="horizontal" id="search" > 
                        <input type="text" name="search_text" value="${param.search_text}" placeholder="Rezeptbezeichnung"/>       
                        <button type="submit" name="action" value="suchen">Rezepte suchen</button>
                    </form> 
                    

                     <form method="POST" action="suche">
                     <h4>Anlass</h4>
                     <c:forEach items="${filterAnlässe}" var="anlass">
                         <ul>
                             <li>
                                 <label>
                                     <input type="checkbox" name="anlass"> ${anlass.name} </label>  
                             </li>
                         </ul>
                    </c:forEach>

                    <h4>Zutaten</h4>
                     <c:forEach items="${filterZutaten}" var="zutat">
                         <ul>
                             <li>
                                 <label>
                                     <input type="checkbox" name="zutat"> ${zutat.name} </label>    
                             </li>
                         </ul>
                    </c:forEach>
                    
                   
                    <h4>Allergien</h4>
                     <c:forEach items="${filterAllergien}" var="allergie">
                         <ul>
                             <li>
                                 <label>
                                     <input type="checkbox" name="allergie"> ${allergie.name} </label>    
                             </li>
                         </ul>
                    </c:forEach>
                     </form>
                   
                        
                        <form method="GET" class="horizontal" id="search" > 
                        <button type="submit" name="action" value="filtern">Rezepte filtern</button>
                        </form>
                        
                    
                </div>
            </div>

            <div class="col-9">
                <div class="container" id="conatiner3">                         
                    <main>   
                        <jsp:invoke fragment="content"/>
                    </main>
                </div> 
            </div>

        </div>
    </body>
</html>
