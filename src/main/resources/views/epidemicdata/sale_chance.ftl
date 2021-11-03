<!DOCTYPE html>
<html>
<head>
    <title>确诊情况</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="customerName"
                           class="layui-input
							searchVal" placeholder="用户名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="createMan" class="layui-input
							searchVal" placeholder="社区" />
                </div>
                <div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="" >是否核酸</option>
                        <option value="0">未核酸</option>
                        <option value="1" >已核酸</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="saleChanceList" class="layui-table"  lay-filter="saleChances">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                刷新
            </a>
        </div>
    </script>


    <!--操作-->
<#--    <script id="saleChanceListBar" type="text/html">-->
<#--        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>-->
<#--        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>-->
<#--    </script>-->

</form>

<script type="text/javascript" src="${ctx}/js/saleChance/sale_chance.js"></script>
</body>
</html>