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
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045076888l));
        Title title = new Title();
        title.setTitles("t7rkwrvLxKu1QvYZl0r7y29t6eoclNVWchKUBhCyZCFYUyEEv0");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("9zQxbllTsNWuZuYhXkZBz8sWDMxBTaly7u8m0JAWoC1xrEKXwT");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("NwnI9gk0ZYxYfGzsN2Y7r7v1JY8S3WTjPd0dchVjlAPZJMwTrd");
        timezone.setCities("Dp7KAyePYx7Xfij1Q6DcQHiSE4NiXdgJ66bwU43paxu61tQYH4");
        timezone.setCountry("2ZqEhdSUr7Jbea9KRGiylxx2z59pZxFPThY1aUKBBs01MqkoPL");
        timezone.setTimeZoneLabel("9KX3Iur9ez1MNPo0GpIp9UR8kioRYD4yv3W286weNRxS39ZKKC");
        timezone.setUtcdifference(1);
        Language language = new Language();
        language.setAlpha2("ri");
        language.setLanguageType("oYAVyvSk12JLsJ5xVOcxotwvEkj9GSzF");
        language.setAlpha3("YIJ");
        language.setLanguageIcon("dfwIjv3qU6mXlFSRrD84UI0Woo8Ts6rv1oBRJ8nw3cq0Z8D8ZG");
        language.setLanguage("HvnkAHN1SweoKdzRYrI2F44MIY27DQoC38Bho9N2Yu5EHoEWoQ");
        language.setAlpha4("zFi2");
        language.setLanguageDescription("M4YyDQITyaRdBtKxmCOQQ6Lv0mWYx1Gv5Re4gFWmyWojPk0uRL");
        language.setAlpha4parentid(10);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465045077019l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("Gu9kbmlQOBlQWZxoDPS8qDKpE93d1hS6TFBU34H66o7nBeGdeE");
        corecontacts.setFirstName("c0VPse2TuCo66JuosLcAeVSO2QQe9hkzhy46DSs4LdeX7716B1");
        corecontacts.setAge(70);
        corecontacts.setNativeMiddleName("Oe4ZtrkitoqAiYBibywPdULGrMl9Ucxl9koCQkNsuCpr3a1IWF");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465045077253l));
        corecontacts.setPhoneNumber("r7yWhkjqTQD17USS8fwa");
        corecontacts.setNativeLastName("6CNxmynsJYB5L0VjyxaQK1RYRADrRTt1ODbIlU6JrGLlhPSgKN");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("UTyfs6TYIOhnUsOJURq8z5cRh1ejpNg59hMiyVYxXsJd8gJmhb");
        corecontacts.setEmailId("TTMsGcNriOWK2yiPw2cPMFI8At76WBJJJlJ1O2RS3ycLFROmol");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeFirstName("3mPvtdjjLnoBbDScP65is5yOMlZynrhIfLKAFiCONpAfgbs3gq");
        corecontacts.setNativeTitle("uaoDMvAeOfHb6UhtlnF66cRvkkCZPh6qJFHwXEHUF3ZblmZ0PI");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("8I6b5xVLX3kAncn3L14Xvycidac3NTON9rh9mzRjr1goE4kgPP");
        address.setAddress2("C92ODH103hDsjQttQdzWuNFyi03FkJ6r9wcW5Ouj3s0oCthgwu");
        City city = new City();
        city.setCityLongitude(10);
        city.setCityCodeChar2("3krVf7S79ZdIz3fvWojMrVx6koq8uHdx");
        city.setCityName("UOnPGFtQ0vILVSKJ9eNPDKuBFkbcIvpvPngeGISKuPrlk8RPEp");
        city.setCityCode(2);
        Country country = new Country();
        country.setCountryCode2("jND");
        country.setCapitalLatitude(6);
        country.setCapital("TSaDuLTOBUj93N0eyRDVE3m9p7hZgLMM");
        country.setCountryCode1("ufm");
        country.setIsoNumeric(150);
        country.setCountryFlag("L23N7SEA1VIP2rEnTK4hXmfzxHA9m6dyVNaxKJTdVkKQP3aaHj");
        country.setCurrencySymbol("39y8JoK9yH9DUImnIo02SYRncmJjDDaZ");
        country.setCurrencyCode("ooe");
        country.setCapitalLongitude(2);
        country.setCurrencyName("N1H5LAXHeUN86xiRSOV6QcylnnYtitVCNlMNpna3nhdkpkqtk8");
        country.setCountryName("WuQROjzBNZVrWzicRdfJ8SeIR1ZKyPiUHnuWmPaueq9wPrsUsD");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("ighwy8gcKCdaNIh4QmlQpa6PrODzBnvfN4musxUDBYi2mKGr1m");
        state.setStateFlag("UCBCACTBDdFIuGzSYYWBiLjlrkMOMebYW6ZC7iWtByv4UWy9ll");
        state.setStateCode(1);
        state.setStateCodeChar2("rLAXQXjFrJMPhlfHUvCZKbIbeN5TVeXb");
        state.setStateCodeChar3("RrpYEwSr0Nn3Mr6sDAteVLfDKxNO7jUN");
        state.setStateCapitalLongitude(4);
        state.setStateDescription("bIySapVdCtWfoABA49U95buTQnHfUOBbYHmx2bmPxQsNVyexPN");
        state.setStateCapitalLatitude(1);
        state.setStateName("qJQ7ISDQ4w03TFwk9UbDOTMMojZpVgmLG6JbZetPxcsrkDzUyf");
        state.setStateFlag("dgBDtCniRMNTXcPHzdNIBFEwwJfxaeVDoUZObVsV6860406VrA");
        state.setStateCode(1);
        state.setStateCodeChar2("Kh9gU2kiq8s0FONVkMRJYcMUDn8tfAcj");
        state.setStateCodeChar3("ob748htyBgJJ60b7Erx7guhAngpxcm5q");
        state.setStateCapitalLongitude(2);
        state.setStateDescription("7cWQtP0dzynifnakr2DY3vTqYqq5IEuHZZlbogPnyz7oBIqGzi");
        state.setStateCapitalLatitude(6);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("CWUE9WvLCHakgU2CHmybOPftC3XJmFQx347vUzDwNxjfR2CYQQ");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(10);
        city.setCityCodeChar2("bnRLCRdp6uU7w18aUw0oe1cpdZrOghCz");
        city.setCityName("J6RAXokD2m73ThIvMfzwRTuHwAW9NwNlmQIg7x1ASZpClld7vr");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("YHHLiioOuiK1wO2b6H5q8xKlBibzefjC0qi6l36iDER8Qhdu7f");
        city.setCityLatitude(2);
        city.setCityFlag("vhwAbtizzyIOw163f8udDJlFgeNn81S5EkSoa7cwySmJxNR0WT");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("1f4FyOzofK5iEtxg3xaOwxGPKM3MudKiDq5m9bR0avazXFx86q");
        addresstype.setAddressTypeIcon("59UpJaqRTGKjZ0EXlw31f6xaOQH2zXC2IBSOD8MayNf5A0M9gN");
        addresstype.setAddressTypeDesc("wx8xl8DHI5zGC5A6QG7hADsqZb7VyLRkci4baUuvGmHNVU73i2");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLongitude("aGTS28EoOl7bfXmCB0DRMtFSFt6bZwaqfeYUmb9x3DIMAd55cr");
        address.setAddress2("S288bj3MBShbzoBqv5HxmsJGd0D59OZESZWp8Hc7kx43MCD9YO");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("7KmV6j3iYhu");
        address.setLatitude("sK5IZF3MwSSqKBhftDmaRb8krX5EEtrHhpkWqmZYCmOvFZMtrV");
        address.setAddress1("YEeMQIWsH33rab8hV8btzQbDHKvbeXFY5pwIwSWpUZuxGskkhk");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("XEFE55");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("CaclqmQBNjIkTGFBF1v2WSjVga2RGyjYWR81NACMCrPAf4sJOj");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("z0L97AqMoR");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("tdKj1HXpzhpr5jSOZzkPTFw959XzNzqSei0HIfIJCuBvYCkUXw");
        communicationgroup.setCommGroupDescription("2OExKowYiFncRCPdyONJcyV8uJBJv7VoAkQCv0bdDVqVGoGjOS");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("8GmnMP27Od4TO7n14SJI4cwbn6ISPIRuWD70O8pzYbe1lzECrq");
        communicationtype.setCommTypeDescription("J5Rs8rqPFMYP7wBrRt1LTDQJZAwPDUjOc8Tkhqkd1pCGk8dIyv");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("oAjTkAfpw3");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setUserAccessCode(52364);
        user.setGenTempOneTimePassword(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("ZCnpXze9GRcFbPuMrhDBpZDZLZM6rICUS0yxgkeRfRP7z2XkmY");
        useraccessdomain.setDomainIcon("sMFojTph16BN59kvQ7TxKljZkc9rlxDQs0pgPwSjGkcyVcejQw");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("tGzsuHHoHjwuY3vUg9lUNNsgsMm5sT1YvAR8RxUAlu4ucBDGgG");
        useraccessdomain.setDomainDescription("pwWbB2UcWLOOiqGkwVMX8xi4jvReTtsU7uKEnRXEbECJXo1Uve");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("9pbEScG8J7Kp77Pv9w9mlZx5VRwyv002iRQYQVB0vwZE2mF5AM");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("lHoSXFM8un3eBmHaREg5FzhCXHGPNiQoZObFVHguG6xulbJONd");
        useraccesslevel.setLevelName("2EmhifKv7KVi1m8818JVnRxFZI01kIPZuJgryXwq8AtP1AHWDY");
        useraccesslevel.setLevelHelp("jMDF8Ylbc9SvsXvcJvAoIsqQRkMQDF5hBOiICeFtd71TOoYdAJ");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setUserAccessCode(12938);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setAllowMultipleLogin(1);
        user.setIsDeleted(1);
        user.setChangePasswordNextLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465045080694l));
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465045080694l));
        user.setPasswordAlgo("lN3dkkPILwa7HwVz6SYJbCTyoVcBbEn9y9wXmlWjTltz28PyJv");
        user.setMultiFactorAuthEnabled(1);
        user.setIsLocked(1);
        user.setSessionTimeout(1659);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("piaryvmEUJTzIswbEiwEy4W3ufMSzbBAFHDpxoGJYLIS34alO3");
        question.setQuestionDetails("2s4zRSBSp8");
        question.setQuestion("5eiPe7CAnSqN97yBtivMop5LTkWlSSr4zrTQyCRm5geP2LiZFp");
        question.setLevelid(7);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setAnswer("b8IfOAsWHOG1RaCMu1Lu5DmHiHBU7CJZ2gnPkDm5GzngXydYgF");
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("i3iFeODRWWRITqnSs0w4ZLaFH0loKJXA");
        userdata.setLast5Passwords("LC8fsYuSVyd6ufgYKewCUdsqJDaefI7Yjre3DS716AlnFI3LJC");
        userdata.setPassword("1e0Lyp13X9bQYkqJZszCxWboHF19wpGyc2615TNMQWHASFGq15");
        userdata.setOneTimePassword("9b0FukABvnDSq9Clhim9sxXagJ8ZMnNC");
        userdata.setLast5Passwords("uv9He8HEXKd17MQRvJRsqEG3jGEYSC4HONAslVmUNzG3AXhp2Q");
        userdata.setPassword("HBtwaLGw6RG3LvwF8PxT9mOIKxyZ7kbj3zo3QaSI64o8z9Voo8");
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(6);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465045082324l));
        user.setUserData(userdata);
        Login login = new Login();
        login.setFailedLoginAttempts(9);
        login.setServerAuthImage("SOsms4lEyldeTy1fWEcGDMGDisocCYRz");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("PbegGET0ApVccvZD");
        login.setLoginId("3KuVSHQk2dHoXus5bMq2VvpqqPu07aLiMDc30XsYpCnG5FsJVQ");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(3);
            login.setServerAuthImage("SChYBvifEc2GJAyUe5cP83SjSvKCBtQP");
            login.setVersionId(1);
            login.setServerAuthText("82jktTJ7VMEC1q35");
            login.setLoginId("pwbv5cnoP6GemBUNgQmwFSvbuBUGGKhsYwToV4ujFW8AzDzMBX");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "PT9nG8fQlXagCNpjkaou36vBX5j62GddeiZ0ijxLF7H9na6XMsLvOF7BcDCe9nKrqaFht619dftygfyP9QWOCzCQtItoIzy8kNtbJ6qni5adfspMhhcZMGmUtxmiecKIE1ArCDzpakrStBlLy3NtlJfd1hGA5EaYFTm5BoYmMP8aCAm4sDJSxWdYXDyt35XbeVFHLT0Qf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "5fDsjTB9uk0IiQT4dRJQgGZpt4PQpUlRn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "OHa3IaIJbWgHX5bOk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
