<%--
  Created by IntelliJ IDEA.
  User: kaona
  Date: 2020/8/7
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Student Info</title>
    <style>
        div{
            width: 50%;
            margin: 0 auto;
            align-content: center;
            text-align: center;
        }
        h1 {
            width: 100%;
            align-content: center;
            text-align: center;
        }
        table {
            width: 90%;
            margin: 5% auto;
            align-content: center;
            border: solid 1px black;
        }
        table thead {
            background: lightgray;
        }
        table td {
            border: solid 1px black;
            text-align: center;
        }
    </style>
    <script>
        function init() {
            document.getElementById('pageSizeSel').value = ${paging.pageSize};
        }
        function pageChange() {
            var pageSize = document.getElementById("pageSizeSel").value;
            location = "stu?method=selectAll&text=${text}&pageSize=" + pageSize;
        }
    </script>
</head>
<body onload="init()">

<div>
    <h1>Student Info</h1>
    <form action="stu?method=selectAll" id="search" method="post">
        <label for="text">搜索关键字：</label>
        <input type="text" id="text" name="text" value="${text}">
        <a href="#" onclick="document.getElementById('search').submit();return false;">查找</a>
    </form>

    <table>
        <thead>
        <tr>
            <td>学号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>编辑</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${paging.datas}" var="stu">
            <tr>
                <td>${stu.id}</td>
                <td>${stu.name}</td>
                <td>${stu.age}</td>
                <td>
                    <a href="stu?method=get&id=${stu.id}&text=${text}">修改</a>
                    <a href="stu?method=delete&id=${stu.id}&text=${text}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a href="stu?method=selectAll&currentPage=1&pageSize=${paging.pageSize}&text=${text}">首页</a>
        <a href="stu?method=selectAll&currentPage=${paging.currentPage - 1}&pageSize=${paging.pageSize}&text=${text}">上一页</a>
        <label>${paging.currentPage}/${paging.totalPage}</label>
        <a href="stu?method=selectAll&currentPage=${paging.currentPage + 1}&pageSize=${paging.pageSize}&text=${text}">下一页</a>
        <a href="stu?method=selectAll&currentPage=${paging.totalPage}&pageSize=${paging.pageSize}&text=${text}">尾页</a>

        <label for="pageSizeSel">每页显示</label>
        <select name="pageSizeSel" id="pageSizeSel" onchange="pageChange()">
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
            <option value="50">50</option>
        </select>
        <label for="pageSizeSel">条</label>

        <label>总共有${paging.totalCount}条数据</label>

    </div>
</div>

</body>
</html>
