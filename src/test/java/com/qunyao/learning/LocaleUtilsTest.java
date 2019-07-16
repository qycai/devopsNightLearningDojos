package com.qunyao.learning;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class LocaleUtilsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_toLocale_withInputIsNull_shouldReturnNull() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = null;
        Locale output = localeUtils.toLocale(input);
        assertEquals(null, output);
    }

    @Test
    public void test_toLocale_withInputIsEmpty_shouldReturnEmpty() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "";
        Locale output = localeUtils.toLocale(input);
        assertEquals("", output.getCountry());
        assertEquals("", output.getLanguage());
    }

    @Test
    public void test_toLocale_withInputContainsHashSymbol_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "test#";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);

    }

    @Test
    public void test_toLocale_withInputLengthLessThanTwo_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "a";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputCountryIsEmptyAndLengthLessThanThree_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_a";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputCountryIsEmptyAndTheFirstCharacterOfCountryIsNotUpperCase_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_aA";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputCountryIsEmptyAndTheSecondCharacterOfCountryIsNotUpperCase_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_Aa";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputCountryIsEmptyAndTheFirstTwoCharacterOfCountryAreLowercase_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_aa";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputCountryIsEmptyAndTheFirstTwoCharacterOfCountryAreUppercase_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_AB";

        Locale output = localeUtils.toLocale(input);

        assertEquals("", output.getLanguage());
        assertEquals("AB", output.getCountry());
    }

    @Test
    public void tets_toLoacle_withInputCountryIsEmptyAndLengthIsFour_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_ABC";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void tets_toLoacle_withInputCountryIsEmptyAndLengthMoreThanFiveAndTheFourthCharacterIsNotDash_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_ABCDE";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputCountryIsEmptyAndLengthMoreThanFiveAndTheourthCharacterIsDash_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "_AB_CD";

        Locale output = localeUtils.toLocale(input);

        assertEquals("", output.getLanguage());
        assertEquals("AB", output.getCountry());
        assertEquals("CD", output.getVariant());
    }

    @Test
    public void tets_toLocale_withInputCountryIsAllLowercaseAndLengthIsTwo_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab";

        Locale output = localeUtils.toLocale(input);

        assertEquals("ab", output.getLanguage());
        assertEquals("", output.getCountry());
        assertEquals("", output.getVariant());
    }

    @Test
    public void tets_toLocale_withInputCountryIsAllLowercaseAndLengthIsThree_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "abc";

        Locale output = localeUtils.toLocale(input);

        assertEquals("abc", output.getLanguage());
        assertEquals("", output.getCountry());
        assertEquals("", output.getVariant());
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndCountryIsAllUppercase_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab_CD";

        Locale output = localeUtils.toLocale(input);

        assertEquals("ab", output.getLanguage());
        assertEquals("CD", output.getCountry());
        assertEquals("", output.getVariant());
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndCountryIsEmpty_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab_";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndCountryIsNumeric_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab_123";

        Locale output = localeUtils.toLocale(input);

        assertEquals("ab", output.getLanguage());
        assertEquals("123", output.getCountry());
        assertEquals("", output.getVariant());
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndCountryIsNotAllNumeric_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "abc_12c";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndISO3166CountryCodeAndVariantLengthOverZero_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab_CD_E";

        Locale output = localeUtils.toLocale(input);

        assertEquals("ab", output.getLanguage());
        assertEquals("CD", output.getCountry());
        assertEquals("E", output.getVariant());
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndNumericAreaCodeAndVariantLengthOverZero_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "abc_123_def";

        Locale output = localeUtils.toLocale(input);

        assertEquals("abc", output.getLanguage());
        assertEquals("123", output.getCountry());
        assertEquals("def", output.getVariant());
    }

    @Test
    public void test_toLocale_withInputAreISO639LanguageCodeAndCountryLengthEqualsZeroAndVariantLengthOverZero_shouldReturnCorrectLocale() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab__cd";

        Locale output = localeUtils.toLocale(input);

        assertEquals("ab", output.getLanguage());
        assertEquals("", output.getCountry());
        assertEquals("cd", output.getVariant());
    }

    @Test
    public void test_toLocale_withInputMoreThanLanguageCountryVariant_shouldThrowException() {
        LocaleUtils localeUtils = new LocaleUtils();
        String input = "ab_cd_ef_gh";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + input);
        localeUtils.toLocale(input);
    }

}
