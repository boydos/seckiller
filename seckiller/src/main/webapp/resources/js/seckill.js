
var seckill = {

    URL : {
        now : function () {
            return "/seckill/time/now";
        },
        exposer : function (seckillerId) {
            return "/seckill/" + seckillerId + "/exposer" ;
        },
        execution : function (seckillerId,md5) {
            return "/seckill/" + seckillerId + "/" +md5 + "/execution"
        }
    },



    detail : {
        init : function (params) {
            var userPhone = $.cookie("killPhone");
            var seckillId = params["seckillId"];
            var startTime = params["startTime"];
            var endTime = params["endTime"];

            if(!seckill.detail.checkPhone(userPhone)) {
                seckill.detail.showInputPhone();
                return;
            }

            $.get(seckill.URL.now(),{},function (result) {
                if(result && result["success"]) {
                    var now = new Date(result["data"]);
                    seckill.detail.checkTime(seckillId,now.getTime(),startTime,endTime);
                } else {
                    console.info("result={}",result);
                }
            });

        },
        checkPhone : function (phone) {
            if(phone != null && phone.length == 11 && !isNaN(phone)) {
                return true;
            }
            return false;
        },
        checkTime : function (seckillId,now,start,end) {
            var panel = $("#killInfoKey");
            if(now > end ) {
                panel.html("秒杀活动已经结束")
            } else if(now < start) {
                var killTime = start +　1000;
                panel.countdown(killTime,function (event) {
                    panel.html(event.strftime("%D天 %H小时 %M分钟 %S秒"))
                }).on("finish.countdown",function (event) {
                    seckill.detail.killProduct(seckillId,panel);
                });
            }　else {
                seckill.detail.killProduct(seckillId,panel);
            }
        },
        showInputPhone : function () {
            var dialog = $("#myModal");
            dialog.modal({
                show : true,
                keyboard : false,
                backdrop : "static"
            });
            $("#submitBtn").click(function () {
                var phoneNumber = $("#phoneKey").val();
                if(seckill.detail.checkPhone(phoneNumber)) {
                    $.cookie("killPhone",phoneNumber,{expires:1,path:"/seckill"});
                    window.location.reload();
                } else {
                    $("#killMessage").hide().html("手机号码不正确").show(300);
                }
            });
        },
        killProduct : function(seckillId,node) {
            node.hide().html('<button id="killBtnKey" class="btn btn-primary btn-lg">开始秒杀</button>');
            $.post(seckill.URL.exposer(seckillId),{},function (result) {
                if( result && result["success"]) {
                    var exposer = result["data"];
                    if(exposer["expose"]) {
                        var md5 = exposer["md5"];
                        var killUrl = seckill.URL.execution(seckillId,md5);

                        $("#killBtnKey").one("click",function () {
                            $.post(killUrl,{},function (result) {
                                console.info("kill-result",result);
                                if(result && result["success"]) {
                                    var killResult = result['data'];
                                    var state = killResult['state'];
                                    var stateInfo = killResult["stateInfo"];
                                    node.html('<label class="label label-success">'+stateInfo+'</label>')

                                }
                            });
                        });

                    } else {
                        seckill.detail.checkTime(seckillId,exposer["now"],exposer["start"],exposer["end"]);
                    }
                }
            });
            node.show();
        }
    }

}