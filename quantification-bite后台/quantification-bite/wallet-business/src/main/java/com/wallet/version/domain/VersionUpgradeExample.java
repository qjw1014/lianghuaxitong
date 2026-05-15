package com.wallet.version.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * app版本升级 VersionUpgradeExample
 * @author fuce_自动生成
 * @email 10@qq.com
 * @date 2020-10-20 07:21:09
 */
public class VersionUpgradeExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VersionUpgradeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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
				
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(Long value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(Long value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }
        
			
        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }
        
			
        public Criteria andVersionIdIsNull() {
            addCriterion("version_id is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("version_id is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(Integer value) {
            addCriterion("version_id =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(Integer value) {
            addCriterion("version_id <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(Integer value) {
            addCriterion("version_id >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_id >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(Integer value) {
            addCriterion("version_id <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(Integer value) {
            addCriterion("version_id <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLike(Integer value) {
            addCriterion("version_id like", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotLike(Integer value) {
            addCriterion("version_id not like", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<Integer> values) {
            addCriterion("version_id in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<Integer> values) {
            addCriterion("version_id not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(Integer value1, Integer value2) {
            addCriterion("version_id between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("version_id not between", value1, value2, "versionId");
            return (Criteria) this;
        }
        
			
        public Criteria andVersionMiniIsNull() {
            addCriterion("version_mini is null");
            return (Criteria) this;
        }

        public Criteria andVersionMiniIsNotNull() {
            addCriterion("version_mini is not null");
            return (Criteria) this;
        }

        public Criteria andVersionMiniEqualTo(Integer value) {
            addCriterion("version_mini =", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniNotEqualTo(Integer value) {
            addCriterion("version_mini <>", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniGreaterThan(Integer value) {
            addCriterion("version_mini >", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_mini >=", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniLessThan(Integer value) {
            addCriterion("version_mini <", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniLessThanOrEqualTo(Integer value) {
            addCriterion("version_mini <=", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniLike(Integer value) {
            addCriterion("version_mini like", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniNotLike(Integer value) {
            addCriterion("version_mini not like", value, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniIn(List<Integer> values) {
            addCriterion("version_mini in", values, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniNotIn(List<Integer> values) {
            addCriterion("version_mini not in", values, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniBetween(Integer value1, Integer value2) {
            addCriterion("version_mini between", value1, value2, "versionMini");
            return (Criteria) this;
        }

        public Criteria andVersionMiniNotBetween(Integer value1, Integer value2) {
            addCriterion("version_mini not between", value1, value2, "versionMini");
            return (Criteria) this;
        }
        
			
        public Criteria andVersionCodeIsNull() {
            addCriterion("version_code is null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeIsNotNull() {
            addCriterion("version_code is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeEqualTo(String value) {
            addCriterion("version_code =", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotEqualTo(String value) {
            addCriterion("version_code <>", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeGreaterThan(String value) {
            addCriterion("version_code >", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("version_code >=", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeLessThan(String value) {
            addCriterion("version_code <", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeLessThanOrEqualTo(String value) {
            addCriterion("version_code <=", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeLike(String value) {
            addCriterion("version_code like", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotLike(String value) {
            addCriterion("version_code not like", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeIn(List<String> values) {
            addCriterion("version_code in", values, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotIn(List<String> values) {
            addCriterion("version_code not in", values, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeBetween(String value1, String value2) {
            addCriterion("version_code between", value1, value2, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotBetween(String value1, String value2) {
            addCriterion("version_code not between", value1, value2, "versionCode");
            return (Criteria) this;
        }
        
			
        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(Integer value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(Integer value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
        
			
        public Criteria andAppUrlIsNull() {
            addCriterion("app_url is null");
            return (Criteria) this;
        }

        public Criteria andAppUrlIsNotNull() {
            addCriterion("app_url is not null");
            return (Criteria) this;
        }

        public Criteria andAppUrlEqualTo(String value) {
            addCriterion("app_url =", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotEqualTo(String value) {
            addCriterion("app_url <>", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlGreaterThan(String value) {
            addCriterion("app_url >", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlGreaterThanOrEqualTo(String value) {
            addCriterion("app_url >=", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlLessThan(String value) {
            addCriterion("app_url <", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlLessThanOrEqualTo(String value) {
            addCriterion("app_url <=", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlLike(String value) {
            addCriterion("app_url like", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotLike(String value) {
            addCriterion("app_url not like", value, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlIn(List<String> values) {
            addCriterion("app_url in", values, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotIn(List<String> values) {
            addCriterion("app_url not in", values, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlBetween(String value1, String value2) {
            addCriterion("app_url between", value1, value2, "appUrl");
            return (Criteria) this;
        }

        public Criteria andAppUrlNotBetween(String value1, String value2) {
            addCriterion("app_url not between", value1, value2, "appUrl");
            return (Criteria) this;
        }
        
			
        public Criteria andUpgradePointIsNull() {
            addCriterion("upgrade_point is null");
            return (Criteria) this;
        }

        public Criteria andUpgradePointIsNotNull() {
            addCriterion("upgrade_point is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradePointEqualTo(String value) {
            addCriterion("upgrade_point =", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointNotEqualTo(String value) {
            addCriterion("upgrade_point <>", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointGreaterThan(String value) {
            addCriterion("upgrade_point >", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointGreaterThanOrEqualTo(String value) {
            addCriterion("upgrade_point >=", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointLessThan(String value) {
            addCriterion("upgrade_point <", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointLessThanOrEqualTo(String value) {
            addCriterion("upgrade_point <=", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointLike(String value) {
            addCriterion("upgrade_point like", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointNotLike(String value) {
            addCriterion("upgrade_point not like", value, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointIn(List<String> values) {
            addCriterion("upgrade_point in", values, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointNotIn(List<String> values) {
            addCriterion("upgrade_point not in", values, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointBetween(String value1, String value2) {
            addCriterion("upgrade_point between", value1, value2, "upgradePoint");
            return (Criteria) this;
        }

        public Criteria andUpgradePointNotBetween(String value1, String value2) {
            addCriterion("upgrade_point not between", value1, value2, "upgradePoint");
            return (Criteria) this;
        }
        
			
        public Criteria andDataStatusIsNull() {
            addCriterion("data_status is null");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNotNull() {
            addCriterion("data_status is not null");
            return (Criteria) this;
        }

        public Criteria andDataStatusEqualTo(Character value) {
            addCriterion("data_status =", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotEqualTo(Character value) {
            addCriterion("data_status <>", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThan(Character value) {
            addCriterion("data_status >", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThanOrEqualTo(Character value) {
            addCriterion("data_status >=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThan(Character value) {
            addCriterion("data_status <", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThanOrEqualTo(Character value) {
            addCriterion("data_status <=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLike(Character value) {
            addCriterion("data_status like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotLike(Character value) {
            addCriterion("data_status not like", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusIn(List<Character> values) {
            addCriterion("data_status in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotIn(List<Character> values) {
            addCriterion("data_status not in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusBetween(Character value1, Character value2) {
            addCriterion("data_status between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotBetween(Character value1, Character value2) {
            addCriterion("data_status not between", value1, value2, "dataStatus");
            return (Criteria) this;
        }
        
			
        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(Date value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(Date value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
        
			
        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(Date value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(Date value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
        
	}

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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