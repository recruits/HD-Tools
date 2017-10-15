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

    <script src="${basePath}/js/busi/dict/add_enum_param.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>设备管理</span><span>新增参数规格</span></h2>
</div>
<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-body">
        <form id="dataEnumParamForm" action="${basePath}/dict/addEnumParam.json" method="post" target="hiddenFrame">
            <div class="form-group">
                <label class="col-xs-2 control-label">类型名称<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <select class="form-control" id="moduleId" name="moduleId">
                    <#list moduleSimpList as module>
                        <option value="${module.id}">${module.moduleName}</option>
                    </#list>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">参数名称<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <select class="form-control" id="enumId" name="enumId">
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">规格名称<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="text" name="enumParamName" class="form-control" placeholder="请输入规格名称" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">规格简拼<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="text" name="enumParamCode" class="form-control" placeholder="请输入规格简拼" required value=""/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">规格顺序<span class="asterisk">*</span></label>
                <div class="col-xs-10">
                    <input type="number" name="orderIdx" class="form-control" placeholder="请输入规格顺序" required value=""/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6 col-xs-offset-6">
                    <button type="reset" class="btn btn-primary btn-xs">重置</button>
                    <button type="submit" class="btn btn-primary btn-xs">提交</button>
                </div>
            </div>
        </form>
        <iframe name="hiddenFrame" style="display:none"></iframe>
        </div>
    </div>
</div>
</body>
</html>