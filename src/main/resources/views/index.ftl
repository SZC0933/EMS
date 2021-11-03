<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>社区疫情防控系统</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/index.css" media="all">
</head>
<body>
<div class="layui-container">
<#--    <img src="${ctx}/images/bg.png" style="width:100%">-->
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1 class="vintage4">社区疫情防控系统</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" name="username" lay-verify="required|account" placeholder="用户名或者邮箱" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" >
                </div>
                <#-- 记住我 -->
                <div class="layui-form-item">
                    <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary" title="记住密码">
                    <a style="float: right;" class="add" href="http://localhost:8081/ems/register">注册账号</a>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>
                </div>

            </form>
        </div>
    </div>
</div>
<script src="${ctx}/js/index.js" charset="utf-8"></script>
</body>
</html>