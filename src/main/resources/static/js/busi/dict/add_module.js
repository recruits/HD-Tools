var currForm;

$(document).ready(function () {
    currForm = $('#dataModelForm');

    currForm.bootstrapValidator(validOption);
    currForm.submit(function (ev) {
        ev.preventDefault();
    });
    currForm.find('button[type=reset]').bind('click', function () {
        $('#dataModelForm').data('bootstrapValidator').resetForm(false);
    });
    currForm.find('button[type=submit]').on('click', function () {
        var currFormValidator = $('#dataModelForm').data('bootstrapValidator');
        // 文本信息校验
        currFormValidator.validate();
        if (currFormValidator.isValid()) {
            var currForm = $('#dataModelForm');
            var submitData = decodeURIComponent(currForm.serialize(), true);
            $.post(currForm.attr('action'), submitData, function (result) {
                Ewin.alert(result.retMsg).on(function () {
                    if (result.retCode == RET_CODE_SUCC)
                        window.location.reload();
                });
            }, 'json');
        } else return;
    });
});

function autoResetForm(retCode) {
    if (retCode == RET_CODE_SUCC) {
        window.location.reload();
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
        moduleName: {
            message: 'The modelName is not valid',
            validators: {
                notEmpty: {
                    message: 'The modelName is required and can\'t be empty'
                },
                stringLength: {
                    min: 2,
                    max: 30,
                    message: 'The modelName must be more than 2 and less than 30 characters long'
                }
            }
        }
    }
};

function initFormValidation() {
    currForm.bootstrapValidator(validOption);
        // .on('success.form.bv', function (e) {
        //     // Prevent form submission
        //     e.preventDefault();
        //
        //     // Get the form instance
        //     var $form = $(e.target);
        //
        //     // Get the BootstrapValidator instance
        //     var bv = $form.data('bootstrapValidator');
        //
        //     // Use Ajax to submit form data
        //     var submitData = decodeURIComponent($form.serialize(), true);
        //     $.post($form.attr('action'), submitData, function (result) {
        //         Ewin.alert(result.retMsg).on(autoResetForm(result.retCode));
        //     }, 'json');
        // });
    ;
};
