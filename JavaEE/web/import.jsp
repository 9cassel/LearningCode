<%--
  Created by IntelliJ IDEA.
  User: kaona
  Date: 2020/8/6
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导入数据</title>
</head>
<style>
    div {
        width: 60%;
        margin: 0 auto;
        text-align: center;
    }
    #uploadFile {
        border: 1px solid;
    }
</style>
<body>
    <div>
        <h1>上传文件</h1>
        <form action="import" enctype="multipart/form-data" method="post">
            <label for="uploadFile">选择文件：</label>
            <input type="file" id="uploadFile" name="uploadFile">
            <input type="submit" value="上传">
        </form>
    </div>
</body>
</html>
