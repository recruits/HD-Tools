$(function () {
    initAreaInfo();

    // 区域信息校验器
    paneAreaInfoForm = $('#addAreaInfoForm');
    // 绑定校验事件
    paneAreaInfoForm.bootstrapValidator(paneAreaInfoValidOption);
    // 创建校验器
    paneAreaInfoValidator = paneAreaInfoForm.data('bootstrapValidator');

    // 房间信息校验器
    paneRoomInfoForm = $('#addRoomInfoForm');
    // 绑定校验事件
    paneRoomInfoForm.bootstrapValidator(paneRoomInfoValidOption);
    // 创建校验器
    paneRoomInfoValidator = paneRoomInfoForm.data('bootstrapValidator');
});

var paneAreaInfoForm ;
var paneAreaInfoValidator;
var paneRoomInfoForm ;
var paneRoomInfoValidator;

function initAreaInfo() {
    // 激活面板，重新加载数据
    $('a[data-toggle="tab"][id="deptDetailInfoTab"]').on('shown.bs.tab', function (e) {
        reloadAreaInfo();
    });

    // 区域信息操作事件
    initAreaEvents();
    // 房间信息操作事件
    initRoomEvents();
    // 模态框操作事件
    initModalEvents();
}
// 区域信息操作事件
function initAreaEvents() {
    // 绑定区域新增事件
    $('#addNewAreaInfoBtn').bind('click', function(){
        resetAreaInfo();
    });
    // 区域信息操作按钮事件绑定
    $('#resetAreaInfoFormBtn').bind('click', function () {
        clearAreaModalData();
    });
    $('#submitAreaInfoFormBtn').bind('click', function () {
        // 不检校部门编号
        paneAreaInfoValidator.updateStatus('orderIdx', 'VALID');

        paneAreaInfoValidator.validate();
        if(paneAreaInfoValidator.isValid()){
            addAreaInfo();
        }
    });
    // 面积系数实时更新
    $('#deptPlanAreaRatioModBtn').bind('click', editDeptPlanAreaRatioValOnTime);
    $('#deptDesignAreaRatioModBtn').bind('click', editDeptDesignAreaRatioValOnTime);
}
// 房间信息操作事件
function initRoomEvents() {
    // 房间信息操作按钮事件绑定
    $('#resetRoomInfoFormBtn').bind('click', function () {
        clearRoomModalData();
    });
    $('#submitRoomInfoFormBtn').bind('click', function () {
        // 不检校部门编号
        paneRoomInfoValidator.updateStatus('orderIdx', 'VALID');

        paneRoomInfoValidator.validate();
        if(paneRoomInfoValidator.isValid()){
            addRoomInfo();
        }
    });
}
// 区域、房间信息模态框
function initModalEvents() {
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
function resetAreaInfo() {
    areaAction = CONST_ACTION_ADD;
    $('#addAreaInfoModal').modal();

    // 更新标题
    $('#areaInfoModalLabel').text(MODAL_TITLE_AREA[areaAction]);

    // 获取父级编码
    var nextAreaCodeInfo = getNextAreaCodeByDeptId();
    if (nextAreaCodeInfo && nextAreaCodeInfo != "") {
        var nextAreaCodeInfos = translateCode(nextAreaCodeInfo);

        // 重新设置父级编码
        $('#addAreaInfoForm span[name="deptCode"]').html(nextAreaCodeInfos[0]);

        // 自动生成下一个部门编号
        $('#addAreaInfoForm input[name="orderIdx"]').val(nextAreaCodeInfos[1]);

        // 初始化部门编码
        $('#addAreaInfoForm input[name="officeCode"]').val(nextAreaCodeInfo);
    }
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
        var linkUrlForAreaSummary = "loadAreaSummary.json?deptId=" + currDeptId + "&deptTypeId=" + currDeptTypeId;
        $.get(linkUrlForAreaSummary, function (outData) {
            freshAreaSumary(outData);
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
// 更新区域汇总信息
function freshAreaSumary(data) {
    if (data) {
        $('#deptDetailInfo input[name="deptTypeCode"]').val(data.deptTypeCode);
        $('#deptDetailInfo input[name="deptTypeName"]').val(data.deptTypeName);
        $('#deptDetailInfo input[name="deptPlanAreaTotal"]').val(fix2(data.planAreaTotal));
        $('#deptDetailInfo input[name="deptDesignAreaTotal"]').val(fix2(data.designAreaTotal));
        $('#deptDetailInfo input[name="deptPlanAreaSummary"]').val(fix2(data.planAreaSummary));
        $('#deptDetailInfo input[name="deptDesignAreaSummary"]').val(fix2(data.designAreaSummary));
        $('#deptDetailInfo input[name="deptPlanAreaRatio"]').val(data.planAreaRatio || "1");
        $('#deptDetailInfo input[name="deptDesignAreaRatio"]').val(data.designAreaRatio || "1");

        // 缓存汇总信息编号
        currAreaSumyId = data.id;
    }
}

// 创建表格一级列表数据
function buildAreaInfoTopLeveHtml(item) {
    return "<tr class='treegrid-" + item.id + "-area'><td>"
        + item.areaCode + "</td><td>" + item.areaName + "</td><td>"
        + (item.planCnt || "") + "</td><td>" + (item.planAreaSummary || "") + "</td><td>"
        + (item.planAreaTotal || "") + "</td><td>"
        + (item.designCnt || "") + "</td><td>" + (item.designAreaSummary || "") + "</td><td>"
        + (item.designAreaTotal || "") + "</td><td>" + (item.note || "") + "</td><td>"
        + buildOperInfoForAreaTopLevel(item.id, item.areaName, item.areaCode, item.note)
        + "</td></tr>";
}
// 创建表格二级列表数据
function buildAreaInfoSubLeveHtml(item) {
    return "<tr class='treegrid-parent-" + item.pid + "-area treegrid-999999-" + item.id
        + "-area' ondblclick='goToRoomPane(" + item.pid + "," + item.id + ")'><td>"
        + item.areaCode + "</td><td>" + item.areaName + "</td><td>"
        + (item.planCnt || "") + "</td><td>"
        + (item.planAreaSummary || "") + "</td><td>"
        + (item.planAreaTotal || "") + "</td><td>"
        + buildEditInfoForRoomCnt(item.designCnt, item.id) + "</td><td>"
        + buildEditInfoForRoomArea(item.designAreaSummary, item.id) + "</td><td>"
        + (item.designAreaTotal || "") + "</td><td>" + (item.note || "") + "</td><td>"
        + buildOperInfoForAreaSubLevel(item.id, item.areaCode, item.areaName, item.designCnt, item.designAreaSummary)
        + "</td></tr>";
}
// 增加房间个数可编辑操作
function buildEditInfoForRoomCnt(currVal, roomId) {
    var postEditValUrl = basePath + "/core/editRoomCntOnTime.json";
    return '<a href="#" name="roomCntForEdit-' + roomId + '" data-type="text" data-pk="'
        + roomId + '" data-url="' + postEditValUrl + '" data-title="输入设计房间个数:">' + currVal + '</a>';
}
// 增加房间使用面积可编辑操作
function buildEditInfoForRoomArea(currVal, roomId) {
    var postEditValUrl = basePath + "/core/editRoomAreaOnTime.json";
    return '<a href="#" name="roomAreaForEdit-' + roomId + '" data-type="text" data-pk="'
        + roomId + '" data-url="' + postEditValUrl + '" data-title="输入设计房间面积:">' + currVal + '</a>';
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

    // 格式化区域编码
    var areaCodes = translateCode(areaCode);

    // 初始化信息
    $('#addAreaInfoForm span[name="deptCode"]').html(areaCodes[0]);
    $('#addAreaInfoForm input[name="officeCode"]').val(areaCode);
    $('#addAreaInfoForm input[name="orderIdx"]').val(areaCodes[1]);
    $('#addAreaInfoForm input[name="officeName"]').val(areaName);
    $('#addAreaInfoForm input[name="note"]').val(note);

    // 保存操作动作
    areaAction = CONST_ACTION_EDIT;

    // 更新标题
    $('#areaInfoModalLabel').text(MODAL_TITLE_AREA[areaAction]);
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

    // 格式化房间编码
    var roomCodes = translateCode(roomCode);

    // 初始化信息
    $('#addRoomInfoForm span[name="areaCode"]').html(roomCodes[0]);
    $('#addRoomInfoForm input[name="roomCode"]').val(roomCode);
    $('#addRoomInfoForm input[name="orderIdx"]').val(roomCodes[1]);
    $('#addRoomInfoForm input[name="roomName"]').val(roomName);
    $('#addRoomInfoForm input[name="designCnt"]').val(roomCnt);
    $('#addRoomInfoForm input[name="designAreaSummary"]').val(roomArea);

    // 保存操作动作
    roomAction = CONST_ACTION_EDIT;

    // 更新标题
    $('#roomInfoModalLabel').text(MODAL_TITLE_ROOM[roomAction]);
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

    // 更新标题
    $('#roomInfoModalLabel').text(MODAL_TITLE_ROOM[roomAction]);

    // 获取父级编码
    var nextRoomCodeInfo = getNextRoomCodeByAreaId();
    if (nextRoomCodeInfo && nextRoomCodeInfo != "") {
        var nextRoomCodeInfos = translateCode(nextRoomCodeInfo);

        // 重新设置父级编码
        $('#addRoomInfoForm span[name="areaCode"]').html(nextRoomCodeInfos[0]);

        // 自动生成下一个区域编号
        $('#addRoomInfoForm input[name="orderIdx"]').val(nextRoomCodeInfos[1]);

        // 初始化部门编码
        $('#addRoomInfoForm input[name="roomCode"]').val(nextRoomCodeInfo);
    }
}
// 清空区域信息数据[模态对话框]
function clearAreaModalData() {
    //$('#addAreaInfoForm span[name="deptCode"]').html('');
    $('#addAreaInfoForm input[name="officeCode"]').val('');
    $('#addAreaInfoForm input[name="orderIdx"]').val('');
    $('#addAreaInfoForm input[name="officeName"]').val('');
    $('#addAreaInfoForm input[name="note"]').val('');
}
// 清空房间信息数据[模态对话框]
function clearRoomModalData() {
    //$('#addRoomInfoForm span[name="areaCode"]').html('');
    $('#addRoomInfoForm input[name="roomCode"]').val('');
    $('#addRoomInfoForm input[name="orderIdx"]').val('');
    $('#addRoomInfoForm input[name="roomName"]').val('');
    $('#addRoomInfoForm input[name="designCnt"]').val('');
    $('#addRoomInfoForm input[name="designAreaSummary"]').val('');
}
// 新增区域信息
function addAreaInfo() {
    var areaInfoForm = $('#addAreaInfoForm');
    var linkUrlForAreaInfo = areaInfoForm.attr('action');

    // 重新格式化区域编码
    var officeCode = $('#addAreaInfoForm span[name="deptCode"]').text() + $('#addAreaInfoForm input[name="orderIdx"]').val();
    $('#addAreaInfoForm input[name="officeCode"]').val(officeCode);

    var submitData = areaInfoForm.serialize();
    var appendData = "&projId=" + projId + "&deptTypeId=" + currDeptTypeId
        + "&deptId=" + currDeptId + "&action=" + areaAction + "&id=" + currAreaId;
    submitData += appendData;

    $.post(linkUrlForAreaInfo, submitData, function (outData) {
        Ewin.alert(outData.retMsg);
        if (outData.retCode == RET_CODE_SUCC) {
            // 清除校验状态
            paneAreaInfoValidator.resetForm(false);
            // 关闭模态对话框
            $('#addAreaInfoModal').modal('hide');
        }
    }, "json");
}
// 新增房间信息
function addRoomInfo() {
    var roomInfoForm = $('#addRoomInfoForm');
    var linkUrlForRoomInfo = roomInfoForm.attr('action');

    // 重新格式化区域编码
    var roomCode = $('#addRoomInfoForm span[name="areaCode"]').text() + $('#addRoomInfoForm input[name="orderIdx"]').val();
    $('#addRoomInfoForm input[name="roomCode"]').val(roomCode);

    var submitData = roomInfoForm.serialize();
    var appendData = "&projId=" + projId + "&deptTypeId=" + currDeptTypeId
        + "&deptId=" + currDeptId + "&areaId=" + currAreaId + "&action="
        + roomAction + "&id=" + currRoomId;
    submitData += appendData;

    $.post(linkUrlForRoomInfo, submitData, function (outData) {
        Ewin.alert(outData.retMsg);
        if (outData.retCode == RET_CODE_SUCC) {
            // 清除校验状态
            paneRoomInfoValidator.resetForm(false);
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

// 实时更新[部门汇总]规则面积系数
function editDeptPlanAreaRatioValOnTime() {
    if (currAreaSumyId && currAreaSumyId != 0) {
        var linkUrlForDeptAreaRatioUpdate = basePath + "/core/editDeptPlanAreaRatioValOnTime.json";
        var submitData = "areaSumyId=" + currAreaSumyId + "&areaRatio=" + $('#deptDetailInfo input[name="deptPlanAreaRatio"]').val();
        $.post(linkUrlForDeptAreaRatioUpdate, submitData, function (outData) {
            Ewin.alert(outData.retMsg).on(function () {
                if (outData.retCode == RET_CODE_SUCC) {
                    // 刷新汇总信息
                    refreshDeptPlanAreaTotalVal();
                }
            });
        }, "json");
    }
}
// 实时更新[部门汇总]设计面积系数
function editDeptDesignAreaRatioValOnTime() {
    if (currAreaSumyId && currAreaSumyId != 0) {
        var linkUrlForDeptAreaRatioUpdate = basePath + "/core/editDeptDesignAreaRatioValOnTime.json";
        var submitData = "areaSumyId=" + currAreaSumyId + "&areaRatio=" + $('#deptDetailInfo input[name="deptDesignAreaRatio"]').val();
        $.post(linkUrlForDeptAreaRatioUpdate, submitData, function (outData) {
            Ewin.alert(outData.retMsg).on(function () {
                if (outData.retCode == RET_CODE_SUCC) {
                    // 刷新汇总信息
                    refreshDeptDesignAreaTotalVal();
                }
            });
        }, "json");
    }
}
// 刷新[区域汇总]规划值小计信息
function refreshDeptPlanAreaTotalVal() {
    var planAreaRatio = $('#deptDetailInfo input[name="deptPlanAreaRatio"]').val();
    var planAreaTotal = $('#deptDetailInfo input[name="deptPlanAreaTotal"]').val();
    if (planAreaRatio != 0) {
        var planAreaSummary = parseFloat(planAreaTotal) / parseFloat(planAreaRatio);
        $('#deptDetailInfo input[name="deptPlanAreaSummary"]').val(fix2(planAreaSummary));
    }
}
// 刷新[区域汇总]设计值小计信息
function refreshDeptDesignAreaTotalVal() {
    var designAreaRatio = $('#deptDetailInfo input[name="deptDesignAreaRatio"]').val();
    var designAreaSummary = $('#deptDetailInfo input[name="deptDesignAreaSummary"]').val();

    var designAreaTotal = parseFloat(designAreaSummary) * parseFloat(designAreaRatio);
    $('#deptDetailInfo input[name="deptDesignAreaTotal"]').val(fix2(designAreaTotal));
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
function getNextAreaCodeByDeptId() {
    var getNextAreaCodeByDeptIdUrl = basePath + "/core/getNextAreaCodeByDeptId.json?deptId=" + currDeptId;
    var outData = synGet(getNextAreaCodeByDeptIdUrl);
    var deptCode;
    if (outData && outData.retCode == RET_CODE_SUCC) {
        deptCode = outData.retExtInfo;
    }
    return deptCode;
}
function checkAndFormatCodeForArea(item) {
    var currCode = $(item).parent().find('input[type="hidden"]').val();
    var areaCodes = translateCode(currCode);

    var currVal = $(item).val();
    if (currVal != areaCodes[1]) {
        var checkAreaCodeUrl = 'areaCodeExist.json';
        var submitData = 'deptId=' + currDeptId + "&orderIdx=" + currVal;

        $.post(checkAreaCodeUrl, submitData, function (outData) {
            if (outData.retCode == RET_CODE_SUCC) {
                Ewin.alert(outData.retMsg).on(function () {
                    $('#addAreaInfoForm input[name="orderIdx"]').val(areaCodes[1]);
                });
            } else {
                autoFormatNumber(item);
            }
        }, "json");
    }
}
function getNextRoomCodeByAreaId() {
    var getNextRoomCodeByAreaIdUrl = basePath + "/core/getNextRoomCodeByAreaId.json?areaId=" + currAreaId;
    var outData = synGet(getNextRoomCodeByAreaIdUrl);
    var areaCode;
    if (outData && outData.retCode == RET_CODE_SUCC) {
        areaCode = outData.retExtInfo;
    }
    return areaCode;
}
function checkAndFormatCodeForRoom(item) {
    var currCode = $(item).parent().find('input[type="hidden"]').val();
    var roomCodes = translateCode(currCode);

    var currVal = $(item).val();
    if (currVal != roomCodes[1]) {
        var checkRoomCodeUrl = 'roomCodeExist.json';
        var submitData = 'areaId=' + currAreaId + "&orderIdx=" + currVal;

        $.post(checkRoomCodeUrl, submitData, function (outData) {
            if (outData.retCode == RET_CODE_SUCC) {
                Ewin.alert(outData.retMsg).on(function () {
                    $('#addRoomInfoForm input[name="orderIdx"]').val(roomCodes[1]);
                });
            } else {
                autoFormatNumber(item);
            }
        }, "json");
    }
}

var paneAreaInfoValidOption = {
    message: "请输入有效的内容!",
    feedbackIcons: {
        valid: "fa fa-check",
        invalid: "fa fa-times",
        validatting: "fa fa-refresh"
    },
    fields: {
        officeName:{
            message: "区域名称信息有误!",
            validators: {
                stringLength: {
                    min: 4,
                    max: 128,
                    message: "区域名称不少于4个字符，不多于128个字符!"
                }
            }
        },
        note:{
            message: "区域备注信息有误!",
            validators: {
                stringLength: {
                    max: 128,
                    message: "区域备注不多于128个字符!"
                }
            }
        }
    }
};
var paneRoomInfoValidOption = {
    message: "请输入有效的内容!",
    feedbackIcons: {
        valid: "fa fa-check",
        invalid: "fa fa-times",
        validatting: "fa fa-refresh"
    },
    fields: {
        roomName:{
            message: "房间名称信息有误!",
            validators: {
                stringLength: {
                    min: 4,
                    max: 128,
                    message: "房间名称不少于4个字符，不多于128个字符!"
                }
            }
        }
    }
};