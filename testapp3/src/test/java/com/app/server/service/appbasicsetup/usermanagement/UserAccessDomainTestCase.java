package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("MaXN3o4ZHtPKI35Ms4b6t8mJv3dUXOb0MeXQygFk0Iq44ld7Xf");
        useraccessdomain.setDomainIcon("RRvUsKrZqKhEQnlwg1aSnmatAXbi3NGarDBdL5Q3dPMuIlQadL");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("8RXXGmWj5fD0OFtYGdsyR5XK2jmtRhqbITJBdpZLoiwLyEdHSi");
        useraccessdomain.setDomainDescription("5caRNyAS67j2IWNFbXahEQqJMcrUp1LdcDGdAHHQOE1Z6Kls5o");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("O9fJhUZMPXDRttwKp1v0xauvIvM5EtzKj1CPee4ApsPpgKjjVl");
            useraccessdomain.setDomainIcon("Q9vyoEFPnWnTMoYDmDt3VI1K5KmStcF8xPw3xaIxwSKBJOqdTI");
            useraccessdomain.setUserAccessDomain(64342);
            useraccessdomain.setDomainHelp("GB6klHhBdHG2tv306p4uox2JGwwtOtDVvQRwtEzRkwzTAv4vPY");
            useraccessdomain.setDomainDescription("IGZW7LkDQfV5itaO2Le9XKNWOyckuXDJr4Vz5Ja3w9VMbIbFuG");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 199399));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "k54Rc7hAfcJBi8zA0oDSQi7VNRrDaQSamXQWAfDnyRoSRsEwETGz7L9B46FYCZ00e1f657KRp8iheCx9iD12vcqhxVDiFAY00niW1t0Onk2dnYHkcDjyIpWBDQoRZmaMfM2v0lvuc8UZwarbt0iBGHw1iJly1LmIB31uGfWfDd1kCoPquS3ZL5oXfiA3nFjHL0Ohh1ETlu4759HZNJJlFLmjDmgHcssMvU49bPnG0qxFwOLrh6MlxVZjnYiGgaATJ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "4IUhAvI3snrMz4AGAJDpIffkY6xRt7nH6PZZOqehC5KOsyGQyVSdsMAXcd11hLIekHvXdCsfFVoBvJ8cuc1zDLC6NCLDLoUMlubp707KS8Mvv00lAhZhCGa00hYbkND4MXXzXcFCrVmbtpltzZ5OYHkaOXXwR1aLHtW0cETCEdOxD0fM56s6fTT3n3BqXX5Qg1PM8zrGCsJ9Fps0hVZxL0POrS6fxfjV449nQnb0O17dGpatKxuB5QxFxWHQ5rQdY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "30HcvHqKhwbnYIzlrGSwXXuoaMLQu6BR21HYRNEzGyagokDgMU4mYPOvKBeMjKcUTg4A9WfGqhIqLVqYb7PSEAtBscnfIHZpTN3k5K602N10Bj67OoJf1OGXB7MQNF8Q7O4SNyq1309niPZgEBLXxq28TWJyvRut4rbnJz34Wdtnj40S3EbeumaLbgrKMHJVXE9e7bZcU2ba2kqm7SGQIoiogpkJ2WLnD2gvP7Vrmzp72HlTR1L6BPUrVS0AkZu4X"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
