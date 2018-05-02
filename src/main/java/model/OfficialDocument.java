package model;

import java.util.Objects;

/**
 * @author guyue
 * @date 2018/4/25
 */
public class OfficialDocument {
    /**
     * 份号(可选)
     */
    private String printNo = "null";
    /**
     * 密级(可选)
     */
    private String secretLevel = "null";
    /**
     * 保密期限(可选)
     */
    private String secretData = "null";
    /**
     * 紧急程度(可选)
     */
    private String emergency = "null";
    /**
     * 发文机关标志
     */
    private String officialName = "null";
    /**
     * 发文字号
     */
    private String officialMark = "null";
    /**
     * 签发人
     */
    private String sender = "null";
    /**
     * 标题
     */
    private String title = "null";
    /**
     * 主送机关
     */
    private String receivedAuthority = "null";
    /**
     * 正文
     */
    private String content = "null";
    /**
     * 附件说明(可选)
     */
    private String enclosureInfo = "null";
    /**
     * 发文机关署名
     */
    private String senderPublicKey = "null";
    /**
     * 成文日期
     */
    private String createDate = "null";
    /**
     * 印章
     */
    private String officialPublicKey = "null";
    /**
     * 附注(可选)
     */
    private String notes = "null";
    /**
     * 附件(可选)
     */
    private String enclosure = "null";
    /**
     * 抄送机关(可选)
     */
    private String copyToAuthority = "null";
    /**
     * 印发机关(可选)
     */
    private String printAuthority = "null";
    /**
     * 印发日期(可选)
     */
    private String printDate = "null";

    public OfficialDocument() {
        this("必填","必填","必填","必填","必填","必填","必填","必填","必填");
    }

    public OfficialDocument(String officialName, String officialMark, String sender,
            String title, String receivedAuthority, String content, String senderPublicKey,
            String createDate, String officialPublicKey) {
        this.officialName = officialName;
        this.officialMark = officialMark;
        this.sender = sender;
        this.title = title;
        this.receivedAuthority = receivedAuthority;
        this.content = content;
        this.senderPublicKey = senderPublicKey;
        this.createDate = createDate;
        this.officialPublicKey = officialPublicKey;
    }

    public String getPrintNo() {
        return printNo;
    }

    public void setPrintNo(String printNo) {
        this.printNo = printNo;
    }

    public String getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    public String getSecretData() {
        return secretData;
    }

    public void setSecretData(String secretData) {
        this.secretData = secretData;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getOfficialMark() {
        return officialMark;
    }

    public void setOfficialMark(String officialMark) {
        this.officialMark = officialMark;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceivedAuthority() {
        return receivedAuthority;
    }

    public void setReceivedAuthority(String receivedAuthority) {
        this.receivedAuthority = receivedAuthority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnclosureInfo() {
        return enclosureInfo;
    }

    public void setEnclosureInfo(String enclosureInfo) {
        this.enclosureInfo = enclosureInfo;
    }

    public String getSenderPublicKey() {
        return senderPublicKey;
    }

    public void setSenderPublicKey(String senderPublicKey) {
        this.senderPublicKey = senderPublicKey;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOfficialPublicKey() {
        return officialPublicKey;
    }

    public void setOfficialPublicKey(String officialPublicKey) {
        this.officialPublicKey = officialPublicKey;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getCopyToAuthority() {
        return copyToAuthority;
    }

    public void setCopyToAuthority(String copyToAuthority) {
        this.copyToAuthority = copyToAuthority;
    }

    public String getPrintAuthority() {
        return printAuthority;
    }

    public void setPrintAuthority(String printAuthority) {
        this.printAuthority = printAuthority;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfficialDocument)) {
            return false;
        }
        OfficialDocument document = (OfficialDocument) o;
        return Objects.equals(officialName, document.officialName) &&
                Objects.equals(officialMark, document.officialMark) &&
                Objects.equals(sender, document.sender) &&
                Objects.equals(title, document.title) &&
                Objects.equals(receivedAuthority, document.receivedAuthority) &&
                Objects.equals(content, document.content) &&
                Objects.equals(senderPublicKey, document.senderPublicKey) &&
                Objects.equals(officialPublicKey, document.officialPublicKey);
    }

    @Override
    public int hashCode() {

        return Objects
                .hash(printNo, secretLevel, secretData, emergency, officialName,
                        officialMark, sender, title, receivedAuthority, content, enclosureInfo,
                        senderPublicKey, createDate, officialPublicKey, notes, enclosure,
                        copyToAuthority, printAuthority, printDate);
    }

    @Override
    public String toString() {
        return "OfficialDocument{" +
                "printNo='" + printNo + '\'' +
                ", secretLevel='" + secretLevel + '\'' +
                ", secretData='" + secretData + '\'' +
                ", emergency='" + emergency + '\'' +
                ", officialName='" + officialName + '\'' +
                ", officialMark='" + officialMark + '\'' +
                ", sender='" + sender + '\'' +
                ", title='" + title + '\'' +
                ", receivedAuthority='" + receivedAuthority + '\'' +
                ", content='" + content + '\'' +
                ", enclosureInfo='" + enclosureInfo + '\'' +
                ", senderPublicKey='" + senderPublicKey + '\'' +
                ", createDate='" + createDate + '\'' +
                ", officialPublicKey='" + officialPublicKey + '\'' +
                ", notes='" + notes + '\'' +
                ", enclosure='" + enclosure + '\'' +
                ", copyToAuthority='" + copyToAuthority + '\'' +
                ", printAuthority='" + printAuthority + '\'' +
                ", printDate='" + printDate + '\'' +
                '}';
    }
}
