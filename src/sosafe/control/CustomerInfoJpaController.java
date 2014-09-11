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
import sosafe.model.SensorInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sosafe.control.exceptions.NonexistentEntityException;
import sosafe.control.exceptions.PreexistingEntityException;
import sosafe.model.CustomerInfo;

/**
 *
 * @author Z
 */
public class CustomerInfoJpaController implements Serializable {

    public CustomerInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CustomerInfo customerInfo) throws PreexistingEntityException, Exception {
        if (customerInfo.getSensorInfoCollection() == null) {
            customerInfo.setSensorInfoCollection(new ArrayList<SensorInfo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SensorInfo> attachedSensorInfoCollection = new ArrayList<SensorInfo>();
            for (SensorInfo sensorInfoCollectionSensorInfoToAttach : customerInfo.getSensorInfoCollection()) {
                sensorInfoCollectionSensorInfoToAttach = em.getReference(sensorInfoCollectionSensorInfoToAttach.getClass(), sensorInfoCollectionSensorInfoToAttach.getSensorId());
                attachedSensorInfoCollection.add(sensorInfoCollectionSensorInfoToAttach);
            }
            customerInfo.setSensorInfoCollection(attachedSensorInfoCollection);
            em.persist(customerInfo);
            for (SensorInfo sensorInfoCollectionSensorInfo : customerInfo.getSensorInfoCollection()) {
                CustomerInfo oldRelatedCustomerIdOfSensorInfoCollectionSensorInfo = sensorInfoCollectionSensorInfo.getRelatedCustomerId();
                sensorInfoCollectionSensorInfo.setRelatedCustomerId(customerInfo);
                sensorInfoCollectionSensorInfo = em.merge(sensorInfoCollectionSensorInfo);
                if (oldRelatedCustomerIdOfSensorInfoCollectionSensorInfo != null) {
                    oldRelatedCustomerIdOfSensorInfoCollectionSensorInfo.getSensorInfoCollection().remove(sensorInfoCollectionSensorInfo);
                    oldRelatedCustomerIdOfSensorInfoCollectionSensorInfo = em.merge(oldRelatedCustomerIdOfSensorInfoCollectionSensorInfo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCustomerInfo(customerInfo.getCustomerId()) != null) {
                throw new PreexistingEntityException("CustomerInfo " + customerInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CustomerInfo customerInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CustomerInfo persistentCustomerInfo = em.find(CustomerInfo.class, customerInfo.getCustomerId());
            Collection<SensorInfo> sensorInfoCollectionOld = persistentCustomerInfo.getSensorInfoCollection();
            Collection<SensorInfo> sensorInfoCollectionNew = customerInfo.getSensorInfoCollection();
            Collection<SensorInfo> attachedSensorInfoCollectionNew = new ArrayList<SensorInfo>();
            for (SensorInfo sensorInfoCollectionNewSensorInfoToAttach : sensorInfoCollectionNew) {
                sensorInfoCollectionNewSensorInfoToAttach = em.getReference(sensorInfoCollectionNewSensorInfoToAttach.getClass(), sensorInfoCollectionNewSensorInfoToAttach.getSensorId());
                attachedSensorInfoCollectionNew.add(sensorInfoCollectionNewSensorInfoToAttach);
            }
            sensorInfoCollectionNew = attachedSensorInfoCollectionNew;
            customerInfo.setSensorInfoCollection(sensorInfoCollectionNew);
            customerInfo = em.merge(customerInfo);
            for (SensorInfo sensorInfoCollectionOldSensorInfo : sensorInfoCollectionOld) {
                if (!sensorInfoCollectionNew.contains(sensorInfoCollectionOldSensorInfo)) {
                    sensorInfoCollectionOldSensorInfo.setRelatedCustomerId(null);
                    sensorInfoCollectionOldSensorInfo = em.merge(sensorInfoCollectionOldSensorInfo);
                }
            }
            for (SensorInfo sensorInfoCollectionNewSensorInfo : sensorInfoCollectionNew) {
                if (!sensorInfoCollectionOld.contains(sensorInfoCollectionNewSensorInfo)) {
                    CustomerInfo oldRelatedCustomerIdOfSensorInfoCollectionNewSensorInfo = sensorInfoCollectionNewSensorInfo.getRelatedCustomerId();
                    sensorInfoCollectionNewSensorInfo.setRelatedCustomerId(customerInfo);
                    sensorInfoCollectionNewSensorInfo = em.merge(sensorInfoCollectionNewSensorInfo);
                    if (oldRelatedCustomerIdOfSensorInfoCollectionNewSensorInfo != null && !oldRelatedCustomerIdOfSensorInfoCollectionNewSensorInfo.equals(customerInfo)) {
                        oldRelatedCustomerIdOfSensorInfoCollectionNewSensorInfo.getSensorInfoCollection().remove(sensorInfoCollectionNewSensorInfo);
                        oldRelatedCustomerIdOfSensorInfoCollectionNewSensorInfo = em.merge(oldRelatedCustomerIdOfSensorInfoCollectionNewSensorInfo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = customerInfo.getCustomerId();
                if (findCustomerInfo(id) == null) {
                    throw new NonexistentEntityException("The customerInfo with id " + id + " no longer exists.");
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
            CustomerInfo customerInfo;
            try {
                customerInfo = em.getReference(CustomerInfo.class, id);
                customerInfo.getCustomerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerInfo with id " + id + " no longer exists.", enfe);
            }
            Collection<SensorInfo> sensorInfoCollection = customerInfo.getSensorInfoCollection();
            for (SensorInfo sensorInfoCollectionSensorInfo : sensorInfoCollection) {
                sensorInfoCollectionSensorInfo.setRelatedCustomerId(null);
                sensorInfoCollectionSensorInfo = em.merge(sensorInfoCollectionSensorInfo);
            }
            em.remove(customerInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CustomerInfo> findCustomerInfoEntities() {
        return findCustomerInfoEntities(true, -1, -1);
    }

    public List<CustomerInfo> findCustomerInfoEntities(int maxResults, int firstResult) {
        return findCustomerInfoEntities(false, maxResults, firstResult);
    }

    private List<CustomerInfo> findCustomerInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CustomerInfo.class));
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

    public CustomerInfo findCustomerInfo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CustomerInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CustomerInfo> rt = cq.from(CustomerInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public CustomerInfo findCustomerInfoByPhoneNumber(String number) {
        EntityManager em = getEntityManager();
        try {
            return (CustomerInfo) em.createNamedQuery("CustomerInfo.findByPhoneNumber").setParameter("phoneNumber", number).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public CustomerInfo findCustomerInfoByEmailAddress(String address) {
        EntityManager em = getEntityManager();
        try {
            return (CustomerInfo) em.createNamedQuery("CustomerInfo.findByEmailAddress").setParameter("emailAddress", address).getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
