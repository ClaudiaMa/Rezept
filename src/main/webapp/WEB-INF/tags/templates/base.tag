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

                    <form method="GET" class="horizontal" id="search">
                        <input type="text" name="search_text" value="${param.search_text}" placeholder="Rezept"/>       
                        <button class="icon-search" type="submit">
                            Suchen
                        </button>
                    </form>
                    </br>
                    
                    <h4>Anlass</h4>
                    <form action="filter">
                        <fieldset>
                            <ul>
                                <li>
                                    <label>
                                        <input type="checkbox" name="anlass" value="frühstück" id="check1">Frühstück</label>
                                </li>
                                <li>
                                    <label>
                                        <input type="checkbox" name="anlass" value="brunch" id="check2">Brunch</label>
                                </li>
                                <li>
                                    <label for="check3">
                                        <input type="checkbox" name="anlass" value="mittagesseb" id="check3">Mittagessen</label>
                                </li>
                                <li>
                                    <label for="check4">
                                        <input type="checkbox" name="anlass" value="abendessen" id="check4">Abendessen</label>
                                </li>
                            </ul>
                        </fieldset>
                    </form>

                    <h4>Zutaten</h4>
                    <form action="filter">
                        <fieldset>
                            <ul>
                                <li>
                                    <label>
                                        <input type="checkbox" name="zutat" value="eier" id="check1">Eier</label>
                                </li>
                                <li>
                                    <label>
                                        <input type="checkbox" name="zutat" value="nudeln" id="check2">Nudeln</label>
                                </li>
                                <li>
                                    <label for="check3">
                                        <input type="checkbox" name="zutat" value="kartoffeln" id="check3">Kartoffeln</label>
                                </li>
                                <li>
                                    <label for="check4">
                                        <input type="checkbox" name="zutat" value="tomaten" id="check4">Tomaten</label>
                                </li>
                            </ul>
                        </fieldset>
                    </form>


                    <h4>Allergien</h4>
                    <form action="filter">
                        <fieldset>
                            <ul>
                                <li>
                                    <label>
                                        <input type="checkbox" name="allergie" value="weizen" id="check1">Weizen</label>
                                </li>
                                <li>
                                    <label>
                                        <input type="checkbox" name="allergie" value="gluten" id="check2">Gluten</label>
                                </li>
                                <li>
                                    <label for="check4">
                                        <input type="checkbox" name="allergie" value="laktose" id="check4">Laktose</label>
                                </li>

                            </ul>
                        </fieldset>
                        <br>
                        <button type="button" id="filtern">Zeige Ergebnisse</button>
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
