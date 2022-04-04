<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="div1">
    <h1>Student List</h1>
</div>
<h2>
    <button id="button1" type="button" onclick="location.href='/school?action=add'">Add New Student</button>
</h2>
<h2>
    <button id="button2" type="button" onclick="location.href='/school?action=class'">Go to Class List</button>
</h2>
<div>
    <table>
        <caption><h2>Student List</h2></caption>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Class Name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${student}" var="student">
            <tr>
                <td><c:out value="${student.firstName}"/></td>
                <td><c:out value="${student.lastName}"/></td>
                <td><c:out value="${student.address}"/></td>
                <td><c:out value="${student.classesName}"/></td>
                <td>
                    <a href="/school?action=edit&id=${student.studentId}">Update</a>
                    <a href="/school?action=delete&id=${student.studentId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
