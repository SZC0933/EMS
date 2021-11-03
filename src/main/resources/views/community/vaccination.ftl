<!DOCTYPE html>
<html>
<head>
    <title>疫苗管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody" style="background: #BFD7E6">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="trueName"
                           class="layui-input searchVal" placeholder="用户名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="first"
                           class="layui-input searchVal" placeholder="第一针(是/否)" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="second"
                           class="layui-input searchVal" placeholder="第二针(是/否)" />
                </div>


                <a class="layui-btn search_btn" data-type="reload" style="background: #566FA7;">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <!-- 数据表格 -->
    <table id="bookCustomerList" class="layui-table" lay-filter="vaccination" >

    </table>
    <!-- 头部工具栏 -->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add" style="background: #566FA7;">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del" style="background: #566FA7;">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>
    <!-- 行工具栏 -->
    <script id="bookCustomerListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit" style="background: #566FA7;">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" style="background: #566FA7;">删除</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/community/vaccination.js"></script>
</body>
</html>