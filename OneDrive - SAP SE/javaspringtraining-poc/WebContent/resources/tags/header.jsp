<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 07/08/2017
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <%-- Style --%>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/css/restaurantcss.css" >


    <%-- Scripts --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>

    <title>Where to lunch, CD?</title>

    <br>
    <%-- Navigation bar at the top --%>
    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
            <ul class="navbar-brand nav-justified">
                <p>Where to lunch, CD?</p>
            </ul>
            <ul class="nav navbar-nav">
                <li role="presentation" class="active"><a href="/restaurants/">List-restaurants</a></li>
                <li role="presentation"><a href="/restaurants/pollHistory">Last polls</a></li>
                <li role="presentation"><a href="/restaurants/add-restaurant">Add restaurant</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 20px">
                <li><p class="navbar-text navbar-right">Signed in as <sec:authentication property="principal.username" /> | </p></li>
                <li><a href="<c:url value="/logoutSpring" />" >Logout</a></li>
            </ul>
    </nav>
</head>

