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

    <script src="${basePath}/js/busi/core/edit_item.js" type="text/javascript"></script>
    <script src="${basePath}/js/busi/core/pane_base_info.js" type="text/javascript"></script>
    <script src="${basePath}/js/busi/core/pane_sumy_info.js" type="text/javascript"></script>
    <script src="${basePath}/js/busi/core/pane_dept_info.js" type="text/javascript"></script>

    <script type="text/javascript">
        // 项目编号
        var projId = 0;
        // 部门分类编号
        var currDeptTypeId = 0;
        // 部门汇总编号
        var currDeptSumyId = 0;
        // 部门信息编号
        var currDeptId = 0;
        // 区域信息编号
        var currAreaId = 0;
        // 房间信息编号
        var currRoomId = 0;
        // 部门信息操作
        var deptAction = CONST_ACTION_ADD;
        // 区域信息操作
        var areaAction = CONST_ACTION_ADD;
        // 房间信息操作
        var roomAction = CONST_ACTION_ADD;
        // 操作类型
        var action = "${action}";
        // 项目名称
        var itemName = "${itemName}";

        $(function () {
            if (action === CONST_ACTION_ADD) {
                <#--$('input[name="note"]').val("${projId}");-->
                <#--$('input[name="groupId"]').val("${groupId}");-->
                <#--$('input[name="verId"]').val("${verId}");-->
                <#--$('input[name="verInfo"]').val("${verInfo}");-->
                $('select[name="projPhase"]').val("${projPhase}");
            }

            projId = "${projId}";
        });
    </script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-edit"></i> 项目管理 <span>项目维护</span><span>项目编辑</span></h2>
</div>
<div class="panel-body">
    <div>
        <ol class="breadcrumb navTitleClass" id="itemNavTitle">
            <#--<li><i class="fa fa-star"></i> 聚丰集团重庆五院A1A2项目 -康复医院</li>
            <li>住院部</li>
            <li>双人病房</li>-->
        </ol>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#baseInfo" data-toggle="tab"><strong>基本信息</strong></a></li>
        <li><a href="#summaryInfo" data-toggle="tab" id="summaryInfoTab"><strong>汇总信息</strong></a></li>
        <li><a href="#deptDetailInfo" data-toggle="tab" id="deptDetailInfoTab"><strong>部门信息</strong></a></li>
        <li><a href="#roomDataInfo" data-toggle="tab" id="roomDataInfoTab"><strong>房间信息</strong></a></li>
    </ul>

    <div class="tab-content mb30">
        <#include "pane_base_info.ftl">
        <#include "pane_sumy_info.ftl">
        <#include "pane_dept_info.ftl">
        <#include "pane_room_info.ftl">
    </div>
</div>
</body>
</html>