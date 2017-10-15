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

    <script src="${basePath}/js/busi/dict/show_dept_list.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>科室管理</span></h2>
</div>
<div class="panel-body">
    <div class="m-5">
        <a class="btn btn-primary btn-xs" href="${basePath}/dict/addDeptInfo.html" target="mainFrame">新增科室</a>
    </div>

    <hr>

    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="deptListTable">
            <thead>
            <tr>
                <th>科室编码</th>
                <th>科室名称</th>
                <th>所属部门</th>
                <th>科室面积</th>
                <th>面积系数</th>
                <th>建筑面积</th>
                <th>注释信息</th>
                <th>房间管理</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr class="odd gradeX">
                <td>I</td>
                <td>第一住院部</td>
                <td>住院部</td>
                <td>200</td>
                <td>1.2</td>
                <td>200</td>
                <td></td>
                <th>
                    <a class="fa fa-edit" href="${basePath}/dict/addAreaInfo.html" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="增加区域"></a>
                    <a class="fa fa-search" href="" data-toggle="modal" data-target="#areaDetailModal" style="margin-right: 10px; font-size: 22px" title="查看区域"></a>
                </th>
                <td><a class="fa fa-edit" href="${basePath}/dict/addDeptInfo.html" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑"></a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade " tabindex="-1" role="dialog" id="areaDetailModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">[ 第一住院部 ]科室区域信息</h4>
            </div>
            <div class="modal-body">
                <table id="areaDetailTable" class="table tree table-bordered table-striped table-condensed">
                    <tr class="treegrid-0">
                        <td>序号</td><td>房间/区域名称</td><td>数量</td><td>净使用面积</td><td>使用面积小计</td><td>注释</td><td>操作</td>
                    </tr>
                    <tr class="treegrid-1">
                        <td>I01.1</td><td>接待等候及公共空间</td><td></td><td></td><td></td><td>大厅、商业、社会化餐厅</td><td></td>
                    </tr>
                    <tr class="treegrid-parent-1 treegrid-101">
                        <td>I01.1.01</td><td>等候区</td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-parent-1 treegrid-101">
                        <td>I01.1.02</td><td>接待</td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-parent-1 treegrid-101">
                        <td>I01.1.03</td><td>无性别厕所</td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-parent-1 treegrid-101">
                        <td>I01.1.04</td><td>阳光活动室</td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-2">
                        <td>I01.2</td><td>病房区</td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-parent-2 treegrid-201">
                        <td>I01.2.01</td><td>双床病房</td><td></a></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-parent-2 treegrid-202">
                        <td>I01.2.02</td><td>VIP病房</td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                    <tr class="treegrid-parent-2 treegrid-203">
                        <td>I01.2.03</td><td></td><td></td><td></td><td></td><td></td><td></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>