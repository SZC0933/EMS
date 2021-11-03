<!DOCTYPE html>
<html>
<head>
    <title>社区管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="comId" class="layui-input searchVal" placeholder="社区编号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="address" class="layui-input searchVal" placeholder="地址" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="leader" class="layui-input searchVal" placeholder="负责人" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>
                    搜索
                </a>
            </div>
        </form>
    </blockquote>
    <table id="communityList" class="layui-table" lay-filter="communities">
    </table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">

        </div>
    </script>
    <!--操作-->
    <script id="communityListBar" type="text/html">

    </script>
</form>
<script type="text/javascript" src="${ctx}/js/community/community.js"></script>
</body>
</html>