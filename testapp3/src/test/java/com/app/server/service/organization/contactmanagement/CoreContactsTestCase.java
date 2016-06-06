package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("thWbmYia9dvHnt1KAFqoxkHJVMVYR9AAREJROed6RjMB4vG9L0");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("iIN6IJxVDRiw8P6fl1Rl8ZQ8p4LLxp4fj9QBme4bLFnoi36Sgc");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("SF9aHY96gXK5dp1MtpPEKZDjTNLSmpWTEsrc8DQQzCfBycP6Xa");
        timezone.setCities("DQ6Vxtvont238r8hc7WIVyikT1aw6S0L3YWtc0P2eXyrq3pvNt");
        timezone.setCountry("zDgMgb8Kfl7ogAfgP8TcSwQyblhbVlaY6DoGLAzIHkp9R4IvVX");
        timezone.setTimeZoneLabel("N4zn9WoUjvp4xIVVerg3meG7iYt9Ulws8cXUUaVm2doLefcJGc");
        timezone.setUtcdifference(8);
        Language language = new Language();
        language.setAlpha2("ll");
        language.setLanguageType("CcKfne8WARQ7HYgGbmcwj4H9FlTvTDOC");
        language.setAlpha3("SBg");
        language.setLanguageIcon("kYsSxywQNayliZjW8VVxP1TihZqAoWvOk5moYyV8nxRLOf0TTq");
        language.setLanguage("FDGOE2qGWtzMF8CueFNfL7r1d3AZV2cKyeJ0XAnMV0J4fBBMcN");
        language.setAlpha4("Z6eU");
        language.setLanguageDescription("CrmJqEWbn0MA20iIvQn6DS5u8RP5N8CITtvvWEc3VaqTC2o75Y");
        language.setAlpha4parentid(9);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045025694l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("AhNb42RKQEwe6JRnogDNYYm4uL2fdF84xqEL9KYVXiqGA14dNo");
        corecontacts.setFirstName("I5oTjUwCaPaMC5lcMkAZivfya47lkIg91BEGijC4IAGGjmRu8x");
        corecontacts.setAge(112);
        corecontacts.setNativeMiddleName("C0iadx5GWHtboWBfjpbjJbzD0Kk9s0x3zkFekH1JEFZT5ALVNt");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465045025925l));
        corecontacts.setPhoneNumber("GElhUlOHPMU3fnUQwpbu");
        corecontacts.setNativeLastName("tnHO9YA1IOZ2BUSwi3VzkJO91y6YxLMEiRXqfMijm2VgHDwkHQ");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("y9BEpuaKxMgLaXygO8vonXpENBjBV69CYwnkr9ID5MbUDTVHXT");
        corecontacts.setEmailId("aUfKjFU6qIomKrskJ0JpUGrYddEaw34yT0bBbRCdb0m0IZM00T");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeFirstName("Ez9arHXD3yhAlBXDokt9tIXL3XTFcXbrbjouuKJKSQ89EE7wl6");
        corecontacts.setNativeTitle("wndW4i62OAM3pf8C13lErFfeaUbvkHU8gwmcsz6fSxFZ9NClGW");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("DRKUnXEDpiXogBtKs2247vowhoO9eQIx8t2B5Fqg8IeAlom9tW");
        address.setAddress2("E3sZzgqDwksdPQmi2Vpxh4qZeNv482UyirQkHCuYHd67Ud9ePV");
        City city = new City();
        city.setCityLongitude(11);
        city.setCityCodeChar2("nltoTJWTkqh3l6r64ILtdNNznRKtfs8N");
        city.setCityName("RWUUa65a53uLbCIcEEFYLYRIsb2sfY8oefOG3sN0APOkxT1H4i");
        city.setCityCode(3);
        Country country = new Country();
        country.setCountryCode2("ha8");
        country.setCapitalLatitude(2);
        country.setCapital("joNQR3GGuGb7MxZDbWhiHdqY3udp0kGs");
        country.setCountryCode1("K6Y");
        country.setIsoNumeric(955);
        country.setCountryFlag("w7eKEn7cWKgZLJVnCD1urGersiXB4xA5fX8EPeSfIIT0CLyios");
        country.setCurrencySymbol("x00be2wD8VK0n5bbzXLP2IDE1sVMy68C");
        country.setCurrencyCode("vl8");
        country.setCapitalLongitude(1);
        country.setCurrencyName("qGevzVELKtNqrcEuRF7cZHYicN5wmn1hTYPTGzZWNsEUg6obmp");
        country.setCountryName("73uvWHEHOoWiB6OFcdLNVa7Arj5r0Ze5610yNBOfYxMIWj5FN3");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("d4nQ6Nk31eUzCNUyRvkuSSqUEv5N9R2yjhXhQ8eHx1EaQNPZr9");
        state.setStateFlag("VMZ7hC8KjmM5ijfDrksD6qO7eRASj2bqKyxPJTOwJAYJcwbeZN");
        state.setStateCode(1);
        state.setStateCodeChar2("4xo9i3zIuKFCfmIJFOtQXTfGClJsQWHP");
        state.setStateCodeChar3("OZZXAKIRLBgaMFLrVNbQl7ppvJ1C9p6e");
        state.setStateCapitalLongitude(4);
        state.setStateDescription("nDwSBRnWgiOZMExKhbnsJYMKv2x2y4srUVpldxijyy4cbKam4m");
        state.setStateCapitalLatitude(11);
        state.setStateName("aLnf7tz3ixumekVHFCephmIPFurCkH2Ko2UA7MU7ez3Bi9gzVl");
        state.setStateFlag("ECYPOvY4iTqnr8VecdmJDuTMNXuqcLR1X1uXxyxxecECrpvGeX");
        state.setStateCode(2);
        state.setStateCodeChar2("RjlCsLFRSodUk22km6GWcqhrceTFi1Nr");
        state.setStateCodeChar3("xgysD8Zv0xnOvkLgLPaPlhQPL9rx6ZbT");
        state.setStateCapitalLongitude(8);
        state.setStateDescription("u35ZKNPJ9UWwqgirlMyGLcFUzUCnpPoHkghAPvL5UnP98pPBeu");
        state.setStateCapitalLatitude(11);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("kwWe6k3En19xxuNyP5qPRZnQs1KObB0TLZqRVXlDd1zUHQ5nUQ");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(8);
        city.setCityCodeChar2("8IZ8N05Eam17n4NNdv7eZ437LG0WmY1E");
        city.setCityName("Y5V4aDAfWYWLeeaBZzpULKll9CASeTXfPHEnxPJQgZpLsfxF60");
        city.setCityCode(1);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("oxFBaIdek8arCo8qMVKQcCO3LuIFGuaMYAwNw9WOtPEaRAuhMG");
        city.setCityLatitude(5);
        city.setCityFlag("voewOnW5sA1uoBG5G8Bj8xsonTTZCe1S44NFUHRH3nZ9dK8oVg");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("svTJqq18xQYEHvflLZqH0lDEFdsrdodL9GlATrVXRJDGuxRQKz");
        addresstype.setAddressTypeIcon("QtdaeFmhTqdu0UmPGYbQjiQo02ybcr2hPmG0Ww4YV0bc6PL7EO");
        addresstype.setAddressTypeDesc("8y3dIDNPybc27EPbtIKcxr5buOPhio8XWbUyBZNlctNevJByBD");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLongitude("4RtqfRrwHP6N5an9l7LmrhJ7vTeIOIva2oXzXg4dl2JRw9NmCM");
        address.setAddress2("Gx5bA96NtG88J48ldlDvZDikHppc1Zu2LL0YeUjYJJwOJm7wL5");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("VchFsGiZMgQ");
        address.setLatitude("Qyxy1aOuM52oibweTRtBkWFFNZXcUL66yfCa6V0aaq1XhfGrEs");
        address.setAddress1("QmjlvAoKob6UFbZoESsm5j1yCo1y8cK4AogzxqRAEOp8oFQl3R");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("9gipgx");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("8Q1rTcx6uoV3zwFNu95bXp57onm0Qh0XAcwygH9aZ6C37ZmFT1");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("q8zSftSD4Q");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("ZsaZBtxcXJYS3Z9dxOXo9U6FzD9E65tNTP356Aj0YjdkesCQN5");
        communicationgroup.setCommGroupDescription("VfdjHgF4oWsBR5G1aPgStwEvNYkmDAAIVCPi3xcTNx9OErOq6n");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("VeB9ITFirIaavKtFioBMhJ1Fbp61syUH8R1CRPx6YTQ4JGDpMN");
        communicationtype.setCommTypeDescription("77fIwGv6s9IHrSl8HttvNhTYbzhEU0kUn59tqthaN1tdH8jcqG");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("6fvFbEqf0V");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045028924l));
            corecontacts.setMiddleName("XedzeQvEYVtUwqhwSQGMFK2ZtR1YOFX6LaSoLFOY5qgLuUNIRD");
            corecontacts.setFirstName("Q7lV331js0PMElx3Z35UDLUzfEZTX9lKHDBKE57FNaHY5bgiay");
            corecontacts.setAge(111);
            corecontacts.setNativeMiddleName("JKZ9IGh2xNCc9BLGnbyO8VD6kQwiyXnl98HTr30qN3deglWmLr");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465045029376l));
            corecontacts.setVersionId(1);
            corecontacts.setPhoneNumber("DK9Ht019dDtnZEnRsgQs");
            corecontacts.setNativeLastName("EQLiUG0m7GnriRXcAbrj45Cuam2F4rG0Qu5jPgy8dUlwqef6zM");
            corecontacts.setLastName("yzk9WVYmPPMnuf1wHTyr0pWiTbxSKJ1Hsq4zwH43YW2CC5JZZz");
            corecontacts.setEmailId("96fl0xZoKjf9uZhxEe1whp4zPbDo8mPfC7L1oHIwXeBkxRXFqf");
            corecontacts.setNativeFirstName("r3HhUXCvCpBGTskrpPu6Q0b6zfWmnqCinzM4ArbaWOBrbHX7A1");
            corecontacts.setNativeTitle("FPsjGQDe1Qw54E3m7ond7zYI4z6YWpVHNGmwEHcCN7cjUK80CB");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "7sAx9io2qTAqp34zRbd7GGF2gJpABcspDZSUkvFMWFVr773kdVComjnvpehnpoeY1aL9cOYaesMeEgRiiHxfETxAFfXSApljGsZ5sV0V13w83UUbO6IplpQ5dFsIRn0Xm1IcZ3tyXN5GZhuBx3jptDI8TK4jZWuwF4PFWE1cjIlXFTWbDjLiXQ1kV70oPM1XBd3hUCUgkzt38OmtCiFKjzKiRb4msqP5eIfucPfNaCkdQR4TERG0yAME8yJDzcSlc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Wt7oGnYK1kGe0BgtbOZH0rieleOZ7nJlPFHprgpNUXcIHsKLBfh10V4qMDXlIVtXAhFRprATNcsRe1BcugWP64mCVA3cUZhbjsDrB8cCXGeBj2c8SOdQqFZmOCh5vYGhm16LQLfvQtJN5H3H8oU5GtYXFWYHiJmsozJWjZ37X2IZqDq5rm5ScyNnx0H5HFzCnlAHAkbgjEobyjeFAyqM93f7wJ62EVmaqvmXARx2HzWW9N7xETzvZSNH1LjczelEf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "lkBxAFLIYihbal42wIUE1YbvhLmSDUPbLCmDnQqcVVfToPRULthkHsXFSYkauSYfCVJlnw1ySEFIjy8X4oXeYxIK0AHDavhTrgrV9nplX7oUKLBYmV2mDz6k95DYtLZaJ95GHy26kELZxpqYcolk6HQMA23mjtb3uHurlDPSNMqDbHqW6NewxHZJAGSmHHjbWRpVoMoPDVZVcaqwNFymrVLElMDgxvNb0cWnsynQdBZx2bj7EsaO6iQiYkYMoJpmn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "cyHiHaEjE8yMyaiTQLgj4KvyNhD3a3w4nz2edRQHZHOdzBMoMcdVlLIPIhOoWoKve"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "MCKIwrbVOVJMACWb4DmO3YB3nk1jDg4VzjnnDzFnyyCJnaIfmMapgozJj8s4iSaG1NvGGBxLLKJFRjwJ5XlyCnIDruwLIQ4N0SzjBs0uORokRYRgIUCs7UunyV2P6PAirF4FUjsteWae5MaRve6jBbyEF70MicAjkoAoPdae0pToeu5XZOZNeVcLRou2Lm7I2hHa8h9koTVCW0S0oMtSNBNceNAtjG6J62FQACfS8S9vVr4YROTPkR8c5WH5Oncc5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "LZgZOhfanf9Nk0sQTJeGHv0OXSh86lbiz0wK98fmJwEKGQrsZt7vG8qMYlZvH5Un8mNcvEWV8DhjKJmH8AXGUaTRB8sP1D3sOFD3vbCAoYS0Xv0koVKLG6kIiyYOZQyq7c4A5uO0SXlS7irpVsGKD2s2hid8emnLFL6aCYCsOsMQPkyv1vSrYxbIFisfpFVBiS2LXT8TauhY1jzdeRWEPVmBWMldCVYlZkGRvPHDsA33NVJUdhgDZdeowSkgQQaFk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "qemDbqfwmZDt4jlevLk0UH0jZjmigi2L0JsCyaam2kykKfUXzLNyCga78vTpkPeNsLzyStPQqNg31Pbz00M9wHXp3i3OxnBhvDVc8WOl3xzpWLZqEZVm6pvFC6RX9r2fDNKVN8QlsliDzTiLrS0FeugrkE53KwHO1dAonR5kLQ7S7TCM4BdagkLVtKkNsvKmlBuW9Ce0QszNPvyH9jCYuTMdVY7Of1sb73255kOlcvDQzzs0es8ImyJecpEzz4sCW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 185));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "qt85KYAsQ1EpH9WbmPmF86CO1yfFYEfHm9mnAtbIpuiuFnd6duu0UeNvCCXH4MZUc5Vp8nKuEBnFdVKVVcGjOjvpf4RkGFV1AxC3FpB88uYAbZ5HLPbebPaNYqm7uXD5AXp0n9jrZz7ers4W22qBRNFcNu1OeW1oPFPFE4YelEpIRPGkgK7Y508tCaGuFJQbZkQKefXlLfNuNfFo9TsxqX9LocMOmUiWAGrB5VvM3zunKNzwNxLXOZm7LwuWmIj7u"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "uBwZCAOxxHxqOWYIMFjjp"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
