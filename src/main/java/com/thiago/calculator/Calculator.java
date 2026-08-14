package com.thiago.calculator;

class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não permitida.");
        }
        return (double) a / b;
    }

    public double pow(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    public double squareRoot(double numero) {
        if (numero < 0) {
            throw new ArithmeticException("Número negativo não tem raiz real.");
        }
        return Math.sqrt(numero);
    }

    public int absolute(int numero) {
        return Math.abs(numero);
    }

    public boolean isEven(int numero) {
        return numero % 2 == 0;
    }

    public boolean isPrime(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    public int maximum(int a, int b) {
        return Math.max(a, b);
    }

    public int minimum(int a, int b) {
        return Math.min(a, b);
    }

}

