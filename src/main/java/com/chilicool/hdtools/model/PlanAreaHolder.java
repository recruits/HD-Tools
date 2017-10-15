package com.chilicool.hdtools.model;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chilicool on 2017/10/14.
 */
public class PlanAreaHolder {
    public PlanAreaHolder(List<PlanAreaModel> planAreaModelList) {
        this.planAreaModelList = planAreaModelList;
        loadAndCacuData();
    }

    private void loadAndCacuData() {
        if (CollectionUtils.isNotEmpty(planAreaModelList)) {
            planAreaTotal = new Double(zeroVal);
            deptTypeIdList = new ArrayList<>();
            everyAreaValMap = new HashMap<>();
            everyPersentMap = new HashMap<>();

            for (PlanAreaModel model : planAreaModelList) {
                Double planAreaVal = model.getPlanArea();
                Long deptTypeId = model.getId();

                if (null == planAreaVal) {
                    planAreaVal = zeroVal;
                }

                planAreaTotal += planAreaVal;
                deptTypeIdList.add(deptTypeId);
                everyAreaValMap.put(deptTypeId, planAreaVal);
            }

            for (Long currDeptTypeId : deptTypeIdList) {
                if (null != planAreaTotal && !zeroVal.equals(planAreaTotal)) {
                    Double persent = everyAreaValMap.get(currDeptTypeId) / planAreaTotal;
                    persent *= handurdVal;
                    everyPersentMap.put(currDeptTypeId, persent);
                }
            }
        }
    }

    private List<PlanAreaModel> planAreaModelList;
    private Double planAreaTotal;
    private List<Long> deptTypeIdList;
    private Map<Long, Double> everyAreaValMap;
    private Map<Long, Double> everyPersentMap;

    public Double getPersentByDeptTypeId(Long deptTypeId) {
        if (null != everyPersentMap && everyPersentMap.size() > 0) {
            Double currVal = everyPersentMap.get(deptTypeId);
            if (null == currVal) {
                currVal = zeroVal;
            }
            return currVal;
        }
        return zeroVal;
    }

    public Map<Long, Double> getEveryPersentMap() {
        return everyPersentMap;
    }

    public List<Long> getDeptTypeIdList() {
        return deptTypeIdList;
    }

    public Double getPlanAreaTotal() {
        return planAreaTotal;
    }

    private static Double zeroVal = new Double(0);
    private static Double oneVal = new Double(1);
    private static Double handurdVal = new Double(100);
}
