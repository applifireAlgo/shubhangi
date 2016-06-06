package com.app.server.repository.salesboundedcontext.sales;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.salesboundedcontext.sales.SalesRegion;
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
@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Repository for SalesRegion Master table Entity", complexity = Complexity.LOW)
public class SalesRegionRepositoryImpl extends SearchInterfaceImpl implements SalesRegionRepository<SalesRegion> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<SalesRegion> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.salesboundedcontext.sales.SalesRegion> query = emanager.createQuery("select u from SalesRegion u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public SalesRegion save(SalesRegion entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<SalesRegion> save(List<SalesRegion> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.salesboundedcontext.sales.SalesRegion obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.salesboundedcontext.sales.SalesRegion s = emanager.find(com.app.shared.salesboundedcontext.sales.SalesRegion.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(SalesRegion entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<SalesRegion> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.salesboundedcontext.sales.SalesRegion obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public SalesRegion findById(String regioncode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesRegion.findById");
        query.setParameter("regioncode", regioncode);
        com.app.shared.salesboundedcontext.sales.SalesRegion listOfSalesRegion = (com.app.shared.salesboundedcontext.sales.SalesRegion) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesRegionRepositoryImpl", "findById", "Total Records Fetched = " + listOfSalesRegion);
        return listOfSalesRegion;
    }
}
