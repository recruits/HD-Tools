$(function () {
    paneBaseInfoForm = $('#basicForm');
    // 绑定校验事件
    paneBaseInfoForm.bootstrapValidator(paneBaseinfoValidOption);
    paneBaseInfoValidator = paneBaseInfoForm.data('bootstrapValidator');

    // 绑定保存动作
    $('#saveItemInfoBtn').bind('click', function () {
        paneBaseInfoValidator.validate();
        if(paneBaseInfoValidator.isValid()){
            // 提交基础信息保存
            saveBaseInfo();
        }
    });

    // 绑定发布运作
    $('#releaseItemInfoBtn').click(function () {
        var options = {
            message: "确定把当前项目发布为一个新版本?"
        };
        Ewin.confirm(options).on(function (action) {
            if (action) {
                releaseProject();
            }
        })
    });

    // Deprecated
    if (action === CONST_ACTION_ADD) {
        initCreateTime();
    }
});
var paneBaseInfoForm ;
var paneBaseInfoValidator;

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
            if (outData.retCode === RET_CODE_SUCC) {
                if (action == CONST_ACTION_ADD) {
                    // 修改项目基础信息
                    updteBaseInfo(outData.retExtObj);
                    // 保存项目信息成功，操作修改为变更
                    action = CONST_ACTION_EDIT;
                }
                // 重置校验样式
                paneBaseInfoForm.data('bootstrapValidator').resetForm(false);
            }
        });
    }, "json");
};

function releaseProject() {
    var linkUrlForProjRelease = 'release.json';
    var submitData = {
        "projId": projId,
        "groupId": $('#basicForm input[name="groupId"]').val()
    };
    var outData = synPost(linkUrlForProjRelease, submitData);
    Ewin.alert(outData.retMsg).on(function () {
        if (outData.retCode === RET_CODE_SUCC) {
            // 修改项目基础信息
            updteBaseInfoAfterRelease(outData.retExtObj);
            // 重置校验样式
            paneBaseInfoForm.data('bootstrapValidator').resetForm(false);
        }
    });
}

function updteBaseInfo(projInfo) {
    // 更新页面元素
    $('#baseInfo input[name="id"]').val(projInfo.id);
    $('#baseInfo input[name="groupId"]').val(projInfo.groupId);
    $('#baseInfo input[name="projPhase"]').val(projInfo.projPhase);
    $('#baseInfo input[name="verId"]').val(projInfo.verId);
    $('#baseInfo input[name="action"]').val(CONST_ACTION_EDIT);

    // 更新全局变量
    projId = projInfo.id;
}

function updteBaseInfoAfterRelease(projInfo) {
    // 更新页面元素
    $('#baseInfo input[name="id"]').val(projInfo.id);
    $('#baseInfo input[name="groupId"]').val(projInfo.groupId);
    $('#baseInfo input[name="verId"]').val(projInfo.verId);
    $('#baseInfo input[name="verInfo"]').val(projInfo.verInfo);
    $('#baseInfo input[name="createTime"]').val(projInfo.createTime);
    $('#baseInfo input[name="action"]').val(CONST_ACTION_EDIT);

    // 更新全局变量
    projId = projInfo.id;
}

var paneBaseinfoValidOption = {
    message: "请输入有效的内容!",
    feedbackIcons: {
        valid: "fa fa-check",
        invalid: "fa fa-times",
        validatting: "fa fa-refresh"
    },
    fields: {
        projName: {
            message: "项目名称信息有误!",
            validators: {
                notEmpty: {
                    message: "项目名称不能为空!"
                },
                stringLength: {
                    min: 1,
                    max: 64,
                    message: "项目名称不少于1个字符，不多于64个字符!"
                }
            }
        },
        projSubtitle:{
            message: "项目描述信息有误!",
            validators: {
                stringLength: {
                    min: 0,
                    max: 128,
                    message: "项目描述不多于128个字符!"
                }
            }
        }
    }
};