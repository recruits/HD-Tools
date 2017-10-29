// ---------- 常量数据，使用大写、下划线分隔
var RET_CODE_SUCC = '0000';
var CONST_ACTION_ADD = 'add';
var CONST_ACTION_EDIT = 'edit';
var CONST_ACTION_DEL = 'del';
var CONST_TC_NAME = 'typeCode';

var CONST_INPUT_TYPE_RADIO = 'radio';
var CONST_INPUT_TYPE_CHECKBOX = 'checkbox';

var CONST_CODE_SEPRATOR = '-';

var MODAL_TITLE_DEPT = {
    'add': "新增部门信息",
    'edit': "编辑部门信息"
};
var MODAL_TITLE_AREA = {
    'add': "新增区域信息",
    'edit': "编辑区域信息"
};
var MODAL_TITLE_ROOM = {
    'add': "新增房间信息",
    'edit': "编辑房间信息"
};
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
var cfg_dt_options = {
    "destroy": true,
    "oLanguage": cfg_dt_lang,
    "bRetrieve": true,
    "paging": true,
    "ordering": false,
    "info": true,
    "searching": true
};
// ---------- 翻译标签
/***************************************************************************
 *
 * 下拉列表框组件初始化;
 *
 **************************************************************************/
var smCodes = {};
$(function () {
    initComponents();
});
function initComponents() {
    collectAndInitTypeCode();
    initSelectComponents();
};
function collectAndInitTypeCode() {
    //收集typeCodes
    var typeCodeArr = [];
    // 收集select组件的typecode
    $(".typecode_for_select").each(function () {
        var typeCode = $(this).attr(CONST_TC_NAME);
        if (!isNull(typeCode) && !smCodes[typeCode.toLowerCase()]) {
            typeCodeArr.push(typeCode);
        }
    });
    if (typeCodeArr.length > 0) {
        var params = typeCodeArr.join(",");
        updateSmCodes(params);
    }
};
function initSelectComponents() {
    $(".typecode_for_select").each(function () {
        var $self = $(this);
        var typeCode = $self.attr(CONST_TC_NAME);

        if (isNull(typeCode)) {
            return;
        }
        typeCode = typeCode.toLowerCase();

        var currItemVal = $self.val();
        var typeCodeItem = smCodes[typeCode];
        if (!isNull(typeCodeItem)) {
            $.each(typeCodeItem, function (index, item) {
                var currOptionId = item.id;
                var selectedFlag = isEqual(currItemVal, currOptionId) ? 'selected' : ' ';
                var option_tag = $('<option value="' + currOptionId + '" ' + selectedFlag + '>' + item.text + '</option>');
                //$self.append(option_tag);
                option_tag.appendTo($self);
            });
        }
    });
};
/***************************************************************************
 *
 * 通过类型码和参数值，获取对应的文本，例如：var desc = $.getCodeDesc("sm_sex","01");
 * （如果需要在.ftl中翻译码表，可使用方法：${getCodeDesc("sm_sex","01")} ）
 *
 **************************************************************************/
function getCodeDesc(typeCode, paramCode, defaultDesc, parentValue) {
    if (!smCodes[typeCode.toLowerCase()]) {
        updateSmCodes(typeCode);
    }
    try {
        var codes = smCodes[typeCode.toLowerCase()];
        var result = recursiveCode(codes, typeCode, paramCode, parentValue);
        if (!result) {
            result = defaultDesc;
        }
        return result;
    }
    catch (err) {
    }
};
function updateSmCodes(params) {
    var tempObj = null;
    $.ajax({
        url: basePath + '/system/getTypeCodes.json?typeCodes=' + params,
        dataType: "json",
        async: false,
        success: function (data) {
            tempObj = data;
        }
    });
    $.extend(smCodes, tempObj);
};
function recursiveCode(codes, typeCode, paramCode, parentValue) {
    for (var i = 0; i < codes.length; i++) {
        if (parentValue) {
            if (codes[i].id == paramCode && codes[i].parentId == parentValue) {
                return codes[i].text;
            }
        } else {
            if (codes[i].id == paramCode) {
                return codes[i].text;
            }
        }
        var children = codes[i].children;
        if (children) {
            var result = recursiveCode(children, typeCode, paramCode, parentValue);
            if (!isNull(result)) {
                return result;
            }
        }

    }
}
function getParamDescByCode(items, code) {
    var desc = "";
    if (items && code) {
        $.each(items, function (index, item) {
            if (isEqual(code, item.id)) {
                desc = item.text;
                return false;
            }
        })
    }
    return desc;
}
/***************************************************************************
 * 判断一个对象是否为空
 *
 **************************************************************************/
function isNull(obj) {
    if (obj == null || (typeof(obj) == 'string' && $.trim(obj) == '')
        || (typeof(obj) == 'object' && $.isEmptyObject(obj))) {
        return true;
    }
    return false;
};

function isEqual(obj1, obj2) {
    if (obj1 && obj2 && obj1 === obj2) {
        return true;
    }
    return false;
};

function fix2(val) {
    if (val) {
        return Math.round(val * 100) / 100;
    }
    return 0;
}

function autoFormatNumber(item) {
    var currVal = $(item).val();
    if (currVal && currVal < 10) {
        $(item).val('0' + parseInt(currVal));
    }
}

function translateCode(currCode) {
    var returnData = [];
    if (currCode && currCode.length > 1) {
        var sepratorIdx = currCode.lastIndexOf(CONST_CODE_SEPRATOR);
        var codePrefix = currCode.substr(0, sepratorIdx + 1);
        var codeIndex = currCode.substr(sepratorIdx + 1);
        if (codeIndex && codeIndex < 10) {
            codeIndex = '0' + parseInt(codeIndex);
        }
        returnData.push(codePrefix);
        returnData.push(codeIndex);
    }
    return returnData;
}

// 同步的POST方法
function synPost(postUrl, submitData) {
    var outData;
    $.ajax({
        url: postUrl,
        type: 'POST',
        data: submitData,
        dataType: 'json',
        async: false,
        success: function (data) {
            outData = data;
        }
    });
    return outData;
}
// 同步的GET方法
function synGet(getUrl) {
    var outData;
    $.ajax({
        url: getUrl,
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function (data) {
            outData = data;
        }
    });
    return outData;
}