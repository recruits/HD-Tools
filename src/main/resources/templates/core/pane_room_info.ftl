<div class="tab-pane" id="contact">
    <!--
    <div class="row m-5">
        <div class="col-xs-4">
            <div class="form-group">
                <select class="selectpicker form-control" data-style="btn-primary btn-xs">
                    <option>公共空间</option>
                    <option>急诊部</option>
                    <option>门诊部</option>
                    <option>住院部</option>
                    <option>医技部</option>
                    <option>行政部</option>
                    <option>后勤支持</option>
                    <option>机房</option>
                </select>
            </div>
        </div>
        <div class="col-xs-8">
            <div class="input-group-xs">
                <button class="btn btn-primary btn-xs" onclick="addPart()">增加房间</button>&nbsp;
            </div>
        </div>
    </div>
    -->
    <div class="m-5">
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >归属区域编号：</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="200">
                </div>
            </div>
            <div class="col-xs-2 col-xs-offset-1">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >归属区域名称：</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >当前房间编号：</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="200">
                </div>
            </div>
            <div class="col-xs-2 col-xs-offset-1">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >当前房间名称：</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
        </div>
    </div>
    <hr>

    <div class="m-5">
        <!--
        <div class="row">
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">科室编号</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">科室名称</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">房间编号</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">房间名称</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
        </div>
        -->
        <div class="row">
            <div class="col-xs-4">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                        <#--<div class="panel-btns">
                            <a href="" class="fa fa-minus-circle" style="font-size: 22px; color: white;" title="删除当前模块"></a>
                            <a href="" class="fa fa-minus-square" style="font-size: 22px; color: white;" title="删除条目"></a>
                            <a href="" class="fa fa-plus-square" style="font-size: 22px; color: white;" title="增加条目"></a>
                        </div>-->
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