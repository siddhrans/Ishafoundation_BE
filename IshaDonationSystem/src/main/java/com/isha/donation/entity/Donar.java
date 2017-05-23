package com.isha.donation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "donardetails")
public class Donar {

    @Id
    @Column(name = "Donor_Details_ID", nullable = false)
    private Long id;

    public Long getDonarId() {
        return id;
    }

    public void setDonarId(Long id) {
        this.id = id;
    }

    public Donar(Long id) {
        this.id = id;
    }

    public Donar() {

    }

    @Column(name = "Donor_Phone_Num", nullable = false)
    private String mobileNumber;

    public String getDonarPhoneNumber() {
        return mobileNumber;
    }

    public void setDonarPhoneNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "Donor_Name", nullable = false)
    private String name;

    public String getDonarName() {
        return name;
    }

    public void setDonarName(String name) {
        this.name = name;
    }

    @Column(name = "Donor_Email_id", nullable = false)
    private String email;

    public String getDonarEmail() {
        return email;
    }

    public void setDonarEmail(String email) {
        this.email = email;
    }

    @Column(name = "Donor_Reference_Name_1")
    private String refName1;

    public String getDonarRefName1() {
        return refName1;
    }

    public void setDonarRefName1(String refName1) {
        this.refName1 = refName1;
    }

    @Column(name = "Donor_Reference_Name_2")
    private String refName2;

    public String getDonarRefName2() {
        return refName2;
    }

    public void setDonarRefName2(String refName2) {
        this.refName2 = refName2;
    }

    @Column(name = "Donor_Status")
    private String status;

    public String getDonarStatus() {
        return status;
    }

    public void setDonarStatus(String status) {
        this.status = status;
    }

    @Column(name = "Donor_Region")
    private String region;

    public String getDonarRegion() {
        return region;
    }

    public void setDonarRegion(String region) {
        this.region = region;
    }

    @Column(name = "Donor_State")
    private String state;

    public String getDonarState() {
        return state;
    }

    public void setDonarState(String state) {
        this.state = state;
    }

    @Column(name = "Donor_City")
    private String city;

    public String getDonarCity() {
        return city;
    }

    public void setDonarCity(String city) {
        this.city = city;
    }

    @Column(name = "Donor_Center")
    private String center;

    public String getDonarcenter() {
        return center;
    }

    public void setDonarCenter(String center) {
        this.center = center;
    }

    @Column(name = "Bank_Acct_Holder_Name", nullable = false)
    private String bankAccountholderName;

    public String getBankAccountholderName() {
        return bankAccountholderName;
    }

    public void setBankAccountholderName(String bankAccountholderName) {
        this.bankAccountholderName = bankAccountholderName;
    }

    @Column(name = "Bank_Name")
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "Bank_Branch")
    private String branchName;

    public String getBankBranch() {
        return branchName;
    }

    public void setBankBranch(String branchName) {
        this.branchName = branchName;
    }

    @Column(name = "Bank_Acct_Num", nullable = false)
    private String bankAccountNumber;

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankaccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Column(name = "Bank_MICR")
    private int bankMICR;

    public int getBankMICR() {
        return bankMICR;
    }

    public void setBankMICR(int bankMICR) {
        this.bankMICR = bankMICR;
    }

    @Column(name = "Bank_IFSC_Cd")
    private String bankIfscCode;

    public String getBankIfscCode() {
        return bankIfscCode;
    }

    public void setBankIfscCode(String bankIfscCode) {
        this.bankIfscCode = bankIfscCode;
    }

    @Column(name = "Bank_Acct_Type", nullable = false)
    private String bankAccountType;

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    @Column(name = "Bank_UMRN")
    private String bankUMRN;

    public String getbankUMRN() {
        return bankUMRN;
    }

    public void setbankUMRN(String bankUMRN) {
        this.bankUMRN = bankUMRN;
    }

    @Column(name = "Bank_Sponsor_Cd")
    private String bankSponsorCode;

    public String getbankSponsorCode() {
        return bankSponsorCode;
    }

    public void setbankSponsorCode(String bankSponsorCode) {
        this.bankSponsorCode = bankSponsorCode;
    }

    @Column(name = "Bank_Utility_Cd")
    private String bankUtilityCode;

    public String getBankUtilityCode() {
        return bankUtilityCode;
    }

    public void setBankUtilityCode(String bankUtilityCode) {
        this.bankUtilityCode = bankUtilityCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "Donation_Start_Dt", nullable = false)
    private Date startDate;

    public Date getDonationStartDate() {
        return startDate;
    }

    public void setDonationStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "Donation_End_Dt", nullable = false)
    private Date endDate;

    public Date getDonationEndDate() {
        return endDate;
    }

    public void setDonationEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Donation_Frequency", nullable = false)
    private String frequency;

    public String getDonationFrequency() {
        return frequency;
    }

    public void setDonationFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Column(name = "Donation_Debit_Type")
    private String debitType;

    public String getDonationDebitType() {
        return debitType;
    }

    public void setDonationDebitType(String debitType) {
        this.debitType = debitType;
    }

    @Column(name = "TPPS_Consumer_Code")
    private String TPPSConsumerCode;

    public String getTppsConsumerCode() {
        return TPPSConsumerCode;
    }

    public void setTppsConsumerCode(String TPPSConsumerCode) {
        this.TPPSConsumerCode = TPPSConsumerCode;
    }

    @Column(name = "Create_Dt", nullable = false)
    private String createDate;

    public String getCreateDonardate() {
        return createDate;
    }

    public void setCreateDonardate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "Create_By", nullable = false)
    private String createdBy;

    public String getCreatorName() {
        return createdBy;
    }

    public void setCreatorName(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "Update_Dt")
    private String updateDate;

    public String getDonarUpdatedDate() {
        return updateDate;
    }

    public void setDonarUpdatedDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "Update_By")
    private String updatedBy;

    public String getUpdaterName() {
        return updatedBy;
    }

    public void setUpdaterName(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "Update_Comments")
    private String comments;

    public String getDonarComments() {
        return comments;
    }

    public void setDonarComments(String comments) {
        this.comments = comments;
    }
}
