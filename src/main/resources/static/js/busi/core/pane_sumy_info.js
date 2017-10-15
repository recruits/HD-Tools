$(function () {
    initSumyInfo();
});

function initSumyInfo() {
    // 激活面板，重新加载数据
    $('a[data-toggle="tab"][id="summaryInfoTab"]').on('shown.bs.tab', function (e) {
        reloadSumyInfo();
    });

    // 部门信息操作按钮事件绑定
    $('#resetSubSumyInfoFormBtn').click(function () {
        clearDeptModalData();
    });
    $('#submitSubSumyInfoFormBtn').click(function () {
        addDepartment();
    });

    // 模态对话框关闭事件
    $('#addSubSumyInfoModal').on('hidden.bs.modal', function (e) {
        // 刷新数据
        reloadSumyInfo();
        // 清空模态数据
        clearDeptModalData();
    });

    // 汇总信息实时更新
    $('#sumyAreaRatioModBtn').click(editSumyAreaRatioValOnTime);
    $('#sumyNoteModBtn').click(editSumyNoteValOnTime);
}

// 刷新页面数据，重新加载整个页面
function reloadSumyInfo() {
    // 重新加载表格数据
    var linkUrlForSumyInfo = "loadAllSumyInfo.json?projId="+projId;
    $.get(linkUrlForSumyInfo, function (outData) {
        renderSumyInfoPage(outData);
    }, "json");

    // 重新加载汇总数据
    var linkUrlForSumyTitle = "loadSumyTitle.json?projId="+projId;
    $.get(linkUrlForSumyTitle, function (outData) {
        freshSumyTitle(outData);
    }, "json");
}

// 重新渲染表格
function renderSumyInfoPage(data) {
    if (data) {
        // 清空列表数据
        $('#sumy_info_tree_grid tr').remove();

        // 分级渲染表格
        $.each(data, function (index, item) {
            if (item.level == 1) {
                $('#sumy_info_tree_grid').append(buildSumyInfoTopLeveHtml(item));
            } else if (item.level == 2) {
                $('#sumy_info_tree_grid').append(buildSumyInfoSubLeveHtml(item));
            }
        });

        // 创建二级列表的图标
        $('#baseTab').treegrid({
            expanderExpandedClass: 'fa fa-minus',
            expanderCollapsedClass: 'fa fa-plus'
        });

        // 设置部门分类规划面积可编辑
        $('a[name^="planAreaForEdit-"]').editable({
            success: function (outData, newVal) {
                if (outData.retCode == RET_CODE_SUCC) {
                    reloadSumyInfo();
                }
            }
        });

        // 设置部门信息规划面积可编辑
        $('a[name^="planAreaForDeptEdit-"]').editable({
            success: function (outData, newVal) {
                if (outData.retCode == RET_CODE_SUCC) {
                    // 部门信息规划面积变更，导致部门分类规划面积变更，才进行页面刷新
                    if (outData.retExtInfo == 'fresh') {
                        reloadSumyInfo();
                    }
                }
            }
        });
    }
}
// 更新部门分类汇总数据
function freshSumyTitle(data) {
    if (data) {
        currDeptSumyId = data.id;
        $('input[name="sumyPlanArea"]').val(data.planArea);
        $('input[name="sumyDesignArea"]').val(data.designArea);
        $('input[name="sumyPlanAreaPersent"]').val(data.planAreaPersent+"%");
        $('input[name="sumyDesignAreaPersent"]').val(data.designAreaPersent+"%");
        $('input[name="sumyAreaRatio"]').val(data.areaRatio || "");
        $('input[name="sumyNote"]').val(data.note || "");
    }
}

// 创建表格一级列表数据
function buildSumyInfoTopLeveHtml(item) {
    return "<tr class='treegrid-" + item.id + "'><td>"
        + item.deptCode + "</td><td>" + item.deptName + "</td><td>"
        + buildEditInfoForPlanArea(item.planArea, item.id) + "</td><td>" + item.designArea + "</td><td>"
        + item.planAreaPersent + "%</td><td>" + item.designAreaPersent + "%</td><td>"
        + (item.node || "") + "</td><td>" + buildOperInfoForTopLevel(item.id) + "</td></tr>";
}
// 创建表格二级列表数据
function buildSumyInfoSubLeveHtml(item) {
    return "<tr class='treegrid-parent-" + item.pid + " treegrid-999999-" + item.id
        + "' ondblclick='goToDeptDetailPane(" + item.pid + "," + item.id + ")'><td>"
        + item.deptCode + "</td><td>" + item.deptName + "</td><td>"
        + buildEditInfoForDeptPlanArea(item.planArea, item.id) + "</td><td>" + item.designArea + "</td><td>"
        + (item.planAreaPersent || "") + "</td><td>" + (item.designAreaPersent || "") + "</td><td>"
        + (item.node || "") + "</td><td>" + buildOperInfoForSubLevel(item.id, item.deptCode, item.deptName, item.planArea) + "</td></tr>";
}
// 增加部门分类规划面积可编辑操作
function buildEditInfoForPlanArea(currVal, deptTypeId) {
    var postEditValUrl = basePath + "/core/editPlanAreaValOnTime.json";
    return '<a href="#" name="planAreaForEdit-' + deptTypeId + '" data-type="text" data-pk="'
        + deptTypeId + '" data-url="' + postEditValUrl + '" data-title="输入规划面积:">' + currVal + '</a>';
}
// 增加部门分类操作信息
function buildOperInfoForTopLevel(deptTypeId) {
    return '<a onclick="resetDeptTypeId(' + deptTypeId
        + ')" class="fa fa-plus addDeptInfoModal" data-toggle="modal" data-target="#addSubSumyInfoModal"'
        + ' style="margin-right: 10px; font-size: 20px" title="新增部门"></a>';
}
// 缓存当前正在操作的部门分类编号
function resetDeptTypeId(deptTypeId) {
    currDeptTypeId = deptTypeId;
    deptAction = CONST_ACTION_ADD;
}
// 清空部门信息数据[模态对话框]
function clearDeptModalData() {
    $('#addSubSumyInfoForm input[name="deptName"]').val('');
    $('#addSubSumyInfoForm input[name="deptCode"]').val('');
    $('#addSubSumyInfoForm input[name="planArea"]').val('');
}

