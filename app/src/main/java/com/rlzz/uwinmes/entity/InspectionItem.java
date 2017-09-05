package com.rlzz.uwinmes.entity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by monty on 2017/9/5.
 */

public class InspectionItem {
    public InspectionItem(String inspectionType, Item... inspectionItems) {
        this.inspectionType = inspectionType;
        this.inspectionItems = Arrays.asList(inspectionItems);
    }

    public String inspectionType; // 检查类型
    public List<Item> inspectionItems; // 检查项列表

    public static class Item {
        public Item(String name, String unitStr) {
            this.name = name;
            this.unitStr = unitStr;

        }

        public String name; // 检查项名称
        public String unitStr; // 检查项单位
        public boolean checked; // 是否选中
    }

}
