<html>
<style type="text/css">
    /*去掉所以样式*/
    *{margin: 0;padding: 0;}
    body{
        font-family: "微软雅黑";
        font-size: 14px;
        background: url(images/bg.jpg) fixed center center;
    }
    /*	登录盒子*/
    .logo_box{
        width: 280px;
        height: 300px;
        padding: 35px;
        color: #EEE;
        position: absolute;
        left: 50%;
        top:200px;
        margin-left: -175px;
    }
    /*	欢迎提示*/
    .logo_box h3{
        text-align: center;
        height: 30px;
        font: 30px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei",sans-serif;
        color: #FFFFFF;
        height: 20px;
        line-height: 20px;
        padding:0 0 50px 0;
    }
    /*	图片切换*/
    .logo_box h3 img{
        width: 50px;
        height: 50px;
        border-radius: 100% 100%;
    }
    /*	文本框*/
    .input_box{
        height: 46px;
        padding: 0 5px;
        margin-bottom: 20px;
        border-radius: 50px;
        position: relative;
        border: rgba(255,255,255,0.2) 2px solid !important;
    }
    .u_user{
        width: 25px;
        height: 25px;
        background: url(images/1-4.png);
        position: absolute;
        margin: 12px 13px;
    }
    .u_password{
        width: 25px;
        height: 25px;
        background-image: url(images/1-5.png);
        position: absolute;
        margin: 12px 13px;
    }
    /*	设置文本框样式*/
    .text{
        width: 220px;
        height: 46px;
        outline: none;
        display: inline-block;
        margin-left: 50px;
        border: none;
        background: none;
        line-height: 46px;
    }
    .login{
        margin-bottom: 20px
    }
    .login a{
        text-decoration: none;
        outline: none;
    }

    .login-text{
        height: 20px;
        line-height: 20px;
        text-align: center;
        font-size: 20px;
        border-radius: 50px;
        background: #0096e6;
    }

    .submit {
        padding: 15px;
        margin-top: 20px;
        display: block;
    }
    #sub {border:none;background: #0096e6;font-size: 20px;color: #FFFFFF}
</style>
<script type="text/javascript">
    //	用户名切换
    var answer;
    function u_user_onFocus(a){
        var username = document.getElementById('username');
        var h3 = document.getElementById('logo_box_title');
        if(a==''){

        }else if(a=='输入ID或用户名登录'){
            username.value='';
            h3.innerHTML = 'WELCOME';
        }
    }
    function u_user_onBlur(a){
        var username = document.getElementById('username');
        var h3 = document.getElementById('logo_box_title');
        if(a=='') {
            username.value='输入ID或用户名登录';
            h3.innerHTML = 'WELCOME';
        }
        if(a!=''){
            h3.innerHTML = '<img src="images/1-1.jpg"/>'
        }
    }

    //	登录鼠标移入
    function login_over(){
        var sub_login = document.getElementById('sub_login');
        var login_box = document.getElementById('login_box');
        sub_login.style.background = '#5b3954';
        sub_login.style.color = 'white';
        login_box.style.background = '#5b3954';
    }
    //	登录鼠标移出
    function login_out(){
        var sub_login = document.getElementById('sub_login');
        var login_box = document.getElementById('login_box');
        sub_login.style.background = '#0096e6';
        sub_login.style.color = 'black';
        login_box.style.background = '#0096e6';
    }
    //	login_over();
    //	Ajax请求
    var xmlHttp ;
    var flag =false;
    function createXMLHttp(){
        if(window.XMLHttpRequest){
            xmlHttp=new XMLHttpRequest();
        }else{
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    /*注册*/
    function userRegister(){
        createXMLHttp();
        var username = document.getElementById('username');
        var password = document.getElementById('password');
        xmlHttp.open("GET","/user/userRegister?userName="+username.value+'&&userPassWord='+password.value);
        xmlHttp.onreadystatechange = userRegisterCallback ;
        xmlHttp.send(null);
    }
    function userRegisterCallback(){
        if(xmlHttp.readyState==4){
            if((xmlHttp.status>=200&&xmlHttp.status<300) || xmlHttp.status==302){
                var text = xmlHttp.responseText;
                var obj = JSON.parse(text);
                if(obj.code == 1){
                    alert("注册成功");
                }else if(obj.code == 0){
                    alert("注册失败");
                }else{
                    alert(obj.msg+":"+obj.code);
                }
            } else{
                alert("请求错误："+xmlHttp.status);
            }
        }
    }
    /*登录*/
     function checkUserid(){
      createXMLHttp();
      var username = document.getElementById('username');
      var password = document.getElementById('password');
      xmlHttp.open("GET","/user/userLogin?userName="+username.value+'&&userPassWord='+password.value);
      xmlHttp.onreadystatechange = checkUseridCallback.then(function(value)=>{
      	window.location = "index.html"
      });
      xmlHttp.send(null);
     }
     function checkUseridCallback(){
     	const promise = new promise((resolve,reject)=>{
     		if(xmlHttp.readyState==4){
      		if((xmlHttp.status>=200&&xmlHttp.status<300) || xmlHttp.status==302){
      			var text = xmlHttp.responseText;
                var obj = JSON.parse(text);
      			if(obj.code == 1){
     			CookieUtil.set('state',1);
     			resolve('done')
     		}else if(obj.code == 2){
     			CookieUtil.set('state',2);
     			resolve('done')
     		}else{
     			alert(obj.msg+":"+obj.code);
     		}

      		} else{
     		alert("请求错误："+xmlHttp.status);
     	}
     }
     	})
     	return promise;

     }

    function checkForm(){
        return false;
    }
</script>

<body>

<div class="logo_box" style="background:rgba(0,0,0,0.5);">
    <h3 id="logo_box_title">WELCOME</h3>
    <!-- <form action="" name="f" method="post" onsubmit="checkForm()"> -->
    <div class="input_box">
        <span class="u_user"></span>
        <input id="username" name="username" class="text" onFocus="u_user_onFocus(this.value)" onBlur="u_user_onBlur(this.value)" value="输入ID或用户名登录" style="color: #FFFFFF !important" type="text">
    </div>
    <div class="input_box">
        <span class="u_password"></span>
        <input id="password" name="password" class="text"  style="color: #FFFFFF !important" type="password" >
    </div>
    <div id="login_box" class="login login-text submit">
        <input id="sub_login" style="width:200px;height: 40px;margin-top: -70px;border: none;font-size: 25px;background:#0096e6; "  type="button" on value="登录">
    </div>
    <div id="register_box" class="login login-text submit">
        <input id="sub_register" style="width:200px;height: 40px;margin-top: -70px;border: none;font-size: 25px;background:#0096e6; "  type="button" on value="注册"  onclick="userRegister()">
    </div>
    <!-- </form> -->

</div>
</body>
</html>
