$(function () {
    initAreaInfo();
});

function initAreaInfo() {
    // 激活面板，重新加载数据
    $('a[data-toggle="tab"][id="deptDetailInfoTab"]').on('shown.bs.tab', function (e) {
        reloadAreaInfo();
    });

    // 绑定区域新增事件
    $('#addNewAreaInfo').click(function () {
        areaAction = CONST_ACTION_ADD;
        $('#addAreaInfoModal').modal();
    });
    // 区域信息操作按钮事件绑定
    $('#resetAreaInfoFormBtn').click(function () {
        clearModalData();
    });
    $('#submitAreaInfoFormBtn').click(function () {
        addAreaInfo();
    });

    // 房间信息操作按钮事件绑定
    $('#resetRoomInfoFormBtn').click(function () {
        clearRoomModalData();
    });
    $('#submitRoomInfoFormBtn').click(function () {
        addRoomInfo();
    });

    // 模态对话框关闭事件
    $('#addAreaInfoModal').on('hidden.bs.modal', function (e) {
        // 刷新数据
        reloadAreaInfo();
        // 清空模态数据
        clearAreaModalData();
    });
    // 模态对话框关闭事件
    $('#addRoomInfoModal').on('hidden.bs.modal', function (e) {
        // 刷新数据
        reloadAreaInfo();
        // 清空模态数据
        clearRoomModalData();
    });
}

// 刷新页面数据，重新加载整个页面
function reloadAreaInfo() {
    if (currDeptId && currDeptTypeId) {
        // 重新加载表格数据
        var linkUrlForAreaInfo = "loadAllAreaInfo.json?deptId=" + currDeptId;
        $.get(linkUrlForAreaInfo, function (outData) {
            renderAreaInfoPage(outData);
        }, "json");

        // 重新加载汇总数据
        var linkUrlForAreaTitle = "loadAreaTitle.json?deptId=" + currDeptId + "&deptTypeId=" + currDeptTypeId;
        $.get(linkUrlForAreaTitle, function (outData) {
            freshAreaTitle(outData);
        }, "json");
    }
}

// 重新渲染表格
function renderAreaInfoPage(data) {
    if (data) {
        // 清空列表数据
        $('#area_info_tree_grid tr').remove();

        // 分级渲染表格
        $.each(data, function (index, item) {
            if (item.level == 1) {
                $('#area_info_tree_grid').append(buildAreaInfoTopLeveHtml(item));
            } else if (item.level == 2) {
                $('#area_info_tree_grid').append(buildAreaInfoSubLeveHtml(item));
            }
        });

        // 创建二级列表的图标
        $('#deptDetailTab').treegrid({
            expanderExpandedClass: 'fa fa-minus',
            expanderCollapsedClass: 'fa fa-plus'
        });

        // 设置房间个数可编辑
        $('a[name^="roomCntForEdit-"]').editable({
            success: function (outData, newVal) {
                if (outData.retCode == RET_CODE_SUCC) {
                    reloadAreaInfo();
                }
            }
        });

        // 设置房间面积可编辑
        $('a[name^="roomAreaForEdit-"]').editable({
            success: function (outData, newVal) {
                if (outData.retCode == RET_CODE_SUCC) {
                    reloadAreaInfo();
                }
            }
        });
    }
}
// 更新部门分类汇总数据
function freshAreaTitle(data) {
    if (data) {
        $('#deptDetailInfo input[name="deptTypeCode"]').val(data.deptTypeCode);
        $('#deptDetailInfo input[name="deptTypeName"]').val(data.deptTypeName);
        $('#deptDetailInfo input[name="deptCode"]').val(data.deptCode);
        $('#deptDetailInfo input[name="deptName"]').val(data.deptName);
        $('#deptDetailInfo input[name="planArea"]').val(data.planArea);
        $('#deptDetailInfo input[name="designArea"]').val(data.designArea);
        $('#deptDetailInfo input[name="areaRatio"]').val(data.areaRatio || "");
    }
}

