<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--    //隐藏域  id  有id代表是修改-->
    <input name="id" type="hidden" value="${(user.id)!}"/>

<#--    确诊表的comid-->
    <input name="man" type="hidden" value="${(user.comId)!}"/>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input trueName"
                   lay-verify="required" name="trueName" id="trueName"
                   value="${(user.trueName)!}" placeholder="请输入姓名">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input tcPhone"
                   lay-verify="required" name="tcPhone" id="tcPhone"
                   value="${(user.tcPhone)!}" placeholder="请输入手机号">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">目前状况</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input state"
                   lay-verify="required" name="state"
                   value="${(user.state)!}" id="state" placeholder="1:轻微,2:重症,3:无症状">
        </div>
    </div>
    <br/>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">所属社区</label>
        <div class="layui-input-block">
            <select name="comId" id="address">
                <option value="">请选择</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消
            </button>
        </div>
    </div>

</form>
<script type="text/javascript" src="${ctx}/js/confirmed/add_update.js"></script>
</body>
</html>