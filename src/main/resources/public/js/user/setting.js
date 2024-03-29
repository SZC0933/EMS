layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    /*监听表单提交*/
    form.on("submit(saveBtn)",function(data){

        /*发送ajax*/
        $.ajax({
            type:"post",
            url:ctx+"/user/setting",
            data:{
                userName:data.field.userName,
                userPhone:data.field.userPhone,
                ecPhone:data.field.ecPhone,
                trueName:data.field.trueName,
                id:data.field.id
            },
            dataType:"json",
            success:function (msg){
                if(msg.code==200){
                    layer.msg("保存成功了",function(){
                        //清空Coookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/ems"});
                        $.removeCookie("userName",{domain:"localhost",path:"/ems"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/ems"});
                        //页面跳转
                        window.parent.location.href=ctx+"/index";
                    });
                }else{
                    //修改失败了提示
                    layer.msg(msg.msg);
                }
            }
        });

        return false;
    });
});