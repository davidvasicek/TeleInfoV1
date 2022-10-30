package com.example.teleinfo.rozvrh;

import java.util.Date;

public class DinningRoomMenuObject {

    public String DayOfWeek, Breakfast, BreakfastAllergens, Brunch, BrunchAllergens, Lunch, LunchAllergens, Snack, SnackAllergens, Dinner, DinnerAllergens, DinnerSecond, DinnerSecondAllergens;
    public Date Date;

    public DinningRoomMenuObject() {
    }

    public DinningRoomMenuObject(Date Date, String DayOfWeek, String Breakfast, String BreakfastAllergens, String Brunch, String BrunchAllergens, String Lunch, String LunchAllergens, String Snack, String SnackAllergens, String Dinner, String DinnerAllergens, String DinnerSecond, String DinnerSecondAllergens) {
        this.Date = Date;
        this.DayOfWeek = DayOfWeek;
        this.Breakfast = Breakfast;
        this.BreakfastAllergens = BreakfastAllergens;
        this.Brunch = Brunch;
        this.BrunchAllergens = BrunchAllergens;
        this.Lunch = Lunch;
        this.LunchAllergens = LunchAllergens;
        this.Snack = Snack;
        this.SnackAllergens = SnackAllergens;
        this.Dinner = Dinner;
        this.DinnerAllergens = DinnerAllergens;
        this.DinnerSecond = DinnerSecond;
        this.DinnerSecondAllergens = DinnerSecondAllergens;
    }
}




