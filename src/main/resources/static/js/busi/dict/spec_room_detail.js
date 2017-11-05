var specRoomDetailTable;
var linkUrlForSpecRoomDetailInfo = basePath + "/dict/loadAllSpecRoomDetail.json";
var paneSize = {
    "height": "340px"
}

$(document).ready(function () {
    init();

    reloadSpecRoomDetail();
});

function init() {
    // 新增样板间操作
    $('#addSpecRoomDetailBtn').click(function () {
        addSpecRoomDetail();
    });

    // 样板间操作，重置页面选项，提交页面数据
    $('#resetSpecRoomDetailFormBtn').click(function () {
        clearModelInfo();
    });
    $('#submitSpecRoomDetailFormBtn').click(function () {
        saveOrUpdateSpecRoomDetail();
    });

    // 表格初始化
    initSpecRoomDataTables();
    // 表格双击事件初始化
    initSpecRoomDataTablesEvents();
}
function initSpecRoomDataTables() {
    specRoomDetailTable = $('#specRoomDetailList').DataTable({
        "ajax": linkUrlForSpecRoomDetailInfo,
        "bFilter": true,
        "bSort": false,
        "pagging": false,
        "columns": [
            {"data": "id"},
            {"data": "deptTypeCode"},
            {"data": "deptTypeName"},
            {"data": "specRoomName"},
            {"data": "createTime"},
            {"data": "updateTime"},
            {"data": "note"},
            {"data": "oper"}
        ],
        "aoColumnDefs": [
            {
                "targets": [0],
                "visible": false
            },
            {
                "targets": [1],
                "visible": false
            },
            {
                "aTargets": [7],
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html(appendOperInfoForEdit(oData.id,
                        oData.deptTypeCode, oData.deptTypeName, oData.specRoomName, oData.note));
                }
            }
        ]
        // "fnRowCallback": function (nRow, aData, iDataIndex) {
        //     var specRoomId = aData.id;
        //     var html = '<input type="radio" name="radio">';
        //     $('td:eq(0)', nRow).html(html);
        //     return nRow;
        // }
    });
}
// 添加列表监听事件
function initSpecRoomDataTablesEvents() {
    $('#specRoomDetailList tbody').on('dblclick', 'tr', function () {
        var data = specRoomDetailTable.row(this).data();
        goToSpecRoomParamPane(data.id, data.specRoomName, data.deptTypeName, data.deptTypeCode);
    });
}
// 跳转部门详情标签
function goToSpecRoomParamPane(specRoomId, specRoomName, deptTypeName, deptTypeCode) {
    currSpecRoomId = specRoomId;
    currSpecRoomName = specRoomName;
    currDeptTypeName = deptTypeName;
    currDeptTypeCode = deptTypeCode;

    $('.nav-tabs li:eq(1) a').tab('show');
}

