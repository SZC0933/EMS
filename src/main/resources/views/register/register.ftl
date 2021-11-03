<!DOCTYPE >
<html>
<html lang="ch">
<head>
    <title>注册</title>
    <#include "../common.ftl">
    <meta charset="utf-8">
    <style type="text/css">

        #lunkuo{
            position: absolute;
            top: 15%;/*顶部到元素*/
            left:30%;
            width: 40%;
            height:500px;
            /*margin-top:-200;!*边缘到底部*!*/
            background-color: #f0a1a8;
            text-align-last: center;
            filter:alpha(Opacity=60);
            opacity: 0.6;
        }
        #lunkuo input{
            font-size: 17px;
            text-align: center;
            border-radius: 25px;
        }

        #userName {
            width: 88%;
        }
        #password1 {
            width: 88%;
        }
        #password2 {
            width: 88%;
        }
        #icon {
            width: 88%;
        }
    </style>
</head>
<body style="background-color: cornsilk">
<div id="lunkuo">
    <div style="height: 8%; padding-top: 10px;padding-bottom: 20px">
        <h1>注册</h1>
    </div>
    <form class="layui-form" >
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName" lay-verify="required" name="userName" id="userName"  value="${(userName)!}" placeholder="3-5位,数字字母下划线"/><span id="namespan"style="color: red;"></span><br /><br />
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input userName" lay-verify="required" name="password1" id="password1"  value="${(password1)!}" placeholder="密码:6-12位" /><span id="passspan"style="color: red;"></span><br /><br />
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input userName" lay-verify="required" name="password2" id="password2"  value="${(password2)!}" placeholder="请再次确认密码" /><span id="passrspan"style="color: red;"></span><br /><br />
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="tel" class="layui-input userName" lay-verify="required" name="icon" id="icon"  value="${(icon)!}"placeholder="请输入手机号" /><br /><br />
            </div>
        </div>
        <button class="layui-btn" lay-submit lay-filter="saveBtn">确认保存</button>
    </form>

</div>
<script type="text/javascript" src="${ctx}/js/register/register.js"></script>
</body>
</html>
