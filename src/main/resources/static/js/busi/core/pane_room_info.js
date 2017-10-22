var allRoomSpecs = [];
var paneSize = {
    "height": "340px"
}

$(function(){
    initRoomInfo();
});

function initRoomInfo() {
    // 激活面板，重新加载数据
    $('a[data-toggle="tab"][id="roomDataInfoTab"]').on('shown.bs.tab', function (e) {
        if (currRoomId && currAreaId) {
            // 加载页面参数
            renderRoomDataSpecs();
            // 加载样式
            initRadioAndCheckbox();
            // 加载数据
            reloadRoomInfo();
        }
    });
}
function updateRoomDataOnTime(val) {
    if (currRoomId && currRoomId !== '0') {
        var postRoomParamUrl = basePath + "/core/submitRoomDataOnTime.json?roomId=" + currRoomId + "&value=" + val;
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
    
    if (currRoomId && currAreaId) {
        // 重新加载参数数据
        var linkUrlForRoomInfo = "loadCurrRoomDeail.json?roomId=" + currRoomId;
        $.get(linkUrlForRoomInfo, function (outData) {
            freshParamInfo(outData);
        }, "json");

        // 重新加载汇总数据
        var linkUrlForRoomTitle = "loadCurrRoomTitle.json?areaId=" + currAreaId + "&roomId=" + currRoomId;
        $.get(linkUrlForRoomTitle, function (outData) {
            freshRoomTitle(outData);
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
function freshRoomTitle(data) {
    if (data) {
        $('#roomDataInfo input[name="belongAreaCode"]').val(data.belongAreaCode);
        $('#roomDataInfo input[name="belongAreaName"]').val(data.belongAreaName);
        $('#roomDataInfo input[name="currentRoomCode"]').val(data.currentRoomCode);
        $('#roomDataInfo input[name="currentRoomName"]').val(data.currentRoomName);
    }
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
        updateRoomDataOnTime(checkVal);
    });
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
            buildEnumParams(paramItem, mCode, item.code, item.subs);
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

function buildEnumParams(enumItem, mCode, eCode, subItems) {
    if (subItems != undefined && subItems.length > 0) {
        var currInputName = mCode + "_" + eCode;
        $.each(subItems, function (index, item) {
            var currInputId = currInputName + "_" + item.code;
            var paramInfo = '<div class="col-xs-4" style="margin-bottom: 5px;">'
                + '<input type="radio" id="' + currInputId
                + '"name="' + currInputName
                + '"><lable style="margin-left: 5px;">'
                + item.name
                + '</lable></div>';
            enumItem.append(paramInfo);
        });
    }
}
function loadAllRoomSpecs() {
    // 重新加载表格数据
    var linkUrlForSpecInfo = "loadAllRoomSpecs.json";

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