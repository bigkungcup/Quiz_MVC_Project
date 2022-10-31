<%--
  Created by IntelliJ IDEA.
  User: bigku
  Date: 26-Jul-22
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            text-align: center;
            width: 50%;
            margin-top: 50px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<h3>Login</h3>
<form action="authen" method="post">
    <p>
        <span for="username">User Name:</span>
        <input type="text" name="userName"/><br>
    </p>
    <p>
        <span for="password">Password:</span>&nbsp;&nbsp;&nbsp;
        <input type="password" name="password"/><br>
    </p>
    <p>
        &nbsp;&nbsp;&nbsp;<input type="submit" value="Login"/>
        <input type="reset" value="Cancel"/>
    </p>
</form>
</body>
</html>
