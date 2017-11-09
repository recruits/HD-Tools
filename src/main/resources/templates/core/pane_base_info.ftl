<div class="tab-pane active" id="baseInfo">
    <form id="basicForm" action="saveProjBaseInfo.json" >
        <div class="form-group">
            <input type="hidden" name="action" value="${action!}"/>
            <input type="hidden" name="groupId" value="${groupId!}"/>
            <label class="col-xs-1 control-label">项目名称<span class="asterisk">*</span></label>
            <div class="col-xs-9">
                <input type="text" name="projName" class="form-control" placeholder="请输入项目名称" required value="${projName!}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-1 control-label">项目描述<span style="color:white">*</span></label>
            <div class="col-xs-9">
                <input type="text" name="projSubtitle" class="form-control" placeholder="请输入项目描述" ßvalue="${projSubtitle!}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-1 control-label">项目区域<span class="asterisk">*</span></label>
            <div class="col-xs-3">
                <@cc.select class="selectpicker form-control" name="regionCode" value="${regionCode!}" typeCode="AREA_CODE" />
            </div>
            <label class="col-xs-1 col-xs-offset-2 control-label">项目类型<span class="asterisk">*</span></label>
            <div class="col-xs-3">
                <select class="selectpicker form-control" name="projType" data-style="btn-default">
                    <option value="1" >儿童医院</option>
                    <option value="2" >疗养院</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-1 control-label">项目编号<span class="asterisk">*</span></label>
            <div class="col-xs-3">
                <input type="text" name="id" class="form-control"  value="${id!}" required readonly/>
            </div>
            <label class="col-xs-1 col-xs-offset-2 control-label">项目阶段<span class="asterisk">*</span></label>
            <div class="col-xs-3">
                <input type="hidden" name="projPhase" value="${projPhase!}" class="form-control" />
                <input type="text" name="projPhaseName" value="${getCodeDesc('PROJ_PHASE', projPhase!)}" class="form-control"  readonly />
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-1 control-label">创建时间<span class="asterisk">*</span></label>
            <div class="col-xs-3 input-append">
                <input size="16" type="text" name="createTime" class="form-control" value="${createTime!}" readonly>
            </div>
            <label class="col-xs-1 col-xs-offset-2 control-label">版本信息<span class="asterisk">*</span></label>
            <div class="col-xs-3">
                <input type="hidden" name="verId" value="${verId!}">
                <input type="text" name="verInfo" class="form-control" placeholder="请输入版本信息" required value="${verInfo!}" readonly/>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col-xs-2 col-xs-offset-5">
            <button class="btn btn-primary btn-xs" id="saveItemInfoBtn">&nbsp;保&nbsp;&nbsp;存&nbsp;</button>
            <button type="reset" class="btn btn-default btn-xs">&nbsp;发&nbsp;&nbsp;布&nbsp;</button>
        </div>
    </div>
</div>