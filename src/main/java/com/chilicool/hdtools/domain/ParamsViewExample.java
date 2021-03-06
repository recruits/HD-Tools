package com.chilicool.hdtools.domain;

import java.util.ArrayList;
import java.util.List;

public class ParamsViewExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table params_view
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table params_view
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table params_view
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public ParamsViewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table params_view
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("module_id is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("module_id is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Long value) {
            addCriterion("module_id =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Long value) {
            addCriterion("module_id <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Long value) {
            addCriterion("module_id >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("module_id >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Long value) {
            addCriterion("module_id <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Long value) {
            addCriterion("module_id <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Long> values) {
            addCriterion("module_id in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Long> values) {
            addCriterion("module_id not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Long value1, Long value2) {
            addCriterion("module_id between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Long value1, Long value2) {
            addCriterion("module_id not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdxIsNull() {
            addCriterion("module_idx is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdxIsNotNull() {
            addCriterion("module_idx is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdxEqualTo(Short value) {
            addCriterion("module_idx =", value, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxNotEqualTo(Short value) {
            addCriterion("module_idx <>", value, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxGreaterThan(Short value) {
            addCriterion("module_idx >", value, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxGreaterThanOrEqualTo(Short value) {
            addCriterion("module_idx >=", value, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxLessThan(Short value) {
            addCriterion("module_idx <", value, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxLessThanOrEqualTo(Short value) {
            addCriterion("module_idx <=", value, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxIn(List<Short> values) {
            addCriterion("module_idx in", values, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxNotIn(List<Short> values) {
            addCriterion("module_idx not in", values, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxBetween(Short value1, Short value2) {
            addCriterion("module_idx between", value1, value2, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleIdxNotBetween(Short value1, Short value2) {
            addCriterion("module_idx not between", value1, value2, "moduleIdx");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNull() {
            addCriterion("module_name is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("module_name is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("module_name =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("module_name <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("module_name >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("module_name >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("module_name <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("module_name <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("module_name like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("module_name not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("module_name in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("module_name not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("module_name between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("module_name not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andEnumIdIsNull() {
            addCriterion("enum_id is null");
            return (Criteria) this;
        }

        public Criteria andEnumIdIsNotNull() {
            addCriterion("enum_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnumIdEqualTo(Long value) {
            addCriterion("enum_id =", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdNotEqualTo(Long value) {
            addCriterion("enum_id <>", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdGreaterThan(Long value) {
            addCriterion("enum_id >", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enum_id >=", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdLessThan(Long value) {
            addCriterion("enum_id <", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdLessThanOrEqualTo(Long value) {
            addCriterion("enum_id <=", value, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdIn(List<Long> values) {
            addCriterion("enum_id in", values, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdNotIn(List<Long> values) {
            addCriterion("enum_id not in", values, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdBetween(Long value1, Long value2) {
            addCriterion("enum_id between", value1, value2, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdNotBetween(Long value1, Long value2) {
            addCriterion("enum_id not between", value1, value2, "enumId");
            return (Criteria) this;
        }

        public Criteria andEnumIdxIsNull() {
            addCriterion("enum_idx is null");
            return (Criteria) this;
        }

        public Criteria andEnumIdxIsNotNull() {
            addCriterion("enum_idx is not null");
            return (Criteria) this;
        }

        public Criteria andEnumIdxEqualTo(Short value) {
            addCriterion("enum_idx =", value, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxNotEqualTo(Short value) {
            addCriterion("enum_idx <>", value, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxGreaterThan(Short value) {
            addCriterion("enum_idx >", value, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxGreaterThanOrEqualTo(Short value) {
            addCriterion("enum_idx >=", value, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxLessThan(Short value) {
            addCriterion("enum_idx <", value, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxLessThanOrEqualTo(Short value) {
            addCriterion("enum_idx <=", value, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxIn(List<Short> values) {
            addCriterion("enum_idx in", values, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxNotIn(List<Short> values) {
            addCriterion("enum_idx not in", values, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxBetween(Short value1, Short value2) {
            addCriterion("enum_idx between", value1, value2, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumIdxNotBetween(Short value1, Short value2) {
            addCriterion("enum_idx not between", value1, value2, "enumIdx");
            return (Criteria) this;
        }

        public Criteria andEnumNameIsNull() {
            addCriterion("enum_name is null");
            return (Criteria) this;
        }

        public Criteria andEnumNameIsNotNull() {
            addCriterion("enum_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnumNameEqualTo(String value) {
            addCriterion("enum_name =", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameNotEqualTo(String value) {
            addCriterion("enum_name <>", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameGreaterThan(String value) {
            addCriterion("enum_name >", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameGreaterThanOrEqualTo(String value) {
            addCriterion("enum_name >=", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameLessThan(String value) {
            addCriterion("enum_name <", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameLessThanOrEqualTo(String value) {
            addCriterion("enum_name <=", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameLike(String value) {
            addCriterion("enum_name like", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameNotLike(String value) {
            addCriterion("enum_name not like", value, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameIn(List<String> values) {
            addCriterion("enum_name in", values, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameNotIn(List<String> values) {
            addCriterion("enum_name not in", values, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameBetween(String value1, String value2) {
            addCriterion("enum_name between", value1, value2, "enumName");
            return (Criteria) this;
        }

        public Criteria andEnumNameNotBetween(String value1, String value2) {
            addCriterion("enum_name not between", value1, value2, "enumName");
            return (Criteria) this;
        }

        public Criteria andSelTypeIsNull() {
            addCriterion("sel_type is null");
            return (Criteria) this;
        }

        public Criteria andSelTypeIsNotNull() {
            addCriterion("sel_type is not null");
            return (Criteria) this;
        }

        public Criteria andSelTypeEqualTo(String value) {
            addCriterion("sel_type =", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeNotEqualTo(String value) {
            addCriterion("sel_type <>", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeGreaterThan(String value) {
            addCriterion("sel_type >", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sel_type >=", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeLessThan(String value) {
            addCriterion("sel_type <", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeLessThanOrEqualTo(String value) {
            addCriterion("sel_type <=", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeLike(String value) {
            addCriterion("sel_type like", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeNotLike(String value) {
            addCriterion("sel_type not like", value, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeIn(List<String> values) {
            addCriterion("sel_type in", values, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeNotIn(List<String> values) {
            addCriterion("sel_type not in", values, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeBetween(String value1, String value2) {
            addCriterion("sel_type between", value1, value2, "selType");
            return (Criteria) this;
        }

        public Criteria andSelTypeNotBetween(String value1, String value2) {
            addCriterion("sel_type not between", value1, value2, "selType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table params_view
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table params_view
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}