<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 26/07/2017
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Where to lunch, CD? </title>
    </head>
    <body>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <table  border="1" id="restaurantTable">
            <tr>
                <th>Name</th>
                <th>Average price</th>
                <th>Location</th>
                <th>Alelo acceptance</th>
                <th>Image</th>
            </tr>
            <c:forEach var="restaurant" items="${restaurants}">
                <tr class="restaurantRow" id="${restaurant.id}">
                    <td>${restaurant.name}</td>
                    <td>${restaurant.averagePrice}</td>
                    <td>${restaurant.location}</td>
                    <td>${restaurant.aleloAccepted}</td>
                    <td>${restaurant.image}</td>

                    <td><a class="button"  href="/update-restaurant?restaurantName=${restaurant.id}" >Update</a>
                    <td><a class="button"  href="/delete-restaurant?restaurantName=${restaurant.id}" >Delete</a>

                </td>
            </tr>
        </c:forEach>
    </table>

    <input type="button" value="Add" onclick="location.href='add-restaurant'" >
                        <iframe id="restaurantMap"+${restaurant.id} name="map_canvas" width="500%" height="500px"
                                frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
                                src=""
                                align="right">
                        </iframe>
                        <script>displayMapAt(-29.796455, -51.148592,15)</script>




    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>

        function displayMapAt(lat, lon, zoom) {
            $("#restaurantMap")
                .html(
                    "<iframe id=\"map_frame\" "
                    + "width=\"100%\" height=\"600px\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" "
                    + "src=\"https://www.google.sk/maps?f=q&amp;output=embed&amp;source=s_q&amp;hl=sk&amp;geocode=&amp;q=https:%2F%2Fwww.google.sk%2Fmaps%2Fms%3Fauthuser%3D0%26vps%3D5%26hl%3Dsk%26ie%3DUTF8%26oe%3DUTF8%26msa%3D0%26output%3Dkml%26msid%3D205427380680792264646.0004fe643d107ef29299a&amp;aq=&amp;sll=48.669026,19.699024&amp;sspn=4.418559,10.821533&amp;ie=UTF8&amp;ll="
                    + lat + "," + lon
                    + "&amp;spn=0.199154,0.399727&amp;t=m&amp;z="
                    + zoom + "\"" + "></iframe>");
        }



    </script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
