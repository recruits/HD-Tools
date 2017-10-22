package com.chilicool.hdtools.service.core.sysdict.impl;

import com.chilicool.hdtools.common.util.SpringContextUtil;
import com.chilicool.hdtools.dao.SmSysDictMapper;
import com.chilicool.hdtools.domain.SmSysDict;
import com.chilicool.hdtools.domain.SmSysDictExample;
import com.chilicool.hdtools.service.core.sysdict.SmSysDictService;
import com.chilicool.hdtools.support.MultiLanguageSupport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chilicool on 2017/10/21.
 */
@Service
public class SmSysDictServiceImpl implements SmSysDictService {
    private final static Logger log = LoggerFactory.getLogger(SmSysDictServiceImpl.class);

    @Autowired
    private SmSysDictMapper sysDictMapper;

    @Override
    public SmSysDict getSmSysDict(String typeCode, String paramCode) {
        SmSysDictExample example = buildDefExample(typeCode, paramCode);
        List<SmSysDict> smSysDictList = sysDictMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(smSysDictList) ? smSysDictList.get(0) : null;
    }

    @Override
    public List<SmSysDict> getSmSysDictList(String typeCode) {
        List<SmSysDict> smSysDictList = new ArrayList<SmSysDict>();

        if (StringUtils.isNotBlank(typeCode) && StringUtils.startsWith(typeCode, "_")) {
            String type_code = StringUtils.substringAfter(typeCode, "_");
            List<SmSysDict> list = this.selectByRootTypeCode(type_code);
            if (list != null && list.size() > 0) {
                return list;
            }
        }
        String key = typeCode.toLowerCase();
        String languageType = MultiLanguageSupport.getLanguageType();

        String chineseKey = key + "_zh_cn";
        key = key + "_" + languageType.toLowerCase();

        // 使用 SpringContextUtil.getBean(getClass())
        // 调用避免通过this内部调用导致基于动态代理的cache不生效
        HashMap<String, List<SmSysDict>> smSysDictMap = SpringContextUtil.getBean(getClass()).loadSysDictCache();
        if (smSysDictMap.containsKey(key)) {
            return smSysDictMap.get(key);
        } else if (smSysDictMap.containsKey(chineseKey)) {
            return smSysDictMap.get(chineseKey);
        }
        return smSysDictList;
    }

    @Override
    public List<SmSysDict> getSmSysDictList(String typeCode, boolean notParent) {
        List<SmSysDict> list = getSmSysDictList(typeCode);
        List<SmSysDict> result = new ArrayList<SmSysDict>();
        if (notParent) {
            for (SmSysDict sys : list) {
                if (StringUtils.isBlank(sys.getParentTypeCode())) {
                    result.add(sys);
                }
            }
        } else {
            for (SmSysDict sys : list) {
                if (StringUtils.isNotBlank(sys.getParentTypeCode())) {
                    result.add(sys);
                }
            }
        }
        return result;
    }

    @Override
    public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode, String typeCode) {
        List<SmSysDict> smSysDictList = new ArrayList<>();
        String key = typeCode.toLowerCase();
        List<SmSysDict> _smSysDictList = getSmSysDictList(key);
        for (SmSysDict bean : _smSysDictList) {
            if (parentTypeCode.equals(bean.getParentTypeCode()) && parentParamCode.equals(bean.getParentParamCode())) {
                smSysDictList.add(bean);
            }
        }
        return smSysDictList;
    }

    @Override
    public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode) {
        List<SmSysDict> smSysDictList = new ArrayList<>();
        List<SmSysDict> _smSysDictList = SpringContextUtil.getBean(getClass()).getSysDictList();
        for (SmSysDict bean : _smSysDictList) {
            if (parentTypeCode.equals(bean.getParentTypeCode()) && parentParamCode.equals(bean.getParentParamCode())) {
                smSysDictList.add(bean);
            }
        }
        return smSysDictList;
    }

    @Cacheable(value = "smSysDictCache", key = "'MAP'")
    public HashMap<String, List<SmSysDict>> loadSysDictCache() {
        log.debug("加载[系统配置参数]缓存数据......start......");
        HashMap<String, List<SmSysDict>> cacheMap = new HashMap<String, List<SmSysDict>>();
        List<SmSysDict> smSysDictList = SpringContextUtil.getBean(getClass()).getSysDictList();
        if (cacheMap != null) {
            String key = null;
            for (int index = 0; index < smSysDictList.size(); index++) {
                SmSysDict bean = smSysDictList.get(index);
                key = (bean.getTypeCode()).toLowerCase();
                key = key + "_" + bean.getLanguageType().toLowerCase();
                if (!cacheMap.containsKey(key)) {
                    cacheMap.put(key, new ArrayList<SmSysDict>());
                }
                cacheMap.get(key).add(bean);
            }
        }
        log.debug("加载[系统配置参数]缓存数据......end......");
        return cacheMap;
    }

    @Cacheable(value = "smSysDictCache", key = "'FLAT'")
    public List<SmSysDict> getSysDictList() {
        return sysDictMapper.selectByExample(new SmSysDictExample());
    }

    public List<SmSysDict> selectByRootTypeCode(String typeCode) {
        Map<String, String> map = new HashMap<>();
        map.put("typeCode", typeCode);
        return sysDictMapper.selectByRootTypeCode(map);
    }

    private SmSysDictExample buildDefExample(String typeCode, String paramCode) {
        SmSysDictExample example = new SmSysDictExample();
        example.createCriteria().andTypeCodeEqualTo(typeCode).andParamCodeEqualTo(paramCode);
        return example;
    }
}
