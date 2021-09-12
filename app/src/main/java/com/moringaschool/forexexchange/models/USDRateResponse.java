
package com.moringaschool.forexexchange.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moringaschool.forexexchange.ConversionRates;


public class USDRateResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("documentation")
    @Expose
    private String documentation;
    @SerializedName("terms_of_use")
    @Expose
    private String termsOfUse;
    @SerializedName("time_last_update_unix")
    @Expose
    private Integer timeLastUpdateUnix;
    @SerializedName("time_last_update_utc")
    @Expose
    private String timeLastUpdateUtc;
    @SerializedName("time_next_update_unix")
    @Expose
    private Integer timeNextUpdateUnix;
    @SerializedName("time_next_update_utc")
    @Expose
    private String timeNextUpdateUtc;
    @SerializedName("base_code")
    @Expose
    private String baseCode;
    @SerializedName("conversion_rates")
    @Expose
    private com.moringaschool.forexexchange.ConversionRates conversionRates;

    /**
     * No args constructor for use in serialization
     * 
     */
    public USDRateResponse() {
    }

    /**
     * 
     * @param result
     * @param timeNextUpdateUtc
     * @param baseCode
     * @param termsOfUse
     * @param documentation
     * @param conversionRates
     * @param timeLastUpdateUnix
     * @param timeNextUpdateUnix
     * @param timeLastUpdateUtc
     */
    public USDRateResponse(String result, String documentation, String termsOfUse, Integer timeLastUpdateUnix, String timeLastUpdateUtc, Integer timeNextUpdateUnix, String timeNextUpdateUtc, String baseCode, ConversionRates conversionRates) {
        super();
        this.result = result;
        this.documentation = documentation;
        this.termsOfUse = termsOfUse;
        this.timeLastUpdateUnix = timeLastUpdateUnix;
        this.timeLastUpdateUtc = timeLastUpdateUtc;
        this.timeNextUpdateUnix = timeNextUpdateUnix;
        this.timeNextUpdateUtc = timeNextUpdateUtc;
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public Integer getTimeLastUpdateUnix() {
        return timeLastUpdateUnix;
    }

    public void setTimeLastUpdateUnix(Integer timeLastUpdateUnix) {
        this.timeLastUpdateUnix = timeLastUpdateUnix;
    }

    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    public void setTimeLastUpdateUtc(String timeLastUpdateUtc) {
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    public Integer getTimeNextUpdateUnix() {
        return timeNextUpdateUnix;
    }

    public void setTimeNextUpdateUnix(Integer timeNextUpdateUnix) {
        this.timeNextUpdateUnix = timeNextUpdateUnix;
    }

    public String getTimeNextUpdateUtc() {
        return timeNextUpdateUtc;
    }

    public void setTimeNextUpdateUtc(String timeNextUpdateUtc) {
        this.timeNextUpdateUtc = timeNextUpdateUtc;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public ConversionRates getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(ConversionRates conversionRates) {
        this.conversionRates = conversionRates;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.result == null)? 0 :this.result.hashCode()));
        result = ((result* 31)+((this.timeNextUpdateUtc == null)? 0 :this.timeNextUpdateUtc.hashCode()));
        result = ((result* 31)+((this.baseCode == null)? 0 :this.baseCode.hashCode()));
        result = ((result* 31)+((this.termsOfUse == null)? 0 :this.termsOfUse.hashCode()));
        result = ((result* 31)+((this.documentation == null)? 0 :this.documentation.hashCode()));
        result = ((result* 31)+((this.conversionRates == null)? 0 :this.conversionRates.hashCode()));
        result = ((result* 31)+((this.timeLastUpdateUnix == null)? 0 :this.timeLastUpdateUnix.hashCode()));
        result = ((result* 31)+((this.timeNextUpdateUnix == null)? 0 :this.timeNextUpdateUnix.hashCode()));
        result = ((result* 31)+((this.timeLastUpdateUtc == null)? 0 :this.timeLastUpdateUtc.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof USDRateResponse) == false) {
            return false;
        }
        USDRateResponse rhs = ((USDRateResponse) other);
        return ((((((((((this.result == rhs.result)||((this.result!= null)&&this.result.equals(rhs.result)))&&((this.timeNextUpdateUtc == rhs.timeNextUpdateUtc)||((this.timeNextUpdateUtc!= null)&&this.timeNextUpdateUtc.equals(rhs.timeNextUpdateUtc))))&&((this.baseCode == rhs.baseCode)||((this.baseCode!= null)&&this.baseCode.equals(rhs.baseCode))))&&((this.termsOfUse == rhs.termsOfUse)||((this.termsOfUse!= null)&&this.termsOfUse.equals(rhs.termsOfUse))))&&((this.documentation == rhs.documentation)||((this.documentation!= null)&&this.documentation.equals(rhs.documentation))))&&((this.conversionRates == rhs.conversionRates)||((this.conversionRates!= null)&&this.conversionRates.equals(rhs.conversionRates))))&&((this.timeLastUpdateUnix == rhs.timeLastUpdateUnix)||((this.timeLastUpdateUnix!= null)&&this.timeLastUpdateUnix.equals(rhs.timeLastUpdateUnix))))&&((this.timeNextUpdateUnix == rhs.timeNextUpdateUnix)||((this.timeNextUpdateUnix!= null)&&this.timeNextUpdateUnix.equals(rhs.timeNextUpdateUnix))))&&((this.timeLastUpdateUtc == rhs.timeLastUpdateUtc)||((this.timeLastUpdateUtc!= null)&&this.timeLastUpdateUtc.equals(rhs.timeLastUpdateUtc))));
    }

}
