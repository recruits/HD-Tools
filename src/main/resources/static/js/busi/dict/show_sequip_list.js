// 房间数据
var linkUrlForSpecRoomDataInfo = basePath + "/dict/loadAllSpecRoomData.json";
// 当前模块参数列表
var currEnumParams = [];
// 全部模块参数列表
var specRoomDetailTable;
// 当前模块参数表格
var specRoomParamTable;
// 表单数据
var specRoomDataParamForm;
// 表单校验
var specRoomDataParamValidator;
// 房间参数操作标记
var specRoomParamAction = CONST_ACTION_ADD;

$(document).ready(function () {
    init();

    reloadSpecRoomData();
});

function init() {
    // 表格初始化
    initSpecRoomDataTables();
    // 按钮初始化
    initBtnAction();

    // 房间信息校验器
    specRoomDataParamForm = $('#specRoomDataParamForm');
    // 绑定校验事件
    specRoomDataParamForm.bootstrapValidator(specRoomparamValidOption);
    // 创建校验器
    specRoomDataParamValidator = specRoomDataParamForm.data('bootstrapValidator');

    // 模态框清理
    initModelAction();
}

function initModelAction() {
    // 模态框关闭事件
    $('#specRoomDataModal').on('hidden.bs.modal', function (e) {
        // 清空表单
        // specRoomDataParamForm.reset();
        $(this).find('form')[0].reset();
        // 清空校验状态
        $('#specRoomDataParamForm').data('bootstrapValidator').destroy();
        $('#specRoomDataParamForm').data('bootstrapValidator', null);
        // 销毁表格
        specRoomParamTable.clear().draw();
    })
}

function initBtnAction() {
    // 新增房间参数按钮初始化
    $('#addNewRoomDataParams').click(function () {
        currEnumParams = [];

        specRoomParamAction = CONST_ACTION_ADD;

        $('#specRoomDataModal').modal();

        initSpecRoomParamTables();

        // 更新标题
        $('#specRoomDataModalLabel').text(MODAL_TITLE_PARAM[specRoomParamAction]);
    });
    // 修改房间参数按钮初始化
    $('#modNewRoomDataParams').click(function () {
        currEnumParams = [];

        // 判断是否选中待修改参数
        var selRow = specRoomDetailTable.row('.selected');

        if (selRow.length === 0) {
            Ewin.alert("请先选中修改的行!");
            return;
        }
        specRoomParamAction = CONST_ACTION_EDIT;

        $('#specRoomDataModal').modal();

        initSpecRoomParamTables();

        // 初始化房间参数信息
        loadParamsIntoPage(selRow.data());

        // 更新标题
        $('#specRoomDataModalLabel').text(MODAL_TITLE_PARAM[specRoomParamAction]);
    });
    // 增加参数信息按钮初始化
    $('#addSpecRoomParamBtn').click(function () {
        addRoomParamVals();
    });
    // 删除参数信息按钮初始化
    $('#delSpecRoomParamBtn').click(function () {
        delRoomParamVals();
    });
    // 保存房间参数按钮初始化
    $('#specRoomDataParamSubmitBtn').bind('click', function () {
        // 校验参数信息
        specRoomDataParamValidator.validate();
        // 提交参数信息
        if (specRoomDataParamValidator.isValid()) {
            addSpecRoomParam();
        }
    });
}
// 把待修改的参数信息加载到页面上
function loadParamsIntoPage(selData) {
    // 设置参数
    $('#specRoomDataModal select[name="moduleId"]').selectpicker('val', selData.dataId);
    $('#specRoomDataModal input[name="enumId"]').val(selData.enumId);
    $('#specRoomDataModal input[name="oldEnumName"]').val(selData.enumName);
    $('#specRoomDataModal input[name="enumName"]').val(selData.enumName);
    $('#specRoomDataModal input[name="enumIdx"]').val(parseInt(selData.enumIdx));
    $('#specRoomDataModal select[name="selType"]').selectpicker('val', selData.selType);

    var paramNames = selData.paramNames;
    if(paramNames){
        var paramNameArry = paramNames.split("/");
        if(paramNameArry && paramNameArry.length > 0){
            if(currEnumParams.length === 0){
                currEnumParams = paramNameArry;
            } else {
                currEnumParams.concat(paramNameArry);
            }
            refreshData();
        }
    }
}

