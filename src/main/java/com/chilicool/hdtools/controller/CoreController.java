package com.chilicool.hdtools.controller;

import com.chilicool.hdtools.common.BusiConst;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.*;
import com.chilicool.hdtools.service.*;
import com.chilicool.hdtools.service.core.version.VersionService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chilicool on 2017/9/9.
 */
@Controller
@RequestMapping(value = "/core")
public class CoreController {
    @Autowired
    private VersionService versionService;
    @Autowired
    private ProjBaseInfoService projBaseInfoService;
    @Autowired
    private ProjDeptInfoService projDeptInfoService;
    @Autowired
    private ProjAreaInfoService projAreaInfoService;
    @Autowired
    private ProjRoomInfoService projRoomInfoService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/selectItem.html")
    public String selectItem() {
        return "core/select_item";
    }

    @RequestMapping(value = "/selectPhase.html")
    public ModelAndView selectPhase(String action, String itemName) {
        ModelAndView modelAndView = new ModelAndView("core/select_phase");
        modelAndView.addObject("action", action);
        modelAndView.addObject("itemName", itemName);
        return modelAndView;
    }

    @RequestMapping(value = "/editItem.json")
    @ResponseBody
    public ModelAndView specifyQuery(String action, String itemName, String projPhase) {
        ModelAndView modelAndView = new ModelAndView("core/edit_item");
        modelAndView.addObject("action", action);
        modelAndView.addObject("itemName", itemName);
        modelAndView.addObject("projPhase", projPhase);
        modelAndView.addObject("createTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        Map<String, Object> returnMap = projectService.initProject(action);
        loadInfoIntoView(returnMap, modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/saveBaseInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase saveBaseInfo(ProjBaseInfo projBaseInfo) {
        ResultBase resultBase = new ResultBase();
        projBaseInfoService.saveProjBaseInfo(projBaseInfo);
        resultBase.setRetExtInfo(String.valueOf(projBaseInfo.getId()));
        return resultBase;
    }

    private void loadInfoIntoView(Map<String, Object> returnMap, ModelAndView view) {
        if (null != returnMap && returnMap.size() > 0) {
            for (Map.Entry<String, Object> currEntry : returnMap.entrySet()) {
                view.addObject(currEntry.getKey(), currEntry.getValue());
            }
        }
    }
    /******************************* 开始部门汇总页面服务 ************************************/
    @RequestMapping(value = "/loadAllSumyInfo.json", method = RequestMethod.GET)
    @ResponseBody
    public List<SumyInfoModel> loadAllSumyInfo(Long projId) {
        return projDeptInfoService.loadAllSumyInfo(projId);
    }

    @RequestMapping(value = "/loadSumyTitle.json", method = RequestMethod.GET)
    @ResponseBody
    public DeptSummary loadSumyTitle(Long projId) {
        return projDeptInfoService.loadDeptSummaryInfo(projId);
    }

    @RequestMapping(value = "/addDeptInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase addDeptInfo(DeptWithAction department) {
        ResultBase resultBase = new ResultBase();
        projDeptInfoService.saveOrUpdateDepartmentInfo(department);
        return resultBase;
    }

    @RequestMapping(value = "/editPlanAreaValOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editPlanAreaValOnTime(Long pk, Double value) {
        ResultBase resultBase = new ResultBase();
        projDeptInfoService.updatePlanAreaValOnTime(pk, value);
        return resultBase;
    }

    @RequestMapping(value = "/editPlanAreaValForDeptOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editPlanAreaValForDeptOnTime(Long pk, Double value) {
        ResultBase resultBase = new ResultBase();
        boolean freshFlag = projDeptInfoService.updatePlanAreaValForDeptOnTime(pk, value);
        if (freshFlag) resultBase.setRetExtInfo("fresh");
        return resultBase;
    }

    @RequestMapping(value = "/delDeptInfoByDeptId.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase delDeptInfoByDeptId(Long deptId) {
        ResultBase resultBase = new ResultBase();
        projDeptInfoService.delDeptInfoByDeptId(deptId);
        return resultBase;
    }

    @RequestMapping(value = "/editSumyAreaRatioValOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editSumyAreaRatioValOnTime(Long sumyId, Double areaRatio) {
        ResultBase resultBase = new ResultBase();
        projDeptInfoService.editSumyAreaRatioValOnTime(sumyId, areaRatio);
        return resultBase;
    }

    @RequestMapping(value = "/editSumyNoteValOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editSumyNoteValOnTime(Long sumyId, String note) {
        ResultBase resultBase = new ResultBase();
        projDeptInfoService.editSumyNoteValOnTime(sumyId, note);
        return resultBase;
    }

    /******************************* 开始部门详情页面服务 ************************************/
    @RequestMapping(value = "/loadAllAreaInfo.json", method = RequestMethod.GET)
    @ResponseBody
    public List<AreaInfoModel> loadAllAreaInfo(Long deptId) {
        return projAreaInfoService.loadAllAreaInfo(deptId);
    }

    @RequestMapping(value = "/loadAreaTitle.json", method = RequestMethod.GET)
    @ResponseBody
    public AreaSumyModel loadAreaTitle(Long deptId, Long deptTypeId) {
        return projAreaInfoService.loadAreaTitle(deptId, deptTypeId);
    }

    @RequestMapping(value = "/addAreaInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase addAreaInfo(AreaWithAction areaWithAction) {
        ResultBase resultBase = new ResultBase();
        projAreaInfoService.saveOrUpdateAreaInfo(areaWithAction);
        return resultBase;
    }

    @RequestMapping(value = "/delAreaInfoByAreaId.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase delAreaInfoByAreaId(Long areaId) {
        ResultBase resultBase = new ResultBase();
        projAreaInfoService.delAreaInfoByAreaId(areaId);
        return resultBase;
    }

    @RequestMapping(value = "/addRoomInfo.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase addRoomInfo(RoomWithAction roomWithAction) {
        ResultBase resultBase = new ResultBase();
        projAreaInfoService.saveOrUpdateRoomInfo(roomWithAction);
        return resultBase;
    }

    @RequestMapping(value = "/delRoomInfoByRoomId.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase delRoomInfoByRoomId(Long roomId) {
        ResultBase resultBase = new ResultBase();
        projAreaInfoService.delRoomInfoByRoomId(roomId);
        return resultBase;
    }

    @RequestMapping(value = "/editRoomCntOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editRoomCntOnTime(Long pk, Integer value) {
        ResultBase resultBase = new ResultBase();
        projAreaInfoService.editRoomCntValOnTime(pk, value);
        return resultBase;
    }

    @RequestMapping(value = "/editRoomAreaOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editRoomAreaOnTime(Long pk, Double value) {
        ResultBase resultBase = new ResultBase();
        projAreaInfoService.editRoomAreaValOnTime(pk, value);
        return resultBase;
    }

    /******************************* 开始房间数据页面服务 ************************************/
    @RequestMapping(value = "/loadAllRoomSpecs.json", method = RequestMethod.GET)
    @ResponseBody
    public List<RoomParamJson> loadAllRoomSpecs() {
        return projRoomInfoService.loadAllRoomSpecs();
    }

    @RequestMapping(value = "/loadCurrRoomDeail.json", method = RequestMethod.GET)
    @ResponseBody
    public List<String> loadCurrRoomDeail(Long roomId){
        return projRoomInfoService.loadCurrRoomDeail(roomId);
    }

    @RequestMapping(value = "/loadCurrRoomTitle.json", method = RequestMethod.GET)
    @ResponseBody
    public RoomSumyModel loadCurrRoomTitle(Long areaId, Long roomId){
        return projRoomInfoService.loadCurrRoomTitle(areaId, roomId);
    }

    @RequestMapping(value = "/submitRoomDataOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase submitRoomDataOnTime(Long roomId, String value){
        ResultBase resultBase = new ResultBase();
        projRoomInfoService.submitRoomDataOnTime(roomId, value);
        return resultBase;
    }
}
