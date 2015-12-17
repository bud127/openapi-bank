<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Index page</title>
</head>
<body>
    <a href="<c:url value="/index.app" />">home</a>
    <a href="<c:url value="/login.app" />">login</a>
    <a href="<c:url value="/account/inquiry.app" />">inquiry</a>
    <a href="<c:url value="/logout" />">logout</a>

    <p>
        <b>User : </b> <sec:authentication property="principal" />
    </p>
</body>
</html>
