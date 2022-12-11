package com.example.teleinfo.modules;

import java.util.Date;

public class Menu_Object {

    public int Status;
    public String StatusText, DayOfWeek, Breakfast, BreakfastAllergens, Brunch, BrunchAllergens, Lunch, LunchAllergens, Snack, SnackAllergens, Dinner, DinnerAllergens, DinnerSecond, DinnerSecondAllergens, Key;
    public String Date;

    public Menu_Object() {
    }

    public Menu_Object(int Status, String Date, String StatusText, String DayOfWeek, String Breakfast, String BreakfastAllergens, String Brunch, String BrunchAllergens, String Lunch, String LunchAllergens, String Snack, String SnackAllergens, String Dinner, String DinnerAllergens, String DinnerSecond, String DinnerSecondAllergens, String Key) {
        this.Status = Status;
        this.Date = Date;
        this.StatusText = StatusText;
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
        this.Key = Key;
    }
}




