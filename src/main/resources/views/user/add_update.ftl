<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--用户的id-->
    <input name="id" type="hidden" value="${(user.id)!}"/>

    <input name="man" type="hidden" value="${(user.vc)!}"/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="userName" id="userName"  value="${(user.userName)!}" placeholder="请输入用户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="trueName" id="trueName" value="${(user.trueName)!}" placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userSex"
                   lay-verify="required" name="sex" value="${(user.sex)!}" id="sex" placeholder="请输入性别">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">社区编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userSex"
                   lay-verify="required" name="comId" value="${(user.comId)!}" id="comId" placeholder="请输入社区编号">
        </div>
    </div>



    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="phone" name="userPhone" value="${(user.userPhone)!}" id="userPhone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">紧急联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="phone" name="ecPhone" value="${(user.ecPhone)!}" id="ecPhone" placeholder="请输入紧急联系电话">
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">是否接种疫苗</label>
        <div class="layui-input-inline">
            <select name="vc" id="vc">
                <option value="">是否接种疫苗</option>
                <option value="1">未接种</option>
                <option value="2">已接种</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">健康状态</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="required" name="health" value="${(user.health)!}" id="health" placeholder="请输入健康状态">
        </div>
    </div>

    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select name="roleIds" xm-select="selectId">
            </select>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/user/add_update.js"></script>
</body>
</html>