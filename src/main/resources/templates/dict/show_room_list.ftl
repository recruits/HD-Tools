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

    <script src="${basePath}/js/busi/dict/show_room_list.js" type="text/javascript"></script>
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-suitcase"></i> 基础数据 <span>房间管理</span></h2>
</div>
<div class="panel-body">
    <div class="m-5">
        <a class="btn btn-primary btn-xs" href="${basePath}/dict/addRoomInfo.html" target="mainFrame">新增房间</a>
    </div>

    <hr>

    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="roomListTable">
            <thead>
            <tr>

                <th>房间简称</th>
                <th>房间名称</th>
                <th>房间类型</th>
                <th>房间面积</th>
                <th>注释信息</th>
                <th>房间数据</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr class="odd gradeX">
                <td>BZSRBF</td>
                <td>标准双人病房</td>
                <td>病房</td>
                <td>30</td>
                <td>普通病房</td>
                <td><a class="fa fa-search" href="" data-toggle="modal" data-target="#equipmentDetailModal" style="margin-right: 10px; font-size: 22px" title="查看"></a></td>
                <td><a class="fa fa-edit" href="${basePath}/dict/addRoomInfo.html" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑"></a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade " tabindex="-1" role="dialog" id="equipmentDetailModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">房间设备详情</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-4">
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h4 class="panel-title">天花</h4>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">高度</a>
                                    </h5>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="form-group">

                                            <div class="col-sm-9">
                                                <div class="ckbox ckbox-primary">
                                                    <input type="checkbox" id="int_website" value="m" name="int[]" required />
                                                    <label for="int_website">3.0米</label>
                                                </div><!-- rdio -->
                                                <div class="ckbox ckbox-primary">
                                                    <input type="checkbox" value="f" id="int_software" name="int[]" />
                                                    <label for="int_software">2.8米</label>
                                                </div><!-- rdio -->
                                                <div class="ckbox ckbox-primary">
                                                    <input type="checkbox" value="f" id="int_mobile" name="int[]" />
                                                    <label for="int_mobile">2.6米</label>
                                                </div><!-- rdio -->
                                                <div class="ckbox ckbox-primary">
                                                    <input type="checkbox" value="f" id="int_24" name="int[]" />
                                                    <label for="int_24">2.4米</label>
                                                </div><!-- rdio -->
                                                <div class="ckbox ckbox-primary">
                                                    <input type="checkbox" value="f" id="int_other" name="int[]" />
                                                    <label for="int_other">其它</label>
                                                </div><!-- rdio -->
                                                <label class="error" for="int[]"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" class="collapsed" data-parent="#accordion" href="#collapseTwo">吊顶材料</a>
                                    </h5>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" id="int_website1" value="m" name="int[]" required />
                                            <label for="int_website1">吸音矿棉板</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_software1" name="int[]" />
                                            <label for="int_software1">石膏板</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_mobile1" name="int[]" />
                                            <label for="int_mobile1">金属板</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_241" name="int[]" />
                                            <label for="int_241">格栅</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_other1" name="int[]" />
                                            <label for="int_other1">不吊顶</label>
                                        </div><!-- rdio -->
                                        <label class="error" for="int[]"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" class="collapsed" data-parent="#accordion" href="#collapseThree">辅助</a>
                                    </h5>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_mobile2" name="int[]" />
                                            <label for="int_mobile2">输液帘</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_242" name="int[]" />
                                            <label for="int_242">输液轨</label>
                                        </div><!-- rdio -->
                                        <label class="error" for="int[]"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-4">
                        <div class="panel-group" id="accordion-2">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h5 class="panel-title">地面</h5>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion-2" href="#collapse2">地面材料</a>
                                    </h4>
                                </div>
                                <div id="collapse2" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" id="int_website111" value="m" name="int[]" required />
                                            <label for="int_website111">PVC块材</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_software122" name="int[]" />
                                            <label for="int_software122">PVC卷材</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_mobile112" name="int[]" />
                                            <label for="int_mobile112">天然橡胶</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_24113" name="int[]" />
                                            <label for="int_24113">瓷砖</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_other114" name="int[]" />
                                            <label for="int_other114">地毯</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_24115" name="int[]" />
                                            <label for="int_24115">自留坪地面</label>
                                        </div><!-- rdio -->
                                        <div class="ckbox ckbox-primary">
                                            <input type="checkbox" value="f" id="int_other116" name="int[]" />
                                            <label for="int_other116">大理石</label>
                                        </div><!-- rdio -->
                                        <label class="error" for="int[]"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" class="collapsed" data-parent="#accordion-2" href="#collapse3">防潮防水</a>
                                    </h4>
                                </div>
                                <div id="collapse3" class="panel-collapse collapse">
                                    <div class="panel-body">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-4">
                        <div class="panel-group" id="accordion-3">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h5 class="panel-title">墙面及踢脚</h5>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion-3" href="#collapse33">墙面材料</a>
                                    </h4>
                                </div>
                                <div id="collapse33" class="panel-collapse collapse">
                                    <div class="panel-body">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>