/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Z
 */
@Entity
@Table(name = "ALARM_INFO_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlarmInfo.findAll", query = "SELECT a FROM AlarmInfo a"),
    @NamedQuery(name = "AlarmInfo.findBySerialNumber", query = "SELECT a FROM AlarmInfo a WHERE a.serialNumber = :serialNumber"),
    @NamedQuery(name = "AlarmInfo.findByAlarmStatus", query = "SELECT a FROM AlarmInfo a WHERE a.alarmStatus = :alarmStatus"),
    @NamedQuery(name = "AlarmInfo.findByAlarmType", query = "SELECT a FROM AlarmInfo a WHERE a.alarmType = :alarmType"),
    @NamedQuery(name = "AlarmInfo.findByOccurrenceTime", query = "SELECT a FROM AlarmInfo a WHERE a.occurrenceTime = :occurrenceTime"),
    @NamedQuery(name = "AlarmInfo.findByResponseTime", query = "SELECT a FROM AlarmInfo a WHERE a.responseTime = :responseTime"),
    @NamedQuery(name = "AlarmInfo.findByAlarmMemo", query = "SELECT a FROM AlarmInfo a WHERE a.alarmMemo = :alarmMemo"),
    @NamedQuery(name = "AlarmInfo.getMaxSerialNumber", query = "SELECT MAX(a.serialNumber) FROM AlarmInfo a")})
public class AlarmInfo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SERIAL_NUMBER")
    private Long serialNumber;
    @Column(name = "ALARM_STATUS")
    private Short alarmStatus;
    @Column(name = "ALARM_TYPE")
    private Short alarmType;
    @Column(name = "OCCURRENCE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date occurrenceTime;
    @Column(name = "RESPONSE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseTime;
    @Column(name = "ALARM_MEMO")
    private String alarmMemo;
    @JoinColumn(name = "RELATED_SENSOR_ID", referencedColumnName = "SENSOR_ID")
    @ManyToOne
    private SensorInfo relatedSensorId;

    public AlarmInfo() {
    }

    public AlarmInfo(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        Long oldSerialNumber = this.serialNumber;
        this.serialNumber = serialNumber;
        changeSupport.firePropertyChange("serialNumber", oldSerialNumber, serialNumber);
    }

    public Short getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Short alarmStatus) {
        Short oldAlarmStatus = this.alarmStatus;
        this.alarmStatus = alarmStatus;
        changeSupport.firePropertyChange("alarmStatus", oldAlarmStatus, alarmStatus);
    }

    public Short getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Short alarmType) {
        Short oldAlarmType = this.alarmType;
        this.alarmType = alarmType;
        changeSupport.firePropertyChange("alarmType", oldAlarmType, alarmType);
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        Date oldOccurrenceTime = this.occurrenceTime;
        this.occurrenceTime = occurrenceTime;
        changeSupport.firePropertyChange("occurrenceTime", oldOccurrenceTime, occurrenceTime);
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        Date oldResponseTime = this.responseTime;
        this.responseTime = responseTime;
        changeSupport.firePropertyChange("responseTime", oldResponseTime, responseTime);
    }

    public String getAlarmMemo() {
        return alarmMemo;
    }

    public void setAlarmMemo(String alarmMemo) {
        String oldAlarmMemo = this.alarmMemo;
        this.alarmMemo = alarmMemo;
        changeSupport.firePropertyChange("alarmMemo", oldAlarmMemo, alarmMemo);
    }

    public SensorInfo getRelatedSensorId() {
        return relatedSensorId;
    }

    public void setRelatedSensorId(SensorInfo relatedSensorId) {
        SensorInfo oldRelatedSensorId = this.relatedSensorId;
        this.relatedSensorId = relatedSensorId;
        changeSupport.firePropertyChange("relatedSensorId", oldRelatedSensorId, relatedSensorId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serialNumber != null ? serialNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlarmInfo)) {
            return false;
        }
        AlarmInfo other = (AlarmInfo) object;
        if ((this.serialNumber == null && other.serialNumber != null) || (this.serialNumber != null && !this.serialNumber.equals(other.serialNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ serialNumber=" + serialNumber + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
