<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanit
  Date: 29/6/22
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Questions</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<h1>List Questions:</h1>
<a href="index.jsp">Home</a> > <a href="manage-question?id=new">Add Question</a><br><br>
<table>
    <tr>
        <th style="width: 10% ">Id</th>
        <th>Question</th>
        <th>#Choices</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${questions}" var="question">
        <tr>
            <td>${question.id}</td>
            <td>${question.question}</td>
            <td>${question.answers.size()}</td>
            <td>
                  <span class="action">
                      [ <a href="manage-question?id=${question.id}">Edit</a> |
                      <a href="manage-question?id=${question.id}&action=remove-question">Delete</a> ]
                  </span>
            </td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>

