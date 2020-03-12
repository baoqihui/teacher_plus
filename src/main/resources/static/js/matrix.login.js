$(function () {
	/***************************注册********************************/
	$("#btn1").attr("disabled",true);

	$("#regis_tel").blur(function () {
		$("#btn1").attr("disabled",true);
		var len= $(this).val().length;
		if(len==11){
			$("#btn1").removeAttr("disabled");
		}
	})
	//获取验证码
	$("#btn1").bind("click",function(){
		var tel=$("#regis_tel").val();
		$.post("sendMessage.do",{tel:tel},function(result){
			alert(result.resp_msg);
		},"json")

		$("#btn1").attr("disabled",true);
		$("#mes").attr("hidden",true);
		var i=60;
		$("#btn1").html("获取验证码("+i+")");
		var time=setInterval(function () {
			if(i>0){
				i=i-1;
				$("#btn1").html("请("+i+")秒后再试...");
				$("#mes").removeAttr("hidden");
			}else{
				$("#btn1").removeAttr("disabled");
				$("#btn1").html("获取验证码");
				$("#mes").attr("hidden",true);
				clearInterval(time);
			}
		},1000)
	})
	//注册
	$("#regis").bind("click",function(){
		var regis_tel=$("#regis_tel").val();
		var regis_password=$("#regis_password").val();
		var regis_codes=$("#regis_codes").val();
		$.post("/users",{tel:regis_tel,password:regis_password,codes:regis_codes},function(result){
			if (result.resp_code=="0"){
				alert(result.resp_msg);
				location.href="/login.html";
			}else{
				$("#msg").text(result.resp_msg);
			}
		},"json")    })

	/***************************修改密码********************************/
	$("#btn2").attr("disabled",true);

	$("#change_tel").blur(function () {
		$("#btn2").attr("disabled",true);
		var len= $(this).val().length;
		if(len==11){
			$("#btn2").removeAttr("disabled");
		}
	})
	//获取验证码
	$("#btn2").bind("click",function(){
		var tel=$("#change_tel").val();
		$.post("sendMessage.do",{tel:tel},function(result){
			alert(result.resp_msg);
		},"json")

		$("#btn2").attr("disabled",true);
		var i=60;
		$("#btn2").html("获取验证码("+i+")");
		var time=setInterval(function () {
			if(i>0){
				i=i-1;
				$("#btn2").html("请("+i+")秒后再试...");
			}else{
				$("#btn2").removeAttr("disabled");
				$("#btn2").html("获取验证码");
				clearInterval(time);
			}
		},1000)
	})
	//修改密码
	$("#change").bind("click",function(){
		var change_tel=$("#change_tel").val();
		var change_password=$("#change_password").val();
		var change_codes=$("#change_codes").val();
		$.post("/users",{tel:change_tel,password:change_password,codes:change_codes},function(result){
			if (result.resp_code=="0"){
				alert(result.resp_msg);
				location.href="/login.html";
			}else{
				$("#msg2").text(result.resp_msg);
			}
		},"json")    })

	/***************************登录********************************/
	$("#login").bind("click",function(){
		var login_tel=$("#login_tel").val();
		var login_password=$("#login_password").val();
		var code=$("#verify").val();
		$.post("userLogin.do",{tel:login_tel,password:login_password,code:code},function(result){
			if(result.resp_code=="0"){
				$.cookie('id',result.datas.id,{expires:1});
				$.cookie('type',result.datas.type,{expires:1});
				$.cookie('username',result.datas.username,{expires:1});
				location.href="/index/index.html";
			}else{
				$("#span1").text(result.resp_msg);
			}
		},"json")
	})
})






























function showDialog(title,msg) {
	$("#myModal").find(".modal-header h3").html(title);
	$("#myModal").attr('class','modal');
	$("#myModal").find(".modal-body p").html(msg);
	setTimeout(function(){
		$("#myModal").attr('class','modal hide');
	},3000);
}

function keydown(e)
{
	var e = e||event;
	var currKey = e.keyCode||e.which||e.charCode;
	if(currKey == 13)
	{
		checkLogin();
	}
}

function checkLogin(){
	var username = $("input[name=username]").val();
	var password = $("input[name=password]").val();

	if ($.trim(username) == "" ||　$.trim(password) == "") {
		return false;
	}

	$.ajax({
		url:'?act=admin.login',
		data: {
			username : username,
			password : password
		},
		type:'post',
		success:function(data) {
			if (data == 1) {
				window.location.href="?act=index.main";
			}else if(data == -1){
				showDialog("登陆信息","<span style='color:red;'>抱歉，账号和密码错误，登录失败！</span>");
			}else{
				showDialog("登陆信息","<span style='color:red;'>抱歉，你的账号非园长或管理员，不能登录！</span>");
			}
		},
		error : function() {}
	});
}

function validatePwd(str){
	if(str.length!=0){
		reg=/^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$/;
		if(!reg.test(str)){
			return false;
		} else {
			return true;
		}
	}

	return false;
}


$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});
	$('#to_respwd').click(function(){
		$("#loginform").slideUp();
		$("#recoverform2").fadeIn();
	});
	$('#to-login').click(function(){
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});
	$('#to-login2').click(function(){
		$("#recoverform2").hide();
		$("#loginform").fadeIn();
	});
	$("#changePwd").click(function(){

		var username = $("input[name=re_username]").val();
		var password = $("input[name=re_password]").val();
		var confirmpassword = $("input[name=re_confirmpassword]").val();

		if ($.trim(username) == "" ||　$.trim(password) == "" || $.trim(confirmpassword) == "" ) {
			showDialog("重置密码信息","<span style='color:red;'>账号、密码和确认密码项不能为空！</span>");
			return false;
		}

		var flag = validatePwd(password);

		if (!flag || password.length<6) {
			showDialog("重置密码信息","<span style='color:red;'>密码必须为英文字符和数字组合，且不能低于6位！</span>");
			return false;
		}

		if (password != confirmpassword) {
			showDialog("重置密码信息","<span style='color:red;'>密码和确认密码项不一致，请检查！</span>");
			return false;
		}

		$.ajax({
			url:'?act=admin.forgetPassword',
			data: {
				username : username,
				password : password
			},
			type:'post',
			success:function(data) {
				if (data == 1) {
					showDialog("重置密码信息","<span style='color:red;'>你好，密码重置成功，请返回登陆！</span>");
				}else if(data==0){
					showDialog("重置密码信息","<span style='color:red;'>你好，密码重置失败，请稍后重试！</span>");
				}else{
					showDialog("重置密码信息","<span style='color:red;'>你好，参数异常，请重新填写表单！</span>");
				}
			},
			error : function() {
			}
		});
	});

	if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
		$('input[placeholder]').each(function(){
			var input = $(this);

			$(input).val(input.attr('placeholder'));

			$(input).focus(function(){
				if (input.val() == input.attr('placeholder')) {
					input.val('');
				}
			});

			$(input).blur(function(){
				if (input.val() == '' || input.val() == input.attr('placeholder')) {
					input.val(input.attr('placeholder'));
				}
			});
		});
	}

	$("#modelClose").click(function(){
		$("#myModal").attr('class','modal hide');
	});

});