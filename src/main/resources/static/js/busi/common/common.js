// ---------- 常量数据，使用大写、下划线分隔
var RET_CODE_SUCC = '0000';
var CONST_ACTION_ADD = 'add';
var CONST_ACTION_MOD = 'mod';
// ---------- 常量对象，使用小写、下划线分隔
// 配置常量对象，命名以 cfg 开始
var cfg_dt_lang = {
    "sProcessing": "处理中...",
    "sLengthMenu": "显示 _MENU_ 条 ",
    "sZeroRecords": "没有您要查询的内容",
    "sInfo": "显示从 _START_ 到 _END_ 条 共 _TOTAL_ 条",
    "sInfoEmpty": "记录数为0",
    "sInfoFiltered": "(全部记录数 _MAX_  条)",
    "sInfoPostFix": "",
    "sSearch": "查询：",
    "sUrl": "",
    "sLoadingRecords": "",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上一页",
        "sNext": "下一页",
        "sLast": "末页"
    }
};
var cfg_dt_options={
    "destroy": true,
    "oLanguage": cfg_dt_lang,
    "bRetrieve": true,
    "paging": true,
    "ordering": false,
    "info": true,
    "searching": true
};