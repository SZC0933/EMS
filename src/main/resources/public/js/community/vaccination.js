layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#bookCustomerList',
        url : ctx + '/community/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "vaccinationListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '编号', minWidth:50, align:"center"},
            {field: 'userId', title: '用户ID', minWidth:50, align:"center"},
            {field: 'trueName', title: '用户名', minWidth:50, align:"center"},
            {field: 'first', title: '第一针疫苗', minWidth:100, align:'center'},
            {field: 'firstDate', title: '①接种时间', minWidth:50, align:'center'},
            {field: 'second', title: '第二针疫苗', align:'center',minWidth:150},
            {field: 'secondDate', title: '②接种时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#bookCustomerListBar',fixed:"right",align:"center"}
        ]]
    });

    /*查询*/
    // 多条件搜索
    $(".search_btn").on("click",function(){
        tableIns.reload({
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                trueName: $("input[name='trueName']").val(),
                first: $("input[name='first']").val(),
                second: $("input[name='second']").val()
            }
        })
    });



    /*头部工具栏绑定*/
    //头工具栏事件
    table.on('toolbar(vaccination)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                openAddOrUpdateUserPage();
                break;
            case 'del':
                deleterUser(checkStatus.data);
                break;

        };
    });


    /*删除函数*/
    function deleterUser(data){
        console.log(data)
        //判断是否有数据
        if(data.length==0){
            layer.msg("请选择要删除的数据");
        }

        layer.confirm("你确定要删除这些数据吗?",{
            btn:["确认","取消"],
        },function(index){
            //关闭询问框
            layer.close(index);
            //收集数据
            // ids=1&ids=2&ids=3
            var ids = "ids=";
            // 遍历获取对应的id
            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    ids = ids + data[i].id + "&ids=";
                } else {
                    ids = ids + data[i].id;
                }
            }
            //发送请求删除数据
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/community/delete",
                data:ids,
                dataType:"json",
                success:function(result){
                    console.log(result);
                    if(result.code==200){
                        layer.msg("删除OK",{icon : 5 });
                        //重新加载一下数据
                        tableIns.reload();
                    }else{
                        console.log(result.code+"状态码");
                        //提示
                        layer.msg(result.msg);
                    }
                }
            });
        });
    }


    /**
     * 添加-更新的
     * @param userId
     */
    function openAddOrUpdateUserPage(id){
        console.log(id+'-----------------')
        var title="<h2>用户模块---添加</h2>";
        var url=ctx+"/community/vaddorupdate";

        //判断是否修改还是添加
        if(id){
            title="<h2>用户模块---更新</h2>";
            url=url+"?id="+id;
            console.log(url);
        }
        //弹出层
        layer.open({
            title:title,
            content:url,
            type:2,//iframe
            area:["650px","400px"],
            maxmin:true,
        })
    }


    /*行内工具栏的绑定*/
    //监听行工具事件
    table.on('tool(vaccination)', function(obj){
        var data = obj.data;
        console.log(data.id);
        if(obj.event === 'del'){
            layer.confirm("主人，你确定狠心删除数据吗?",{
                btn:["确认","取消"]
            },function(index){
                //关闭
                layer.close(index);
                //发送ajax删除数据
                $.post(ctx+"/community/delete",{ids:data.id},function(result){
                    if(result.code==200){
                        //重新加载数据
                        tableIns.reload();
                    }else{
                        //提示一下
                        layer.msg(result.msg,{icon:5 });
                    }
                },"json");
            });
        } else if(obj.event === 'edit'){
            openAddOrUpdateUserPage(data.id);
        }
    });

});