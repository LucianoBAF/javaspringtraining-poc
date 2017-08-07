<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 02/08/2017
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Where to lunch, CD? - Login</title>
    <%@include file="/resources/tags/header.jsp"%>
</head>
<body>

    <h2 id="headerText" class="header">Where to lunch, CD?</h2>
    <hr>
    <br>

    <div  class="myContainer">
        <h1 >Log in</h1>
        <c:if test="${param.error == 'true'}">
            <div class="form-group">
                <span style="color: crimson">Login error, please verify your entries</span>
            </div>
        </c:if>
        <form:form modelAttribute="user" servletRelativeAction="/" method="POST" class="loginForm">
            Email:    <form:input path="email" id="loginEmail"/>
            <form:errors path="email"/><br/>
            Password: <form:password path="password" id="loginPassword"/>
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
