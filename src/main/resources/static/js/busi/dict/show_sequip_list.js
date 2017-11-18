var linkUrlForSpecRoomDataInfo = basePath+"/dict/loadAllSpecRoomData.json";
$(document).ready(function () {
    init();

    reloadSpecRoomData();
});

function init() {
    // 表格初始化
    initSpecRoomDataTables();
}

function initSpecRoomDataTables() {
    specRoomDetailTable = $('#specRoomDataTable').DataTable({
        "ajax": linkUrlForSpecRoomDataInfo,
        "bFilter": true,
        "bSort": false,
        "pagging": false,
        "columns": [
            {"data": "moduleid"},
            {"data": "moduleIdx"},
            {"data": "moduleName"},
            {"data": "enumId"},
            {"data": "enumIdx"},
            {"data": "enumName"},
            {"data": "selType"},
            {"data": "paramIds"},
            {"data": "paramNames"},
            {"data": "oper"}
        ],
        "aoColumnDefs": [
            {
                "targets": [0],
                "visible": false
            },
            {
                "targets": [3],
                "visible": false
            }
        ]
    });
}

function reloadSpecRoomData() {

}