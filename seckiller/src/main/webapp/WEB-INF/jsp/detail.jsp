<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
<head>
    <title>秒杀详情页面</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>${seckiller.name}</h2>
            </div>
            <div class="panel-body">
                <h2 class="text-danger text-center">
                    <i class="glyphicon glyphicon-time"></i>
                    <span class="glyphicon" id="killInfoKey"></span>
                </h2>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center" id="myModalLabel">
                        <i class="glyphicon glyphicon-phone"></i>填写用户信息
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input id="phoneKey" type="text" class="form-control" placeholder="手机号码"/>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <div id="killMessage" class="glyphicon text-danger"></div>
                    <button id="submitBtn" type="button" class="btn btn-info">
                        <i class="glyphicon glyphicon-cog"></i>
                        确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
<%@include file="common/foot.jsp"%>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js" type="text/javascript"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js" type="text/javascript"></script>
<script src="/resources/js/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    seckill.detail.init({
       seckillId:${seckiller.seckillerId},
       startTime:${seckiller.startTime.time},
       endTime:${seckiller.endTime.time}
    });
</script>
</html>
