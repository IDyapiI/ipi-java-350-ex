package com.ipiecoles.java.java350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void augmenterSalaireNormalUse() {
        // Given
        Employe employe = new Employe();
        // salaire de base 1521.22
        Double newSalaire = 2890.318;
        // When
        employe.augmenterSalaire(0.9);
        // Then
        Assertions.assertEquals(newSalaire, employe.getSalaire());
    }

    @Test
    public void augmenterSalaireBy0() {
        // Given
        Employe employe = new Employe();
        // salaire de base 1521.22
        Double newSalaire = 1521.22;
        // When
        employe.augmenterSalaire(0);
        // Then
        Assertions.assertEquals(newSalaire, employe.getSalaire());
    }

    @Test
    public void augmenterSalaireByNegatif() {
        // Given
        Employe employe = new Employe();
        // salaire de base 1521.22
        Double newSalaire = 1521.22;
        // When pas possible kick manager pls
        employe.augmenterSalaire(-0.4);
        // Then
        Assertions.assertEquals(newSalaire, employe.getSalaire());
    }

    @Test
    public void augmenterSalaireWithANull() {
        // Given
        Employe employe = new Employe();
        employe.setSalaire(0.0);
        Double newSalaire = 0.0;
        // When
        employe.augmenterSalaire(0.2);
        // Then
        Assertions.assertEquals(newSalaire, employe.getSalaire());
    }

    @Test
    public void getNombreAnneeAncienneteNow() {
        // Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        // When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        // Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNminus2() {
        // Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        // When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        // Then
        Assertions.assertEquals(2, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNull() {
        // Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        // When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        // Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNplus2() {
        // Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        // When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        // Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @ParameterizedTest
    @CsvSource({ "1, 'T12345', 0, 1.0, 1000.0", "1, 'T12345', 2, 0.5, 600.0", "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0", "2, 'T12345', 1, 1.0, 2400.0", "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0", "2, 'M12345', 0, 1.0, 1700.0", "2, 'M12345', 8, 1.0, 2500.0" })
    public void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel,
            Double primeAnnuelle) {
        // Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete),
                Entreprise.SALAIRE_BASE, performance, tempsPartiel);

        // When
        Double prime = employe.getPrimeAnnuelle();

        // Then
        Assertions.assertEquals(primeAnnuelle, prime);

    }

    @ParameterizedTest
    @CsvSource({"2021, 1.0, 11",
            "2021, 0.5, 5.5", "2022, 1.0, 10", "2022, 0.5, 5", "2023, 1.0, 8", "2023, 0.5, 4", "2032, 1.0, 12",})

    public void testGetNbrRtt(int years, Double tempsPartiel, Double NbRtt) {
        // Given
        Employe employe = new Employe();

        // When
        employe.setTempsPartiel(tempsPartiel);

        // Then
        Assertions.assertEquals(NbRtt, employe.getNbRtt(LocalDate.ofYearDay(years, 1)));
    }
}
