var currForm;

$(document).ready(function () {
    currForm = $('#dataEnumParamForm');

    currForm.bootstrapValidator(validOption);
    currForm.submit(function (ev) {
        ev.preventDefault();
    });
    currForm.find('button[type=reset]').bind('click', function () {
        $('#dataEnumParamForm').data('bootstrapValidator').resetForm(false);
    });
    currForm.find('button[type=submit]').on('click', function () {
        var currFormValidator = $('#dataEnumParamForm').data('bootstrapValidator');
        // 文本信息校验
        currFormValidator.validate();
        if (currFormValidator.isValid()) {
            var currForm = $('#dataEnumParamForm');
            var submitData = decodeURIComponent(currForm.serialize(), true);
            $.post(currForm.attr('action'), submitData, function (result) {
                Ewin.alert(result.retMsg).on(function () {
                    if (result.retCode == RET_CODE_SUCC) {
                        window.location.reload();
                    }
                });
            }, 'json');
        } else return;
    });

    $('#moduleId').bind('change',function () {
       var moduleId = $(this).children('option:selected').val();
       var enumInfos = loadEnumInfo(moduleId);
        buildHtmlInfo(enumInfos);
    });

    $('#moduleId').change();
});

function buildHtmlInfo(enumInfos) {
    if(enumInfos && enumInfos instanceof Array){
        // 清除现有option选项
        $('#enumId option').remove();

        // 重新生成option选项
        var selectDom = $('#enumId');
        $.each(enumInfos, function (index, item) {
            var option = $("<option value="+item.id+">"+item.enumName+"</option>");
            option.appendTo(selectDom);
        });
    }
}

function loadEnumInfo(moduleId) {
    var tempObj = null;
    var linkUrl = "moduleEnumSimp.json?moduleId="+moduleId;
    $.ajax({
        url: linkUrl,
        dataType: "json",
        async: false,
        success: function (data) {
            tempObj = data;
        }
    });
    if(tempObj){
        return tempObj.data;
    }
}

var validOption = {
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'fa fa-check',
        invalid: 'fa fa-times',
        validating: 'fa fa-refresh'
    },
    fields: {
        enumParamName: {
            message: 'The enumParamName is not valid',
            validators: {
                stringLength: {
                    min: 2,
                    max: 30,
                    message: 'The enumParamName must be more than 2 and less than 30 characters long'
                }
            }
        },
        enumParamCode: {
            message: 'The enumParamCode is not valid'
        },
        orderIdx: {
            message: 'The orderIdx is not valid',
            validators: {
                between: {
                    min: 1,
                    max: 999,
                    message: '排列顺序可选范围为：1~999'
                }
            }
        }
    }
};