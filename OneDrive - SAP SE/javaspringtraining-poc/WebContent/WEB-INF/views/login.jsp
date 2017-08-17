<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 02/08/2017
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>

<html>
<%@include file="/resources/tags/headerLogin.jsp"%>
<body>

    <div class="col-lg-1"></div>
    <div class="col-lg-10 container-fluid">
        <h1 class="header">Where to lunch, CD?</h1>
        <br>

        <div class="col-lg-1"></div>
        <div class="container col-lg-10">
            <div class="card card-container default-color2">
                <h2 class="text-center">Login</h2>
                <c:if test="${param.error == 'true'}">
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        Please verify your entries
                    </div>
                </c:if>

                <form class="form-signin" role="form" method="post" action="/" >
                    <div class="input-group">
                        <span class="input-group-addon">Email</span>
                        <input name="email" type="email" class="form-control" placeholder="Email" aria-describedby="basic-addon1" required autofocus>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Password</span>
                        <input name="password" type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1" required>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
                    <br>
                    <p>Don't have an account? </p>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" onclick="window.location='/registration'">Register now!</button>
                </form>
            </div><!-- /card-container -->
        </div><!-- /container -->
        <div class="col-lg-1"></div>
    </div>
    <div class="col-lg-1"></div>
</body>
</html>
