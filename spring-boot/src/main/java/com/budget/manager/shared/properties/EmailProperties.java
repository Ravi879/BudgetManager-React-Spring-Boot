package com.budget.manager.shared.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email")
public class EmailProperties {

    private String senderAddress;
    private String subjectRegisterVerification;
    private String urlRegisterVerification;
    private String urlPasswordReset;
    private String subjectPasswordReset;
    private String urlFrontEnd;

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getUrlPasswordReset() {
        return urlPasswordReset;
    }

    public void setUrlPasswordReset(String urlPasswordReset) {
        this.urlPasswordReset = urlPasswordReset;
    }


    public String getSubjectRegisterVerification() {
        return subjectRegisterVerification;
    }

    public void setSubjectRegisterVerification(String subjectRegisterVerification) {
        this.subjectRegisterVerification = subjectRegisterVerification;
    }

    public String getSubjectPasswordReset() {
        return subjectPasswordReset;
    }

    public void setSubjectPasswordReset(String subjectPasswordReset) {
        this.subjectPasswordReset = subjectPasswordReset;
    }

    public String getUrlRegisterVerification() {
        return urlRegisterVerification;
    }

    public void setUrlRegisterVerification(String urlRegisterVerification) {
        this.urlRegisterVerification = urlRegisterVerification;
    }

    public String getUrlFrontEnd() {
        return urlFrontEnd;
    }

    public void setUrlFrontEnd(String urlFrontEnd) {
        this.urlFrontEnd = urlFrontEnd;
    }
}
