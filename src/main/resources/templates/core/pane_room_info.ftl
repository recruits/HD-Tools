<div class="tab-pane" id="roomDataInfo">
    <div class="m-5">
        <div class="row m-b-5">
            <div class="col-xs-2 col-xs-offset-7">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >归属区域编号：</span>
                    <input type="text" name="belongAreaCode" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >归属区域名称：</span>
                    <input type="text" name="belongAreaName" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
        </div>
        <div class="row m-b-5">
            <div class="col-xs-2">
                <div class="input-group input-group-xs">
                    <button class="btn btn-primary btn-xs" id="selSpecRoomDataBtn" disabled="disabled">选择样板数据
                    </button>
                </div>
            </div>
            <div class="col-xs-2 col-xs-offset-5">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >当前房间编号：</span>
                    <input type="text" name="currentRoomCode" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="input-group input-group-xs">
                    <span class="input-group-addon" >当前房间名称：</span>
                    <input type="text" name="currentRoomName" class="form-control" placeholder="" aria-describedby="sizing-addon1" readonly>
                </div>
            </div>
        </div>
    </div>
    <hr>

    <div class="m-5" id="roomDataSpecDiv">
    </div>
</div>

<div class="modal fade" id="selSpecRoomDataModal" tabindex="-1" role="dialog" aria-labelledby="selSpecRoomDataModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="specRoomDataModalLabel">选择样板数据</h4>
            </div>
            <div class="modal-body">
                <div class="row m-5">
                    <table id="specRoomDetailList" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th></th>
                            <th>适用类别编号</th>
                            <th>适用部门类别</th>
                            <th>样板房间名称</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>备注信息</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button id="saveSpecRoomDataDetailBtn" type="submit" class="btn btn-primary btn-xs">选择</button>
            </div>
        </div>
    </div>
</div>