<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login page</title>
</head>
<body>

<form action="<c:url value="/login" />" method="post">
    <h3>Login</h3>
    <label>Username: </label> <input type="text" name="username" value="client-user01"> <br/>
    <label>Password: </label> <input type="text" name="password" value="1234"> <br/>
    <input type="submit" title="login"> <br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
