package com.app.server.repository.salesboundedcontext.sales;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Repository for Distributor Master table Entity", complexity = Complexity.LOW)
public interface DistributorRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByRegioncode(String regioncode) throws Exception;

    public T findById(String distributorcode) throws Exception;
}
