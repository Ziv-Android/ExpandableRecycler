package com.ziv.expandablerecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziv
 * Date: 15-8-28
 * Time: 下午7:10
 */
public class Entity {
    private boolean group;
    private boolean expand = false;
    private String text;
    private List<Entity> children;

    public Entity(boolean group, String text) {
        this.group = group;
        this.text = text;
        if (group){
            children = new ArrayList<>();
        }
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Entity> getChildren() {
        return children;
    }

    public void setChildren(List<Entity> children) {
        this.children = children;
    }
}
