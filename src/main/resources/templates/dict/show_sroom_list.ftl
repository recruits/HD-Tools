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

    <script src="${basePath}/js/busi/dict/spec_room_detail.js" type="text/javascript"></script>
    <script src="${basePath}/js/busi/dict/spec_room_param.js" type="text/javascript"></script>
    <script type="text/javascript">
        var currDeptTypeCode;
        var currDeptTypeName;
        var currSpecRoomName;
        var currSpecRoomId = 0;
    </script>

</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>样板房间管理</span></h2>
</div>
<div class="panel-body">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#specRoomDetailPane" data-toggle="tab" id="specRoomDetailTab"><strong>样板房间列表</strong></a></li>
        <li><a href="#specRoomParamPane" data-toggle="tab" id="specRoomParamTab"><strong>样板房间参数</strong></a></li>
    </ul>

    <div class="tab-content mb30">
        <#include "spec_room_detail.ftl">
        <#include "spec_room_param.ftl">
    </div>
</div>

<div class="modal fade " tabindex="-1" role="dialog" id="addSpecRoomDetailModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增样板房间</h4>
            </div>
            <div class="modal-body">
                <form id="saveSpecRoomDetailForm" action="editSpecRoomData.json">
                    <div class="form-group">
                        <label class="col-xs-3 control-label">适用部门类别<span class="asterisk">*</span></label>
                        <div class="col-xs-8">
                            <@cc.select class="selectpicker form-control" name="deptTypeCode" value="${deptTypeCode!}" typeCode="DEPT_TYPE" />
                            <input type="hidden" name="deptTypeName" class="form-control" required value=""/>
                            <input type="hidden" name="action"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">样板房间名称<span class="asterisk">*</span></label>
                        <div class="col-xs-8">
                            <input type="text" name="specRoomName" class="form-control" placeholder="请输入样板房间名称" required
                                   value=""/>
                            <input type="hidden" name="id" class="form-control" required value=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">备注信息<span class="asterisk">*</span></label>
                        <div class="col-xs-8">
                            <input type="text" name="note" class="form-control" placeholder="请输入备注信息" required
                                   value=""/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="resetSpecRoomDetailFormBtn" type="reset" class="btn btn-default btn-xs">重置</button>
                <button id="submitSpecRoomDetailFormBtn" type="submit" class="btn btn-primary btn-xs">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>