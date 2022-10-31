<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bigku
  Date: 26-Jul-22
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Users</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<h1>List Users:</h1>
<a href="index.jsp">Home</a> > <a href="manage-users?id=new">Add User</a>
<table>
    <tr>
        <th>Id</th>
        <th>User</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="u">
        <tr>
            <td>${u.index+1}</td>
            <td>${user.userName}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>
                <span class="action">
                [<a href="manage-users?id=${user.userName}&action=edit-user">Edit</a>|
                <a href="manage-users?id=${user.userName}&action=remove-user">Delete</a>]
                </span>
            </td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>
