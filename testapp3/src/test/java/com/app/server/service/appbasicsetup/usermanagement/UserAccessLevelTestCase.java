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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("I7lMZev2medOFmOmjLh8Rf9SPggLKNMSjiUB2IpEui0pQp42Im");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("VU0mfyZxmt5n5jsv79V6U1bg2TJ00EGw080RRR6sLAJLxv576h");
        useraccesslevel.setLevelName("vOndNTNy29oRil8ahisXAGxeCgRSTVvnLzXpNZJv1MnYKpmhdh");
        useraccesslevel.setLevelHelp("WDEdmCr2KN6gfwSuH6ja8kR3Dyk2E0ipGlko0iMJTZ0ilnUckW");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("AbIPRmk7s6HVelnkCylpD6gtN81WIJc7FYg7X9kUiDXA6ZcdYU");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(69081);
            useraccesslevel.setLevelIcon("i1nCAvfQ9NrdIeVzBCtRKL8Vm9Kgrv6tGxL1erjW9osU0WnG6l");
            useraccesslevel.setLevelName("FR9dAh8WNH6FXEZ4ZOamliNnyMHeV9ROGlg1FRZju6tugAxI56");
            useraccesslevel.setLevelHelp("8FbbUe1naQSU35FZb0zn2YQ3BD5MAHIFJ9XQ4BcEx76ihsMnLo");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 137777));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "um2m4BaVXx4ZGz31bVDDIxZvdztj7L1ohipSD9Kza3KKwJ6SPvSRUdjMZV38ZbJdPVw0FDIuVNJ9F2sxJa31JpWt6Uu7YCiYA7gTBCZte3y3toZCKCSiEx6QbpMojL1nrwIxe3AbELDudarRIg8UJZAnY9vSpMu8OuiFY8PBGew8OvbHZuTUfop77yFvM3oAivuNFvxZAZhJ9dsuQJFK5D59KPGmnx0Ad1MY857CKw5Y5cQHX71lJhfUp8fv5HkRO"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "cj05bbiX7jhyhS8SUBh84yvHhjVjOpOokC97dE7NHozpjIw31tkGYsPaJbS8IGxP18jtmXi4sSuGstDqTW02oyJ6ixnd56e34n6T85hm6bPp0QGdqODw3PNRh1xUAbQVPeouameFUT8gIlCg8VTwbKkhn3suUOkdZG39hsKljzD5t1IKCw0Xc5oaG6hrh4PHGfTnPmYTPJyXnlZ92BijOQAreNlz7vt3CNyzupR7shHkzemZfujZH7KiThCCnmNlI"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "OYhYadub04d06Ag7Ai1hG90eNr4afKEV7pUsouRVXQdrLYHCpiPtQA86Dd9EOmG5FsUfiV6ye1JRo86c01f3rLPe356WOtZttqyUocYLOotGzOPgbvK3clRoGafeirs4xkNKz0iE9LtTFqv6fTRSjkOsuBzNtcri46CRJDaHco7n7ARkV3cEgmFuq9fSk91pLHeAXzwB167CW7FQNIyIGb5kpW18QFuSo9Te5CPoM2llkZB2UzokwD8dZTpG6A2ZO"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
