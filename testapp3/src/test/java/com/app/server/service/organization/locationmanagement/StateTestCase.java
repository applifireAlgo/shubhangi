package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode2("tEJ");
        country.setCapitalLatitude(5);
        country.setCapital("1ze9RBrNnyzlo8eWkjUpiAlmydGAetGF");
        country.setCountryCode1("ZkR");
        country.setIsoNumeric(934);
        country.setCountryFlag("YNBMuMGB9Qp1eXJpznNrLioKultMq6DcCTIwW7ijl7dD6SgpHm");
        country.setCurrencySymbol("4zYCguDd65IwqRYQjQYc8jT7oJgDEiP7");
        country.setCurrencyCode("0rp");
        country.setCapitalLongitude(1);
        country.setCurrencyName("zjF5P2OMV9KTl4AVsYs1P2ut0nszlmUseVXAOwrPUNnsTn3OeK");
        country.setCountryName("2HqeSHmoORFS10RPe7U1uQ9sdd6hmJGQFjNXZJJOQj0gywLoML");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("U9vGTO2Iwx43A6zRT3DZCU1yW5uhsSc8oM03LYsq1z8S08YNyG");
        state.setStateFlag("3azD85SxQxjcBhruJu1L9gOG1a2QLXH4AvsTAasVdnFWQ2lzQN");
        state.setStateCode(1);
        state.setStateCodeChar2("EOznWMQK3KNh5R7MHHT20apri2HFXJl2");
        state.setStateCodeChar3("yg2G6Bo5CHeVz7zwB6ApzeVXbJrFOKRS");
        state.setStateCapitalLongitude(1);
        state.setStateDescription("oBT410vEHP3DfUsJi9uizyPOyKV47UEF7cVQkPtJTSXTCDqVBL");
        state.setStateCapitalLatitude(10);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapital("IJwoOGimjz0j5XDn3L9ztJmfNByjSuNkrAbwxIUHxKlkpz7J3N");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateName("sinDLl92bog7qMSZtVdNMzNWkR5WpnRGgAXNdkaHe9w7gKOlwv");
            state.setStateFlag("Uzk1w304vwb58st8svQo9Fm0URMz7uQ2ha3nSKP0YU0PZrvjMc");
            state.setStateCode(1);
            state.setStateCodeChar2("BQdBQQxkUjeBFP7T9qGuG67NMBQEsGvq");
            state.setStateCodeChar3("mguh7zEQ2AkfZWz6skLGARofX3FqmVzR");
            state.setStateCapitalLongitude(3);
            state.setVersionId(1);
            state.setStateDescription("1PAPv3THycugejKSIjkSlUjXIiplRqNkInoaczaIzynAn7qmVC");
            state.setStateCapitalLatitude(11);
            state.setStateCapital("6geoE1ZsqVzXvCCuolfRmRMWSG00ASSPsLU61hm87JZ6yJHGjM");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "347emIMrtZm8HTEFppjMy4zAviSNbr0a5JbopXrj9pCwnmBDYyEIrvQvXebJZxDxkVS0tVYKZ1rBMwouslWcdpN6mpqHZEbLAuwcuJy7LBN7kv0up96PuSenNStXly1ebEPmlPZtqDNoYdWCUWITRI5W0cyuqPyee2rp2reJ1sWYoyAok1fQZAMX69ZbiHZ1zrbhvNZR7jgBs1PpS6W0vlxuwE0oVET96ehdD38rnllpKqEaaqzudylUtYhHCk78b"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "SB2OtDKvjcgjkQLQB6ooO4hquIlZrnssN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "M04BSDffJHtRuyoOYqZpxUnUGo2Gs0tHp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "XFL4dsfgjCFcGlz1MAlxsulf0RDx2WXCRCr92TCdHTzX6b7l2xmXlKp9gD3ON6q6PjmikXToAgxCMyTcSO5UDkF3DvOIeIPZHcyIpfDhVTc8jvMUlXzKBdi8bWfLdPIoNAESyzuMkTBC59dn5vMUWA92g8gpiwgFpf6fmJ1UmGER0Rj5k3BVcpDMhBXR2Ts1HWH9kI59v828WoHfPnwECresU9KgguvlDr9FxIJnhmmHNA7dBnFkjL1swtPYA5rrw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "0ZObVP1rWi4FklRAdGT3p0r9OA6lapv6H2xglzQuWMFguONO3EukwUVp5hHm5dotwsfwbgQcW1riVAJ7uzYPaohPduQ3qEsk6vc2sVEdh2UZ8vQK4Sgrgz0ONBUWcpSn0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "J7dvPOmn1Zkfi2sL9rMvJFO42OWvb9aKW7wsJu3gg1FK3hX4ohV5PPPLvNOX9woaoN6oOhCt5NNnPLxkjNLuCcshzfETj4Uqf45AZhKi3wJAbFNYXu6wzdncOf9A27VMl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 20));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
