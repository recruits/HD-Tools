var currForm;

$(document).ready(function () {
    currForm = $('#dataModuleEnumForm');

    currForm.bootstrapValidator(validOption);
    currForm.submit(function (ev) {
        ev.preventDefault();
    });
    currForm.find('button[type=reset]').bind('click', function () {
        $('#dataModuleEnumForm').data('bootstrapValidator').resetForm(false);
    });
    currForm.find('button[type=submit]').on('click', function () {
        var currFormValidator = $('#dataModuleEnumForm').data('bootstrapValidator');
        // 文本信息校验
        currFormValidator.validate();
        if (currFormValidator.isValid()) {
            var currForm = $('#dataModuleEnumForm');
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
});

var validOption = {
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'fa fa-check',
        invalid: 'fa fa-times',
        validating: 'fa fa-refresh'
    },
    fields: {
        moduleId: {
            message: 'The modelName is not valid'
        },
        enumName: {
            message: 'The enumName is not valid',
            validators: {
                stringLength: {
                    min: 2,
                    max: 30,
                    message: 'The enumName must be more than 2 and less than 30 characters long'
                }
            }
        },
        enumCode: {
            message: 'The enumCode is not valid'
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