<div class="tab-pane" id="deptDetailInfo">
    <div class="m-5">
        <div class="row m-b-5">
            <div class="col-xs-3 col-xs-offset-6">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >上级部门编码：</span>
                    <input type="text" name="deptTypeCode" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >上级部门名称：</span>
                    <input type="text" name="deptTypeName" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-3 col-xs-offset-6">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">&nbsp;&nbsp;[P]面积总计：</span>
                    <input type="text" name="deptPlanAreaTotal" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">&nbsp;&nbsp;[D]面积总计：</span>
                    <input type="text" name="deptDesignAreaTotal" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-3 col-xs-offset-6">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">&nbsp;&nbsp;[P]面积小计：</span>
                    <input type="text" name="deptPlanAreaSummary" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">&nbsp;&nbsp;[D]面积小计：</span>
                    <input type="text" name="deptDesignAreaSummary" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-3 col-xs-offset-6">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">&nbsp;&nbsp;[P]面积系数：</span>
                    <input type="number" name="deptPlanAreaRatio" min="0" step="0.1" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                    <span class="input-group-btn">
                        <button class="btn btn-primary btn-xs" type="button" id="deptPlanAreaRatioModBtn">更新</button>
                    </span>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">&nbsp;&nbsp;[D]面积系数：</span>
                    <input type="number" name="deptDesignAreaRatio" min="0" step="0.1" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                    <span class="input-group-btn">
                        <button class="btn btn-primary btn-xs" type="button" id="deptDesignAreaRatioModBtn">更新</button>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <hr>

    <div class="m-5">
        <div class="row">
            <div class="col-xs-1">
                <button class="btn btn-primary btn-xs" id="addNewAreaInfoBtn">新增区域</button>
            </div>
        </div>
        <table id="deptDetailTab" class="table tree table-bordered table-striped table-condensed">
            <thead>
                <th>序号</th>
                <th>房间/区域名称</th>
                <th>规划数量</th>
                <th>规划使用面积</th>
                <th>规划面积小计</th>
                <th>设计数量</th>
                <th>设计使用面积</th>
                <th>设计面积小计</th>
                <th>注释</th>
                <th>操作</th>
            </thead>
            <tbody id="area_info_tree_grid">
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" id="addAreaInfoModal" tabindex="-1" role="dialog" aria-labelledby="addAreaInfoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="areaInfoModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="addAreaInfoForm" action="addAreaInfo.json" >
                        <div class="form-group">
                            <label class="col-xs-3 control-label">区域编码<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <#--<input type="text" name="officeCode" class="form-control" placeholder="请输入区域编码" required value=""/>-->
                                <div class="input-group">
                                    <input type="hidden" name="officeCode">
                                    <span class="input-group-addon" name="deptCode"></span>
                                    <input type="number" name="orderIdx" min="1" max="99" class="form-control"
                                           aria-describedby="sizing-addon1" value=""
                                           onchange="checkAndFormatCodeForArea(this)">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">区域名称<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="officeName" class="form-control" placeholder="请输入区域名称" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">区域备注&nbsp;&nbsp;</label>
                            <div class="col-xs-8">
                                <input type="text" name="note" class="form-control" placeholder="请输入区域备注" value=""/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button id="resetAreaInfoFormBtn" type="reset" class="btn btn-default btn-xs">重置</button>
                <button id="submitAreaInfoFormBtn" type="submit" class="btn btn-primary btn-xs">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addRoomInfoModal" tabindex="-1" role="dialog" aria-labelledby="addRoomInfoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="roomInfoModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="addRoomInfoForm" action="addRoomInfo.json" >
                        <div class="form-group">
                            <label class="col-xs-3 control-label">房间编码<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <#--<input type="text" name="roomCode" class="form-control" placeholder="请输入房间编码" required value=""/>-->
                                <div class="input-group">
                                    <input type="hidden" name="roomCode">
                                    <span class="input-group-addon" name="areaCode"></span>
                                    <input type="number" name="orderIdx" min="1" max="99" class="form-control"
                                           aria-describedby="sizing-addon1" value=""
                                           onchange="checkAndFormatCodeForRoom(this)">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">房间名称<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="roomName" class="form-control" placeholder="请输入房间名称" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">房间个数<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="number" name="designCnt" class="form-control" placeholder="请输入房间个数" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">房间面积<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="number" name="designAreaSummary" class="form-control" placeholder="请输入房间面积" required value=""/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button id="resetRoomInfoFormBtn" type="reset" class="btn btn-default btn-xs">重置</button>
                <button id="submitRoomInfoFormBtn" type="submit" class="btn btn-primary btn-xs">保存</button>
            </div>
        </div>
    </div>
</div>