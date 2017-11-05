/***********************************************************************************
 **  重复代码，加载房间参数的代码，将来需要重构，最好做成组件
 **********************************************************************************/
var allRoomSpecs = [];
var loadParamFlag = false;
$(function () {
    // 激活面板，重新加载数据
    $('a[data-toggle="tab"][id="specRoomParamTab"]').on('shown.bs.tab', function (e) {
        if (currDeptTypeCode && currDeptTypeCode != '' && currSpecRoomId && currSpecRoomId != 0) {
            // 初始化参数面板
            initSpecRoomParams();
            // 重新加载数据
            reloadRoomInfo();
        }
    });
});

function initSpecRoomParams() {
    // 初始化房间参数
    if(!loadParamFlag){
        // 加载面板参数
        renderRoomDataSpecs();
        // 加载按钮样式
        initRadioAndCheckbox();

        loadParamFlag = true;
    }
    // 初始化样板房间信息
    $('#specRoomParamPane input[name="applyDeptName"]').val(currDeptTypeName);
    $('#specRoomParamPane input[name="currentRoomName"]').val(currSpecRoomName);
}

function initRadioAndCheckbox() {
    // 设置样式
    $("#roomDataSpecDiv input").iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });
    // 绑定选中事件
    $('#roomDataSpecDiv input').on('ifChecked', function (event) {
        var checkVal = event.target.id;
        updateRoomDataOnTime(checkVal, CONST_ACTION_ADD);
    });
    // 绑定取消选中事件
    $('#roomDataSpecDiv input').on('ifUnchecked', function (event) {
        var inputType = event.target.type;

        if (inputType == CONST_INPUT_TYPE_CHECKBOX) {
            var checkVal = event.target.id;
            updateRoomDataOnTime(checkVal, CONST_ACTION_DEL);
        }
    });
}
function updateRoomDataOnTime(val, action) {
    if (currSpecRoomId && currSpecRoomId !== 0) {
        var postRoomParamUrl = basePath + "/dict/submitRoomDataOnTime.json?specRoomId="
            + currSpecRoomId + "&value=" + val + "&action=" + action;
        $.post(postRoomParamUrl, function (outData) {
            if (outData.retCode == RET_CODE_SUCC) {
                // 暂时无事可做
            }
        }, "json");
    }
}
function reloadRoomInfo() {
    // 清空已选中项
    clearAllCheckVal();

    if (currSpecRoomId && currSpecRoomId != 0) {
        // 重新加载参数数据
        var linkUrlForRoomInfo = "loadCurrRoomDeail.json?specRoomId=" + currSpecRoomId;
        $.get(linkUrlForRoomInfo, function (outData) {
            freshParamInfo(outData);
        }, "json");
    }
}
function clearAllCheckVal() {
    $("#roomDataSpecDiv input").iCheck("uncheck");
}
function freshParamInfo(data) {
    if (data) {
        $.each(data, function (index, item) {
            $('#' + item).iCheck('check');
        });
    }
}
function renderRoomDataSpecs() {
    // 加载所有规格数据
    loadAllRoomSpecs();
    // 开始渲染页面内容
    var roomDataSpecDiv = $('#roomDataSpecDiv');
    // 先清空原有页面
    roomDataSpecDiv.empty();
    // 分组创建面板
    var currGroupDiv;
    $.each(allRoomSpecs, function (index, item) {
        if (index % 3 == 0) {
            currGroupDiv = $('<div class="row" id="roomDataSpecDiv">');
            currGroupDiv.appendTo(roomDataSpecDiv);
        }
        buildOneSiglePane(currGroupDiv, item.code, item.name, item.subs);
    });
}

function buildOneSiglePane(groupDiv, mCode, title, subItems) {
    // 创建表格元素
    var itemDiv = $('<div class="col-xs-4"></div>');
    itemDiv.appendTo(roomDataSpecDiv);
    // 创建面板
    var paneDiv = $('<div class="panel panel-default"></div>');
    paneDiv.appendTo(itemDiv);
    // 面板标题
    var paneHead = $('<div class="panel-heading"></div>');
    paneHead.append(buildPaneHeadTitle(title));
    paneHead.appendTo(paneDiv);
    // 面板内容
    var paneBody = $('<div class="panel-body"></div>');
    buildPaneBodyCont(paneBody, mCode, subItems);
    paneBody.appendTo(paneDiv);
}

function buildPaneHeadTitle(title) {
    return $('<h3 class="panel-title">'+title+'</h3>');
}

function buildPaneBodyCont(paneBody, mCode, subItems) {
    if(subItems != undefined && subItems.length > 0){
        paneBody.css("height", paneSize.height);

        var itemNum = subItems.length;
        $.each(subItems, function (index, item) {
            // 创建模块枚举内容
            var enumItem = $('<div style="font-size: 12px; margin-bottom: 6px; line-height: 16px"></div>');
            // 枚举标题
            enumItem.append(buildEnumTitle(item.name));
            // 枚举参数
            var paramItem = $('<div class="row"></div>');
            buildEnumParams(paramItem, mCode, item.code, item.type, item.subs);
            enumItem.append(paramItem);

            if(index != itemNum-1){
                // 创建参数分隔符
                var sepInfo = $('<hr>');
                enumItem.append(sepInfo);
            }

            // 绑定面板
            enumItem.appendTo(paneBody);
        });
    }
}

function buildEnumTitle(title) {
    return $('<h5>'+title+'</h5>');
}

function buildEnumParams(enumItem, mCode, eCode, eType, subItems) {
    if (subItems != undefined && subItems.length > 0) {
        var currInputName = mCode + "_" + eCode;
        $.each(subItems, function (index, item) {
            var currInputId = currInputName + "_" + item.code;
            var paramInfo = '<div class="col-xs-4" style="margin-bottom: 5px;">'
                + '<input type="' + eType + '" id="' + currInputId + '"name="' + currInputName
                + '"><lable style="margin-left: 5px;">' + item.name + '</lable></div>';
            enumItem.append(paramInfo);
        });
    }
}
function loadAllRoomSpecs() {
    // 重新加载表格数据
    var linkUrlForSpecInfo = basePath + "/core/loadAllRoomSpecs.json";

    $.ajax({
        type: "GET",
        url: linkUrlForSpecInfo,
        async: false,
        dataType: "json",
        success: function (outData) {
            allRoomSpecs = outData;
        }
    });
}