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

    <script src="${basePath}/js/busi/dict/show_equip_list.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>设备管理</span></h2>
</div>
<div class="panel-body">
    <div class="m-5">
        <a class="btn btn-primary btn-xs" href="${basePath}/dict/addDataModule.html" target="mainFrame">新增数据类型</a>
        <a class="btn btn-primary btn-xs" href="${basePath}/dict/addModuleEnum.html" target="mainFrame">新增数据参数</a>
        <a class="btn btn-primary btn-xs" href="${basePath}/dict/addEnumParam.html" target="mainFrame">新增参数属性</a>
    </div>

    <hr>

    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="roomDataModule">
            <thead>
            <tr>
                <#--<th>类型编号</th>-->
                <th>类型简称</th>
                <th>类型名称</th>
                <#--<th>类型分类</th>-->
                <th>参数简称</th>
                <th>参数名称</th>
                <th>参数顺序</th>
                <th>参数规格</th>
                <th>操作</th>
            </tr>
            </thead>
            <!--
            <tbody>
            <tr class="odd gradeX">
                <td>TH</td>
                <td>天花</td>
                <td>装修装饰</td>
                <td>GD</td>
                <td>高度</td>
                <td>3.0m,2.8m,2.6m,2.4m,其它</td>
                <td><a class="fa fa-edit" href="${basePath}/core/editItem.html" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a><a class="fa fa-trash-o" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a></td>
            </tr>
            <tr class="even gradeC">
                <td>TH</td>
                <td>天花</td>
                <td>装修装饰</td>
                <td>DDCL</td>
                <td>吊顶材料</td>
                <td>吸音矿棉板,石膏板,金属板,格栅,不吊顶</td>
                <td><a class="fa fa-edit" href="${basePath}/core/editItem.html" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a><a class="fa fa-trash-o" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a></td>
            </tr>
            <tr class="even gradeC">
                <td>TH</td>
                <td>天花</td>
                <td>装修装饰</td>
                <td>FZ</td>
                <td>辅助</td>
                <td>输液帘,输液轨</td>
                <td><a class="fa fa-edit" href="${basePath}/core/editItem.html" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a><a class="fa fa-trash-o" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a></td>
            </tr>
            </tbody>
            -->
        </table>
    </div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">新增数据模块</h4>
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
                <!--
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="addDataModel()">提交</button>
                </div>
                -->
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        <iframe name="targetIfr" style="display:none"></iframe>
    </div>
</div>
</body>
</html>