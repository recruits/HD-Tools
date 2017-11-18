package com.chilicool.hdtools.controller;

import com.chilicool.hdtools.common.ErrorMsg;
import com.chilicool.hdtools.domain.*;
import com.chilicool.hdtools.model.*;
import com.chilicool.hdtools.service.ParamDataService;
import com.chilicool.hdtools.service.RoomDataService;
import com.chilicool.hdtools.support.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by chilicool on 2017/9/10.
 */
@Controller
@RequestMapping(value = "/dict")
public class DictController {
    @Autowired
    private RoomDataService roomDataService;
    @Autowired
    private ParamDataService paramDataService;

    @RequestMapping(value = "/showEquipList.html")
    public String showEquipList() {
        return "dict/show_sequip_list";
    }

    @RequestMapping(value = "/addDataModule.html")
    public String addDataModule() {
        return "dict/add_module";
    }

    @RequestMapping(value = "/addDataModule.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResultBase addDataModule(RoomDataModule roomDataModule) {
        ResultBase resultBase = ResultUtil.builtResultInfo(null);

        try {
            roomDataService.addDataModule(roomDataModule);
        } catch (Exception exp) {
            resultBase = ResultUtil.builtResultInfo(ResultBase.RET_CODE_FAIL, ResultBase.RET_CODE_FAIL, exp.getMessage());
            exp.printStackTrace();
        }

        return resultBase;
    }

    @RequestMapping(value = "/addModuleEnum.html")
    public ModelAndView addDataModuleEnum() {
        ModelAndView module = new ModelAndView("dict/add_module_enum");
        List<DataModuleSimp> dataModuleSimpList = roomDataService.getAllDataModuleSimp();
        module.addObject("moduleSimpList", dataModuleSimpList);
        return module;
    }

    @RequestMapping(value = "/addDataModuleEnum.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResultBase addDataModuleEnum(DataModuleEnum dataModuleEnum) {
        ResultBase resultBase = ResultUtil.builtResultInfo(null);

        try {
            roomDataService.addDataModuleEnum(dataModuleEnum);
        } catch (Exception exp) {
            resultBase = ResultUtil.builtResultInfo(ResultBase.RET_CODE_FAIL, ResultBase.RET_CODE_FAIL, exp.getMessage());
            exp.printStackTrace();
        }

        return resultBase;
    }

    @RequestMapping(value = "/addEnumParam.html")
    public ModelAndView addEnumParam() {
        ModelAndView module = new ModelAndView("dict/add_enum_param");
        List<DataModuleSimp> dataModuleSimpList = roomDataService.getAllDataModuleSimp();
        module.addObject("moduleSimpList", dataModuleSimpList);
        return module;
    }

    @RequestMapping(value = "/addEnumParam.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResultBase addEnumParam(DataModuleEnumParam dataModuleEnumParam) {
        ResultBase resultBase = ResultUtil.builtResultInfo(null);

        try {
            roomDataService.addDataModuleEnuParam(dataModuleEnumParam);
        } catch (Exception exp) {
            resultBase = ResultUtil.builtResultInfo(ResultBase.RET_CODE_FAIL, ResultBase.RET_CODE_FAIL, exp.getMessage());
            exp.printStackTrace();
        }

        return resultBase;
    }

    @RequestMapping(value = "/roomDataMgr.html")
    public String roomDataMgr() {
        return "dict/room_data_mgr";
    }


    @RequestMapping(value = "/dataModuleSimp.json", method = RequestMethod.GET)
    @ResponseBody
    public DataModuleJson getAllDataModuleSimp() {
        DataModuleJson data = new DataModuleJson();
        data.setData(roomDataService.getAllDataModuleSimp());
        return data;
    }

    @RequestMapping(value = "/moduleEnumSimp.json", method = RequestMethod.GET)
    @ResponseBody
    public ModuleEnumJson getModuleEnumSimp(String moduleId) {
        ModuleEnumJson data = new ModuleEnumJson();
        data.setData(roomDataService.getModuleEnumWithModuleId(moduleId));
        return data;
    }

