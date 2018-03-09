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
        <a class="btn btn-primary btn-xs" href="${basePath}/dict/addDataModule.html" target="mainFrame">新增数据</a>
    </div>

    <hr>

    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="specRoomDataTable">
            <thead>
                <tr>
                    <th>设备编号</th>
                    <th>设备顺序</th>
                    <th>设备名称</th>
                    <th>模块编号</th>
                    <th>模块顺序</th>
                    <th>模块名称</th>
                    <th>参数选中方式</th>
                    <th>参数编号列表</th>
                    <th>参数名称列表</th>
                    <th>调整顺序</th>
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
                    <h4 class="modal-title" id="myModalLabel">新增模块参数</h4>
                </div>
                <div class="modal-body">
                    <form id="dataModelForm" action="${basePath}/dict/addDataModel.json" method="post" target="targetIfr">
                        <div class="form-group">
                            <label class="col-xs-2 control-label">模块名称<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <input type="text" name="moduleName" class="form-control" placeholder="请输入模块名称" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 control-label">模块分类<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <!--<input type="text" name="modelType" class="form-control" placeholder="请选择模块分类" required value=""/>-->
                                <select class="selectpicker form-control" name="moduleType" data-style="btn-default">
                                    <option value="1" >装饰装修</option>
                                    <option value="2" >设备</option>
                                    <option value="3" >耗材</option>
                                    <option value="9" >其它</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 control-label">模块简拼<span class="asterisk">*</span></label>
                            <div class="col-xs-10">
                                <input type="text" name="nameCode" class="form-control" placeholder="请输入模块简拼" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-4 col-xs-offset-8">
                                <button type="button" class="btn btn-default btn-xs" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary btn-xs">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <iframe name="targetIfr" style="display:none"></iframe>
    </div>
</div>
</body>
</html>