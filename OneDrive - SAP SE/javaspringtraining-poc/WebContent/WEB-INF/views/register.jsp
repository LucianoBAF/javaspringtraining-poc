<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 02/08/2017
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@include file="/resources/tags/headerLogin.jsp"%>

<body>
    <div class="row">
        <h1 id="headerText" class="header">Welcome, please register</h1>
    </div>
    <br>

    <div class="col-lg-5"></div>
    <div class="col-lg-2 container-fluid ">
        <div class="row center-block">
            <form:form modelAttribute="user" servletRelativeAction="/registration" method="POST" cssStyle="font-family: 'Yu Gothic';align-items: center; align-self: center">
                <div class="form-group">
                    <div class="col-lg-6">Name: </div>
                    <div class="col-lg-6"><form:input path="name" id="userName" /></div>
                    <form:errors path="name"  class="errors"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Email: </div>
                    <div class="col-lg-6"><form:input path="email"/></div>

                    <form:errors path="email"  class="errors"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Date of birth: </div>
                    <div class="col-lg-6"><form:input  path="dateOfBirth" format="dd/MM/yyyy"/></div>

                    <form:errors path="dateOfBirth"  class="errors"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Password: </div>
                    <div class="col-lg-6"><form:password path="password"/></div>

                    <form:errors path="password"  class="errors"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Confirm password: </div>
                    <div class="col-lg-6"><form:password path="passwordConfirm" /></div>

                    <form:errors path="passwordConfirm"  class="errors"/><br/>
                </div>
                <form:hidden path="id"  />

                 <div class="col-lg-6"><button class="btn btn-lg btn-primary btn-block btn-signin" type="submit" >Save</button></div>
                <div class="col-lg-6"><button class="btn btn-lg btn-primary btn-block btn-signin" onclick="window.location='/';return false;">Cancel</button></div>

            </form:form>
        </div>
    </div>
    <div class="col-lg-5"></div>
</body>
</html>
