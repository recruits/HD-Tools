package com.chilicool.hdtools.controller;

import com.chilicool.hdtools.domain.SmSysDict;
import com.chilicool.hdtools.service.core.sysdict.SmSysDictService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.Collator;
import java.util.*;

/**
 * Created by chilicool on 2017/10/21.
 */
@Controller
@RequestMapping(value = "/system")
public class SystemController {
    @Autowired
    private SmSysDictService smSysDictService;

    @RequestMapping(value = "/getTypeCodes.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTypeCodes(HttpServletRequest request, String typeCodes, String parentValue) throws Exception {
        JSONObject rtnJson = new JSONObject();

        if (typeCodes == null) {
            return rtnJson;
        }

        if (StringUtils.isNotEmpty(parentValue) && !"undefined".equals(parentValue)) {
            JSONArray arr = getJSONArrayByParentCode(typeCodes, parentValue);
            rtnJson.put("_PV" + parentValue + "_" + typeCodes, arr);
        } else {
            String[] typeCodeArr = typeCodes.split(",");
            Set<String> set = new HashSet<>();
            // 去掉重复typeCode
            for (String typeCode : typeCodeArr) {
                typeCode = typeCode.toLowerCase();
                set.add(typeCode);
            }
            for (String typeCode : set) {
                JSONArray arr = getJSONArray(typeCode);
                rtnJson.put(typeCode, arr);
            }
        }

        return rtnJson;
    }

    /**
     * 根据parentTypeCode和parentValue获取JSONArray
     *
     * @param parentTypeCode
     * @param parentValue
     * @return
     */
    private JSONArray getJSONArrayByParentCode(String parentTypeCode,
                                               String parentValue) {
        JSONArray arr = new JSONArray();
        List<SmSysDict> list = smSysDictService.getSmSysDictList(parentTypeCode.toUpperCase(), parentValue);
        boolean isleaf = isleaf(list);
        for (SmSysDict dict : list) {
            JSONObject json = new JSONObject();
            json.put("id", dict.getParamCode());
            json.put("text", dict.getParamDesc());
            json.put("attributes", dict.getTypeCode());
            if (isleaf) {
                json.put("state", "open");
            } else {
                json.put("state", "closed");
            }

            arr.add(json);
        }
        return arr;
    }

    private boolean isleaf(List<SmSysDict> list) {
        if (null != list && list.size() > 0) {
            SmSysDict bean = list.get(0);
            List<SmSysDict> result = smSysDictService.getSmSysDictList(bean.getTypeCode(), bean.getParamCode());
            if (result == null || result.size() == 0) {
                return true;
            }
        }
        return false;
    }

    private JSONArray getJSONArray(String typeCode) {
        JSONArray arr = new JSONArray();
        setJsonArray(typeCode, arr);
        return arr;
    }

    private void setJsonArray(String typeCode, JSONArray arr) {
        // 获取是否中文排序
        String[] arrs = typeCode.split("\\$");
        boolean chineseOrder = false;
        if (arrs.length == 2) {
            String type = arrs[0];
            String code = arrs[1];
            String[] codeArr = code.split("=");
            if (codeArr.length == 2 && "true".equals(codeArr[1])) {
                typeCode = type;
                chineseOrder = true;
            }
        }
        //是否lazy
        boolean lazy = false;
        if (typeCode.startsWith("_lazy")) {
            typeCode = typeCode.substring(6);
            lazy = true;
        }
        List<SmSysDict> list = smSysDictService.getSmSysDictList(typeCode);
        if (chineseOrder) {
            orderList(list, arr);
        } else {
            for (SmSysDict dict : list) {
                JSONObject json = new JSONObject();
                json.put("id", dict.getParamCode());
                json.put("text", dict.getParamDesc());
                json.put("attributes", dict.getTypeCode());
                if (lazy) {
                    json.put("attributes", typeCode);
                    json.put("state", "closed");
                }
                arr.add(json);
            }
        }
    }

    private void orderList(List<SmSysDict> list, JSONArray arr) {
        TreeSet<SmSysDict> tree = new TreeSet<SmSysDict>(new Comparator<SmSysDict>() {

            @Override
            public int compare(SmSysDict a, SmSysDict b) {
                int flag = 0;
                if ("09".equals(b.getParamCode()) || "000".equals(b.getParamCode()) || "00000".equals(b.getParamCode())) {
                    flag = 1;
                } else if ("09".equals(a.getParamCode()) || "000".equals(a.getParamCode()) || "00000".equals(a.getParamCode())) {
                    flag = -1;
                } else {
                    flag = Collator.getInstance(Locale.CHINESE).compare(a.getParamDesc(), b.getParamDesc());
                }
                return flag;
            }
        });
        for (SmSysDict sys : list) {
            tree.add(sys);
        }
        for (SmSysDict dict : tree) {
            JSONObject json = new JSONObject();
            json.put("id", dict.getParamCode());
            json.put("text", dict.getParamDesc());
            json.put("attributes", dict.getTypeCode());
            arr.add(json);
        }
    }
}
