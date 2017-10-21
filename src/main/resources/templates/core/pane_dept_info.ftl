<div class="tab-pane" id="deptDetailInfo">
    <div class="m-5">
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >部门分类编码：</span>
                    <input type="text" name="deptTypeCode" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >部门分类名称：</span>
                    <input type="text" name="deptTypeName" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >部门信息编码：</span>
                    <input type="text" name="deptCode" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >部门信息名称：</span>
                    <input type="text" name="deptName" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >使用面积系数：</span>
                    <input type="text" name="areaRatio" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >规划面积总计：</span>
                    <input type="text" name="planArea" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3 col-xs-offset-9">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >设计面积总计：</span>
                    <input type="text" name="designArea" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
    </div>
    <hr>

    <div class="m-5">
        <div class="row">
            <div class="col-xs-1">
                <button class="btn btn-primary btn-xs" id="addNewAreaInfo">新增区域</button>
            </div>
        </div>
        <table id="deptDetailTab" class="table tree table-bordered table-striped table-condensed">
            <thead>
                <th>序号</th>
                <th>房间/区域名称</th>
                <th>数量</th>
                <th>净使用面积</th>
                <th>使用面积小计</th>
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
                <h4 class="modal-title" id="areaInfoModalLabel">新增区域信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="addAreaInfoForm" action="addAreaInfo.json" >
                        <div class="form-group">
                            <label class="col-xs-3 control-label">区域编码<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="officeCode" class="form-control" placeholder="请输入区域编码" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">区域名称<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="officeName" class="form-control" placeholder="请输入区域名称" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">区域备注<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="note" class="form-control" placeholder="请输入区域备注" required value=""/>
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
                <h4 class="modal-title" id="roomInfoModalLabel">新增房间信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="addRoomInfoForm" action="addRoomInfo.json" >
                        <div class="form-group">
                            <label class="col-xs-3 control-label">房间编码<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="roomCode" class="form-control" placeholder="请输入房间编码" required value=""/>
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
                                <input type="number" name="cnt" class="form-control" placeholder="请输入房间个数" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">房间面积<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="number" name="areaTotal" class="form-control" placeholder="请输入房间面积" required value=""/>
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