<%--
  Created by IntelliJ IDEA.
  User: kaona
  Date: 2020/8/7
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student Info</title>
    <style>
        div {
            width: 50%;
            margin: 0 auto;
            text-align: center;
            align-content: center;
        }
    </style>
</head>
<body>
    <div>
        <h1>Edit Info</h1>
        <form action="stu?method=update&text=${text}&id=${stu.id}" method="post" id="myForm">
            <label for="stuId">学号：</label>
            <input type="text" id="stuId" value="${stu.id}" disabled>
            <br>
            <label for="stuName">姓名：</label>
            <input type="text" id="stuName" value="${stu.name}" name="name">
            <br>
            <label for="stuAge">年龄：</label>
            <input type="text" id="stuAge" value="${stu.age}" name="age">
            <br>
            <a href="#" onclick="document.getElementById('myForm').submit();return false;">修改</a>
            <a href="stu?method=selectAll">返回</a>
        </form>
    </div>
</body>
</html>
