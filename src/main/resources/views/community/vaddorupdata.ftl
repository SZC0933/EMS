<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--    //隐藏域  id  有id代表是修改-->
    <input name="id" type="hidden" value="${(vacciantion.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="userId"
                   id="userId" value="${(vacciantion.userId)!}"  placeholder="用户编号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--用户id-->
<#--        <div>-->
<#--            <label>请输入用户ID:</label>-->
<#--            <input name="userId" value="${(vacciantion.userId)!}">-->
<#--        </div>-->

        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="trueName" id="trueName"  value="${(vacciantion.trueName)!}" placeholder="请输入客户名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">第一针疫苗</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="first"
                   id="first" value="${(vacciantion.first)!}"  placeholder="第一针疫苗(是/否)">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">第二针疫苗</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="second"
                   name="second" value="${(vacciantion.second)!}" id="second" placeholder="第二针疫苗(是/否)">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="vaddOrUpdata">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/community/vaddorupdata.js"></script>
</body>
</html>