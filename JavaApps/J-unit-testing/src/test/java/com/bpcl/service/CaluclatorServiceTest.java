package com.bpcl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.annotation.processing.SupportedAnnotationTypes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bpcl.dao.CaluclatorDao;

//@TestMethodOrder(value = OrderAnnotation.class) // to make the test scripts by order which we mentioned
//@ExtendWith(MockitoExtension.class)
public class CaluclatorServiceTest {

	private static CaluclatorService caluclatorService;

	@InjectMocks
	private CaluclatorService service;

	@Mock
	private CaluclatorDao caluclatorDao;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getName() {
		System.out.println("CaluclatorServiceTest.getName()");

		when(caluclatorDao.getNameById()).thenReturn("Praveen");

		String name = service.getNameById();

		verify(caluclatorDao, times(1)).getNameById();

		assertEquals("Praveen", name);
	}

	// @BeforeAll
	// @BeforeEach
	// @AfterEach
	// @AfterAll
	public static void beforeAll() {
		caluclatorService = new CaluclatorService();
	}

	// @Test
	public void testAdd() {

		int expet = caluclatorService.add(10, 20);
		int actual = 30;

		assertEquals(expet, actual, "Both are not matched");// compare the value
	}

	// @ParameterizedTest // execute test with multiple inputs
	// @ValueSource(strings = { "radar", "racecar", "abc", "prp" })
	public void testIsPalindrome(String str) {
		boolean result = caluclatorService.isPalindrome(str);
		assertTrue(result);
	}

	// @ParameterizedTest
	// @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1) // Read the data
	// from csv file
	public void evenOrOdd(String value, String expect) {
		assertEquals(expect, (Integer.parseInt(value)) % 2 == 0 ? "even" : "odd");
	}

	// @Test
	public void convertToInt() {
		String str = null;
		assertThrows(IllegalArgumentException.class, () -> CaluclatorService.convertToInt(str));
	}

}
