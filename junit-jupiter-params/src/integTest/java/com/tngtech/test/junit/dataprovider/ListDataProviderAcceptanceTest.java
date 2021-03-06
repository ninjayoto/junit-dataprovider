package com.tngtech.test.junit.dataprovider;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.UseDataProvider;

class ListDataProviderAcceptanceTest {

    @DataProvider
    static List<List<Object>> dataProviderNumberFormat() {
        List<List<Object>> result = new ArrayList<>();
        List<Object> first = new ArrayList<>();
        first.add(Integer.valueOf(101));
        first.add("%5d");
        first.add("  101");
        result.add(first);
        List<Object> second = new ArrayList<>();
        second.add(125);
        second.add("%06d");
        second.add("000125");
        result.add(second);
        return result;
    }

    @ParameterizedTest
    @UseDataProvider("dataProviderNumberFormat")
    void testNumberFormat(Number number, String format, String expected) {
        // Given:

        // When:
        String result = String.format(format, number);

        // Then:
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider
    static List<? extends Number> dataProviderIsNumber() {
        List<Number> result = new ArrayList<>();
        result.add(101);
        result.add(125L);
        result.add(125.0);
        return result;
    }

    @ParameterizedTest
    @UseDataProvider
    void testIsNumber(Number number) {
        // Expect:
        assertThat(number).isInstanceOf(Number.class);
    }
}
