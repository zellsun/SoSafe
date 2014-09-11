/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sosafe.control.exceptions.NonexistentEntityException;
import sosafe.control.exceptions.PreexistingEntityException;
import sosafe.model.AlarmInfo;
import sosafe.model.SensorInfo;

/**
 *
 * @author Z
 */
public class AlarmInfoJpaController implements Serializable {

    public AlarmInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AlarmInfo alarmInfo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SensorInfo relatedSensorId = alarmInfo.getRelatedSensorId();
            if (relatedSensorId != null) {
                relatedSensorId = em.getReference(relatedSensorId.getClass(), relatedSensorId.getSensorId());
                alarmInfo.setRelatedSensorId(relatedSensorId);
            }
            em.persist(alarmInfo);
            if (relatedSensorId != null) {
                relatedSensorId.getAlarmInfoCollection().add(alarmInfo);
                relatedSensorId = em.merge(relatedSensorId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlarmInfo(alarmInfo.getSerialNumber()) != null) {
                throw new PreexistingEntityException("AlarmInfo " + alarmInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AlarmInfo alarmInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlarmInfo persistentAlarmInfo = em.find(AlarmInfo.class, alarmInfo.getSerialNumber());
            SensorInfo relatedSensorIdOld = persistentAlarmInfo.getRelatedSensorId();
            SensorInfo relatedSensorIdNew = alarmInfo.getRelatedSensorId();
            if (relatedSensorIdNew != null) {
                relatedSensorIdNew = em.getReference(relatedSensorIdNew.getClass(), relatedSensorIdNew.getSensorId());
                alarmInfo.setRelatedSensorId(relatedSensorIdNew);
            }
            alarmInfo = em.merge(alarmInfo);
            if (relatedSensorIdOld != null && !relatedSensorIdOld.equals(relatedSensorIdNew)) {
                relatedSensorIdOld.getAlarmInfoCollection().remove(alarmInfo);
                relatedSensorIdOld = em.merge(relatedSensorIdOld);
            }
            if (relatedSensorIdNew != null && !relatedSensorIdNew.equals(relatedSensorIdOld)) {
                relatedSensorIdNew.getAlarmInfoCollection().add(alarmInfo);
                relatedSensorIdNew = em.merge(relatedSensorIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = alarmInfo.getSerialNumber();
                if (findAlarmInfo(id) == null) {
                    throw new NonexistentEntityException("The alarmInfo with id " + id + " no longer exists.");
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
            AlarmInfo alarmInfo;
            try {
                alarmInfo = em.getReference(AlarmInfo.class, id);
                alarmInfo.getSerialNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alarmInfo with id " + id + " no longer exists.", enfe);
            }
            SensorInfo relatedSensorId = alarmInfo.getRelatedSensorId();
            if (relatedSensorId != null) {
                relatedSensorId.getAlarmInfoCollection().remove(alarmInfo);
                relatedSensorId = em.merge(relatedSensorId);
            }
            em.remove(alarmInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AlarmInfo> findAlarmInfoEntities() {
        return findAlarmInfoEntities(true, -1, -1);
    }

    public List<AlarmInfo> findAlarmInfoEntities(int maxResults, int firstResult) {
        return findAlarmInfoEntities(false, maxResults, firstResult);
    }

    private List<AlarmInfo> findAlarmInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AlarmInfo.class));
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

    public AlarmInfo findAlarmInfo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlarmInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlarmInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AlarmInfo> rt = cq.from(AlarmInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Long getMaxSerialNumber() {
        EntityManager em = getEntityManager();
        try {
            return (Long) em.createNamedQuery("AlarmInfo.getMaxSerialNumber").getSingleResult();
        } finally {
            em.close();
        }
    }
}
