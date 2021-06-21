
package com.testing.mvvm_java.pojo.companyPojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CompanyStructure {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sort_order")
    @Expose
    private Integer sortOrder;
    @SerializedName("is_custom")
    @Expose
    private Integer isCustom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Integer isCustom) {
        this.isCustom = isCustom;
    }

}
