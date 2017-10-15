$(function () {
    $('#deptListTable').DataTable(cfg_dt_options);

    $(document).ready(function() {
        $('.tree').treegrid({
            expanderExpandedClass: 'fa fa-minus',
            expanderCollapsedClass: 'fa fa-plus'
        });

        $('.tree').treegrid('collapseAll');

        $('.treegrid-0').treegrid('expand');
    });
});