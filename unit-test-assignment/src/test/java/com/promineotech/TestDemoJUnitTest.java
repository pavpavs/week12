package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoJUnitTest {

	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveValuesAreAddedCorrectly(int a, int b, 
			int expected, boolean expectException) {
				
		if (!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(()-> testDemo.addPositive(a,b))
			.isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	static Stream<Arguments> argumentsForAddPositive(){
		
		return Stream.of(
				arguments(2, 4, 6, false),
				arguments(0, 2, 2, true),
				arguments(2, -1, 1, true),
				arguments(-3, 0, -7, true),
				arguments(-1, 18, 17, true)
				);
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(1,3)).isEqualTo(4);
		assertThat(testDemo.addPositive(3,5)).isEqualTo(8);
	}
	
	@Test
	void assertThatPairOfPositiveNumbersAreDividedCorrectly() {
		assertThat(testDemo.divideTwoPositives(4,2)).isEqualTo(2);
		assertThat(testDemo.divideTwoPositives(5,2)).isEqualTo(2);
		assertThat(testDemo.divideTwoPositives(1,3)).isEqualTo(0);
	}
	
	@Test
	void assertThatDivideTwoPositivesThrowsCorrectException() {
		assertThatThrownBy(()-> testDemo.divideTwoPositives(-1,8))
		.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(()-> testDemo.divideTwoPositives(8,-1))
		.isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
}
