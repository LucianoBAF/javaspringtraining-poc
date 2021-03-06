<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 27/07/2017
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html >
<head>
    <%@include file="/resources/tags/header.jsp"%>
    <title>Where to lunch, CD? - Adding a restaurant</title>
</head>
<body onload="isSavingOrUpdating()">

    <h2 id="headerText" class="header">What is your favorite restaurant</h2>
    <hr/>
    <div class="items-center" style="margin-left: 40%" >
    <form:form modelAttribute="restaurant" servletRelativeAction="/restaurants/add-restaurant" method="POST" cssStyle="font-family: 'Yu Gothic';align-items: center; align-self: center">
        <form:hidden path="id"  />
        Name: <form:input path="name" id="restaurantName" />
        <form:errors path="name"/><br/>
        Price: <form:input path="averagePrice" format="0.00"/>
        <form:errors path="averagePrice"/><br/>
        Location: <form:input path="location" type="text"/>
        <form:errors path="location"/><br/>
        Alelo acceptance: <form:checkbox path="aleloAccepted" />
        <form:errors path="aleloAccepted"/><br/>
        Image: <form:input path="image" type="text"/>
        <form:errors path="image"/><br/>

        <form:button>Save</form:button>
        <!--    <a href='/'>Cancel</a>  -->
        <button type="cancel" onclick="window.location='/restaurants/';return false;">Cancel</button>

    </form:form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript">
        function isSavingOrUpdating(){
            if($("#restaurantName").val().toString() != null){
                //alert("The name of this restaurant is "+$("#restaurantName").val());
                $('#headerText').text("Update a restaurant");
            }
            else{
                //alert("Null restaurant. Name: "+$("#restaurantName").val());
                $('#headerText').text("Add a new restaurant");
            }

        }
    </script>
    <script type="text/javascript">

        //window.onload = isSavingOrUpdating;

    </script>

</body>
</html>
