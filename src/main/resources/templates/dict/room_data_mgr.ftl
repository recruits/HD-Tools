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
        var selModuleId = 0;
        var selEnumId = 0;

        $(document).ready(function () {
            // 数据模块表格
            var dataModuleTab = $('#dataModule').DataTable({
                "destroy": true,
                "oLanguage": dt_lang_cfg,
                "bRetrieve": true,
                "paging":   true,
                "ordering": false,
                "info":     true,
                "searching":true,
                "ajax":     "dataModuleSimp.json",
                "columns": [
                    /**
                    {
                        "data": null,
                        "render": function (data, type, row) {    //列渲染
                            return '<label class="position-relative">' +
                                    '<input type="checkbox" class="ace" value="' + data + '"/>' +
                                    '<span class="lbl"></span>' +
                                    '</label>';
                        }
                    },
                    {
                        "data": function (row, type, set, meta) {
                            var c = meta.settings._iDisplayStart + meta.row + 1;
                            return c;
                        }
                    },
                     **/
                    { "data": "id" },
                    { "data": "moduleName" },
                        /**
                    { "data": "moduleType" },
                         **/
                    { "data": "moduleCode" }
                ],

                "columnDefs": [
                    {
                        "targets": [ 0 ],
                        "visible": false,
                        "searchable": false
                    }
                ],
                "order" : [ [ 0, 'asc' ] ]
            });

            // 数据模块分类表格
            var moduleEnumTabOptions = {
                "destroy": true,
                "oLanguage": dt_lang_cfg,
                "bRetrieve": true,
                "paging":   true,
                "ordering": false,
                "info":     true,
                "searching":true,
                "columns": [
                    /**
                    { "data": function(row, type, set, meta) {
                        var c = meta.settings._iDisplayStart + meta.row + 1;
                        return c;
                    }},
                     **/
                    { "data": "id" },
                    { "data": "enumName" },
                    { "data": "enumCode" },
                    { "data": "orderIdx" }
                ],
                "columnDefs": [
                    {
                        "targets": [ 0 ],
                        "visible": false,
                        "searchable": false
                    }
                ],
                "order" : [ [ 0, 'asc' ] ]
            };
            //对行单击添加监听事件
//            $('#dataModule tbody').on('click', 'tr', function () {
//                var tr = $(this).closest('tr');
//                var checkbox = tr.find('td:first-child input:checkbox')[0];
//                checkbox.checked = !checkbox.checked;
//            });
            //对行双击添加监听事件
            $('#dataModule tbody').on('dblclick', 'tr', function () {
                var currData = dataModuleTab.row(this).data();
                selModuleId = currData.id;
                $('#moduleEnum').DataTable().ajax.url("moduleEnumSimp.json?moduleId="+selModuleId).load();
            });
            var moduleEnumTab = $('#moduleEnum').DataTable(moduleEnumTabOptions);

            // 数据模块分类参数表格
            var enumParamTabOptions = {
                "destroy": true,
                "oLanguage": dt_lang_cfg,
                "bRetrieve": true,
                "paging":   true,
                "ordering": false,
                "info":     true,
                "searching":true,
                "columns": [
                    { "data": function(row, type, set, meta) {
                        var c = meta.settings._iDisplayStart + meta.row + 1;
                        return c;
                    }},
                    { "data": "id" },
                    { "data": "enumName" },
                    { "data": "enumCode" },
                    { "data": "orderIdx" }
                ],
                "columnDefs": [
                    {
                        "targets": [ 1 ],
                        "visible": false,
                        "searchable": false
                    }
                ],
                "order" : [ [ 0, 'asc' ] ]
            };

            //对行双击添加监听事件
            $('#moduleEnum tbody').on('dblclick', 'tr', function () {
                var currData = moduleEnumTab.row(this).data();
                selEnumId = currData.id;
                $('#enumParam').DataTable().ajax.url("enumParamSimp.json?moduleId="+selModuleId+"&enumId="+selEnumId).load();
            });
            var moduleEnumTab = $('#enumParam').DataTable(enumParamTabOptions);

        });

        $(function () {
            $('#btn_add_modal').bind('click', function () {
                $('#addModal_modal').modal();
            });
        });
    </script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>房间数据管理</span></h2>
</div>
<div class="panel-body">
    <div class="row  m-5" >
        <div class="col-sm-4">
            <div class="btn-group">
                <button id="btn_add_modal" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
                <button id="btn_edit" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
                <button id="btn_delete" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
            </div>
            <div class="table-responsive">
                <table id="dataModule" class="table table-striped table-bordered compact dataTable" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <!--
                        <th class="center">
                            <label class="position-relative">
                                <input type="checkbox" class="ace"/>
                                <span class="lbl"></span>
                            </label>
                        </th>
                        -->
                        <!--
                        <th>序号</th>
                        -->
                        <th>模块编号</th>
                        <th>模块名称</th>
                        <!--<th>模块类型</th>-->
                        <th>模块简称</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-sm-4">
            <div id="toolbar2" class="btn-group">
                <button id="btn_add" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
                <button id="btn_edit" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
                <button id="btn_delete" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
            </div>
                <div class="table-responsive">
                    <table id="moduleEnum" class="table table-striped table-bordered compact dataTable" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <!--
                            <th>序号</th>
                            -->
                            <th>分类编号</th>
                            <th>分类名称</th>
                            <th>分类简称</th>
                            <th>分类顺序</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
        </div>
        <div class="col-sm-4">
            <div id="toolbar3" class="btn-group">
                <button id="btn_add" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
                <button id="btn_edit" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
                <button id="btn_delete" type="button" class="btn btn-primary btn-xs">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
            </div>
                <div class="table-responsive">
                    <table id="enumParam" class="table table-striped table-bordered compact dataTable" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>参数名称</th>
                            <th>参数值</th>
                            <th>参数顺序</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
        </div>
    </div>

    <div id="addModal_modal" class="modal fade">
        <form id="dataModelForm" action="${basePath}/dict/addDataModel.json" method="post" target="hiddenFrame">
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
                    <input type="text" name="moduleCode" class="form-control" placeholder="请输入模块简拼" required value=""/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6 col-xs-offset-6">
                    <button id="resetBtn" type="reset" class="btn btn-primary btn-xs">重置</button>
                    <button id="submitBtn" type="submit" class="btn btn-primary btn-xs">提交</button>
                </div>
            </div>
        </form>
        <!--
        <iframe name="hiddenFrame" style="display:none"></iframe>
        -->
    </div>
</div>
</body>
</html>