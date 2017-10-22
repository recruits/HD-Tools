var projInfoTable;
$(function () {
    reloadProjInfo();
});
function addProject() {
    var linkUrl = $('#editProjectItem')[0].href;
    window.parent.frames['mainFrame'].location.href = linkUrl;
}
// 加载数据
function reloadProjInfo() {
    // 加载表格数据
    var linkUrlForProjInfo = "loadAllProjInfo.json";
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
                    $(nTd).html(appendOperInfoForEdit(oData.id, oData.projPhase));
                }
            }
        ]
    });
}

function appendOperInfoForEdit(projId, projPhase) {
    return '<a class="fa fa-edit" href="'
        + basePath
        + '/core/addOrEditItem.json?action=edit&projId=' + projId
        + '&projPhase=' + projPhase
        + '" target="mainFrame" style="margin-right: 10px; font-size: 22px" title="编辑项目"></a>'
}
