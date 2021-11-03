layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    /*监听表单提交*/
    form.on("submit(saveBtn)",function(data){
        console.log(data)
        /*发送ajax*/
        $.ajax({
            type:"post",
            url:ctx+"/user/registerUser",
            data:{
                userName:data.field.userName,
                password1:data.field.password1,
                password2:data.field.password2,
                icon:data.field.icon,
            },
            dataType:"json",
            success:function (msg){
                if(msg.code==200){
                    layer.msg("注册成功，请登录",function(){
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