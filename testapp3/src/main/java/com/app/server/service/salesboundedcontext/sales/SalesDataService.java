package com.app.server.service.salesboundedcontext.sales;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.salesboundedcontext.sales.SalesData;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Service for SalesData Transaction table", complexity = Complexity.MEDIUM)
public abstract class SalesDataService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(SalesData entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<SalesData> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(Integer id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(SalesData entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<SalesData> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByChannelId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByReatilercode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByMaterialcode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByBrandcode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByCategory(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
