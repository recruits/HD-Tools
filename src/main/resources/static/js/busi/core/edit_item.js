$(function(){
});
// Deprecated
function addPart(){
    var total = $('#baseTab tr').length;
    var subSize = $('#baseTab tr[class^="treegrid-parent"]').length;
    var size = parseInt(total) - parseInt(subSize);

    var trInfo = $('<tr class="treegrid-"'+(size+1)+'></tr>');
    var tdInfo = $('<td>Test</td><td></td><td></td><td></td><td></td><td></td><td></td>');
    tdInfo.appendTo(trInfo);
    trInfo.appendTo($('#baseTab'));
}

// 项目阶段关联版本号
$(function () {
});

// 刷新导航栏
$(function () {
});
// 刷新导航栏
function refreshNavInfos(level, name) {
    var navObj = $('#itemNavTitle');

    // 当前有几级导航
    var navNum = $('.navTitleClass li').length;

    var currNavInfo;
    // 导航信息不存在
    if(navNum == 0){
        // 编辑项目,根据级别重建一级导航
        if(action == 'edit'){
            navObj.append($('<li><i class="fa fa-star"></i>'+projName+'</li>'));
        } else {
            // 新增项目,取项目名称建导航;名称不存在,不建;
            projName = $('[name=projName]:input').val();
            if(projName){
                navObj.append($('<li><i class="fa fa-star"></i>'+projName+'</li>'));
            }
        }
    }

    // 导航信息已存在,进行更新
    if ((navNum == 1 && level == 1) || (navNum == 2 && level == 2)) {
        $('.navTitleClass li:eq(' + navNum + ')').text(name);
    }

    // 导航信息不存在，新建
    if (navNum > 0 && navNum == parseInt(level)-1) {
        navObj.append($('<li>'+name+'</li>'));
    }

    // 跨级导航,暂时不处理
    if (navNum > 0 && navNum == parseInt(level)-2) {
    }

    // 指定级别更新
    if (name && navNum > 0 && navNum > level) {
        if(level == 0){
            $('.navTitleClass li:eq('+level+')').text('<i class="fa fa-star"></i>'+name);
        } else {
            $('.navTitleClass li:eq('+level+')').text(name);
        }
    }
}