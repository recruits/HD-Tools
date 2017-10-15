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
</head>

<body>
<div class="pageheader">
    <h2><i class="fa fa-home"></i> 主页 </h2>
</div>
<div class="contentpanel">
    <div class="row">

        <div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
            <div class="panel panel-success panel-stat">
                <div class="panel-heading">

                    <div class="stat">
                        <div class="row">
                            <div class="col-xs-4">
                                <i class="fa fa-eye fa-4x"></i>
                            </div>
                            <div class="col-xs-8">
                                <small class="stat-label">当月工单数量</small>
                                <h1>900k+</h1>
                            </div>
                        </div><!-- row -->

                        <div class="mb15"></div>

                        <div class="row">
                            <div class="col-xs-6">
                                <small class="stat-label">环比增长量</small>
                                <h4>7.80</h4>
                            </div>

                            <div class="col-xs-6">
                                <small class="stat-label">环比增长率</small>
                                <h4>76.43%</h4>
                            </div>
                        </div><!-- row -->
                    </div><!-- stat -->

                </div><!-- panel-heading -->
            </div><!-- panel -->
        </div><!-- col-sm-6 -->

        <div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
            <div class="panel panel-danger panel-stat">
                <div class="panel-heading">

                    <div class="stat">
                        <div class="row">
                            <div class="col-xs-4">
                                <i class="fa fa-pie-chart fa-4x"></i>
                            </div>
                            <div class="col-xs-8">
                                <small class="stat-label">当月异常报文数量</small>
                                <h1>1930</h1>
                            </div>
                        </div><!-- row -->

                        <div class="mb15"></div>

                        <small class="stat-label">异常报文数量占比</small>
                        <h4>2%</h4>

                    </div><!-- stat -->

                </div><!-- panel-heading -->
            </div><!-- panel -->
        </div><!-- col-sm-6 -->

        <div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
            <div class="panel panel-primary panel-stat">
                <div class="panel-heading">

                    <div class="stat">
                        <div class="row">
                            <div class="col-xs-4">
                                <i class="fa fa-file-text fa-4x"></i>
                            </div>
                            <div class="col-xs-8">
                                <small class="stat-label">本月断网情况</small>
                                <h1></h1>
                            </div>
                        </div><!-- row -->

                        <div class="mb15"></div>

                        <div class="row">
                            <div class="col-xs-6">
                                <small class="stat-label">断网次数</small>
                                <h4>7.80</h4>
                            </div>

                            <div class="col-xs-6">
                                <small class="stat-label">断网时长</small>
                                <h4>76.43%</h4>
                            </div>
                        </div><!-- row -->

                    </div><!-- stat -->

                </div><!-- panel-heading -->
            </div><!-- panel -->
        </div><!-- col-sm-6 -->

        <div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
            <div class="panel panel-info panel-stat">
                <div class="panel-heading">

                    <div class="stat">
                        <div class="row">
                            <div class="col-xs-4">
                                <i class="fa fa-money fa-4x"></i>
                            </div>
                            <div class="col-xs-8">
                                <small class="stat-label">本月转办工单数量</small>
                                <h1>12</h1>
                            </div>
                        </div><!-- row -->

                        <div class="mb15"></div>

                        <div class="row">
                            <div class="col-xs-6">
                                <small class="stat-label">已办结</small>
                                <h4>2</h4>
                            </div>

                            <div class="col-xs-6">
                                <small class="stat-label">未办结</small>
                                <h4>10</h4>
                            </div>
                        </div><!-- row -->

                    </div><!-- stat -->

                </div><!-- panel-heading -->
            </div><!-- panel -->
        </div><!-- col-sm-6 -->
    </div><!-- row -->

    <div class="row">
        <div class="col-sm-8 col-md-9">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-8">
                            <h5 class="subtitle mb5">Network Performance</h5>
                            <p class="mb15">Duis autem vel eum iriure dolor in hendrerit in vulputate...</p>
                            <div id="basicflot" style="width: 100%; height: 300px; margin-bottom: 20px"></div>
                        </div><!-- col-sm-8 -->
                        <div class="col-sm-4">
                            <h5 class="subtitle mb5">Server Status</h5>
                            <p class="mb15">Summary of the status of your server.</p>

                            <span class="sublabel">CPU Usage (40.05 - 32 cpus)</span>
                            <div class="progress progress-sm">
                                <div style="width: 40%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-primary"></div>
                            </div><!-- progress -->

                            <span class="sublabel">Memory Usage (32.2%)</span>
                            <div class="progress progress-sm">
                                <div style="width: 32%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
                            </div><!-- progress -->

                            <span class="sublabel">Disk Usage (82.2%)</span>
                            <div class="progress progress-sm">
                                <div style="width: 82%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-danger"></div>
                            </div><!-- progress -->

                            <span class="sublabel">Databases (63/100)</span>
                            <div class="progress progress-sm">
                                <div style="width: 63%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-warning"></div>
                            </div><!-- progress -->

                            <span class="sublabel">Domains (2/10)</span>
                            <div class="progress progress-sm">
                                <div style="width: 20%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
                            </div><!-- progress -->

                            <span class="sublabel">Email Account (13/50)</span>
                            <div class="progress progress-sm">
                                <div style="width: 26%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="40" role="progressbar" class="progress-bar progress-bar-success"></div>
                            </div><!-- progress -->


                        </div><!-- col-sm-4 -->
                    </div><!-- row -->
                </div><!-- panel-body -->
            </div><!-- panel -->
        </div><!-- col-sm-9 -->

        <div class="col-sm-4 col-md-3">

            <div class="panel panel-default">
                <div class="panel-body">
                    <h5 class="subtitle mb5">Most Browser Used</h5>
                    <p class="mb15">Duis autem vel eum iriure dolor in hendrerit in vulputate...</p>
                    <div id="donut-chart2" class="ex-donut-chart"></div>
                </div><!-- panel-body -->
            </div><!-- panel -->

        </div><!-- col-sm-3 -->

    </div>
</div>
</body>
</html>