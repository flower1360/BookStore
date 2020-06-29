<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
<script type="text/javascript">
	$(function () {
		$("#codeJpg").click(function () {
			var url = "code.jpg?"+Math.random();
			$(this).prop("src",url);
		});
		$(".itxt[name='username']").blur(function () {
			var username = $(".itxt[name='username']").val();
			var regUserName = /^[a-z0-9_-]{5,16}$/;
			if(regUserName.test(username)){
				$.get("UserServlet?method=checkName&username="+username,function (data) {
					$(".errorMsg").text(data);
				});
			}else {
				$(".errorMsg").text("用户名不符合规范");
				return false;
			}
			
		});
		//验证用户名
		$("#sub_btn").click(function () {
			//验证成功提交表单
			var username = $(".itxt[name='username']").val();
			var password = $(".itxt[name='password']").val();
			var repwd = $(".itxt[name='repwd']").val();
			var email = $(".itxt[name='email']").val();
			var code = $(".itxt[name='code']").val();
			//创建正则表达式
			var regUserName = /^[a-z0-9_-]{5,16}$/;
			var regPwd = /^[a-z0-9_-]{5,16}$/;
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]){2,6}$/;
			if(!regUserName.test(username)){
				$(".errorMsg").text("用户名不符合规范");
				return false;
			}
			if(!regPwd.test(password)){
				$(".errorMsg").text("密码不符合规范");
				return false;
			}
			if(password != repwd){
				$(".errorMsg").text("请输入相同的密码");
				return false;
			}
			if(!regEmail.test(email)){
				$(".errorMsg").text("邮箱不符合规范");
				return false;
			}
			if(code == ""){
				$(".errorMsg").text("请输入验证码");
				return false;
			}
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${msg==null?"欢迎注册":msg}
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="method" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" 
										value="${param.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
									   value="${param.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
									<img alt="" src="code.jpg" style="float: right; margin-right: 40px; width: 120px; height: 40px" id="codeJpg">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>