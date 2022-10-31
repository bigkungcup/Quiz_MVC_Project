<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz JPA Project</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1><%= "Quiz JPA Project" %>
</h1>
<br/>
<h3><a href="list-questions">List Questions</a></h3>
<c:if test='${user.userName == "admin"}'>
    <h3><a href="manage-users">List Users</a></h3>
</c:if>
<c:if test="${cookie.lastquestion != null}">
    <h3><a href="${cookie.lastquestion.value}"> Last Question</a></h3>
</c:if>
<h3><a href="authen?action=logout">Logout</a></h3>
</body>
</html>


