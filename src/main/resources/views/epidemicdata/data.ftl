<!DOCTYPE html>
<html lang="en">
<#include "../common.ftl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>☆☆☆☆☆</title>
    <style>
        .picture {
            position: relative;
            width: 90%;
            height: 444px;
            margin: 0 auto;
            border: 4px solid rgb(231, 127, 2);
            overflow: hidden;
        }

        .radius {           /* 圆点所在的p (容器)  */
            width: 100%;
            height: 10px;
            position: absolute;
            bottom: 30px;
            text-align: center;
        }

        .pg {
            position: absolute;
            margin: 0;
            width: 100%;
            height: 20px;
            background-color: rgba(0, 0, 0, .4);
            text-align: center;
            font-size: 16px;
            font-weight: 600;
            color: #fff;
        }

        .title {
            position: absolute;
            width: 100%;
            bottom: 0px;
            text-align: center;
            font-size: 16px;
            font-weight: 600;
            color: rgb(21, 223, 72);
        }

        span {
            display: inline-block;
            border: 10px solid #fdfdfd;
            border-radius: 50%;
        }

        .active {
            border: 10px solid #656466;
        }

        /* 左右箭头  */
        .arrowhead-left,
        .arrowhead-right {
            position: absolute;
            width: 41px;
            height: 69px;
            font-size: 30px;
            line-height: 70px;
            text-align: center;
            color: #D6D8D4;
            background-color: rgba(0,0,0,.3);
        }

        .arrowhead-left {
            left: 0;
            top: 40%;
        }

        .arrowhead-right {
            right: 0;
            top: 40%;
        }
    </style>
</head>

<body style="background-color: bisque">
<div class="picture">
    <!-- 图片页码 -->
    <p class="pg">封面</p>
    <img src="${ctx}/images/img1.jpeg" alt="">

    <!-- 小圆点点 -->
    <p class="radius"></p>
    <!-- 图片的下面标题 -->
    <p class="title">抗疫作品</p>

    <!-- 左右箭头 -->
    <div class="arrowhead-left" id="al"> < </div>
    <div class="arrowhead-right" id="ar"> > </div>
</div>

<script>
    var address = ["${ctx}/images/img1.jpeg", "${ctx}/images/img2.jpeg", "${ctx}/images/img3.jpeg", "${ctx}/images/img4.jpeg", "${ctx}/images/bg.png", "${ctx}/images/img6.jpeg"];
    //  var imgs = document.getElementsByTagName("img");
    var imgs = document.querySelector("img");
    var len = address.length;               //图片地址的数量为len
    var str = "";
    var pp = document.getElementsByTagName("p");//获取的是p标签的集合
    //  var pp  = document.querySelector("p");    //获取的是一个元素
    var al = document.getElementById("al");
    var ar = document.getElementById("ar");
    var n = 0 ;

    //添加span标签（小圆点），个数为len个
    for (i = 0; i < len; i++) {
        str += ' <span></span>'
    }
    pp[1].innerHTML = str;

    var spans = pp[1].getElementsByTagName('span');  //获取p[1]里所有span标签
    spans[0].className = 'active';                  //给第一个span标签添加样式 active

    for (i = 0; i < len; i++) {
        spans[i].index = i;              //自定义索引值

        spans[i].onmouseover = function () {            //鼠标指向圆点时的事件
            for (i = 0; i < len; i++) {
                spans[i].className = "";               //通过循环，清除所有圆点的类名
            }
            n=this.index ;
            this.className = 'active';                 //给鼠标移入的圆点添加类名
            imgs.src = address[this.index];
            pp[0].innerHTML = [this.index + 1] + "/6";
            pp[2].innerHTML = "✧✧✧✧✧" + [this.index + 1];

        }

    }

    ar.onclick = function () {            //右侧箭头，点击一次图片向右换一张
        n++;
        if (n>5) {
            n=0;
        }
        for (i = 0; i < len; i++) {
            spans[i].className = "";
        }

        spans[n].className = "active";
        imgs.src = address[n];
        pp[0].innerHTML = (n+1) + "/6";
        pp[2].innerHTML = "✧" +(n+1);

    }

    al.onclick = function () {        // //左侧箭头，点击一次图片向左换一张
        n--;
        if (n<0) {
            n=(len-1);
        }
        for (i = 0; i < len; i++) {
            spans[i].className = "";
        }
        spans[n].className = "active";
        imgs.src = address[n];
        pp[0].innerHTML = (n+1) + "/6";
        pp[2].innerHTML = "✧" +(n+1);
    }

    setInterval(ar.onclick,3000);             //添加定时器  setInterval（函数，间隔时间单位为毫秒）
                                              //此次添加的函数为点击右侧箭头，间隔为3秒
</script>

<hr>

