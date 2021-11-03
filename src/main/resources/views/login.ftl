<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${ctx}/images/favicon.ico">
    <title>社区疫情防控系统</title>
    <link rel="stylesheet" href="${ctx}/css/index1.css" media="all">
</head>
<body>
<div class="box">
    <div class="left"></div>
    <div class="right">
        <h4>社区疫情防控系统</h4>
        <form class="layui-form" action="" method="">
            <input class="acc" type="text" placeholder="用户名">
            <input class="acc" type="password" placeholder="密码">
<#--            <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>-->
            <input class="submit" lay-filter="login" type="submit" value="Login">
        </form>
        <div class="fn">
            <a class="add" href="http://localhost:8080/ems/register">注册账号</a>
<#--            <a class="add" href="javascript:;">找回密码</a>-->
        </div>
        <#-- 记住我 -->
        <div class="layui-form-item">
         <font style="mso-font-format: truetype">记住密码</font>
            <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary" title="记住密码">
        </div>

    </div>
</div>

<script src="${ctx}/index.js" charset="utf-8"></script>

</body>
</html>