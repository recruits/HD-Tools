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

    <script src="${basePath}/js/busi/dict/add_module.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>设备管理</span><span>新增数据类型</span></h2>
</div>
<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-body">
            <form id="dataModelForm" action="${basePath}/dict/addDataModel.json" method="post" target="hiddenFrame">
                <div class="form-group">
                    <label class="col-xs-2 control-label">类型名称<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <input type="text" name="moduleName" class="form-control" placeholder="请输入类型名称" required/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">类型种类<span class="asterisk">*</span></label>
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
                    <label class="col-xs-2 control-label">类型简拼<span class="asterisk">*</span></label>
                    <div class="col-xs-10">
                        <input type="text" name="moduleCode" class="form-control" placeholder="请输入类型简拼" required value=""/>
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
</body>
</html>