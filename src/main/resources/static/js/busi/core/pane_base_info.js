$(function () {
    $('#saveItemInfoBtn').bind('click', function () {
        saveBaseInfo();
    });

    if (action === CONST_ACTION_ADD) {
        initCreateTime();
    }
});

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
        Ewin.alert(outData.retMsg);
    }, "json");
};