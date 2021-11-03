layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    /*监听表单提交*/
    form.on("submit(saveBtn)",function(data){
       alert("此处写逻辑代码~~~~~~~~~~~~~~~~")
    });
});