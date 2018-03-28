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

    <script src="${basePath}/js/busi/dict/show_sequip_list.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>设备管理</span></h2>
</div>
<div class="panel-body">
    <div class="m-5">
        <a class="btn btn-primary btn-xs" href="javascript:void(0);" id="addNewRoomDataParams">新增设备参数</a>
        <a class="btn btn-primary btn-xs" href="javascript:void(0);" id="modNewRoomDataParams">修改设备参数</a>
    </div>

    <hr>

    <div class="table-responsive">
        <table class="table table-bordered" id="specRoomDataTable">
            <thead>
                <tr>
                    <th>设备编号</th>
                    <th>设备顺序</th>
                    <th>设备名称</th>
                    <th>模块编号</th>
                    <th>顺序</th>
                    <th>模块名称</th>
                    <th>选中方式</th>
                    <th>参数编号列表</th>
                    <th>参数名称</th>
                    <th>操作</th>
                </tr>
            </thead>
        </table>
    </div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="specRoomDataModal" tabindex="-1" role="dialog" aria-labelledby="specRoomDataModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="specRoomDataModalLabel"></h4>
                </div>
                <div class="modal-body">
                    <form id="specRoomDataParamForm" action="${basePath}/dict/addSpecRoomParam.json" method="post">
                        <div class="form-group">
                            <label class="col-xs-2 control-label">设备类型<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <@cc.select class="selectpicker form-control" name="moduleId" value="1001" typeCode="ROOM_MODULE" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 control-label">模块名称<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <input type="hidden" name="enumId">
                                <input type="hidden" name="oldEnumName">
                                <input type="text" name="enumName" class="form-control" placeholder="请输入模块名称" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 control-label">模块顺序<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <input type="number" min="1" name="enumIdx" class="form-control" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 control-label">选中方式<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <@cc.select class="selectpicker form-control" name="selType" typeCode="SELECT_TYPE" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 control-label">模块参数&nbsp;</label>
                            <div class="col-xs-10">
                                <div class="input-group input-group-xs">
                                    <input type="text" name="singleParamName" class="form-control" aria-describedby="sizing-addon1">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button" id="addSpecRoomParamBtn">增加参数</button>
                                        <#--<button class="btn btn-default" type="button" id="delSpecRoomParamBtn">全部删除</button>-->
                                    </span>
                                </div>
                                <div class="x_panel">
                                    <div class="x_content" style="display: block;">
                                        <table id="specRoomParamTable" class="table">
                                            <thead>
                                                <tr>
                                                    <th>参数顺序</th>
                                                    <th>参数名称</th>
                                                    <th>删除参数</th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-default btn-xs" id="specRoomDataParamResetBtn">重置</button>
                    <button type="submit" class="btn btn-primary btn-xs" id="specRoomDataParamSubmitBtn">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>