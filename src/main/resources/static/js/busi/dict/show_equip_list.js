$.fn.dataTable.ext.search.push(
    function( settings, data, dataIndex ) {
        var min = parseInt( $('#min').val(), 10 );
        var max = parseInt( $('#max').val(), 10 );
        var age = parseFloat( data[3] ) || 0; // use data for the age column

        if ( ( isNaN( min ) && isNaN( max ) ) ||
            ( isNaN( min ) && age <= max ) ||
            ( min <= age   && isNaN( max ) ) ||
            ( min <= age   && age <= max ) )
        {
            return true;
        }
        return false;
    }
);

$(document).ready(function() {
    $('#table2').dataTable({
        "sPaginationType": "full_numbers"
    });

    // Delete row in a table
    $('.delete-row').click(function () {
        var c = confirm("Continue delete?");
        if (c)
            jQuery(this).closest('tr').fadeOut(function () {
                jQuery(this).remove();
            });

        return false;
    });

    // Show aciton upon row hover
    $('.table-hidaction tbody tr').hover(function () {
        $(this).find('.table-action-hide a').animate({opacity: 1});
    }, function () {
        $(this).find('.table-action-hide a').animate({opacity: 0});
    });

    var table = $('#table2').DataTable();
    $('#min, #max').keyup( function() {
        table.draw();
    } );
});

// 添加入库操作
function addDataModel() {
    //$('#dataModelForm').ajaxSubmit(function() { alert("设置保存成功！"););

    /*            var formData = $("#dataModelForm").serialize();
     var submitData = decodeURIComponent(formData, true);
     var submitUrl = '${basePath}/dict/addDataModel.html';
     $.ajax({
     url: submitUrl,
     data: submitData,
     cache: false,//false是不缓存，true为缓存
     async: false,//true为异步，false为同步
     beforeSend: function () {
     //请求前
     },
     success: function (result) {
     //请求成功时
     alert(result);
     },
     complete: function () {
     //请求结束时
     },
     error: function () {
     //请求失败时
     }
     })*/
}

$(document).ready(function() {
    $('#dataModelForm').bootstrapValidator({
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
    })
        .on('success.form.bv', function(e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
//                $.post($form.attr('action'), $form.serialize(), function (result) {
//                    console.log(result);
//                }, 'json');

            var submitData = decodeURIComponent($form.serialize(), true);
            $.ajax({
                type: "POST",
                url: $form.attr('action'),
                dataType: "json",
                data: submitData,
                cache: false,//false是不缓存，true为缓存
                async: false,//true为异步，false为同步
                beforeSend: function () {
                    //请求前
                },
                success: function (result) {
                    //请求成功时
                    BootstrapDialog.alert(result.retCode);
                },
                complete: function () {
                    //请求结束时
                },
                error: function () {
                    //请求失败时
                }
            })
        });;
});


// 数据模块分类表格
var moduleEnumTabOptions = {
    "ajax": "roomDataSimp.json",
    "destroy": true,
    "oLanguage": cfg_dt_lang,
    "bRetrieve": true,
    "paging": true,
    "ordering": false,
    "info": true,
    "searching": true,
    "columns": [
        /*{"data": "moduleId"},*/
        {"data": "moduleCode"},
        {"data": "moduleName"},
        /*{"data": "moduleType"},*/
        {"data": "enumCode"},
        {"data": "enumName"},
        {"data": "enumIdx"},
        {"data": "enumParams"},
        {"data": "oper"}
    ],
};
$(function () {
    $('#roomDataModule').DataTable(moduleEnumTabOptions);
});