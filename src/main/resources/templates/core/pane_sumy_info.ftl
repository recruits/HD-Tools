<div class="tab-pane" id="summaryInfo">
    <div class="m-5">
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">[P]面积总计：</span>
                    <input type="text" name="sumyPlanArea" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                </div>
            </div>
            <div class="col-xs-2 col-xs-offset-1">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">[D]面积总计：</span>
                    <input type="text" name="sumyDesignArea" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">[P]面积占比：</span>
                    <input type="text" name="sumyPlanAreaPersent" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
            <div class="col-xs-2 col-xs-offset-1">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">[D]面积占比：</span>
                    <input type="text" name="sumyDesignAreaPersent" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">面积系数：</span>
                    <input type="number" name="sumyAreaRatio" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                    <span class="input-group-btn">
                        <button class="btn btn-primary btn-xs" type="button" id="sumyAreaRatioModBtn">更新</button>
                    </span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-5 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" id="sizing-addon1">备注信息：</span>
                    <input type="text" name="sumyNote" class="form-control" placeholder="" aria-describedby="sizing-addon1" value="">
                    <span class="input-group-btn">
                        <button class="btn btn-primary btn-xs" type="button" id="sumyNoteModBtn">更新</button>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <hr>

    <div class="m-5">
        <table id="baseTab" class="table tree table-bordered table-striped table-condensed">
            <thead>
                <th>序号</th>
                <th>功能分区</th>
                <th>规划面积</th>
                <th>设计面积</th>
                <th>规划百分比</th>
                <th>设计百分比</th>
                <th>备注</th>
                <th>操作</th>
            </thead>
            <tbody id="sumy_info_tree_grid">
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" id="addSubSumyInfoModal" tabindex="-1" role="dialog" aria-labelledby="addSubSumyInfoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addSubSumyInfoModalLabel">新增部门信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form id="addSubSumyInfoForm" action="addDeptInfo.json" >
                        <div class="form-group">
                            <label class="col-xs-3 control-label">部门编码<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="deptCode" class="form-control" placeholder="请输入部门编码" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">部门名称<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="text" name="deptName" class="form-control" placeholder="请输入部门名称" required value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">规划面积<span class="asterisk">*</span></label>
                            <div class="col-xs-8">
                                <input type="number" name="planArea" class="form-control" placeholder="请输入规划面积" required value=""/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button id="resetSubSumyInfoFormBtn" type="reset" class="btn btn-default btn-xs">重置</button>
                <button id="submitSubSumyInfoFormBtn" type="submit" class="btn btn-primary btn-xs">保存</button>
            </div>
        </div>
    </div>
</div>