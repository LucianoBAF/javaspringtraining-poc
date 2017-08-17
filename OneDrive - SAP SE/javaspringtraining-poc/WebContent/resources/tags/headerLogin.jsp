<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 08/08/2017
  Time: 10:29
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
    <link rel="stylesheet" href="../../resources/css/login.css" >

    <%-- Scripts --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>

    <title>Where to lunch, CD?</title>

    <br>
    <%-- Navigation bar at the top --%>
    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
        <ul class="navbar-header navbar-brand nav-justified">
            <p class="navbar-text text-center">Where to lunch, CD?</p>
        </ul>
        <ul class="nav navbar-nav">
            <li role="presentation" class="active"><a href="/">Login</a></li>
            <li role="presentation"><a href="/registration">Register</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 20px">
            <li><p class="navbar-text navbar-right">User not signed yet</p></li>
        </ul>
    </nav>
</head>