    @RequestMapping(value = "/enumParamSimp.json", method = RequestMethod.GET)
    @ResponseBody
    public EnumParamJson getEnumParamSimp(String moduleId, String enumId) {
        EnumParamJson data = new EnumParamJson();
        data.setData(roomDataService.getEnumParamWithModuleIdAndEnumId(moduleId, enumId));
        return data;
    }

    @RequestMapping(value = "/roomDataSimp.json", method = RequestMethod.GET)
    @ResponseBody
    public RoomDataJson getRoomDataSimp(String moduleId, String enumId) {
        RoomDataJson data = new RoomDataJson();
        data.setData(roomDataService.getAllRoomDataSimp());
        return data;
    }

    /********************************************************************************
     * 房间管理
     ********************************************************************************/
    @RequestMapping(value = "/showRoomList.html")
    public String showRoomList() {
        return "dict/show_room_list";
    }

    @RequestMapping(value = "/addRoomInfo.html")
    public String addRoomInfo() {
        return "dict/add_room_info";
    }

    /********************************************************************************
     * 科室管理
     ********************************************************************************/
    @RequestMapping(value = "/showDeptList.html")
    public String showDeptList() {
        return "dict/show_dept_list";
    }

    @RequestMapping(value = "/addDeptInfo.html")
    public String addDeptInfo() {
        return "dict/add_dept_info";
    }

    @RequestMapping(value = "/addAreaInfo.html")
    public String addAreaInfo() {
        return "dict/add_area_info";
    }
    /********************************************************************************
     * 样板房间管理
     ********************************************************************************/
    @RequestMapping(value = "/showSroomList.html")
    public String showSpecRoom() {
        return "dict/show_sroom_list";
    }

    @RequestMapping(value = "/loadAllSpecRoomDetail.json")
    @ResponseBody
    public SpecRoomJson loadAllSpecRoomDetail() {
        SpecRoomJson specRoomModel = new SpecRoomJson();
        List<SpecRoomSimple> specRoomDataList = roomDataService.loadAllSpecRoomDetail();
        specRoomModel.setData(specRoomDataList);
        return specRoomModel;
    }

    @RequestMapping(value = "/editSpecRoomData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase editSpecRoomDetail(SpecRoomWithAction specRoomWithAction) {
        ResultBase resultBase = ResultUtil.builtResultInfo(null);
        roomDataService.editSpecRoomData(specRoomWithAction);
        return resultBase;
    }

    @RequestMapping(value = "/delSpecRoomData.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase delSpecRoomData(Long specRoomId) {
        ResultBase resultBase = ResultUtil.builtResultInfo(null);
        roomDataService.delSpecRoomData(specRoomId);
        return resultBase;
    }

    @RequestMapping(value = "/specRoomNameCheck.json", method = RequestMethod.GET)
    @ResponseBody
    public ResultBase specRoomNameCheck(String specRoomName, String deptTypeCode) {
        ResultBase resultBase = ResultUtil.builtResultInfo(null);
        boolean specRoomNameExist = roomDataService.specRoomNameCheck(specRoomName, deptTypeCode);
        if (specRoomNameExist) {
            resultBase.setRetCode(ResultBase.RET_CODE_FAIL);
            resultBase.setRetMsg(ErrorMsg.ERROR_SPEC_ROOM_NAME_HAS_EXIST);
        }
        return resultBase;
    }

    @RequestMapping(value = "/loadCurrRoomDeail.json", method = RequestMethod.GET)
    @ResponseBody
    public List<String> loadCurrRoomDeail(Long specRoomId) {
        return roomDataService.loadCurrRoomDeail(specRoomId);
    }

    @RequestMapping(value = "/submitRoomDataOnTime.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultBase submitRoomDataOnTime(Long specRoomId, String value, String action) {
        ResultBase resultBase = new ResultBase();
        roomDataService.submitRoomDataOnTime(specRoomId, value, action);
        return resultBase;
    }

    /********************************************************************************
     * 房间数据管理-重构
     ********************************************************************************/
    @RequestMapping(value = "/loadAllSpecRoomData.json", method = RequestMethod.GET)
    @ResponseBody
    public SpecRoomDataJson loadAllSpecRoomData() {
        return paramDataService.loadAllSpecRoomData();
    }
}
