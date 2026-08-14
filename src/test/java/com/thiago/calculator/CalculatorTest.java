package com.thiago.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculator calculator = new Calculator();

    @Nested
    class sum {

        @Test
        @DisplayName("Should add two numbers")
        public void shouldAddTwoNumbers(){
            // Triple A (Arrange, Act, Assert)
            // Arrange
            int a = 2;
            int b = 3;

            // Act
            var output = calculator.sum(a, b);

            // Assert
            assertEquals(5, output);
        }

        @Test
        @DisplayName("Should add when two numbers are zero")
        void shouldAddWhenTwoNumbersAreZero(){
            int a = 0;
            int b = 0;

            var output = calculator.sum(a, b);

            assertEquals(0, output);
        }

    }

    @Nested
    class subtract{
        @Test
        @DisplayName("Should subtract two numbers")
        void shouldSubtractTwoNumbers(){
            int a = 5;
            int b = 2;
            var output = calculator.subtract(a, b);
            assertEquals(3, output);
        }

        @Test
        @DisplayName("Should subtract negative numbers")
        void shoudSubtractNegativeNumbers(){
            int a = -4;
            int b = -8;
            var output = calculator.subtract(a, b);
            assertEquals(4, output);
        }
    }

    @Nested
    class multiply {
        @Test
        @DisplayName("Should multiply two numbers")
        void shouldMultiplyTwoNumbers(){
            int a = 3;
            int b = 6;
            var output = calculator.multiply(a, b);
            assertEquals(18, output);
        }
    }

    @Nested
    class divide {
        @Test
        @DisplayName("Should divide two numbers")
        void shouldDivideTwoNumbers(){
            int a = 30;
            int b = 5;
            var output = calculator.divide(a, b);
            assertEquals(6, output);
        }

        @Test
        @DisplayName("Should throw exception when dividing by zero")
        void shouldNotDivideByZero(){
            int a = 5;
            int b = 0;
            var ex = assertThrows(ArithmeticException.class, () -> {
                calculator.divide(a, b);
            });
            assertEquals("Divisão por zero não permitida.", ex.getMessage());
        }
    }

    @Nested
    class pow {
        @Test
        @DisplayName("should calculate pow correctly")
        void shouldPowTwoNumbers(){
            int a = 5;
            int b = 2;
            var output = calculator.pow(5, 2);
            assertEquals(25, output);
        }
    }

    @Nested
    class squareRoot {
        @Test
        @DisplayName("Should calculate square root of a number")
        void shouldCalculateSquareRootOfANumber(){
            double a = 25;
            var output = calculator.squareRoot(25);
            assertEquals(5, output);

        }

        @Test
        @DisplayName("Should throw exception when number is negative")
        void shouldThrowExceptionWhenNumberIsNegative(){
            double a = -2;
            var ex = assertThrows(ArithmeticException.class, () -> {
                calculator.squareRoot(a);
            });
            assertEquals("Número negativo não tem raiz real.", ex.getMessage());
        }
    }

    @Nested
    class absolute {
        @Test
        @DisplayName("Should return abs of a number")
        void shouldReturnAbsOfANumber(){
            int number = -50;
            var output = calculator.absolute(number);
            assertEquals(50, output);
        }
    }

    @Nested
    class isEven {
        @Test
        @DisplayName("Should return true if the number is even")
        void shouldReturnTrueIfTheNumberIsEven(){
            int number = 4;
            var output = calculator.isEven(number);
            assertTrue(output);
        }

        @Test
        @DisplayName("Should return false if the number is not even")
        void shouldReturnFalseIfTheNumberIsEven(){
            int number = 5;
            var output = calculator.isEven(number);
            assertFalse(output);
        }

    }

    @Nested
    class isPrime {
        @Test
        @DisplayName("Should determine whether a number is prime")
        void ShouldDetermineWhetherANumberIsPrime(){
            int a = 29;
            var output = calculator.isPrime(a);
            assertTrue(output);
        }

        @Test
        @DisplayName("Should determine whether a number is not prime")
        void ShouldDetermineWhetherANumberIsNotPrime(){
            int a = 25;
            var output = calculator.isPrime(a);
            assertFalse(output);
        }
    }

    @Nested
    class maximum {
        @Test
        @DisplayName("Should get the maximum of two numbers")
        void shouldGetTheMaximumOfTwoNumbers(){
            int a = 8;
            int b = 6;
            var output = calculator.maximum(a, b);
            assertEquals(8, output);
        }
    }

    @Nested
    class minimum {
        @Test
        @DisplayName("Should get the minimum of two numbers")
        void shouldGetTheMinimumOfTwoNumbers(){
            int a = 8;
            int b = 6;
            var output = calculator.minimum(a, b);
            assertEquals(6, output);
        }
    }

}