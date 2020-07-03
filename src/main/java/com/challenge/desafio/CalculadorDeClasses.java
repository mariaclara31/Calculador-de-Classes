package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object o) {
        BigDecimal soma = BigDecimal.ZERO;
        int qtd = 0;

        for(Field atributo : o.getClass().getDeclaredFields()) {
            atributo.setAccessible(true);
            if(atributo.isAnnotationPresent(Somar.class) && atributo.getType().equals(BigDecimal.class)) {
                try {
                    soma = soma.add((BigDecimal) atributo.get(o));
                    qtd++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if(qtd == 0)
            return BigDecimal.ZERO;
        else
            return soma;
    }
    @Override
    public BigDecimal subtrair(Object o) {
        BigDecimal soma = new BigDecimal(0);
        int qtd = 0;

        for(Field atributo : o.getClass().getDeclaredFields()) {
            atributo.setAccessible(true);
            if(atributo.isAnnotationPresent(Subtrair.class) && atributo.getType().equals(BigDecimal.class)) {
                try {
                    soma = soma.add((BigDecimal) atributo.get(o));
                    qtd++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if(qtd == 0)
            return BigDecimal.ZERO;
        else
            return soma;

    }
    @Override
    public BigDecimal totalizar(Object o) {
        return somar(o).subtract(subtrair(o));
    }
}