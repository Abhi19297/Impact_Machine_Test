package com.threedev.datestreak.data;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class AppViewModelTest {



    @Test
    public void whenInputIsValid() {
        String date = "Tue, 15 Jun 2021";
        int streak = 1;

        String result = AppViewModel.increaseDate(date,streak);

        assertThat(!result.isEmpty());
    }

    @Test
    public void whenInputIsInvalid() {
        String date = null;
        int streak = 0;

        String result = AppViewModel.increaseDate(date,streak);

        assertThat(!result.isEmpty());
    }

}