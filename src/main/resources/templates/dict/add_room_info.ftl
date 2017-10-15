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

    <script src="${basePath}/js/busi/dict/add_room_info.js" type="text/javascript"></script>
</head>

<body class="stickyheader">
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>房间管理</span><span>新增房间</span></h2>
</div>
<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-body">
        <form id="dataModelForm" action="" method="post" target="hiddenFrame">
            <div class="form-group">
                <label class="col-xs-2 control-label">房间名称<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="text" name="roomName" class="form-control" placeholder="请输入房间名称" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">房间类型<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <!--<input type="text" name="modelType" class="form-control" placeholder="请选择模块分类" required value=""/>-->
                    <select class="selectpicker form-control" name="roomType" data-style="btn-default">
                        <option value="1" >普通病房</option>
                        <option value="2" >重症病房</option>
                        <option value="3" >体能测试</option>
                        <option value="4" >检验化验</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">房间简拼<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="text" name="roomCode" class="form-control" placeholder="请输入房间简拼" required value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">房间面积<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="text" name="roomArea" class="form-control" placeholder="请输入房间面积" required value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">注释信息<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="text" name="noteInfo" class="form-control" placeholder="请输入注释信息" required value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">房间设备<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <div class="input-group input-group">
                        <input type="text" name="equipmentInfos" class="form-control" placeholder="请选择房间设备" required value="" readonly/>
                        <span class="input-group-addon" data-toggle="modal" data-target="#selectEquipmentModal" id="chooseEquipmentBtn">选择</span>
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

<div class="modal fade " tabindex="-1" role="dialog" id="selectEquipmentModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择房间设备</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="40">地面</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="38">电气及照明</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="42">非医疗设备</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="43">固定家具</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="35">给水</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="44">活动家具</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="32">门窗</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="34">屏蔽</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="36">排水</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="31">墙面及踢脚</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="21">弱电信息及物流系统</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="22">通风空调系统</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="39">天花</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments" value="33">五金</label>
                            </div>
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="37">医疗气体</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label class="checkbox-inline"><input type="checkbox" name="equipments"
                                                                      value="41">医疗设备及显示屏</label>
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