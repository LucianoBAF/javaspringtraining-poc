<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 02/08/2017
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Where to lunch, CD? - Register</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../../resources/css/restaurantcss.css" >

</head>
<body>

    <div class="myContainer">
        <h2>Welcome. Please register</h2>

        <form:form modelAttribute="user" servletRelativeAction="/registration" method="POST" cssStyle="font-family: 'Yu Gothic';align-items: center; align-self: center">
            Name: <form:input path="name" id="userName" />
            <form:errors path="name"  class="errors"/><br/>
            Email: <form:input path="email"/>
            <form:errors path="email"  class="errors"/><br/>
            Date of birth: <form:input  path="dateOfBirth" format="dd/MM/yyyy"/>
            <form:errors path="dateOfBirth"  class="errors"/><br/>
            Password: <form:password path="password"/>
            <form:errors path="password"  class="errors"/><br/>
            Confirm password: <form:password path="passwordConfirm" />
            <form:errors path="passwordConfirm"  class="errors"/><br/>


            <form:hidden path="id"  />



            <form:button >Save</form:button>
            <!--    <a href='/'>Cancel</a>  -->
            <button type="cancel" onclick="window.location='/';return false;">Cancel</button>

        </form:form>
    </div>
</body>


</html>
