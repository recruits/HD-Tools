var specRoomDetailTable;
var linkUrlForSpecRoomDetailInfo = basePath + "/dict/loadAllSpecRoomDetail.json";
var allRoomSpecs = [];
var paneSize = {
    "height": "340px"
}
var cachedSpecRoomId = 0;// 缓存选择的样板房间编号

$(function(){
    initRoomInfo();

    initSpecRoomInfo();
});

function initRoomInfo() {
    // 激活面板，重新加载数据
    $('a[data-toggle="tab"][id="roomDataInfoTab"]').on('shown.bs.tab', function (e) {
        if (currRoomId && currRoomId != 0 && currAreaId && currAreaId != 0) {
            // 加载页面参数
            renderRoomDataSpecs();
            // 加载样式
            initRadioAndCheckbox();
            // 加载数据
            reloadRoomInfo();
            // 设置样板参数按钮可用
            resetSpecRoomInfo(false);
        } else {
            // 清空已选数据
            clearAllCheckVal();
            // 清空标题内容
            freshRoomTitle();
            // 禁用样板控件
            resetSpecRoomInfo(true);
        }
    });
}
function resetSpecRoomInfo(state) {
    // 设置样板参数按钮可用
    $('#selSpecRoomDataBtn').prop('disabled', state);

}
function initSpecRoomInfo() {
    // 初始化事件
    initSpecRoomEvents();
    // 表格初始化
    initSpecRoomDataTables();
    // 清空缓存数据
    clearSpecRoomId();
}
function initSpecRoomEvents() {
    $('#selSpecRoomDataBtn').click(function () {
        var options = {
            "message": "确定拷贝样板房间数据到当前房间?"
        }
        Ewin.confirm(options).on(function (ret) {
            if (ret) {
                // 清空数据选中状态
                $('input[name="spec_room_data_detail_radio"]').prop("checked", false);
                // 打开选择数据页面
                $('#selSpecRoomDataModal').modal();
            }
        });
    });
    $('#saveSpecRoomDataDetailBtn').click(function () {
        updateRoomDataBySpecRoomId();
    });
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
            {"data": "note"}
        ],
        "aoColumnDefs": [
            {
                "targets": [1],
                "visible": false
            }
        ],
        "fnRowCallback": function (nRow, aData, iDataIndex) {
            var specRoomId = aData.id;
            var html = '<input type="radio" onclick="cacheSpecRoomId(' + specRoomId + ')" name="spec_room_data_detail_radio">';
            $('td:eq(0)', nRow).html(html);
            return nRow;
        }
    });
}
function clearSpecRoomId() {
    cachedSpecRoomId = 0;
}
function cacheSpecRoomId(selSpecRoomId) {
    cachedSpecRoomId = selSpecRoomId;
}
function updateRoomDataBySpecRoomId() {
    if (cachedSpecRoomId != 0) {
        var linkUrlForUpdateRoomData = "updateRoomDataBySpecRoomId.json?roomId="
            + currRoomId + "&specRoomId=" + cachedSpecRoomId;

        $.post(linkUrlForUpdateRoomData, function (outData) {
            if (outData.retCode == RET_CODE_SUCC) {
                Ewin.alert("拷贝样板房间数据成功!");
                // 关闭参数选择页面
                closeSpecRoomDataModel();
                // 重新加载数据
                reloadRoomInfo();
            }
        });
    } else {
        Ewin.alert("请先选择样板房间!");
    }
}
function closeSpecRoomDataModel() {
    $('#selSpecRoomDataModal').modal('hide');
}
function updateRoomDataOnTime(val, action) {
    if (currRoomId && currRoomId !== '0') {
        var postRoomParamUrl = basePath + "/core/submitRoomDataOnTime.json?roomId="
            + currRoomId + "&value=" + val + "&action=" + action;
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
    } else {
        $('#roomDataInfo input[name="belongAreaCode"]').val('');
        $('#roomDataInfo input[name="belongAreaName"]').val('');
        $('#roomDataInfo input[name="currentRoomCode"]').val('');
        $('#roomDataInfo input[name="currentRoomName"]').val('');
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