// 创建表格一级列表数据
function buildAreaInfoTopLeveHtml(item) {
    return "<tr class='treegrid-" + item.id + "-area'><td>"
        + item.areaCode + "</td><td>" + item.areaName + "</td><td>"
        + (item.cnt || "") + "</td><td>" + (item.areaTotal || "") + "</td><td>"
        + (item.areaSummary || "") + "</td><td>" + (item.note || "") + "</td><td>"
        + buildOperInfoForAreaTopLevel(item.id, item.areaName, item.areaCode, item.note)
        + "</td></tr>";
}
// 创建表格二级列表数据
function buildAreaInfoSubLeveHtml(item) {
    return "<tr class='treegrid-parent-" + item.pid + "-area treegrid-999999-" + item.id
        + "-area' ondblclick='goToRoomPane(" + item.pid + "," + item.id + ")'><td>"
        + item.areaCode + "</td><td>" + item.areaName + "</td><td>"
        + buildEditInfoForRoomCnt(item.cnt, item.id) + "</td><td>"
        + buildEditInfoForRoomArea(item.areaTotal, item.id) + "</td><td>"
        + (item.areaSummary || "") + (item.node || "") + "</td><td></td><td>"
        + buildOperInfoForAreaSubLevel(item.id, item.areaCode, item.areaName, item.cnt, item.areaTotal)
        + "</td></tr>";
}
// 增加房间个数可编辑操作
function buildEditInfoForRoomCnt(currVal, roomId) {
    var postEditValUrl = basePath + "/core/editRoomCntOnTime.json";
    return '<a href="#" name="roomCntForEdit-' + roomId + '" data-type="text" data-pk="'
        + roomId + '" data-url="' + postEditValUrl + '" data-title="输入房间个数:">' + currVal + '</a>';
}
// 增加房间使用面积可编辑操作
function buildEditInfoForRoomArea(currVal, roomId) {
    var postEditValUrl = basePath + "/core/editRoomAreaOnTime.json";
    return '<a href="#" name="roomAreaForEdit-' + roomId + '" data-type="text" data-pk="'
        + roomId + '" data-url="' + postEditValUrl + '" data-title="输入房间面积:">' + currVal + '</a>';
}

