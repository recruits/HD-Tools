var projInfoTable;
// 加载表格数据
var linkUrlForProjInfo = "loadAllProjInfo.json";
$(function () {
    reloadProjInfo();
});
function addProject() {
    var linkUrl = $('#editProjectItem')[0].href;
    window.parent.frames['mainFrame'].location.href = linkUrl;
}
// 加载数据
function reloadProjInfo() {

    projInfoTable = $("#proj_info_datatable").dataTable({
        "bDeferRender": true,
        "sPaginationType": "full_numbers",
        "ajax": linkUrlForProjInfo,
        "columns": [
            {"data": "id"},
            {"data": "projName"},
            {"data": "regionCode"},
            {"data": "projType"},
            {"data": "projPhase"},
            {"data": "createTime"},
            {"data": "verInfo"},
            {"data": "note"}
        ],
        "order": [[0, "desc"]],
        "aoColumnDefs": [
            {
                "aTargets": [2],
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var sDataName = getCodeDesc('AREA_CODE', sData);
                    $(nTd).html(sDataName);
                }
            },
            {
                "aTargets": [4],
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if (sData) {
                        var sDataName = getCodeDesc('PROJ_PHASE', sData);
                        $(nTd).html(sDataName);
                    }
                }
            },
            {
                "aTargets": [7],
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html(appendOperInfoForOper(oData.id, oData.projPhase));
                }
            }
        ]
    });
}

function refreshProjInfo() {
    $('#proj_info_datatable').DataTable().ajax.url(linkUrlForProjInfo).load();
}

function appendOperInfoForOper(projId, projPhase) {
    return '<a class="fa fa-edit" href="'
        + basePath
        + '/core/addOrEditItem.json?action=edit&projId=' + projId
        + '&projPhase=' + projPhase
        + '" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a>'
        + ' <a class="fa fa-copy" href="javascript:void()" onclick="cloneProjectConfirm(' + projId + ')"'
        + ' style="margin-right: 10px; font-size: 22px" title="复制项目"></a>'
}

function cloneProjectConfirm(projId) {
    var options = {
        message: "确定把当前项目复制为一个新项目?"
    };
    Ewin.confirm(options).on(function (action) {
        if (action) {
            cloneProject(projId);
        }
    });
}

function cloneProject(projId) {
    var linkUrlForProjRelease = 'cloneProject.json';
    var submitData = {
        "projId": projId
    };
    var outData = synPost(linkUrlForProjRelease, submitData);
    Ewin.alert(outData.retMsg).on(function () {
        if (outData.retCode === RET_CODE_SUCC) {
            refreshProjInfo();
        }
    });
}