<ul class="layui-timeline">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2020年新年伊始</h3>
            <p>
                <br>?回顾抗疫，举国同心?
                ?一场新型冠状病毒肺炎疫情突然袭来，全国各地迅速行动，纷纷启动重大突发公共卫生事件一级响应机制，全面打响了疫情防控阻击战。
                <br>?面对疫情，领导人高度重视，多次召开会议、听取汇报，作出重要指示，亲自指挥、亲自部署，全国迅速形成了全面动员、全面部署、全面加强疫情防控的战略格局。 直到4月29日中央政治局常委会上，最高领导人作出“在党中央坚强领导和各方面大力支持下，在湖北人民特别是武汉人民积极参与配合下，经过艰苦卓绝的努力，湖北保卫战、武汉保卫战取得决定性成果，全国新冠肺炎疫情防控阻击战取得重大战略成果”的重要论断。 <i class="layui-icon"></i>
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">?回顾抗疫，无私无畏?</h3>
            <p>
                <br>?疫情防控工作开展以来，每个行业、每位人民群众本着生命至上的原则，通过各种途径和方式为战胜疫情防控阻击战贡献自己的力量。既有奋战在抗击疫情第一线的医务工作者，穿着防护服坚定地站成一排，形成一道坚实的城墙堡垒，保护着身后人们的生命安全，在前方迎面病毒的威胁，他们不畏惧困难险阻，英勇与病毒展开激烈的争夺，争夺生命、争夺时间，令人感动。又有干群齐心战疫情的驻村的“第一书记”，他们想办法、
                找物资，费尽千辛万苦，为村里群众送去防护口罩和消毒药品。还有“我是党员，我先上”的先锋模范,在抗击疫情的战斗中，
                各级党组织和广大党员干部，夜以继日地值守、宣传、排查……到处都有党员不畏艰难、无私奉献的身影，他们用实际行动践行着共产党员的职责和使命。
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title"> ?回顾抗疫，砥砺前行?</h3>
            <p>
                ?至今中国解决疫情扩散，成为世界之瞩目?
                <br>常常在想，尽管对这个国家有这样那样的抱怨，但我们的确生在了最好的时代
                <br>铭记、感恩
                <br>所有为中华民族浴血奋战的英雄将士
                <br>永垂不朽！！！
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <div class="layui-timeline-title"> ?未来?</div>
        </div>
    </li>
</ul>

<hr>

<hr>

<ul class="layui-timeline">
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2020年新年伊始</h3>
            <p>
                <br>?回顾抗疫，举国同心?
                ?一场新型冠状病毒肺炎疫情突然袭来，全国各地迅速行动，纷纷启动重大突发公共卫生事件一级响应机制，全面打响了疫情防控阻击战。
                <br>?面对疫情，领导人高度重视，多次召开会议、听取汇报，作出重要指示，亲自指挥、亲自部署，全国迅速形成了全面动员、全面部署、全面加强疫情防控的战略格局。 直到4月29日中央政治局常委会上，最高领导人作出“在党中央坚强领导和各方面大力支持下，在湖北人民特别是武汉人民积极参与配合下，经过艰苦卓绝的努力，湖北保卫战、武汉保卫战取得决定性成果，全国新冠肺炎疫情防控阻击战取得重大战略成果”的重要论断。 <i class="layui-icon"></i>
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">?回顾抗疫，无私无畏?</h3>
            <p>
                <br>?疫情防控工作开展以来，每个行业、每位人民群众本着生命至上的原则，通过各种途径和方式为战胜疫情防控阻击战贡献自己的力量。既有奋战在抗击疫情第一线的医务工作者，穿着防护服坚定地站成一排，形成一道坚实的城墙堡垒，保护着身后人们的生命安全，在前方迎面病毒的威胁，他们不畏惧困难险阻，英勇与病毒展开激烈的争夺，争夺生命、争夺时间，令人感动。又有干群齐心战疫情的驻村的“第一书记”，他们想办法、
                找物资，费尽千辛万苦，为村里群众送去防护口罩和消毒药品。还有“我是党员，我先上”的先锋模范,在抗击疫情的战斗中，
                各级党组织和广大党员干部，夜以继日地值守、宣传、排查……到处都有党员不畏艰难、无私奉献的身影，他们用实际行动践行着共产党员的职责和使命。
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title"> ?回顾抗疫，砥砺前行?</h3>
            <p>
                ?至今中国解决疫情扩散，成为世界之瞩目?
                <br>常常在想，尽管对这个国家有这样那样的抱怨，但我们的确生在了最好的时代
                <br>铭记、感恩
                <br>所有为中华民族浴血奋战的英雄将士
                <br>永垂不朽！！！
            </p>
        </div>
    </li>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <div class="layui-timeline-title"> ?未来?</div>
        </div>
    </li>
</ul>

<hr>



<script type="text/javascript" src="${ctx}/js/community/community.js"></script>
</body>

</html>
