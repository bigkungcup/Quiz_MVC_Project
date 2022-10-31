<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bigku
  Date: 26-Jul-22
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="card">
    <c:choose>
        <c:when test='${id=="new"}'>
            <h3 class="label">New User</h3>
            <form action="manage-users" method="post">
                <p class="label">User :
                    <span><input type="text" name="username"/></span>
                </p>
                <p class="label">Password :
                    <span><input type="password" name="password"/></span>
                </p>
                <p class="label">Firstname :
                    <span><input type="text" name="firstname"/></span>
                </p>
                <p class="label">Lastname :
                    <span><input type="text" name="lastname"/></span>
                </p>
                <p class="label">Email :
                    <span><input type="text" name="email"/></span>
                </p>
                <input type="hidden" name="id" value="new"/>
                <input type="hidden" name="action" value="add-user"/>
                <input type="submit" value="Save">
            </form>
            <a href="manage-users"><button>Back</button></a>
        </c:when>
        <c:otherwise>
            <h3 class="label">User : ${user.userName}</h3>
            <div>
                <span class="label">User : </span> ${user.userName}
            </div>

            <c:if test="${action == 'edit'}">
                <form action="manage-users" method="post">
                <input type="hidden" name="id" value="${user.userName}">
                <input type="hidden" name="action" value="edit-user">
            </c:if>

            <c:choose>
                <c:when test="${action == 'edit'}">
                    <p>
                        <span class="label">Firstname : </span>
                        <input type="text" name="firstname" value="${user.firstName}">
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        <span class="label">Firstname : </span>
                            ${user.firstName}
                    </p>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${action == 'edit'}">
                    <p>
                        <span class="label">Lastname : </span>
                        <input type="text" name="lastname" value="${user.lastName}">
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        <span class="label">Lastname : </span>
                            ${user.lastName}
                    </p>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${action == 'edit'}">
                    <div>
                        <span class="label">Email : </span>
                        <input type="text" name="email" value="${user.email}">
                    </div><br>
                </c:when>
                <c:otherwise>
                    <div>
                        <span class="label">Email : </span>
                            ${user.email}
                    </div><br>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${action == 'edit'}">
                    <input type="submit" value="Save">
                    </form>
                    <a href="manage-users"><button>Back</button></a>
                </c:when>
                <c:otherwise>
                    <a href="manage-users"><button>Back</button></a>
                    <a href="manage-users?action=edit&id=${user.userName}"><button>Edit</button></a>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>


<%--            <div id="edit-user-form" class="hide">--%>
<%--                <form action="manage-users" method="post">--%>
<%--                    <span class="label">User: </span>--%>
<%--                    <input type="text" name="username" value="${user.userName}" />--%>
<%--                    <input type="hidden" name="action" value="edit-user" />--%>
<%--                    <input type="hidden" name="id" value="${user.userName}" />--%>
<%--                    <input type="submit" value="Save">--%>
<%--                </form>--%>
<%--            </div>--%>