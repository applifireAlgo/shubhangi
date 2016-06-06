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
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("8Nvbzl0qZvLILEDelPMlAsU9hFBjfz6p7rWCTRJLKig3sSI6yX");
        roles.setRoleName("8u3qTDYEHFxJqclHIYdtHeIM7q2zUXAJvJlsJOSbJaTC2kL2d0");
        roles.setRoleDescription("v2Y7q4zbkUDJzuULbBbMQaLWMEkT9jaPGqrupnFWKfFZBlQBBe");
        roles.setRoleIcon("DRtdnoybmipXA8ghY5v9ZYg3XnHS1IcJ1p2fI3ZIZnJelX309Y");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsExecute(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("VGRCqJn47rBvOKlD8X2mcSn7Gsm5Zg5f6BIhsVt3EGTTvyXjfM");
        appmenus.setMenuTreeId("zc0jU9CC4U4tijnwUbdnw6rECnYVUmaeRUfVVDtHz9MnAAzMLN");
        appmenus.setMenuDisplay(true);
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("ZBK8wQdWRl3yEbvrU3gzf9SWAWhN4K1oSa9MkjBHhXSqU34CD7");
        appmenus.setAppId("WHys0CEx2XhwTF69wuSxolTFryZPnAwaFqfIBSw7ZshDIM7khM");
        appmenus.setMenuAction("PQOzRfQYvWuOQAXdgXMjE2S5hRTLiZuCK4KF1QLYzTKd77Wfc7");
        appmenus.setAppType(2);
        appmenus.setMenuHead(true);
        appmenus.setMenuCommands("qfyGs1SJG7DDN0Ar0w6XrSuNIryesXCTJuN4HcgVsFyqbfjNYo");
        appmenus.setMenuIcon("YPo77e8PqmYbBuYTP1LC084kxQDIShYe5BapruZBsluluTe5gT");
        appmenus.setMenuAccessRights(7);
        appmenus.setUiType("Blr");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("fyZMS0POft03MU0k7CBLXnp9mMkiMZvumTFRpcMcOi3SvLE923");
            roles.setRoleName("QRNHi19uWZmfaMS0jxlQLxtKL4bs8OMRwjH8piI4Yh2OUZFrr2");
            roles.setRoleDescription("AbhrILIghtEBy3tRSVkUgYDTRQOwjhnc7VQp7slK4TwcsHG7kE");
            roles.setVersionId(1);
            roles.setRoleIcon("klCxUjTvzg3B87cOWevijqc7OPIUUf1rjxXbLrulp8Yzu9OEAn");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "9hZM0TUrSvCSUq5lBGlI3fTppWMtI9k1Q4WuoXfqOOzi7ao5cXOH6VBEivavTanN005NsvvZaWCCthhTOMtqZtOCRYzd4GpRTH9FL1jbnwqtBBYBX96its0x70Vl816yueOeV24MDoaWrVsSdiahxSOuBQh7aI4lAwGadm2xHZtUh19wJAINzt224ZarPWvlVcIvYmIeemtfP6p8JaXLHUC5CtNGQyzdTkx13aZPiOlCuK1b8b4TAt4GrZ16homNA"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "yiz9mpKcrrdXzRmDVA02YukOcT9lYEiagzPR7C5Qs42y7aEWfIPUAWmgcuhbcg7d1zppHw1w2EkZ8yNuOlLXJITPud28HWls7XpZqLUqnK5ElPCdqKByDsMp8xtibFJ8cofjem16aJKYsXl1ZaZkokUfNbuDKnLnf0W1pYJAKWyqSP8qAuwSnYHyjM5p5Au9t5Uvs5B7mGN0HH36Vwc6l26CCAASkpJCREz5CeeaacFbBQtbuH3gf1IkEC9FLkRI6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "QtENWUqw9plXDUa7Fj0JhG9UgTIvqBraoEIHxOR7mz0n60aCp6e97nnNjoW0kAYlJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "h380BJeepHn4AwBvB2pFPniby3qiuN6iQiUnuy267aPs1sNeKKMeHjBFhLgWET2CZV4H0XSjR21aL81zNtBAcvnGhzMwvxgJewOOxvwSHT2GXp5RHNNB9dO1DV6SeBLJBxYvgPVkQJTkyPWB1GyhrJ19FQiOM0HD7yjiokcncFQUBdysjIiBcVHTxV1WrpLxydPTuiloP0tfDKINg6T8bIY6nOOtcMG4KjzshtTPL0AEvcLvorFM1OQn99q82D02D"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
