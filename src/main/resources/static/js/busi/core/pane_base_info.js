$(function () {
    $('#saveItemInfoBtn').bind('click', function () {
        saveBaseInfo();
    });

    // Deprecated
    if (action === CONST_ACTION_ADD) {
        initCreateTime();
    }
});

// Deprecated
function initCreateTime() {
    var currtime = new Date();
    $(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        initialDate: currtime
    });
}

function saveBaseInfo() {
    var baseInfoForm = $('#basicForm');
    var linkUrlForBaseInfo = baseInfoForm.attr('action');

    $.post(linkUrlForBaseInfo, baseInfoForm.serialize(), function (outData) {
        Ewin.alert(outData.retMsg).on(function () {
            if(outData.retCode === RET_CODE_SUCC){
                if(action == CONST_ACTION_ADD){
                    // 修改项目基础信息
                    updteBaseInfo(outData.retExtObj);
                    // 保存项目信息成功，操作修改为变更
                    action = CONST_ACTION_EDIT;
                }
            }
        });
    }, "json");
};

function updteBaseInfo(projInfo) {
    // 更新页面元素
    $('#baseInfo input[name="id"]').val(projInfo.id);
    $('#baseInfo input[name="groupId"]').val(projInfo.groupId);
    $('#baseInfo input[name="projPhase"]').val(projInfo.projPhase);
    $('#baseInfo input[name="verId"]').val(projInfo.verId);

    // 更新全局变量
    projId = projInfo.id;
}