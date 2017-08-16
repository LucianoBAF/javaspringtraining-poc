<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 11/08/2017
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@include file="/resources/tags/header.jsp"%>

<body>
    <div class="row">
        <h1 id="headerText" class="header">Vote history</h1>
    </div>
    <br>

    <div class="col-lg-3"></div>
    <div class="col-lg-6 container-fluid ">
        <div class="text-center" >
            <br>
            <table  border="0" id="restaurantTable" class="restaurantsTable">
                <tr>
                    <th>Date</th>
                    <th>Restaurant</th>
                    <th>Voters</th>
                </tr>
                <br>
                <br>


                <c:set value="${voteHistory[0].date}" var="lastDate"></c:set>
                <c:forEach var="vote" items="${voteHistory}">
                    <c:choose>
                        <c:when test="${lastDate.equals(vote.date) }">
                            <tr  class="restaurantRow" >
                                <td>${vote.date}</td>
                                <td>${vote.restaurant.name}</td>
                                <td>${vote.user.name}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td><hr></td>
                                <td><hr></td>
                                <td><hr></td>
                            </tr>
                            <tr  class="restaurantWinner" >
                                <td>${vote.date}</td>
                                <td>${vote.restaurant.name}</td>
                                <td>${vote.user.name}</td>
                                <td style="color: gold">Winner!</td>
                            </tr>

                        </c:otherwise>
                    </c:choose>
                    <c:set value="${vote.date}" var="lastDate"></c:set>


                </c:forEach>
            </table>
        </div>
    </div>
    <div class="col-lg-3"></div>
</body>
</html>
