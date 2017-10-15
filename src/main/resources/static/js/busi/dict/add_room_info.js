$(function () {
    $('#selectEquipmentModal').on('hidden.bs.modal', function (e) {
        var selEquipments = $('[name=equipments]:checkbox:checked');
        var selTexts = '';
        if(selEquipments){
            $.each(selEquipments, function (index, item) {
                selTexts += "[ "+$(this).parent().text()+" ]";
            });
        }
        $('[name=equipmentInfos]:text').val(selTexts);
    })
});