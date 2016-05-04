package com.wisdom.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * 下拉列表
 * Created by fusj on 16/4/21.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Select {

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
    private List<Select> children;

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

    public List<Select> getChildren() {
        return children;
    }

    public void setChildren(List<Select> children) {
        this.children = children;
    }
}
