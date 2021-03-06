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

    <script src="${basePath}/js/busi/core/select_item.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-edit"></i> 项目管理 <span>项目维护</span></h2>
</div>
<div class="panel-body">
    <div class="m-5">
        <a class="btn btn-primary btn-xs" href="${basePath}/core/selectPhase.html?action=add&itemName=" target="mainFrame">新增项目</a>
        <#--<a class="btn btn-primary btn-xs" href="" target="mainFrame">复制项目</a>-->
    </div>

    <hr>

    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="proj_info_datatable">
            <thead>
                <tr>
                    <th>项目编号</th>
                    <th>项目名称</th>
                    <th>项目区域</th>
                    <th>项目类型</th>
                    <th>项目阶段</th>
                    <th>创建时间</th>
                    <th>版本信息</th>
                    <th>操作</th>
                </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>