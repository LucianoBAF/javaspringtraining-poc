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
    <script>
        function changeMap(restaurantName, restaurantLocation){
            document.getElementById('mapFrame').src = "https://www.google.com/maps/embed/v1/directions?key=" + "AIzaSyC4dIDnErJSgIj7lzYHKzDP8tjuExTlJK4" +
                "&origin=SAP+Sao+Leopoldo"+"&destination="+ restaurantName.replace(' ', '+') + "+" + restaurantLocation.replace(' ', '+');
        }

        function voteAndRefreshView(restaurantId, userId) {

            var todayVoteHistory = <c:out value="${'todayVoteHistory'}"/>;
            var todayVote;

            for( var vote in todayVoteHistory){
                if (vote['restaurant']['id'] === restaurantId){
                    todayVote = vote;
                    break;
                }
            }

            if(vote === null || vote === undefined){
                alert("First vote of the day");
                $.ajax({
                    url : '/restaurants/addVote',
                    data: { restaurantId: restaurantId},
                    dataType: 'json',
                    success : function(response) {

                    }
                });
                alert("Vote added");
            }
            else    alert("Not the first vote of the day");

            refreshViewAfterVote(restaurantId);
        }

        function refreshViewAfterVote(restaurantId){

        }
    </script>
</head>

<body>
    <h2 id="headerText" class="header">List of CD's favorite restaurants</h2>
    <hr>



    <br>
    <div class="text-center" >

        <table  border="0" id="restaurantTable" class="restaurantsTable">
            <tr>
                <th>Name</th>
                <th>Average price</th>
                <th>Location</th>
                <th>Alelo acceptance</th>
                <th>Image</th>
                <th>
                    <a href="/restaurants/add-restaurant" class="addRestaurantButton">
                        <img class="button" height="20px" width="30px" alt="Add a new restaurant"
                             src="/resources/images/add_icon.png"
                        />
                    </a>
                </th>
            </tr>
            <c:forEach var="restaurant" items="${restaurants}">
                <tr  class="restaurantRow" id="${restaurant.id}" >
                    <td>${restaurant.name}</td>
                    <td>${restaurant.averagePrice}</td>
                    <td>${restaurant.location}</td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${restaurant.aleloAccepted}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${restaurant.image}</td>

                    <td><a href="/restaurants/update-restaurant?restaurantId=${restaurant.id}">
                            <img class="button" height="30px" width="30px" alt="Update the restaurant"
                                src="/resources/images/update_icon.png"/>
                        </a>

                    <td><a href="/restaurants/delete-restaurant?restaurantId=${restaurant.id}">
                        <img class="button" height="30px" width="30px" alt="Delete the restaurant"
                             src="/resources/images/delete_icon.png"/>

                    <td><img src="/resources/images/map_icon.png" height="30px" width="30px"
                             alt="Show restaurant directions"
                             onmousedown="changeMap('${restaurant.name}','${restaurant.location}')"/>
                    </td>
                    
                    <td><img src="/resources/images/vote_icon.png" height="30px" width="30px"
                             alt="Vote for this restaurant"
                             onmousedown="voteAndRefreshView('${restaurant.id}','${user.id}')"/>
                    </td>
                </tr>
            </c:forEach>
        </table>


        <table  border="1" id="mapTable" class="googleMaps">
        <tr>
            <td>
                <iframe id="mapFrame"
                        width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
                        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyC4dIDnErJSgIj7lzYHKzDP8tjuExTlJK4&q=SAP+Sao+Leopoldo";
                        >
                </iframe>
            </td>
        </tr>
        </table>

    </div>
</body>
</html>
