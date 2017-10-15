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

    <script src="${basePath}/js/busi/dict/add_area_info.js" type="text/javascript"></script>
</head>

<body class="stickyheader">
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>科室管理</span><span>新增区域</span></h2>
</div>
<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-body">
            <form id="dataModelForm" action="" method="post" target="hiddenFrame">
                <div class="form-group">
                    <label class="col-xs-2 control-label">区域名称<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <input type="text" name="areaName" class="form-control" placeholder="请输入区域名称" required/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">区域顺序<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <input type="text" name="areaCode" class="form-control" placeholder="请输入房间简拼" required value=""/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">区域面积<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <input type="text" name="regionArea" class="form-control" placeholder="请输入房间面积" required value=""/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">区域数量<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <input type="text" name="areaNum" class="form-control" placeholder="请输入注释信息" required value=""/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">房间信息<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <div class="input-group input-group">
                            <input type="text" name="roomInfos" class="form-control" placeholder="请选择房间信息" required value="" readonly/>
                            <span class="input-group-addon" data-toggle="modal" data-target="#chooseRoomModal" id="chooseRoomBtn">选择</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-6 col-xs-offset-6">
                        <button id="resetBtn" type="reset" class="btn btn-primary btn-xs">重置</button>
                        <button id="submitBtn" type="submit" class="btn btn-primary btn-xs">提交</button>
                    </div>
                </div>
            </form>
            <iframe name="hiddenFrame" style="display:none"></iframe>
        </div>
    </div>
</div>

<div class="modal fade " tabindex="-1" role="dialog" id="chooseRoomModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择房间</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="40">等候区</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="38">接待区</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="42">无性别厕所</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="43">阳光活动室</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="35">开水配餐间</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="44">双床病房</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="rooms" value="32">VIP病房</label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>