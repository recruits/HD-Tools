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

    <title>管理系统</title>

    <#include "../common/header.ftl">

    <style>
        body,html{
            height:100%;
        }
        /*左侧菜单滚动条插件必须css*/
        .leftpanel {overflow: hidden;}
        /*.leftpanel-collapsed .nav-bracket .children {display:none;}*/
    </style>
    <script type="text/javascript">
        //    左侧菜单滚动条
        (function ($) {
            $(window).load(function () {
                $(".leftpanel").perfectScrollbar();
                $(".leftpanel").perfectScrollbar("update");
                $(".leftpanel-collapsed .nav-bracket > ul").perfectScrollbar();

            });
        })(jQuery);

        // 右侧iframe高度定义
        $(function(){
            function Rise(){
                var bh = $("body").height();
                var hh = $(".headerbar").height();
                var fh = $(".footerpanel").height();
                $(".mainpanel").height(bh-hh-fh);
            }
            Rise();
            $(window).resize(function(){
                Rise();
            });
        });

        // iframe页面内部刷新iframe
        function iframeFresh(linkUrl){
            $('#mainFrame').load(function () {
                $(this).attr('src',linkUrl);
            });
        }
    </script>
</head>

<body class=" stickyheader">
<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>
    <div class="leftpanel sticky-leftpanel">
        <div class="leftpanelinner">
            <!-- This is only visible to small devices -->
            <div class="visible-xs hidden-sm hidden-md hidden-lg">
                <div class="media userlogged">
                    <img alt="" src="images/photos/loggeduser.png" class="media-object">
                    <div class="media-body">
                        <h4>系统管理员</h4>
                        <span>"Life is so..."</span>
                    </div>
                </div>

                <h5 class="sidebartitle actitle">帐户</h5>
                <ul class="nav nav-pills nav-stacked nav-bracket mb30">
                    <li><a href="profile.html"><i class="fa fa-user"></i> <span>Profile</span></a></li>
                    <li><a href=""><i class="fa fa-cog"></i> <span>帐户设置</span></a></li>
                    <li><a href=""><i class="fa fa-question-circle"></i> <span>帮助</span></a></li>
                    <li><a href="signout.html"><i class="fa fa-sign-out"></i> <span>注销</span></a></li>
                </ul>
            </div>

            <a class="menutoggle"><i class="fa fa-bars"></i></a>
            <#include "../common/menu.ftl">

        </div><!-- leftpanelinner -->
    </div><!-- leftpanel -->

    <div class="mainpanel">

        <div class="headerbar">
            <div class="header-left">
                <div class="topnav">
                    <div class="logopanel">
                        <h1> 医疗设计方案 - 辅助工具 </h1>
                    </div><!-- logopanel -->
                </div>
            </div>

            <div class="toggle-bar-div hidden-lg">
                <a class="toggle-bars" href="#"><i class="fa fa-bars"></i></a>
            </div>
            <div class="header-right">
                <ul class="headermenu">
                    <li>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <img src="images/photos/loggeduser.png" alt="" />
                                系统管理员
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                                <li><a href="profile.html"><i class="fa fa-user"></i> 我的帐户</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i> 帐户设置</a></li>
                                <li><a href="#"><i class="fa fa-question-circle"></i> 帮助</a></li>
                                <li><a href="signin.html"><i class="fa fa-sign-out"></i> 注销</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <iframe src="${basePath}/home_page.html" scrolling="auto" frameborder="0" class=""
            style="height:100%;width:100%;" name="mainFrame" id="mainFrame"></iframe>
    </div><!-- mainpanel -->

</section>
</body>
</html>
