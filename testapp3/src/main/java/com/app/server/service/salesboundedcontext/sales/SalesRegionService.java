package com.app.server.service.salesboundedcontext.sales;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.salesboundedcontext.sales.SalesRegion;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Service for SalesRegion Master table Entity", complexity = Complexity.LOW)
public abstract class SalesRegionService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(SalesRegion entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<SalesRegion> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(SalesRegion entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<SalesRegion> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
