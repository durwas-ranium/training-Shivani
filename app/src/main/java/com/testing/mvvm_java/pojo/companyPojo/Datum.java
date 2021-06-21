
package com.testing.mvvm_java.pojo.companyPojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("federal_tax_id")
    @Expose
    private String federalTaxId;
    @SerializedName("date_founded")
    @Expose
    private String dateFounded;
    @SerializedName("company_structure_type_id")
    @Expose
    private Integer companyStructureTypeId;
    @SerializedName("company_structure")
    @Expose
    private CompanyStructure companyStructure;
    @SerializedName("is_closely_held")
    @Expose
    private Integer isCloselyHeld;
    @SerializedName("is_ever_bankrupt")
    @Expose
    private Integer isEverBankrupt;
    @SerializedName("phone_number")
    @Expose
    private PhoneNumber phoneNumber;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("company_url")
    @Expose
    private String companyUrl;

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

    public String getFederalTaxId() {
        return federalTaxId;
    }

    public void setFederalTaxId(String federalTaxId) {
        this.federalTaxId = federalTaxId;
    }

    public String getDateFounded() {
        return dateFounded;
    }

    public void setDateFounded(String dateFounded) {
        this.dateFounded = dateFounded;
    }

    public Integer getCompanyStructureTypeId() {
        return companyStructureTypeId;
    }

    public void setCompanyStructureTypeId(Integer companyStructureTypeId) {
        this.companyStructureTypeId = companyStructureTypeId;
    }

    public CompanyStructure getCompanyStructure() {
        return companyStructure;
    }

    public void setCompanyStructure(CompanyStructure companyStructure) {
        this.companyStructure = companyStructure;
    }

    public Integer getIsCloselyHeld() {
        return isCloselyHeld;
    }

    public void setIsCloselyHeld(Integer isCloselyHeld) {
        this.isCloselyHeld = isCloselyHeld;
    }

    public Integer getIsEverBankrupt() {
        return isEverBankrupt;
    }

    public void setIsEverBankrupt(Integer isEverBankrupt) {
        this.isEverBankrupt = isEverBankrupt;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

}
