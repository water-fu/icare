package com.wisdom.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * 行政区域下拉列表
 * Created by fusj on 16/4/21.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ZoneSelect {

    /**
     * 值
     */
    private String value;

    /**
     * 名称
     */
    private String text;

    /**
     * 下级
     */
    private List<ZoneSelect> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ZoneSelect> getChildren() {
        return children;
    }

    public void setChildren(List<ZoneSelect> children) {
        this.children = children;
    }
}
