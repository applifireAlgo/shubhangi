package com.app.server.repository.salesboundedcontext.sales;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.salesboundedcontext.sales.CurrentMonth;
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
@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Repository for CurrentMonth Master table Entity", complexity = Complexity.LOW)
public class CurrentMonthRepositoryImpl extends SearchInterfaceImpl implements CurrentMonthRepository<CurrentMonth> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<CurrentMonth> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.salesboundedcontext.sales.CurrentMonth> query = emanager.createQuery("select u from CurrentMonth u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public CurrentMonth save(CurrentMonth entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<CurrentMonth> save(List<CurrentMonth> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.salesboundedcontext.sales.CurrentMonth obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.salesboundedcontext.sales.CurrentMonth s = emanager.find(com.app.shared.salesboundedcontext.sales.CurrentMonth.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(CurrentMonth entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<CurrentMonth> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.salesboundedcontext.sales.CurrentMonth obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public CurrentMonth findById(Integer monthid) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("CurrentMonth.findById");
        query.setParameter("monthid", monthid);
        com.app.shared.salesboundedcontext.sales.CurrentMonth listOfCurrentMonth = (com.app.shared.salesboundedcontext.sales.CurrentMonth) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthRepositoryImpl", "findById", "Total Records Fetched = " + listOfCurrentMonth);
        return listOfCurrentMonth;
    }
}
