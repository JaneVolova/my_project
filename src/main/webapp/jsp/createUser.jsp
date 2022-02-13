<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: ${color}">Users count - ${userList.size()}</h1>

<table id="users_table">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
    </tr>
    <c:forEach items="${userList}" var="users">
        <tr>
            <td>${users.id}</td>
            <td>${users.firstName}</td>
            <td>${users.lastName}</td>
            <td>${users.age}</td>
        </tr>
    </c:forEach>
</table>
<form action="/create" method="post">
<%--<div>--%>
    <input id="firstName" name="firstName" placeholder="Enter First Name">
    <input id="lastName" name="lastName" placeholder="Enter Last Name">
    <input id="age" placeholder="Enter Age">
    <input type="submit" value="Add!!!!!">
<%--    <button onclick="addUser(--%>
<%--        document.getElementById('firstName').value,--%>
<%--        document.getElementById('lastName').value,--%>
<%--        document.getElementById('age').value)">--%>
<%--        Add--%>
<%--    </button>--%>

</form>
<%--</div>--%>
</body>
</html>
