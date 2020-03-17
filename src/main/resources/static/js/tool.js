$(function () {
    var CLASS_NAME=$("#span1").attr("className");
    var cuId=$.cookie("cuId");
    $("#cuId").val(cuId);
    $("#cuId2").val(cuId);
    //退出
    $('#backid').click(function(){window.location.href="index.html";});
    //导入
    $("#leadIn").click(function () {
        var r = confirm("* 导入excel应确保格式正确	\n"+
            "* 如不确定请先点击‘保存为Excel’查看模板格式 \n"+
            "\t\t\t您是否确定导入？")
        if (r == true) {leadIn(CLASS_NAME);}
    })
    //删除
    $("#remove").live("click",function() {
        var r = confirm("\t确定删除？")
        if (r == true) {
            var eid = $(this).attr("eid");
            $.ajax({
                url:"../"+CLASS_NAME+"/"+ eid,
                type:"delete",
                data:{},
                dataType:"json",
                success:function (result) {
                    if (result.resp_code == "0") {
                        alert(result.resp_msg);
                        window.location.href = ""+CLASS_NAME+".html";
                    }else {
                        $("#span1").text(result.resp_msg);
                    }
                }
            })
        }
    })
})

//导入函数
function leadIn(className) {
    var CLASS_NAME=className;
    if ($("#excel").val()==""){
        alert("请先选择文件!");
    }else {
        var excel=$("#excel")[0].files[0];
        var formData = new FormData();
        formData.append("excel",excel);
        formData.append("cuId",$.cookie("cuId"));
        $.ajax({
            url:"/"+CLASS_NAME+"/leadIn",
            dataType:'json',
            type:'POST',
            async: false,
            data: formData,
            processData : false, // 使数据不做处理
            contentType : false, // 不要设置Content-Type请求头
            success: function(result){
                console.log(result);
                if (result.resp_code == '0') {
                    alert(result.resp_msg);
                    window.location.href=""+CLASS_NAME+".html";
                }else {
                    $("#span1").text(result.resp_msg);
                }
            },
            error:function(response){
                console.log(response);
            }
        });
    }
}
//添加函数
function add(className) {
    var CLASS_NAME=className;
    console.log($('#form1').serializeJson())
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "../"+CLASS_NAME+"",
        contentType: "application/json",
        data:JSON.stringify($('#form1').serializeJson()),
        success: function (result) {
            console.log("data is :" + result)
            if (result.resp_code == "0") {
                alert(result.resp_msg);
                window.location.href=""+CLASS_NAME+".html";
            }else {
                $("#span1").val(result.resp_msg);
            }
        }
    });
}
//form->json
(function (window, $) {
    $.fn.serializeJson = function () {//form serialize to json
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(
            function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };
})(window, jQuery);
//调用加载表单
function tolLadData(className) {
    var CLASS_NAME=className;
    //获取json数据
    $.get("/"+CLASS_NAME+"/"+$.cookie('cuId'),{},function(result){
        if (result.resp_code=="0"){
            loadData(result.datas);
        }else{
            $("#span1").text(result.resp_msg);
        }
    },"json")
}
//加载前端数据到表单
function loadData(obj){
    if($.type(obj)!="object"){
        return false;
    }
    var key,value,tagName,type,arr;
    for(x in obj){
        key = x;
        value = obj[x];
        $("[name='"+key+"'],[name='"+key+"[]']").each(function(index){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName=='IMG'){
                $(this).attr('src',value);
            }
            if(tagName=='INPUT'){
                if(type=='radio'){                      //处理radio
                    $(this).attr('checked',$(this).val()==value);
                }else if(type=='checkbox'){             //处理checkbox
                    for(var i =0;i<value.length;i++){
                        if($(this).val()==value[i]){
                            $(this).attr('checked',true);
                            break;
                        }
                    }
                }else if(type=='date'){                  //处理日期型表单
                    if(parseInt(value)>1000000000000)      //毫秒时间戳
                        $(this)[0].valueAsNumber=parseInt(value);
                    else if(parseInt(value)>1000000000)    //秒时间戳
                        $(this)[0].valueAsNumber=parseInt(value)*1000;
                    else                                   //字符串时间
                        $(this)[0].valueAsDate=new Date(value);
                }else{
                    if($.isArray(value))                    //表单组情形(多个同名表单)
                        $(this).val(value[index]);
                    else
                        $(this).val(value);
                }
                $(this).uniform(); //自用！
            }else if(tagName=='SELECT'){    //处理select和textarea
                $(this).val(value);
            }
        });
    }
}
//上传图片
function upLoadImg(){
    $("#m_image_addr").live("change",function(){
        //注意这里不能写错。。。
        var file=$("#m_image_addr")[0].files[0];
        var formData = new FormData();
        formData.append("m_image_addr",file);
        //对文件类型进行判断
        var index=file.name.lastIndexOf(".");
        var type=file.name.substring(index);
        if(type!=".jpg"&&type!=".png"){
            alert("只能上传jpg和png格式的图片！！");
            return;
        }
        $.ajax({
            url:"../upload.do",
            data:formData,
            dataType:"text",
            type:"post",
            //这两个属性必须设置！！！！
            contentType: false,
            processData: false, //设置为true时，ajax提交的时候不会序列化data，而是直接使用data
            success:function (path) {
                $("#yulan").attr("src",path);
                $("input[name='imgUrl']").attr("value",path);
            }
        })
    })
}

/**
 * 1. form表单 id="form1"
 * 2. <span id="span1" style="color: red;" className="education"></span> span1的className属性必须设置
 *
 * */