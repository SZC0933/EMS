layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 社区列表展示
     */
    var tableIns = table.render({
        elem: '#communityList',
        url : ctx+'/com/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "communityListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'address', title: '地址', minWidth:50, align:"center"},
            {field: 'number', title: '社区人数', minWidth:100, align:'center'},
            {field: 'leader', title: '负责人', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},

        ]]
    });


    /*实现搜索功能，页面的重载*/
    $(".search_btn").click(function(){
        //这里以搜索为例
        tableIns.reload({
            where:{ //设定异步数据接口的额外参数，任意设
                comId:$("input[name=comId]").val(),
                address:$("input[name=address]").val(),
                leader:$("input[name=leader]").val()
            }
            ,page:{
                curr: 1 //重新从第 1 页开始
            }
        });
    });


});