<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 26/07/2017
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Where to lunch, CD? </title>

    <%--Scripts Area--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript">

        function changeMap(restaurantName, restaurantLocation){
            document.getElementById('mapFrame').src = "https://www.google.com/maps/embed/v1/directions?key=" + "AIzaSyC4dIDnErJSgIj7lzYHKzDP8tjuExTlJK4" +
                "&origin=SAP+Sao+Leopoldo"+"&destination="+ restaurantName.replace(' ', '+') + "+" + restaurantLocation.replace(' ', '+');
        }

    </script>
    <%--Scripts Area--%>

</head>

<body>


    <h2 id="headerText" class="headers-region" align="center" style="font-family: 'Yu Gothic Medium'">List of CD's favorite restaurants</h2>

    <a href="<c:url value="/logoutSpring" />">Logout</a>


    <div class="container" style="alignment: center; align-items: center">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <table  border="0" id="restaurantTable" style="vertical-align: top;font-family: 'Yu Gothic';  align-items: center; position: relative;">
            <tr>
                <th>Name</th>
                <th>Average price</th>
                <th>Location</th>
                <th>Alelo acceptance</th>
                <th>Image</th>
                <th>
                    <a href="/restaurants/add-restaurant" style="align-self: center;background-color: forestgreen;">
                        <img class="button" height="30px" width="30px" alt="Add a new restaurant"
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
                    <td align="center"> <c:choose>
                            <c:when test="${restaurant.aleloAccepted}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${restaurant.image}</td>

                    <td><a href="/restaurants/update-restaurant?restaurantId=${restaurant.id}">
                            <img class="button" height="30px" width="30px" alt="Update the restaurant"
                                src="/resources/images/update_icon.png"
                            />
                        </a>
                    <td><a href="/restaurants/delete-restaurant?restaurantId=${restaurant.id}">
                        <img class="button" height="30px" width="30px" alt="Delete the restaurant"
                             src="/resources/images/delete_icon.png"
                        />
                    <td><img src="/resources/images/map_icon.png" height="30px" width="30px" alt="Show restaurant directions"
                             onmousedown="changeMap('${restaurant.name}','${restaurant.location}')"/>

                    </td>
                </tr>
            </c:forEach>
        </table>


        <table  border="1" id="mapTable" style="font-family: 'Yu Gothic';align-self: center;position: relative;background-position: center;">
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
