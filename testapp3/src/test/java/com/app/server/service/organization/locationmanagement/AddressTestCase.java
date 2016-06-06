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
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        City city = new City();
        city.setCityLongitude(6);
        city.setCityCodeChar2("CZGk44DsoygOw0UWdtIT5EqXhXeLDjCS");
        city.setCityName("BBb4565NsifFb0yOqgbhh3H0299ZsjYGU3115ekKJjQFrNsVEM");
        city.setCityCode(2);
        Country country = new Country();
        country.setCountryCode2("FSx");
        country.setCapitalLatitude(11);
        country.setCapital("2X25Ux1OJcPxoNpO1Kw8yqQaOKW2sW2y");
        country.setCountryCode1("hcl");
        country.setIsoNumeric(829);
        country.setCountryFlag("zoCYAmy81Jd9XQhw1V9MCFrEuJXnM0hfACKjopaOBRwetbHnmd");
        country.setCurrencySymbol("epvSGkf2ySjxidBIWfm2qKyWSuKiCUsD");
        country.setCurrencyCode("T2X");
        country.setCapitalLongitude(1);
        country.setCurrencyName("uubfpDTdhcMnqC425fuNXaS4RZSrcoaAHtwxpZQNPDWwYIKKSY");
        country.setCountryName("s74e3jHDFOnoyWSk1GsipVKnS0y9nkiW3En6S44K47kRjxgeih");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("Fd4XeGgbwLskRzoD627nL7hsdCuzMEflsyWFItaNxGh4bz0sMb");
        state.setStateFlag("jzTgMBQq71kXTgNwKboGENPL9ZGm0dZgvnSQecT2hl6evFjE7M");
        state.setStateCode(2);
        state.setStateCodeChar2("f3n8Nw7jES1oqYkVzoqQbrrorOayHeT2");
        state.setStateCodeChar3("SEw2oGeae6VRm25hExwP0VpzFH83BPdq");
        state.setStateCapitalLongitude(11);
        state.setStateDescription("QWgqtnM1kZ66YE0wdtYMlAIz3NPWu1Qo08Z3HAHdD14VZ5Aujr");
        state.setStateCapitalLatitude(11);
        state.setStateName("jDRLJkGT0LbjUBsQv1ujLpzEw6CWSvSXZMXXuxcyHmpYA8LmSi");
        state.setStateFlag("Zp2CehXP5XZyynrr2MNmPi2atcDKDZUI3dp78AsRP4n19iLoYD");
        state.setStateCode(1);
        state.setStateCodeChar2("XWi5gsrIGCi94X3jlz6LzZlUZ5f1RXqx");
        state.setStateCodeChar3("EFgkrUwkWg75SpgOjRYnK318kPsszwlc");
        state.setStateCapitalLongitude(8);
        state.setStateDescription("4o1bXxV7dvcJW2hKz0L3xQyQ8AtnvhaqIY4NrNbxxhMRTBSNQ6");
        state.setStateCapitalLatitude(9);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("hHngimsquLtfuM5KXqDVBfJ3CKZfSYzMgL4mfVJnLmN0oFfT58");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(1);
        city.setCityCodeChar2("lBPCYPXRmgIiF5PlqPDUAapwkIkdOo2c");
        city.setCityName("NM0oaGpLleg0beYc7mJKHMvAxZxqWg2cqoOkvpurbfWiJ9VXyJ");
        city.setCityCode(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("xgcqbIBK1CyQlRAqMlLrhucXe4yuw7IhvOqWZinxbr6mUHwitP");
        city.setCityLatitude(8);
        city.setCityFlag("WROzmxhr4u1rlrZfJuXLxMsQmbCt0UoQNI2RZ2LIDzfXfEtXR0");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("JQRfTuvpVCwhH1yC2IsI9ZAUxzzeMCLAx2456CHM4O7w4uvUvk");
        addresstype.setAddressTypeIcon("MlyRERtub3TKxjsLFck2x6Qe2hiro3PPAPXuw2uD18ZDaVHHcd");
        addresstype.setAddressTypeDesc("vztSdiBoTS44kRYKwTXiiyHaiELRGqPo2GxDiHhmNyPkZCDPr7");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setLongitude("dXrTMkH1CwdDWx4uwE1eWF00kpyfWTnOnb5Mu2HDrSYyhBiAgg");
        address.setAddress2("bCNpe6r0TEETY8yV1Izv3R6HCydJCTUATtlYcBRjBBTPm2Bko3");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("5ndwyOaMBUW");
        address.setLatitude("4hCd9CMtCL8O8UfVL12eqD06CpTiQj1vcCnkIova9fgXujYLBf");
        address.setAddress1("kTROG9I9gTF1geqYHQf8NtR4P9fSuZFOEhJkx7oYiDbcG3slJl");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("caA8p4");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("BS725Ti9CcKDJPmkHlL591kkdVpDkRO4P4QM5LT9g5XKrvDU3P");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLongitude("Z4f1geD4P8wknXqfKNMgX1CyyLvqTbY6SknVZdOEILeQeV6gtF");
            address.setAddress2("Rx7gGRdOfYjfaEvqcduythgNJJJ2tWrVuJH9Q4nN0dmNYcjFQN");
            address.setVersionId(1);
            address.setAddressLabel("18oUVctZcP0");
            address.setLatitude("Ijxz9uY323n8WMmhpHDrcHGlUwHYnZisMRPhVekmT8qUbNvpXf");
            address.setAddress1("WYQyrhPIex1pJtBrfh7axzj9UNmLrUJrI8XjSx2q913qrIdU13");
            address.setZipcode("HqndHH");
            address.setAddress3("hHKr6fIejtOoZJ2ACSqf1mhSMTR7uTsZvG3fVRS5GhjaJBbPmi");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "2w3fuiePm6EP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "ZwL6FIfiiYrXmILY2uBbasdukoxq7bGDFfhGUKXjZDWZdesH4sOiirpZ6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "gvt7yAYgBnoFogXeTFOqsZI9NTyk6W6dzm0mfRB9ZPwfWqKjHoEUWUmMV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "eFOYL70lltCeieoE7jtWyejqOxuuSMIdONq5m2JACBTlt0QFYIhMdm7Aj"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "O28kctv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "JhJ1HL1dl20ejvwuEIXcwbNiBCoalEiBzIUglHXhc0A5VHGu7ktAmlm8U5mXqRYXW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "sHa6Q1Rf6uY6k0xnAImZVBBAlaglSOd49hPuzgyaH4SNigd26JEQYQRqzP11ne29r"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
