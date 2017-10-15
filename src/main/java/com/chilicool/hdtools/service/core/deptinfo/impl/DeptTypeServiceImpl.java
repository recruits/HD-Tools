package com.chilicool.hdtools.service.core.deptinfo.impl;

import com.chilicool.hdtools.dao.DeptTypeMapper;
import com.chilicool.hdtools.domain.DeptType;
import com.chilicool.hdtools.service.core.deptinfo.DeptTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.chilicool.hdtools.service.core.deptinfo.impl.DeptTypeServiceImpl.DeptInitData.initArea;
import static com.chilicool.hdtools.service.core.deptinfo.impl.DeptTypeServiceImpl.DeptInitData.initPersent;

/**
 * Created by chilicool on 2017/10/7.
 */
@Service
public class DeptTypeServiceImpl implements DeptTypeService {
    @Autowired
    private DeptTypeMapper deptTypeMapper;

    @Override
    public void initDeptTypeInfo(Long projId) {
        List<DeptType> deptTypes = generateDeptType(projId);
        saveDeptTypeInfos(deptTypes);
    }

    @Override
    public DeptType loadDeptTypeByPK(Long deptTypeId) {
        return deptTypeMapper.selectByPrimaryKey(deptTypeId);
    }

    @Override
    public Long getProjIdWithDeptTypeId(Long deptTypeId) {
        DeptType deptType = loadDeptTypeByPK(deptTypeId);
        return null != deptType ? deptType.getProjId() : 0L;
    }

    /**
     * 保存部门分类信息
     *
     * @param deptTypes
     */
    private void saveDeptTypeInfos(List<DeptType> deptTypes) {
        for (DeptType deptType : deptTypes) {
            deptTypeMapper.insert(deptType);
        }
    }

    /**
     * 创建部门分类信息
     *
     * @param projId
     * @return
     */
    private List<DeptType> generateDeptType(final Long projId) {
        List<DeptType> deptTypes = new ArrayList<>();

        for (DeptTypeEnum currType : DeptTypeEnum.values()) {
            DeptType deptType = new DeptType();
            deptType.setDeptName(currType.getName());
            deptType.setDeptCode(currType.getCode());
            deptType.setOrderIdx(currType.getOrder());
            deptType.setCreateTime(new Date());
            deptType.setProjId(projId);
            // 初始化部门分类的百分比
            deptType.setPlanAreaPersent(initPersent);
            deptType.setDesignAreaPersent(initPersent);
            // 初始化部门分类的面积
            deptType.setPlanArea(initArea);
            deptType.setDesignArea(initArea);
            deptTypes.add(deptType);
        }

        return deptTypes;
    }

    interface DeptInitData{
        Double initArea = new Double(0);
        Double initPersent = new Double("12.50");
    }

    enum DeptTypeEnum {
        HALL("公共空间", "P", (short) 1),
        SUBITUM("急诊部", "A", (short) 2),
        OUTPATIENTS("门诊部", "C", (short) 3),
        INPATIENTS("住院部", "I", (short) 4),
        MEDICAL("医技部", "D", (short) 5),
        ADMINISTRATION("行政部", "AD", (short) 6),
        LOGISTICS("后勤支持", "S", (short) 7),
        MACHINE("机房", "M", (short) 8);

        private DeptTypeEnum(String name, String code, Short order) {
            this.name = name;
            this.code = code;
            this.order = order;
        }

        private String name;
        private String code;
        private Short order;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Short getOrder() {
            return order;
        }

        public void setOrder(Short order) {
            this.order = order;
        }
    }
}
