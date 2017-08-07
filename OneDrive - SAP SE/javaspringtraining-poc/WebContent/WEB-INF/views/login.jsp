<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 02/08/2017
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Where to lunch, CD? - Login</title>
</head>
<body>

    <div class="container" style="padding-left: 35%; align-items: center">
        <h1 >Log in</h1>
        <c:if test="${param.error == 'true'}">
            <div class="form-group">
                <jsp:text>Login error</jsp:text>
            </div>
        </c:if>
        <form:form modelAttribute="user" servletRelativeAction="/" method="POST" cssStyle="font-family: 'Yu Gothic';align-items: right; align-self: center">
            Email: <form:input path="email"/>
            <form:errors path="email"/><br/>
            Password: <form:password path="password"/>
            <form:errors path="password"/><br/>



            <div style="align-items: center;width: 100%;align-self: stretch;">
                <form:button>Login</form:button>
            </div>


            <br/>
            <a style="font-size: smaller">Don't have an account? </a>
            <button type="cancel" onclick="window.location='/registration';return false;">Register now!</button>

        </form:form>
    </div>

</body>
</html>