// 增加部门分类规划面积可编辑操作
function buildEditInfoForDeptPlanArea(currVal, deptId) {
    var postEditValForDeptUrl = basePath + "/core/editPlanAreaValForDeptOnTime.json";
    return '<a href="#" name="planAreaForDeptEdit-' + deptId + '" data-type="text" data-pk="'
        + deptId + '" data-url="' + postEditValForDeptUrl + '" data-title="输入规划面积:">' + currVal + '</a>';
}
// 增加部门信息操作信息
function buildOperInfoForSubLevel(deptId, deptCode, deptName, planArea) {
    return '<a onclick="modDeptInfo(' + deptId + ',\'' + deptCode + '\',\'' + deptName + '\',' + planArea
        + ')" class="fa fa-edit addDeptInfoModal" data-toggle="modal" data-target="#addSubSumyInfoModal"'
        + ' style="margin-right: 10px; font-size: 20px" title="修改部门信息"></a>'
        + '<a onclick="delDeptInfo(' + deptId + ',\'' + deptName + '\''
        + ')" href="#" class="fa fa-minus"'
        + ' style="margin-right: 10px; font-size: 20px" title="删除部门信息"></a>';
}
// 修改部门信息
function modDeptInfo(deptId, deptCode, deptName, planArea) {
    currDeptId = deptId;
    // 初始化信息
    $('input[name="deptName"]').val(deptName);
    $('input[name="deptCode"]').val(deptCode);
    $('input[name="planArea"]').val(planArea);
    // 保存操作动作
    deptAction = CONST_ACTION_MOD;
}
// 缓存当前正在操作的部门信息编号
function delDeptInfo(deptId, deptName) {
    var options = {
        title: "删除部门信息",
        message: "是否确定删除部门 [ "+deptName+" ]?",
    };
    Ewin.confirm(options).on(function (delFlag) {
        if(delFlag){
            var postDelDeptInfoUrl = basePath + "/core/delDeptInfoByDeptId.json?deptId=" + deptId;
            $.post(postDelDeptInfoUrl, function (outData) {
                if (outData.retCode == RET_CODE_SUCC) {
                    reloadSumyInfo();
                    currDeptId = '';
                }
            }, "json");
        }
    });
}
// 新增部门信息
function addDepartment() {
    var sumyInfoForm = $('#addSubSumyInfoForm');
    var linkUrlForDeptInfo = sumyInfoForm.attr('action');

    var submitData = sumyInfoForm.serialize();
    var appendData = "&projId=" + projId + "&deptTypeId=" + currDeptTypeId + "&action=" + deptAction + "&id=" + currDeptId;
    submitData += appendData;

    $.post(linkUrlForDeptInfo, submitData, function (outData) {
        Ewin.alert(outData.retMsg);
        if(outData.retCode == RET_CODE_SUCC){
            // 关闭模态对话框
            $('#addSubSumyInfoModal').modal('hide');
        }
    }, "json");
}
// 跳转部门详情标签
function goToDeptDetailPane(deptTypeId, deptId) {
    currDeptTypeId = deptTypeId;
    currDeptId = deptId;

    $('.nav-tabs li:eq(2) a').tab('show');
}

// 实时更新面积系数
function editSumyAreaRatioValOnTime() {
    if (currDeptSumyId && currDeptSumyId != 0) {
        var linkUrlForSumyAreaRatioUpdate = basePath + "/core/editSumyAreaRatioValOnTime.json";
        var submitData = "sumyId=" + currDeptSumyId + "&areaRatio=" + $('input[name="sumyAreaRatio"]').val();
        $.post(linkUrlForSumyAreaRatioUpdate, submitData, function (outData) {
            Ewin.alert(outData.retMsg);
        }, "json");
    }
}
// 实时更新备注信息
function editSumyNoteValOnTime() {
    if (currDeptSumyId && currDeptSumyId != 0) {
        var linkUrlForSumyNoteUpdate = basePath + "/core/editSumyNoteValOnTime.json";
        var submitData = "sumyId=" + currDeptSumyId + "&note=" + $('input[name="sumyNote"]').val();
        $.post(linkUrlForSumyNoteUpdate, submitData, function (outData) {
            Ewin.alert(outData.retMsg);
        }, "json");
    }
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

