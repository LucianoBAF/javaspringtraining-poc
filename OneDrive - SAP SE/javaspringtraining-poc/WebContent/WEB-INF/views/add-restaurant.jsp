<%--
  Created by IntelliJ IDEA.
  User: I863409
  Date: 27/07/2017
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<html >
<%@include file="/resources/tags/header.jsp"%>

<body>
    <div class="row">
        <h1 id="headerText" class="header">What is your favorite restaurant?</h1>
    </div>
    <br><br>

    <div class="col-lg-5"></div>
    <div class="col-lg-2 container-fluid ">
        <div class="row center-block">
            <form:form modelAttribute="restaurant" servletRelativeAction="/restaurants/add-restaurant" method="POST" cssStyle="font-family: 'Yu Gothic';">
                <form:hidden path="id"  />
                <div class="form-group">
                    <div class="col-lg-6">Name </div>
                    <div class="col-lg-6"><form:input path="name" id="restaurantName" /></div>
                    <form:errors path="name"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Price </div>
                    <div class="col-lg-6"><form:input path="averagePrice" format="0.00"/></div>
                    <form:errors path="averagePrice"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Location</div>
                    <div class="col-lg-6"><form:input path="location" type="text"/></div>
                    <form:errors path="location"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-7">Alelo acceptance</div>
                    <div class="col-lg-5"><form:checkbox path="aleloAccepted" /></div>
                    <form:errors path="aleloAccepted"/><br/>
                </div>
                <div class="form-group">
                    <div class="col-lg-6">Image </div>
                    <div class="col-lg-6"><form:input path="image" type="text"/></div>
                    <form:errors path="image"/><br/>
                </div>

                <form:button>Save</form:button>
                <button type="cancel" onclick="window.location='/restaurants/';return false;">Cancel</button>

            </form:form>
        </div>
    </div>
    <div class="col-lg-5"></div>

</body>
</html>
