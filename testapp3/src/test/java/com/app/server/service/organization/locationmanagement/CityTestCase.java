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
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode2("PBy");
        country.setCapitalLatitude(11);
        country.setCapital("7D6Xh3lbzEtZfkQFL0VU6xYHOwLhRRLg");
        country.setCountryCode1("GiU");
        country.setIsoNumeric(319);
        country.setCountryFlag("ZafBvYQY9mq65fO06VeT4DihsXl4nCJ8ZaKNeJ84fuEjBxy46q");
        country.setCurrencySymbol("FLnpDAmI6DHD0EAiTeswWzR1zIgG6RZH");
        country.setCurrencyCode("NCs");
        country.setCapitalLongitude(2);
        country.setCurrencyName("85P37eXpzb8mE7ZR74puVkRdzZNGun7Np8Cz4YX5pPy0G651jY");
        country.setCountryName("YsM9ro6NEmSGApalqdQD6PP8cjRzzWpCZlF59p70IBrznAOerN");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("s0XkVj94iMwsPJ67pErknbswinmq9IopDHdEZ7TTXdfFDI0XHt");
        state.setStateFlag("GFx6raalsFkZwezRhmPBqHdEPdo8Q48H0NCtEozveEAzGZmeNN");
        state.setStateCode(2);
        state.setStateCodeChar2("KrlOegHMvvr4P4b4s4wwsrgJCRa3fsYz");
        state.setStateCodeChar3("jkXMOsFTcsvGxwUmICNQ5yGM2ohhgDQn");
        state.setStateCapitalLongitude(4);
        state.setStateDescription("iqtBVMJQKeIiDeNCtEXMT06KIOmJB8O22PNjFW580Y16svI2bm");
        state.setStateCapitalLatitude(1);
        state.setStateName("XepxKBBOq1P0ZPxPVdcOx4S3tuEMDXyitp0xIoT9OyFp1DePkV");
        state.setStateFlag("sANoB5t0ILuFDh3pluw9t9TWnr8KF0WpfvjzEjPxGWVJ792C3X");
        state.setStateCode(1);
        state.setStateCodeChar2("y2CvYNJeyjZVZHCHLNhiYp0TW24SsQT3");
        state.setStateCodeChar3("IaSwufC6LRZMjVvwDCAKMuJ5TAh0SXcA");
        state.setStateCapitalLongitude(7);
        state.setStateDescription("Z5It4ewilnNiwPutmAWwiSE9iAd1B2h0mMMOZcInsauiVwC8FD");
        state.setStateCapitalLatitude(10);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("F3kolE5R63kpSxFvTvWTYAvcQ4uMUtO4mDXW440QW0Be2dHW74");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(2);
        city.setCityCodeChar2("l5cRWgx4fx1FjywpTKhYf2a5BQHTxQFW");
        city.setCityName("d8dPyjVvE6ahoIw2YJ03tUv7tqJDngBN8RyUTxKTvdoL1gkGhR");
        city.setCityCode(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("XJrAxy8k6XlxDtajJUyHNMLS6gpDtJjKdhJIOiAGuYTAd6eCst");
        city.setCityLatitude(3);
        city.setCityFlag("q6QRak8V8pdohncGLwdInxTvaPAjreiBDorE4Rpfa6FKjpoMt6");
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLongitude(3);
            city.setCityCodeChar2("00omhOTBfx2ZKnC8CJoZpgoAiflxS3sM");
            city.setCityName("2ZVGgpYijPgaFlFbkczh6ppra8preTu3AmjHDIWZwK903rgq00");
            city.setVersionId(1);
            city.setCityCode(2);
            city.setCityDescription("4KvSTTZbBeWsIVp7BFJucL7blccAjt9cVv539mhx4eiJmpbKYn");
            city.setCityLatitude(9);
            city.setCityFlag("Qi4JA8wMFX9fmqYFcQPTiGwdf18nhacybFakovRqmA3hA9Qjrv");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "LcEArX9LwFVrzwmR7oFoNJqafVExr1vWFnCBOmA93fmfDTX9olWfqjVmpGkYw9ygNtChz9qqQ4NAntnW0lwyIIPz6Y3KVpXZpl2ZgFfkeRleYtA6T7w6mzKGxsPsOQli9J9jeCTHrHJOonDCvxFrgeltQvLSh9s4bcLLgC0XNY4qvb4WNDEbJDOdyBrIsdQJ1PPdgRFWZ3TCXaN02KorJ8chY3uIIyd8mhvdCR6fmzVOVcUYwHUWS9TW44MaOCjBK"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "Xwhj94O1ulGO3oMJ3fGfR3R6vERlzH1C7"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "4MPN7e005JMJN5QZaqAnA2rnk640cGBxAY2mjYDmnjx5uHA9AO0JbQYiNhrJ4GFOZ1fOv3Zy0kEEzBPJTkg7O6r0cduaM6UF4gxjXalq4Mm9KKs8Inwdy5pkCb6G3yxZ4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "sMEgx7o9VtEVhi1Fd4nA2xPY1JXn8Nzk3OV59pGOTvEzpf13vuqyBhYxYrPlNcbVK4mu7u8Vlc4A27J28pFY75WuXHJrObm03s9LlX9bNXetHZbFq4kU6qL1uNzQNJhwV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 17));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 20));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
