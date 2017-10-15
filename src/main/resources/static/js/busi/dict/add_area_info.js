$(function () {
    $('#chooseRoomModal').on('hidden.bs.modal', function (e) {
        var selRooms = $('[name=rooms]:checkbox:checked');
        var selTexts = '';
        if(selRooms){
            $.each(selRooms, function (index, item) {
                selTexts += "[ "+$(this).parent().text()+" ]";
            });
        }
        $('[name=roomInfos]:text').val(selTexts);
    });
});