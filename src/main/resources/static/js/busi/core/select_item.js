$(document).ready(function() {
    $('#table2').dataTable({
        "sPaginationType": "full_numbers"
    });

    // Select2
    $('select').select2({
        minimumResultsForSearch: -1
    });

    $('select').removeClass('form-control');

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
});

function addProject() {
    var linkUrl = $('#editProjectItem')[0].href;
    window.parent.frames['mainFrame'].location.href=linkUrl;
    //parent.iframeFresh(linkUrl);
    //onclick="addProject()"
}

