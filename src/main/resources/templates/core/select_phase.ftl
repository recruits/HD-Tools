<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="images/favicon.png" type="image/png">

    <#include "../common/header.ftl">

    <script type="text/javascript">
        var itemName = "${itemName}";

        $(function () {
            var linkUrl = "${basePath}/core/addOrEditItem.json";
            var inParam = "?action=add&projId=-1&projPhase=";

            $('#goNextBtn').bind('click', function () {
                linkUrl += inParam + $('select[name="projPhase"]').val();
                $(this).attr("href", linkUrl);
            });
        });
    </script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-edit"></i> 项目管理 <span>项目维护</span><span>选择阶段</span></h2>
</div>
<div class="panel-body mb30">
    <div class="panel panel-default">
        <div class="panel-body">
            <form id="phaseForm" action="editItem.html">
                <div class="form-group">
                    <label class="col-xs-2 control-label">项目阶段<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                    <@cc.select class="selectpicker form-control" name="projPhase" value="${projPhase!}" typeCode="PROJ_PHASE" />
                        <!--
                    <select class="selectpicker form-control" name="projPhase" data-style="btn-default">
                        <option value="0" >概念阶段</option>
                        <option value="1" >方案阶段</option>
                        <option value="2" >设计阶段</option>
                        <option value="3" >施工阶段</option>
                    </select>
                    -->
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-4 col-xs-offset-2">
                        <a class="btn btn-primary btn-xs" href="${basePath}/core/selectItem.html"
                           target="mainFrame">返回</a>
                        <a class="btn btn-primary btn-xs" href="javascript:void(0)" id="goNextBtn" target="mainFrame">下一步</a>
                    </div>
                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>