function addSpecRoomDetail() {
    clearModelInfo();

    $('#saveSpecRoomDetailForm input[name="action"]').val(CONST_ACTION_ADD);
}
function editSpecRoomDetail(specRoomId, deptTypeCode, deptTypeName, specRoomName, note) {
    // 更新缓存数据
    currSpecRoomId = specRoomId;
    currSpecRoomName = specRoomName;
    currDeptTypeCode = deptTypeCode;
    currDeptTypeName = deptTypeName;

    clearModelInfo();

    $('#saveSpecRoomDetailForm .selectpicker').selectpicker('val', deptTypeCode);
    $('#saveSpecRoomDetailForm input[name="specRoomName"]').val(specRoomName);
    $('#saveSpecRoomDetailForm input[name="id"]').val(specRoomId);
    $('#saveSpecRoomDetailForm input[name="note"]').val(note);
    $('#saveSpecRoomDetailForm input[name="action"]').val(CONST_ACTION_EDIT);
}
function delSpecRoomDetail(specRoomId, specRoomName) {
    var linkUrlForSpecRoomDetailDel = basePath + '/dict/delSpecRoomData.json?specRoomId=' + specRoomId;

    var confirmMsg = "确定删除 [ " + specRoomName + " ] ?";
    var options = {
        "title": "删除样板房间",
        "message": confirmMsg
    };
    Ewin.confirm(options).on(function (ret) {
        if (ret) {
            $.post(linkUrlForSpecRoomDetailDel, function (outData) {
                Ewin.alert(outData.retMsg).on(function () {
                    if (outData.retCode === RET_CODE_SUCC) {
                        // 刷新列表数据
                        reloadSpecRoomDetail();
                    }
                });
            }, "json");
        }
    });
}
function saveOrUpdateSpecRoomDetail() {
    var specRoomDetailForm = $('#saveSpecRoomDetailForm');
    var linkUrlForSpecRoomDetail = specRoomDetailForm.attr('action');

    var deptTypeCode = $('#saveSpecRoomDetailForm select[name="deptTypeCode"]').val();
    $('#saveSpecRoomDetailForm input[name="deptTypeName"]').val(getCodeDesc('DEPT_TYPE', deptTypeCode));

    var specRoomName = $('#saveSpecRoomDetailForm input[name="specRoomName"]').val();
    var action = $('#saveSpecRoomDetailForm input[name="action"]').val();
    var whetherCheck = true;

    // 编辑信息，且未修改名称，不进行检查
    if (action == CONST_ACTION_EDIT && currSpecRoomName === specRoomName) {
        whetherCheck = false;
    }

    var outData;
    if (whetherCheck) {
        outData = checkSpecRoomNameExist(specRoomName, deptTypeCode);
    }
    if (whetherCheck && outData.retCode != RET_CODE_SUCC) {
        Ewin.alert(outData.retMsg);
        return;
    } else {
        $.post(linkUrlForSpecRoomDetail, specRoomDetailForm.serialize(), function (outData) {
            Ewin.alert(outData.retMsg).on(function () {
                if (outData.retCode === RET_CODE_SUCC) {
                    // 关闭模态对话框
                    $('#addSpecRoomDetailModal').modal('hide');
                    // 刷新列表数据
                    reloadSpecRoomDetail();
                }
            });
        }, "json");
    }
}

function clearModelInfo() {
    $('#saveSpecRoomDetailForm select[name="deptTypeCode"]').val('A');
    $('#saveSpecRoomDetailForm input[name="deptTypeName"]').val('');
    $('#saveSpecRoomDetailForm input[name="specRoomName"]').val('');
    $('#saveSpecRoomDetailForm input[name="id"]').val('');
    $('#saveSpecRoomDetailForm input[name="note"]').val('');
    $('#saveSpecRoomDetailForm input[name="action"]').val('');
}

function reloadSpecRoomDetail() {
    $('#specRoomDetailList').DataTable().ajax.url(linkUrlForSpecRoomDetailInfo).load();
}
function checkSpecRoomNameExist(specRoomName, deptTypeCode) {
    var linkUrlForSpecRoomNameCheck = "specRoomNameCheck.json?specRoomName="
        + specRoomName + "&deptTypeCode=" + deptTypeCode;
    return synGet(linkUrlForSpecRoomNameCheck);
}

function onLoading() {
    $('<div class="loading">正在加载……</div>').appendTo('body');
}
function appendOperInfoForEdit(specRoomId, deptTypeCode, deptTypeName, specRoomName, note) {
    return '<a class="fa fa-edit" onclick="editSpecRoomDetail('
        + specRoomId + ',\'' + deptTypeCode + '\',\'' + deptTypeName
        + '\',\''+ specRoomName + '\',\'' + note
        + '\')" href="" data-toggle="modal" data-target="#addSpecRoomDetailModal"'
        + ' style="margin-right: 10px; font-size: 22px" title="编辑样板房间"></a>'
        + ' <a class="fa fa-minus" onclick="delSpecRoomDetail('
        + specRoomId + ',\'' + specRoomName
        + '\')" href="javascript:void()" style="margin-right: 10px; font-size: 22px" title="删除样板房间"></a>';
}
