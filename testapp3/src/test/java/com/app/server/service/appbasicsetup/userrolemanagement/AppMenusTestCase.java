package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("0hGWLTLMqv3l9ont4rLIIMmoJUhNzdOo3VNAV15IgIKW0cIq4L");
        appmenus.setMenuTreeId("d9WkVk4icHgpyhktJXzE2R2z7avlitTmfULVHQx7JQ4iro2XOW");
        appmenus.setMenuDisplay(true);
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("1ZNq9Fn3uKbD5MkHC3MTFH4NBOHvIhV41J81rQwqZRFHT3teHc");
        appmenus.setAppId("S9PcPEtAn5TlHxKT6Wg3wFCZdPYUIw74118qy5a5k7EocCYkqv");
        appmenus.setMenuAction("jWWyPeuUTzVYVcV2tptVR901p0AIKvEmoS2Q0PrxWw8xNbwbBs");
        appmenus.setAppType(1);
        appmenus.setMenuHead(true);
        appmenus.setMenuCommands("28RDc8oBIFISBhVekhb3Oq7HcbqRDUZBYYWgnjiPILXWTGnckR");
        appmenus.setMenuIcon("eiz8fO0uMSaYJqGDnX94DITkP6LU7u4nzxmKn3oE9p6GgCRUDV");
        appmenus.setMenuAccessRights(9);
        appmenus.setUiType("gcQ");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setRefObjectId("bomgkgywd83xjhLMjWyOgGACMOvUPZKvcn2BDHHlEYHkHk9tET");
            appmenus.setMenuTreeId("b94S1BIMY9fPiPfKDknjMP3SqjWoKcAPclAfsGLRauCErYWprM");
            appmenus.setMenuLabel("btBvToipxb6tDCnsKyyXlWpJtDeDlxXBRcAxgcxezSAjDH2ETL");
            appmenus.setAppId("TM0Zhc9sYpFwIHpn3Gvgl0SyM9fTlt8Gtq5e3uhFnTYiNQOad6");
            appmenus.setMenuAction("ifZ31pkiTIwLuch6WINXHM3iJn0fOzYm2h3sR6fIleCzsAH3Q1");
            appmenus.setAppType(1);
            appmenus.setMenuCommands("vi4XWNRrxiolEc0i5YslOa9GyPpeBECeCLZQfzr0eOWS2VI7KX");
            appmenus.setMenuIcon("vASqHBuAyWngN9OOPgyBSpErwvDvmj2Du9pvE7mOBnGOy47yZ2");
            appmenus.setMenuAccessRights(7);
            appmenus.setUiType("huk");
            appmenus.setVersionId(1);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "2Tho1B9T4evbAYta00pt2vY5UIJWPhBs75nJYhHJ62YPfO3VHkUNEf6xutQuPpFKKNRdZOCA36o9hr49KFBhl4nMGKRymuinczmj0xf40qWp9GvEf1NIdhpOuUwT1vRvW3kIPt3NQc3IEM5vTIukBdINvDfiEqS9AeM9BuH9J3h5WQjqeJBdYQShHtLYCuALVM4sxmaaV4xhPa2YDc6UOTEHP04vMGBk9JCyGu9dXdvPfKTqRXZWVT61TBXEOA0Zq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "8wocNwmeVlIx1Jx9TWrZbsH5qr6JUWtjnSVhg1dGxw067wAiMvHiXxmwjOITrO2H0gSzuT7jymgTwbRP6sYeDcxZp6V7D5p0LCqYfWJZ2cS1CmbI2xPwnUYKupxPiugsZosEOAdl5QCaQ8SbIcpPqMg949XcCmtUB8eyUS8qx2yehzUPUb8UwxFnXAQD5miq019gnsuqHagVgKI45rOZj6Z0IypQfVWyeii3mXXzb28qZZtutxha2yadGjzq2QBHW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "DbwKMeM7MrgbYVJkTcLcgFCJt85CsqUdB7n5CnSc7msZSVnzcS5ygW9dcznxkKRRyagE2xMwpxgW1ji4gz7kuJX2YudtP8Y0HKMCnv1fLAOsbYFBDjE5fMegsleC3b1M49WXSyEYLMQLL0OqYjOstplaCeRcvvjPspliuJRpfJubUEXcH9Znbrzefrw6gNtaRLHGk2bSsiegnzYd5s9xW2Cczxzl19NQvpQf3waKSmJnllKTKLkpqX6B7MIHihtlC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "Y3kEfe9aDTTKaojGK4aMLLDrVNxyVgmoHTeWZhXHaZc9hSjzgcYWJmMvroxxK7q35"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "QPgEOhv9YtQo3PdMUFpmsVLSaEs8syl7i4YVyDoAseWLVskHLUWYvUdFdp875Iy5lURSHbWc2Gb0GQoMEBGIvzv2SR0fq4ciVbdFFpJ44gUZ57iSX866qbj1wyRSgYxhsInYPnjc8Jf0goYexBfgZuPLTlMHkjtnBwDlEXyPyezh4lVAQ3WbjmTjz9A9idFN3S4NOFMOsrqxOmpnM1ZSYpfVFaX8EYrFOxbZfmnqKi9qCoqwZGxPqAdgSBiPL5ca4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "pJpw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "BMqnYV423LzJUwJEkZ5kfp5SeDUFmzertajyeRJunslcip8FeVx8nrCFD4Zf1kVUB9NSlgjdNEmQdaVpsIVJPebuPgxmPxqeGCXteHRL36gZ944SdQerXFpVYacFnDyAHvyX9Rrq9suRDpg0Wa2TWwyrY8tsS5WCD3Z1BTVqrO5vCuQw35CR10eDQOOiSwYw3MRn3mfRT8fpL7mRIhpFtJfbWiEGDjCZx5mu5CyJGEgMit6tYhKJLs9tsEUYEX4xX"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "UtvDfK3ZoEHTFZyHEMjfu3duOyhjHu3kRvq4C0HqOWaIySV9qrn0GJc70PUWFRNsqfiyL91vMNnXhpj8LsiVrJ7SggddWF3M56BPFEmsgJT7tXGwrzOgs5uaegshVg8OmCPVf4iNhOrV5PeSEMkQqeorlCsZD8Ao5Ivmjhlfy3J76NVCWjdYOEPwCBmme5E6KWaIC0nuTKy016COtcRS1evKRYiQ1cNMXKPXW3gEZhQ8YoIZiiTjPNFlJp0oyh95w"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
