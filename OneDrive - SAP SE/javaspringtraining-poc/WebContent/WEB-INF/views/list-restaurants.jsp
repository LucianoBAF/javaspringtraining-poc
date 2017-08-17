<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 26/07/2017
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>


<%-- <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> --%>

<html lang="pt_BR">
    <%@include file="/resources/tags/header.jsp"%>
<head>
    <%--Scripts Area--%>
        <script type="text/javascript">

            function changeMap(restaurantName, restaurantLocation){
                document.getElementById('mapFrame').src = "https://www.google.com/maps/embed/v1/directions?key=" + "AIzaSyC4dIDnErJSgIj7lzYHKzDP8tjuExTlJK4" +
                    "&origin=SAP+Sao+Leopoldo"+"&destination="+ restaurantName.replace(' ', '+') + "+" + restaurantLocation.replace(' ', '+');
            }

            function searchRestaurantName(){
                var cards =  $("#cardsContainer").children("div");

                var restaurantName = $("#restaurantSearch").val();
                if(restaurantName === null || restaurantName === ""){
                    for(var i = 0; i < cards.length; i++){
                        $(cards[i]).show();
                    }
                    return;
                }

                $.post(
                    "/restaurants/searchRestaurantName",
                    "nameToSearch="+restaurantName,
                    function (response) {
                                for(var i = 0; i < cards.length; i++){
                                    if(response.length === 0){
                                        $(cards[i]).hide();
                                    }
                                    else {
                                        response.every(function (restaurant) {
                                            var cardId = "restaurant".concat(restaurant.id);
                                            if (cards[i].id === cardId) {
                                                $(cards[i]).show();
                                                return false;
                                            }
                                            else {
                                                $(cards[i]).hide();
                                                return true;
                                            }
                                        });
                                    }
                                }
                    }
                )
                .fail(function() {
                    alert( "error" );
                });
            }

        </script>
</head>

<body>
    <div class="col-lg-1"></div>
    <div class="col-lg-10 container-fluid">
        <h1 id="headerText" class="header">List of CD's favorite restaurants</h1>
        <br><br>

        <div class="row">
                <iframe id="mapFrame" class="center-block"
                        width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
                        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyC4dIDnErJSgIj7lzYHKzDP8tjuExTlJK4&q=SAP+Sao+Leopoldo";>
                </iframe>
        </div>
        <br>
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
            <div class="col-lg-1">Search restaurant:</div>
            <div class="col-lg-10"><input id="restaurantSearch" type="text" class="animatedInput" onkeyup="searchRestaurantName()"></div>
        </div>
        <div class="col-lg-1"></div>

        <br>

        <div id="cardsContainer">
        <c:forEach var="restaurant" items="${restaurants}">
                <div id="restaurant${restaurant.id}" class="card col-md-4 text-center card-full-height container-fluid">
                    <img class="card-image-top center-block container-fluid" width="300px" height="200px" alt="Restaurant" src=${restaurant.image} >
                    <div class="card-block">
                        <h3 class="card-title">${restaurant.name}</h3>
                        <p class="card-text">Location: ${restaurant.location}</p>
                        <p class="card-text">Price: ${restaurant.averagePrice}</p>
                        <p>
                            <c:choose>
                                <c:when test="${restaurant.aleloAccepted}">
                                    <img class="button" height="40px" width="40px" alt="Alelo accepted"
                                         src="/resources/images/alelo_active_icon.png"/>
                                </c:when>
                                <c:otherwise>
                                    <img class="button" height="40px" width="40px" alt="Alelo not accepted"
                                         src="/resources/images/alelo_inactive_icon.png"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p id="numberOfVotes">Number of votes: ${restaurant.numberOfVotes}</p>
                        <p class="voters">Voters:
                            <c:forEach items="${restaurant.voters}" var="voter">
                                <c:out value="${voter.name}, " />
                            </c:forEach>
                        </p>
                        <div class="card-footer">
                        <a href="/restaurants/update-restaurant?restaurantId=${restaurant.id}">
                            <img class="button" height="30px" width="30px" alt="Update the restaurant"
                                 src="/resources/images/update_icon.png"/>
                        </a>

                        <a href="/restaurants/delete-restaurant?restaurantId=${restaurant.id}">
                            <img class="button" height="30px" width="30px" alt="Delete the restaurant"
                                 src="/resources/images/delete_icon.png"/>
                        </a>

                        <img src="/resources/images/map_icon.png" height="30px" width="30px"
                                 alt="Show restaurant directions"
                                 onmousedown="changeMap('${restaurant.name}','${restaurant.location}')"/>


                            <c:set var="restaurantId" value='${restaurant.id}'/>
                            <c:set var="userVote" value='${restaurantUserVotedToday}'/>

                            <c:choose>
                                <c:when test="${userVote == '0'}">
                                    <p><a href="/restaurants/vote?restaurantId=${restaurant.id}"
                                          class="btn btn-primary" role="button">
                                          Vote</a>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${restaurantId == userVote}">
                                            <p><a href="/restaurants/vote?restaurantId=${restaurant.id}"
                                                  class="btn btn-primary" role="button">
                                                  Remove vote</a>
                                            </p>
                                        </c:when>
                                        <c:otherwise>
                                            <br/><br/><br/>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
        </c:forEach>
        </div>
    </div>
    <div class="col-lg-1"></div>




</body>
</html>
