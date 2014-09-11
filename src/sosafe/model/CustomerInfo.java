/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Z
 */
@Entity
@Table(name = "CUSTOMER_INFO_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerInfo.findAll", query = "SELECT c FROM CustomerInfo c"),
    @NamedQuery(name = "CustomerInfo.findByCustomerId", query = "SELECT c FROM CustomerInfo c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "CustomerInfo.findByCustomerName", query = "SELECT c FROM CustomerInfo c WHERE c.customerName = :customerName"),
    @NamedQuery(name = "CustomerInfo.findByEmailAddress", query = "SELECT c FROM CustomerInfo c WHERE c.emailAddress = :emailAddress"),
    @NamedQuery(name = "CustomerInfo.findByLicenseCode", query = "SELECT c FROM CustomerInfo c WHERE c.licenseCode = :licenseCode"),
    @NamedQuery(name = "CustomerInfo.findByMasterPassword", query = "SELECT c FROM CustomerInfo c WHERE c.masterPassword = :masterPassword"),
    @NamedQuery(name = "CustomerInfo.findByPhoneNumber", query = "SELECT c FROM CustomerInfo c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "CustomerInfo.findByPrimaryContactNumber", query = "SELECT c FROM CustomerInfo c WHERE c.primaryContactNumber = :primaryContactNumber"),
    @NamedQuery(name = "CustomerInfo.findByPropertyAddress", query = "SELECT c FROM CustomerInfo c WHERE c.propertyAddress = :propertyAddress"),
    @NamedQuery(name = "CustomerInfo.findByResponseCode", query = "SELECT c FROM CustomerInfo c WHERE c.responseCode = :responseCode"),
    @NamedQuery(name = "CustomerInfo.findBySecondaryContactNumber", query = "SELECT c FROM CustomerInfo c WHERE c.secondaryContactNumber = :secondaryContactNumber")})
public class CustomerInfo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "LICENSE_CODE")
    private String licenseCode;
    @Column(name = "MASTER_PASSWORD")
    private String masterPassword;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "PRIMARY_CONTACT_NUMBER")
    private String primaryContactNumber;
    @Column(name = "PROPERTY_ADDRESS")
    private String propertyAddress;
    @Column(name = "RESPONSE_CODE")
    private String responseCode;
    @Column(name = "SECONDARY_CONTACT_NUMBER")
    private String secondaryContactNumber;
    @OneToMany(mappedBy = "relatedCustomerId")
    private Collection<SensorInfo> sensorInfoCollection;

    public CustomerInfo() {
    }

    public CustomerInfo(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        Long oldCustomerId = this.customerId;
        this.customerId = customerId;
        changeSupport.firePropertyChange("customerId", oldCustomerId, customerId);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        String oldCustomerName = this.customerName;
        this.customerName = customerName;
        changeSupport.firePropertyChange("customerName", oldCustomerName, customerName);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        String oldEmailAddress = this.emailAddress;
        this.emailAddress = emailAddress;
        changeSupport.firePropertyChange("emailAddress", oldEmailAddress, emailAddress);
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        String oldLicenseCode = this.licenseCode;
        this.licenseCode = licenseCode;
        changeSupport.firePropertyChange("licenseCode", oldLicenseCode, licenseCode);
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        String oldMasterPassword = this.masterPassword;
        this.masterPassword = masterPassword;
        changeSupport.firePropertyChange("masterPassword", oldMasterPassword, masterPassword);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String oldPhoneNumber = this.phoneNumber;
        this.phoneNumber = phoneNumber;
        changeSupport.firePropertyChange("phoneNumber", oldPhoneNumber, phoneNumber);
    }

    public String getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public void setPrimaryContactNumber(String primaryContactNumber) {
        String oldPrimaryContactNumber = this.primaryContactNumber;
        this.primaryContactNumber = primaryContactNumber;
        changeSupport.firePropertyChange("primaryContactNumber", oldPrimaryContactNumber, primaryContactNumber);
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        String oldPropertyAddress = this.propertyAddress;
        this.propertyAddress = propertyAddress;
        changeSupport.firePropertyChange("propertyAddress", oldPropertyAddress, propertyAddress);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        String oldResponseCode = this.responseCode;
        this.responseCode = responseCode;
        changeSupport.firePropertyChange("responseCode", oldResponseCode, responseCode);
    }

    public String getSecondaryContactNumber() {
        return secondaryContactNumber;
    }

    public void setSecondaryContactNumber(String secondaryContactNumber) {
        String oldSecondaryContactNumber = this.secondaryContactNumber;
        this.secondaryContactNumber = secondaryContactNumber;
        changeSupport.firePropertyChange("secondaryContactNumber", oldSecondaryContactNumber, secondaryContactNumber);
    }

    @XmlTransient
    public Collection<SensorInfo> getSensorInfoCollection() {
        return sensorInfoCollection;
    }

    public void setSensorInfoCollection(Collection<SensorInfo> sensorInfoCollection) {
        this.sensorInfoCollection = sensorInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerInfo)) {
            return false;
        }
        CustomerInfo other = (CustomerInfo) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ customerId=" + customerId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
