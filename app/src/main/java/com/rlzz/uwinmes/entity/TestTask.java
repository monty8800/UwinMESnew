package com.rlzz.uwinmes.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by monty on 2017/9/1.
 */

public class TestTask implements Parcelable {
    /**
     * 是否可录入
     */
    public boolean isEnterEnable;
    /**
     * 处理人
     */
    public String operator;

    /**
     * 到货日期
     */
    public String arrivalDate;

    /**
     * 到货订单编号
     */
    public String arrivalOrderNumber;

    /**
     * 行号
     */
    public int lineNumber;

    /**
     * 物料编号
     */
    public String materialNumber;

    /**
     * 物料名称
     */
    public String materialName;

    /**
     * 物料规格
     */
    public String specification;

    /**
     * 检验方式
     */
    public String inspectionMethod;

    /**
     * 检验数量
     */
    public long inspectionCount;

    /**
     * what's this???
     */
    public String AQL;

    /**
     * 检验模板
     */
    public String inspectionTemplate;

    /**
     * 检验单号
     */
    public String inspectionNumber;

    /**
     * 检验员
     */
    public String inspector;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isEnterEnable ? (byte) 1 : (byte) 0);
        dest.writeString(this.operator);
        dest.writeString(this.arrivalDate);
        dest.writeString(this.arrivalOrderNumber);
        dest.writeInt(this.lineNumber);
        dest.writeString(this.materialNumber);
        dest.writeString(this.materialName);
        dest.writeString(this.specification);
        dest.writeString(this.inspectionMethod);
        dest.writeLong(this.inspectionCount);
        dest.writeString(this.AQL);
        dest.writeString(this.inspectionTemplate);
        dest.writeString(this.inspectionNumber);
        dest.writeString(this.inspector);
    }

    public TestTask() {
    }

    protected TestTask(Parcel in) {
        this.isEnterEnable = in.readByte() != 0;
        this.operator = in.readString();
        this.arrivalDate = in.readString();
        this.arrivalOrderNumber = in.readString();
        this.lineNumber = in.readInt();
        this.materialNumber = in.readString();
        this.materialName = in.readString();
        this.specification = in.readString();
        this.inspectionMethod = in.readString();
        this.inspectionCount = in.readLong();
        this.AQL = in.readString();
        this.inspectionTemplate = in.readString();
        this.inspectionNumber = in.readString();
        this.inspector = in.readString();
    }

    public static final Parcelable.Creator<TestTask> CREATOR = new Parcelable.Creator<TestTask>() {
        @Override
        public TestTask createFromParcel(Parcel source) {
            return new TestTask(source);
        }

        @Override
        public TestTask[] newArray(int size) {
            return new TestTask[size];
        }
    };
}