// 增加部门分类规划面积可编辑操作
function buildEditInfoForPlanArea(currVal, deptTypeId) {
    var postEditValUrl = basePath + "/core/editPlanAreaValOnTime.json";
    return '<a href="#" name="planAreaForEdit-' + deptTypeId + '" data-type="text" data-pk="'
        + deptTypeId + '" data-url="' + postEditValUrl + '" data-title="输入规划面积:">' + currVal + '</a>';
}
// 增加区域操作信息
function buildOperInfoForAreaTopLevel(areaId, areaName, areaCode, note) {
    return '<a onclick="modAreaInfo(' + areaId + ',\'' + areaName + '\',\'' + areaCode + '\',\'' + note
        + '\')" class="fa fa-edit addAreaInfoModal" data-toggle="modal" data-target="#addAreaInfoModal"'
        + ' style="margin-right: 10px; font-size: 20px" title="编辑区域"></a>'
        + '<a onclick="delAreaInfo(' + areaId + ',\'' + areaName + '\''
        + ')" class="fa fa-minus addAreaInfoModal" '
        + ' style="margin-right: 10px; font-size: 20px" title="删除区域"></a>' + ' | '
        + '<a onclick="resetAreaId(' + areaId
        + ')" class="fa fa-plus addRoomInfoModal" data-toggle="modal" data-target="#addRoomInfoModal"'
        + ' style="margin-right: 10px; font-size: 20px" title="新增房间"></a>';
}
// 增加房间操作信息
function buildOperInfoForAreaSubLevel(roomId, roomCode, roomName, roomCnt, roomArea) {
    return '<a onclick="modRoomInfo(' + roomId + ',\'' + roomCode  + '\',\'' + roomName + '\',\'' + roomCnt + '\',' + roomArea
        + ')" class="fa fa-edit addRoomInfoModal" data-toggle="modal" data-target="#addRoomInfoModal"'
        + ' style="margin-right: 10px; font-size: 20px" title="编辑房间"></a>'
        + '<a onclick="delRoomInfo(' + roomId + ',\'' + roomName + '\''
        + ')" class="fa fa-minus addRoomInfoModal" '
        + ' style="margin-right: 10px; font-size: 20px" title="删除房间"></a>';
}
// 编辑区域信息
function modAreaInfo(areaId, areaName, areaCode, note) {
    currAreaId = areaId;
    // 初始化信息
    $('#addAreaInfoForm input[name="officeCode"]').val(areaName);
    $('#addAreaInfoForm input[name="officeName"]').val(areaCode);
    $('#addAreaInfoForm input[name="note"]').val(note);
    // 保存操作动作
    areaAction = CONST_ACTION_EDIT;
}
// 删除区域信息
function delAreaInfo(areaId, areaName) {
    var options = {
        title: "删除区域信息",
        message: "是否确定删除区域 [ "+areaName+" ]?",
    };
    Ewin.confirm(options).on(function (delFlag) {
        if(delFlag){
            var postDelAreaInfoUrl = basePath + "/core/delAreaInfoByAreaId.json?areaId=" + areaId;
            $.post(postDelAreaInfoUrl, function (outData) {
                if (outData.retCode == RET_CODE_SUCC) {
                    reloadAreaInfo();
                    currAreaId = '';
                    currRoomId = '';
                }
            }, "json");
        }
    });
}
// 编辑房间信息
function modRoomInfo(roomId, roomCode, roomName, roomCnt, roomArea) {
    currRoomId = roomId;
    // 初始化信息
    $('#addRoomInfoForm input[name="roomCode"]').val(roomCode);
    $('#addRoomInfoForm input[name="roomName"]').val(roomName);
    $('#addRoomInfoForm input[name="cnt"]').val(roomCnt);
    $('#addRoomInfoForm input[name="areaTotal"]').val(roomArea);
    // 保存操作动作
    roomAction = CONST_ACTION_EDIT;
}
// 删除房间信息
function delRoomInfo(roomId, roomName) {
    var options = {
        title: "删除房间信息",
        message: "是否确定删除房间 [ "+roomName+" ]?",
    };
    Ewin.confirm(options).on(function (delFlag) {
        if(delFlag){
            var postDelRoomInfoUrl = basePath + "/core/delRoomInfoByRoomId.json?roomId=" + roomId;
            $.post(postDelRoomInfoUrl, function (outData) {
                if (outData.retCode == RET_CODE_SUCC) {
                    reloadAreaInfo();
                    currRoomId = '';
                }
            }, "json");
        }
    });
}
// 缓存当前正在操作的区域编号
function resetAreaId(areaId) {
    currAreaId = areaId;
    roomAction = CONST_ACTION_ADD;
}
// 清空区域信息数据[模态对话框]
function clearAreaModalData() {
    $('#addAreaInfoForm input[name="officeCode"]').val('');
    $('#addAreaInfoForm input[name="officeName"]').val('');
    $('#addAreaInfoForm input[name="note"]').val('');
}
// 清空房间信息数据[模态对话框]
function clearRoomModalData() {
    $('#addRoomInfoForm input[name="roomCode"]').val('');
    $('#addRoomInfoForm input[name="roomName"]').val('');
    $('#addRoomInfoForm input[name="cnt"]').val('');
    $('#addRoomInfoForm input[name="areaTotal"]').val('');
}
// 新增区域信息
function addAreaInfo() {
    var areaInfoForm = $('#addAreaInfoForm');
    var linkUrlForAreaInfo = areaInfoForm.attr('action');

    var submitData = areaInfoForm.serialize();
    var appendData = "&projId=" + projId + "&deptTypeId=" + currDeptTypeId
        + "&deptId=" + currDeptId + "&action=" + areaAction + "&id=" + currAreaId;
    submitData += appendData;

    $.post(linkUrlForAreaInfo, submitData, function (outData) {
        Ewin.alert(outData.retMsg);
        if (outData.retCode == RET_CODE_SUCC) {
            // 关闭模态对话框
            $('#addAreaInfoModal').modal('hide');
        }
    }, "json");
}
// 新增房间信息
function addRoomInfo() {
    var roomInfoForm = $('#addRoomInfoForm');
    var linkUrlForRoomInfo = roomInfoForm.attr('action');

    var submitData = roomInfoForm.serialize();
    var appendData = "&projId=" + projId + "&deptTypeId=" + currDeptTypeId
        + "&deptId=" + currDeptId + "&areaId=" + currAreaId + "&action="
        + roomAction + "&id=" + currRoomId;
    submitData += appendData;

    $.post(linkUrlForRoomInfo, submitData, function (outData) {
        Ewin.alert(outData.retMsg);
        if (outData.retCode == RET_CODE_SUCC) {
            // 关闭模态对话框
            $('#addRoomInfoModal').modal('hide');
        }
    }, "json");
}
// 跳转房间详情标签
function goToRoomPane(areaId, roomId) {
    currAreaId = areaId;
    currRoomId = roomId;

    $('.nav-tabs li:eq(3) a').tab('show');
}
/**********************************************************************************************
 * 页面字段编辑逻辑
 * - 规划面积变更
 *   - 变更后的内容提交到服务器
 *   - 服务端修改规划面积总计值
 *   - 服务端修改部门分类规划占比
 *   - 页面修改当前规则面积值显示
 *   - 页面修改部门分类规则面积占比显示
 *   - 页面修改规划面积总计显示值
 *********************************************************************************************/

