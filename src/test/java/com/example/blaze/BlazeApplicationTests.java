package com.example.blaze;

import com.example.blaze.dao.PersonBlazeDao;
import com.example.blaze.dao.PersonCriteriaDao;
import com.example.blaze.dao.PersonQueryDao;
import com.example.blaze.model.Address;
import com.example.blaze.model.Country;
import com.example.blaze.model.Person;
import com.example.blaze.model.Telephone;
import com.example.blaze.repository.AddressRepository;
import com.example.blaze.repository.CountryRepository;
import com.example.blaze.repository.PersonRepository;
import com.example.blaze.repository.TelephoneRepository;
import com.example.blaze.view.AddressView;
import com.example.blaze.view.PersonView;
import com.example.blaze.view.TelephoneView;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class BlazeApplicationTests {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonBlazeDao personBlazeDao;

    @Autowired
    PersonCriteriaDao personCriteriaDao;

    @Autowired
    PersonQueryDao personQueryDao;

    @BeforeEach
    void setup() {
        Country poland = Country.builder()
                .code("PL")
                .name("Poland")
                .build();

        Country uk = Country.builder()
                .code("UK")
                .name("United Kingdom")
                .build();

        countryRepository.saveAll(List.of(poland, uk));

        Address polishAddress = Address.builder()
                .city("Warsaw")
                .country(poland)
                .build();

        Address ukAddress = Address.builder()
                .city("London")
                .country(uk)
                .build();

        addressRepository.saveAll(List.of(polishAddress, ukAddress));

        Telephone iphone = Telephone.builder()
                .model("IPhone")
                .number("123456789")
                .build();

        Telephone samsung = Telephone.builder()
                .model("IPhone")
                .number("987654321")
                .build();

        telephoneRepository.saveAll(List.of(iphone, samsung));

        Person person = Person.builder()
                .firstName("Adam")
                .lastName("Mazurek")
                .phones(List.of(iphone, samsung))
                .addresses(List.of(polishAddress, ukAddress))
                .build();

        personRepository.save(person);
    }

    @Test
    @Transactional
    void testQuery() {
        var person = personRepository.findAll().stream().findFirst().get();
        log.info("Person: {}", person);

        Assertions.assertNotNull(person);

        // MultipleBagFetchException
        Executable executable = () -> personRepository.findPersons(person.getId());
        Assertions.assertThrowsExactly(InvalidDataAccessApiUsageException.class,
                executable);

        List<PersonView> personViewList = personBlazeDao.findByName("Mazurek");
        assertThat(personViewList).isNotEmpty();
        PersonView personView = personViewList.get(0);
        assertThat(personView.getFirstName()).isEqualTo("Adam");
        assertThat(personView.getAddresses().stream().filter(a -> a.getCountry().getCode().equals("PL")).findAny()).isPresent();
        assertThat(personView.getAddresses().stream().filter(a -> a.getCountry().getCode().equals("UK")).findAny()).isPresent();
        assertThat(personView.getPhones().stream().filter(p -> p.getNumber().equals("123456789")).findAny()).isPresent();
        assertThat(personView.getPhones().stream().filter(p -> p.getNumber().equals("987654321")).findAny()).isPresent();
        log.info("Person phones: {}, addresses {}",
                personView.getPhones().stream().map(TelephoneView::getNumber).collect(Collectors.joining(",")),
                personView.getAddresses().stream().map(AddressView::getCity).collect(Collectors.joining(",")));
    }

	@Test
	@Transactional
	void testCriteriaQuery() {
		var personList = personCriteriaDao.findByName("Mazurek");
		assertThat(personList).isNotEmpty();
		personList.get(0).getAddresses().stream().forEach(a -> log.info("address: {}", a.getCountry().getCode()));
	}

	@Test
	void testConstructorBasedProjectionQuery() {
		var result = personQueryDao.findByName("Mazurek");
		log.info("result {}", result.size());
	}

}
