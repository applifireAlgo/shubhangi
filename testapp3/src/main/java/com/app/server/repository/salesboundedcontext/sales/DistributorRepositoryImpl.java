package com.app.server.repository.salesboundedcontext.sales;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.salesboundedcontext.sales.Distributor;
import org.springframework.stereotype.Repository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Repository for Distributor Master table Entity", complexity = Complexity.LOW)
public class DistributorRepositoryImpl extends SearchInterfaceImpl implements DistributorRepository<Distributor> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Distributor> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.salesboundedcontext.sales.Distributor> query = emanager.createQuery("select u from Distributor u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Distributor save(Distributor entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Distributor> save(List<Distributor> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.salesboundedcontext.sales.Distributor obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.salesboundedcontext.sales.Distributor s = emanager.find(com.app.shared.salesboundedcontext.sales.Distributor.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Distributor entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Distributor> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.salesboundedcontext.sales.Distributor obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<Distributor> findByRegioncode(String regioncode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Distributor.findByRegioncode");
        query.setParameter("regioncode", regioncode);
        java.util.List<com.app.shared.salesboundedcontext.sales.Distributor> listOfDistributor = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "findByRegioncode", "Total Records Fetched = " + listOfDistributor.size());
        return listOfDistributor;
    }

    @Transactional
    public Distributor findById(String distributorcode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Distributor.findById");
        query.setParameter("distributorcode", distributorcode);
        com.app.shared.salesboundedcontext.sales.Distributor listOfDistributor = (com.app.shared.salesboundedcontext.sales.Distributor) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistributorRepositoryImpl", "findById", "Total Records Fetched = " + listOfDistributor);
        return listOfDistributor;
    }
}
