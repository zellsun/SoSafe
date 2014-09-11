/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sosafe.model.CustomerInfo;
import sosafe.model.AlarmInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sosafe.control.exceptions.NonexistentEntityException;
import sosafe.control.exceptions.PreexistingEntityException;
import sosafe.model.SensorInfo;

/**
 *
 * @author Z
 */
public class SensorInfoJpaController implements Serializable {

    public SensorInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SensorInfo sensorInfo) throws PreexistingEntityException, Exception {
        if (sensorInfo.getAlarmInfoCollection() == null) {
            sensorInfo.setAlarmInfoCollection(new ArrayList<AlarmInfo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerInfo relatedCustomerId = sensorInfo.getRelatedCustomerId();
            if (relatedCustomerId != null) {
                relatedCustomerId = em.getReference(relatedCustomerId.getClass(), relatedCustomerId.getCustomerId());
                sensorInfo.setRelatedCustomerId(relatedCustomerId);
            }
            Collection<AlarmInfo> attachedAlarmInfoCollection = new ArrayList<AlarmInfo>();
            for (AlarmInfo alarmInfoCollectionAlarmInfoToAttach : sensorInfo.getAlarmInfoCollection()) {
                alarmInfoCollectionAlarmInfoToAttach = em.getReference(alarmInfoCollectionAlarmInfoToAttach.getClass(), alarmInfoCollectionAlarmInfoToAttach.getSerialNumber());
                attachedAlarmInfoCollection.add(alarmInfoCollectionAlarmInfoToAttach);
            }
            sensorInfo.setAlarmInfoCollection(attachedAlarmInfoCollection);
            em.persist(sensorInfo);
            if (relatedCustomerId != null) {
                relatedCustomerId.getSensorInfoCollection().add(sensorInfo);
                relatedCustomerId = em.merge(relatedCustomerId);
            }
            for (AlarmInfo alarmInfoCollectionAlarmInfo : sensorInfo.getAlarmInfoCollection()) {
                SensorInfo oldRelatedSensorIdOfAlarmInfoCollectionAlarmInfo = alarmInfoCollectionAlarmInfo.getRelatedSensorId();
                alarmInfoCollectionAlarmInfo.setRelatedSensorId(sensorInfo);
                alarmInfoCollectionAlarmInfo = em.merge(alarmInfoCollectionAlarmInfo);
                if (oldRelatedSensorIdOfAlarmInfoCollectionAlarmInfo != null) {
                    oldRelatedSensorIdOfAlarmInfoCollectionAlarmInfo.getAlarmInfoCollection().remove(alarmInfoCollectionAlarmInfo);
                    oldRelatedSensorIdOfAlarmInfoCollectionAlarmInfo = em.merge(oldRelatedSensorIdOfAlarmInfoCollectionAlarmInfo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSensorInfo(sensorInfo.getSensorId()) != null) {
                throw new PreexistingEntityException("SensorInfo " + sensorInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SensorInfo sensorInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SensorInfo persistentSensorInfo = em.find(SensorInfo.class, sensorInfo.getSensorId());
            CustomerInfo relatedCustomerIdOld = persistentSensorInfo.getRelatedCustomerId();
            CustomerInfo relatedCustomerIdNew = sensorInfo.getRelatedCustomerId();
            Collection<AlarmInfo> alarmInfoCollectionOld = persistentSensorInfo.getAlarmInfoCollection();
            Collection<AlarmInfo> alarmInfoCollectionNew = sensorInfo.getAlarmInfoCollection();
            if (relatedCustomerIdNew != null) {
                relatedCustomerIdNew = em.getReference(relatedCustomerIdNew.getClass(), relatedCustomerIdNew.getCustomerId());
                sensorInfo.setRelatedCustomerId(relatedCustomerIdNew);
            }
            Collection<AlarmInfo> attachedAlarmInfoCollectionNew = new ArrayList<AlarmInfo>();
            for (AlarmInfo alarmInfoCollectionNewAlarmInfoToAttach : alarmInfoCollectionNew) {
                alarmInfoCollectionNewAlarmInfoToAttach = em.getReference(alarmInfoCollectionNewAlarmInfoToAttach.getClass(), alarmInfoCollectionNewAlarmInfoToAttach.getSerialNumber());
                attachedAlarmInfoCollectionNew.add(alarmInfoCollectionNewAlarmInfoToAttach);
            }
            alarmInfoCollectionNew = attachedAlarmInfoCollectionNew;
            sensorInfo.setAlarmInfoCollection(alarmInfoCollectionNew);
            sensorInfo = em.merge(sensorInfo);
            if (relatedCustomerIdOld != null && !relatedCustomerIdOld.equals(relatedCustomerIdNew)) {
                relatedCustomerIdOld.getSensorInfoCollection().remove(sensorInfo);
                relatedCustomerIdOld = em.merge(relatedCustomerIdOld);
            }
            if (relatedCustomerIdNew != null && !relatedCustomerIdNew.equals(relatedCustomerIdOld)) {
                relatedCustomerIdNew.getSensorInfoCollection().add(sensorInfo);
                relatedCustomerIdNew = em.merge(relatedCustomerIdNew);
            }
            for (AlarmInfo alarmInfoCollectionOldAlarmInfo : alarmInfoCollectionOld) {
                if (!alarmInfoCollectionNew.contains(alarmInfoCollectionOldAlarmInfo)) {
                    alarmInfoCollectionOldAlarmInfo.setRelatedSensorId(null);
                    alarmInfoCollectionOldAlarmInfo = em.merge(alarmInfoCollectionOldAlarmInfo);
                }
            }
            for (AlarmInfo alarmInfoCollectionNewAlarmInfo : alarmInfoCollectionNew) {
                if (!alarmInfoCollectionOld.contains(alarmInfoCollectionNewAlarmInfo)) {
                    SensorInfo oldRelatedSensorIdOfAlarmInfoCollectionNewAlarmInfo = alarmInfoCollectionNewAlarmInfo.getRelatedSensorId();
                    alarmInfoCollectionNewAlarmInfo.setRelatedSensorId(sensorInfo);
                    alarmInfoCollectionNewAlarmInfo = em.merge(alarmInfoCollectionNewAlarmInfo);
                    if (oldRelatedSensorIdOfAlarmInfoCollectionNewAlarmInfo != null && !oldRelatedSensorIdOfAlarmInfoCollectionNewAlarmInfo.equals(sensorInfo)) {
                        oldRelatedSensorIdOfAlarmInfoCollectionNewAlarmInfo.getAlarmInfoCollection().remove(alarmInfoCollectionNewAlarmInfo);
                        oldRelatedSensorIdOfAlarmInfoCollectionNewAlarmInfo = em.merge(oldRelatedSensorIdOfAlarmInfoCollectionNewAlarmInfo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sensorInfo.getSensorId();
                if (findSensorInfo(id) == null) {
                    throw new NonexistentEntityException("The sensorInfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SensorInfo sensorInfo;
            try {
                sensorInfo = em.getReference(SensorInfo.class, id);
                sensorInfo.getSensorId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sensorInfo with id " + id + " no longer exists.", enfe);
            }
            CustomerInfo relatedCustomerId = sensorInfo.getRelatedCustomerId();
            if (relatedCustomerId != null) {
                relatedCustomerId.getSensorInfoCollection().remove(sensorInfo);
                relatedCustomerId = em.merge(relatedCustomerId);
            }
            Collection<AlarmInfo> alarmInfoCollection = sensorInfo.getAlarmInfoCollection();
            for (AlarmInfo alarmInfoCollectionAlarmInfo : alarmInfoCollection) {
                alarmInfoCollectionAlarmInfo.setRelatedSensorId(null);
                alarmInfoCollectionAlarmInfo = em.merge(alarmInfoCollectionAlarmInfo);
            }
            em.remove(sensorInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SensorInfo> findSensorInfoEntities() {
        return findSensorInfoEntities(true, -1, -1);
    }

    public List<SensorInfo> findSensorInfoEntities(int maxResults, int firstResult) {
        return findSensorInfoEntities(false, maxResults, firstResult);
    }

    private List<SensorInfo> findSensorInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SensorInfo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SensorInfo findSensorInfo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SensorInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSensorInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SensorInfo> rt = cq.from(SensorInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Long getMaxSensorId() {
        EntityManager em = getEntityManager();
        try {
            return (Long) em.createNamedQuery("SensorInfo.getMaxSensorId").getSingleResult();
        } finally {
            em.close();
        }
    }
}
