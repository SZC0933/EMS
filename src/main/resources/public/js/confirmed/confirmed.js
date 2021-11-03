
layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //角色列表展示
    var  tableIns = table.render({
        elem: '#saleChanceList',
        url : ctx+'/confirmed/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "saleChanceList",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'trueName', title: '真实姓名', minWidth:100, align:'center'},
            {field: 'tcPhone', title: '手机号', minWidth:100, align:'center'},
            {
                field: 'comId', title: '所属社区', align: 'center', templet: function (d) {
                    return formattercomId(d.address);
                }
            },
            {
                field: 'state', title: '目前状态', align: 'center', templet: function (d) {
                    return formatterState(d.state);
                }
            },
            {field: 'createDate', title: '确诊时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#ListOfConfirmeCases',fixed:"right",align:"center"}

        ]]
    });

    function formatterState(state) {
        if (state === 1) {
            return "<div style='color: orange'>轻微</div>";
        } else if (state === 2) {
            return "<div style='color: red'>重症</div>";
        } else if (state === 3) {
            return "<div style='color: yellowgreen'>无症状</div>";
        } else {
            return "<div style='color: blue'>其他</div>";
        }
    }

    function formattercomId(address) {
            console.log(address)
            if (address === "浦东区") {
                return "<div >浦东区</div>";
            } else if (address === "黄浦区") {
                return "<div >黄浦区</div>";
            } else if (address === "松江区") {
                return "<div >松江区</div>";
            }else if (address === "徐汇区") {
                return "<div >徐汇区</div>";
            }else if (address === "虹口区") {
                return "<div >虹口区</div>";
            }

    }


    //实现搜索功能  页面重载   重构的时候 刷新这个页面  查询调用上面的  '/confirmed/list',
    $(".search_btn").click(function() {
        //搜索         方法一   tableIns 找寻头部工具栏
        tableIns.reload({
            where: { //设定异步数据接口的额外参数
                //通过name属性获取客户名
                trueName: $("input[name=trueName]").val(),
                //通过手机号搜索
                tcPhone:$("input[name=tcPhone]").val(),
                //通过name属性获取创建人
                address: $("input[name=address]").val(),
                state: $("#state").val()
            },
            page: {
                curr: 1   //重新从第一页开始
            }
        })

    });


    /*头部工具栏绑定*/
    //头工具栏事件
    table.on('toolbar(saleChances)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        //打印全部数据  调错时用的
        console.log(checkStatus);
        //只打印data数据 也就是每条信息
        console.log(checkStatus.data);
        switch(obj.event){
            case 'flash':
                //layer.msg("刷新页面");
                //重新加载数据
                tableIns.reload();
                break;

            case 'add':
                //layer.msg("添加");
                openAddOrUpdateUserPage();
                break;

            case 'del':
                //layer.msg("dels");
                deleterUser(checkStatus.data);
                break;

        };
    });


    /*行内工具栏绑定*/
    //监听行工具事件
    table.on('tool(saleChances)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm("确定删除吗?",{
                btn:["确认","取消"]
            },function(index){
                //关闭
                layer.close(index);
                //发送ajax删除数据
                $.post(ctx+"/confirmed/dels",{ids:data.id},function(result){
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

    /**
     * 添加 || 修改
     * @param userId
     */
    function openAddOrUpdateUserPage(userId){
        //表头
        var title="<h2>确诊名单---添加</h2>";
        //发送给谁   提前定义好地址值  默认是添加
        var url=ctx+"/confirmed/addOrUpdatePage";

        //判断是否修改还是添加
        if(userId){
            //表头
            title="<h2>确诊名单---更新</h2>";
            //带上id  就代表是更新
            url=url+"?id="+userId;
        }
        //
        layer.open({
            title:title,
            content:url,
            type:2,//iframe
            area:["650px","400px"],   //宽高
            maxmin:true,               //最大，最小
        })
    }


    //选择删除时  弹出的提示框
    function deleterUser(datas){
        if(datas.length==0){
            layer.msg("请选择要要删除的数据");
            return ;
        }
        layer.confirm("确定删除数据?",{
            btn:["确认","取消"]
        },function(index){
            //关闭
            layer.close(index);
            //收集数据
            var ids="&ids=";
            for (var i = 0; i < datas.length ; i++) {
                if(i< datas.length -1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }
            console.log(ids);
            //发送ajax删除数据
            $.post(ctx+"/confirmed/dels",ids,function(result){
                if(result.code==200){
                    //重新加载数据
                    tableIns.reload();
                }else{
                    //提示一下
                    layer.msg(result.msg,{icon:5 });
                }
            },"json");
        });
    }









});
