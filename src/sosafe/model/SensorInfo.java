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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SENSOR_INFO_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SensorInfo.findAll", query = "SELECT s FROM SensorInfo s"),
    @NamedQuery(name = "SensorInfo.findBySensorId", query = "SELECT s FROM SensorInfo s WHERE s.sensorId = :sensorId"),
    @NamedQuery(name = "SensorInfo.findByIpAddress", query = "SELECT s FROM SensorInfo s WHERE s.ipAddress = :ipAddress"),
    @NamedQuery(name = "SensorInfo.findByPortNumber", query = "SELECT s FROM SensorInfo s WHERE s.portNumber = :portNumber"),
    @NamedQuery(name = "SensorInfo.findByScreenPosition", query = "SELECT s FROM SensorInfo s WHERE s.screenPosition = :screenPosition"),
    @NamedQuery(name = "SensorInfo.findBySensorLocation", query = "SELECT s FROM SensorInfo s WHERE s.sensorLocation = :sensorLocation"),
    @NamedQuery(name = "SensorInfo.findBySensorPrice", query = "SELECT s FROM SensorInfo s WHERE s.sensorPrice = :sensorPrice"),
    @NamedQuery(name = "SensorInfo.findBySensorStatus", query = "SELECT s FROM SensorInfo s WHERE s.sensorStatus = :sensorStatus"),
    @NamedQuery(name = "SensorInfo.findBySensorType", query = "SELECT s FROM SensorInfo s WHERE s.sensorType = :sensorType"), 
    @NamedQuery(name = "SensorInfo.getMaxSensorId", query = "SELECT MAX(s.sensorId) FROM SensorInfo s")})
public class SensorInfo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SENSOR_ID")
    private Long sensorId;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "PORT_NUMBER")
    private Integer portNumber;
    @Column(name = "SCREEN_POSITION")
    private String screenPosition;
    @Column(name = "SENSOR_LOCATION")
    private String sensorLocation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SENSOR_PRICE")
    private Double sensorPrice;
    @Column(name = "SENSOR_STATUS")
    private Short sensorStatus;
    @Column(name = "SENSOR_TYPE")
    private Short sensorType;
    @OneToMany(mappedBy = "relatedSensorId")
    private Collection<AlarmInfo> alarmInfoCollection;
    @JoinColumn(name = "RELATED_CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne
    private CustomerInfo relatedCustomerId;

    public SensorInfo() {
    }

    public SensorInfo(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        Long oldSensorId = this.sensorId;
        this.sensorId = sensorId;
        changeSupport.firePropertyChange("sensorId", oldSensorId, sensorId);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        String oldIpAddress = this.ipAddress;
        this.ipAddress = ipAddress;
        changeSupport.firePropertyChange("ipAddress", oldIpAddress, ipAddress);
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        Integer oldPortNumber = this.portNumber;
        this.portNumber = portNumber;
        changeSupport.firePropertyChange("portNumber", oldPortNumber, portNumber);
    }

    public String getScreenPosition() {
        return screenPosition;
    }

    public void setScreenPosition(String screenPosition) {
        String oldScreenPosition = this.screenPosition;
        this.screenPosition = screenPosition;
        changeSupport.firePropertyChange("screenPosition", oldScreenPosition, screenPosition);
    }

    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        String oldSensorLocation = this.sensorLocation;
        this.sensorLocation = sensorLocation;
        changeSupport.firePropertyChange("sensorLocation", oldSensorLocation, sensorLocation);
    }

    public Double getSensorPrice() {
        return sensorPrice;
    }

    public void setSensorPrice(Double sensorPrice) {
        Double oldSensorPrice = this.sensorPrice;
        this.sensorPrice = sensorPrice;
        changeSupport.firePropertyChange("sensorPrice", oldSensorPrice, sensorPrice);
    }

    public Short getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(Short sensorStatus) {
        Short oldSensorStatus = this.sensorStatus;
        this.sensorStatus = sensorStatus;
        changeSupport.firePropertyChange("sensorStatus", oldSensorStatus, sensorStatus);
    }

    public Short getSensorType() {
        return sensorType;
    }

    public void setSensorType(Short sensorType) {
        Short oldSensorType = this.sensorType;
        this.sensorType = sensorType;
        changeSupport.firePropertyChange("sensorType", oldSensorType, sensorType);
    }

    @XmlTransient
    public Collection<AlarmInfo> getAlarmInfoCollection() {
        return alarmInfoCollection;
    }

    public void setAlarmInfoCollection(Collection<AlarmInfo> alarmInfoCollection) {
        this.alarmInfoCollection = alarmInfoCollection;
    }

    public CustomerInfo getRelatedCustomerId() {
        return relatedCustomerId;
    }

    public void setRelatedCustomerId(CustomerInfo relatedCustomerId) {
        CustomerInfo oldRelatedCustomerId = this.relatedCustomerId;
        this.relatedCustomerId = relatedCustomerId;
        changeSupport.firePropertyChange("relatedCustomerId", oldRelatedCustomerId, relatedCustomerId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sensorId != null ? sensorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensorInfo)) {
            return false;
        }
        SensorInfo other = (SensorInfo) object;
        if ((this.sensorId == null && other.sensorId != null) || (this.sensorId != null && !this.sensorId.equals(other.sensorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ sensorId=" + sensorId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