function initSpecRoomDataTables() {
    specRoomDetailTable = $('#specRoomDataTable').DataTable({
        "ajax": linkUrlForSpecRoomDataInfo,
        "bFilter": true,
        "bSort": false,
        "pagging": false,
        "columns": [
            {"data": "moduleId"},
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
                "targets": [1],
                "visible": false
            },
            {
                "targets": [3],
                "visible": false
            },
            {
                "targets": [7],
                "visible": false
            },
            {
                "targets": [9],
                "visible": false
            }
        ]
    });

    // 设置一行选中高亮显示
    $('#specRoomDataTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            specRoomDetailTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
}
// 房间参数表格初始化
function initSpecRoomParamTables() {
    if ($.fn.dataTable.isDataTable('#specRoomParamTable')) {
        specRoomParamTable = $('#specRoomParamTable').DataTable();
    } else {
        specRoomParamTable = $('#specRoomParamTable').DataTable({
            "bPaginate": false, //翻页功能
            "bLengthChange": false, //改变每页显示数据数量
            "bFilter": false, //过滤功能
            "bSort": false, //排序功能
            "bInfo": false,//页脚信息
            "bAutoWidth": true,//自动宽度
            "aoColumnDefs": [
                {
                    "targets": [2],
                    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html('<a class="fa fa-minus" href="javascript:void(0)" onclick="delRoomParamVal(\''
                            + oData[1] + '\')" style="margin-right: 10px; font-size: 22px" title="删除参数"></a>');
                    }
                },
            ]
        });
    }
}

function reloadSpecRoomData() {
    specRoomDetailTable.ajax.url(linkUrlForSpecRoomDataInfo).load();
}

function addSpecRoomParam() {
    var linkUrlForSpecRoomParam = specRoomDataParamForm.attr('action');

    // 取出参数列表
    var tableRows = specRoomParamTable.data();
    var roomParams = [];
    if (tableRows && tableRows.length > 0) {
        $.each(tableRows, function (index, item) {
            roomParams.push(item[0] + '-' + item[1]);
        });
    }

    var submitData = specRoomDataParamForm.serialize();
    var appendData = "&paramNames=" + roomParams.join(',') + "&action=" + specRoomParamAction;
    submitData += appendData;

    $.post(linkUrlForSpecRoomParam, submitData, function (outData) {
        Ewin.alert(outData.retMsg).on(function () {
            if (outData.retCode == RET_CODE_SUCC) {
                // 清除校验状态
                specRoomDataParamValidator.resetForm(false);
                // 关闭模态对话框
                $('#specRoomDataModal').modal('hide');
                // 刷新表格数据
                reloadSpecRoomData();
            }
        });
    }, "json");
}

/**
 * 新增参数
 *   1. 判断当前参数是否已存在；如果存在，跳转5
 *   2. 把当前参数加入到数据集；
 *   3. 清空table数据重新绘制；
 *   4. 使用新数据集合绘制表格；
 *   5. 结束本次操作
 */
function addRoomParamVals() {
    var currVal = $('#specRoomDataModal input[name="singleParamName"]').val();

    if (currVal) {
        var currValArry = currVal.split(/[,，;；、]/);
        if (currValArry.length > 1) {
            $.each(currValArry, function (index, item) {
                addOneRoomParamVal(item);
            });
        } else {
            addOneRoomParamVal(currVal);
        }

        refreshData();
    }
}
// 增加房间参数
function addOneRoomParamVal(paramVal) {
    if (paramVal && paramVal !== '' && (currEnumParams.length == 0 || !checkIfParamExist(paramVal))) {
        currEnumParams.push(paramVal);
    }
}
/**
 * 删除全部参数
 */
function delRoomParamVals() {
    // 删除全部数据
    currEnumParams.length = 0;
    // 重绘表格
    refreshData();
}
// 删除房间参数
function delRoomParamVal(pavamVal) {
    // 删除数据
    currEnumParams.splice($.inArray(pavamVal, currEnumParams), 1);
    // 重绘表格
    refreshData();
}

// 判断当前参数是否已经存在
function checkIfParamExist(paramVal) {
    paramVal = $.trim(paramVal);
    return $.inArray(paramVal, currEnumParams) !== -1;
}

// 刷新表格
function refreshData() {
    // 先清空表格数据
    specRoomParamTable.rows().clear().draw();
    // 重新绘制表格
    $.each(currEnumParams, function (index, item) {
        var currData = [index + 1, item, ""];
        specRoomParamTable.row.add(currData).draw();
    });
}

var specRoomparamValidOption = {
    message: "请输入有效的内容!",
    feedbackIcons: {
        valid: "fa fa-check",
        invalid: "fa fa-times",
        validatting: "fa fa-refresh"
    },
    fields: {
        enumName: {
            message: "模块名称信息有误!",
            validators: {
                stringLength: {
                    min: 2,
                    max: 128,
                    message: "模块名称不少于2个字符，不多于128个字符!"
                },
                threshold: 2, //有2字符以上才发送ajax请求
                remote: {
                    url: basePath + '/dict/ifEnumNameExist.json',
                    delay: 600,
                    data: {
                        moduleId: function () {
                            return $('#specRoomDataModal select[name="moduleId"]').selectpicker('val');
                        },
                        enumName: function () {
                            return $.trim($('#specRoomDataModal input[name="enumName"]').val());
                        },
                        ignoreCheck :function () {
                            var oldName = $.trim($('#specRoomDataModal input[name="oldEnumName"]').val());
                            var currName = $.trim($('#specRoomDataModal input[name="enumName"]').val());
                            return oldName === currName ? true : false;
                        }
                    },
                    message: "模块名称已经存在，请使用新的名称!"
                }
            }
        }
    }
};