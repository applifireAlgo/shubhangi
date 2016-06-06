package com.app.server.service.salesboundedcontext.sales;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.salesboundedcontext.sales.Distributor;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Service for Distributor Master table Entity", complexity = Complexity.LOW)
public abstract class DistributorService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Distributor entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Distributor> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Distributor entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Distributor> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByRegioncode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
