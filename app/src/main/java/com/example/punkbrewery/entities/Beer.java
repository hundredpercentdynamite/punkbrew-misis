package com.example.punkbrewery.entities;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Beer {
    @NonNull
    @Override
    public String toString() {
        return Long.toString(id) + ":  " + this.name;
    }

    public final long id;
    public final String name;
    public final String tagline;
    public final String first_brewed;
    public final String description;
    public final double abv;
    public final double ibu;
    public final long target_fg;
    public final double target_og;
    public final long ebc;
    public final long srm;
    public final double ph;
    public final long attenuation_level;
    public final Volume volume;
    public final Boil_volume boil_volume;
    public final Method method;
    public final Ingredients ingredients;
    public final String[] food_pairing;
    public final String brewers_tips;
    public final String contributed_by;
    public final String image_url;

    @JsonCreator
    public Beer(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("tagline") String tagline, @JsonProperty("first_brewed") String first_brewed, @JsonProperty("description") String description, @JsonProperty("abv") double abv, @JsonProperty("ibu") double ibu, @JsonProperty("target_fg") long target_fg, @JsonProperty("target_og") double target_og, @JsonProperty("ebc") long ebc, @JsonProperty("srm") long srm, @JsonProperty("ph") double ph, @JsonProperty("attenuation_level") long attenuation_level, @JsonProperty("volume") Volume volume, @JsonProperty("boil_volume") Boil_volume boil_volume, @JsonProperty("method") Method method, @JsonProperty("ingredients") Ingredients ingredients, @JsonProperty("food_pairing") String[] food_pairing, @JsonProperty("brewers_tips") String brewers_tips, @JsonProperty("contributed_by") String contributed_by, @JsonProperty("image_url") String image_url){
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.first_brewed = first_brewed;
        this.description = description;
        this.abv = abv;
        this.ibu = ibu;
        this.target_fg = target_fg;
        this.target_og = target_og;
        this.ebc = ebc;
        this.srm = srm;
        this.ph = ph;
        this.attenuation_level = attenuation_level;
        this.volume = volume;
        this.boil_volume = boil_volume;
        this.method = method;
        this.ingredients = ingredients;
        this.food_pairing = food_pairing;
        this.brewers_tips = brewers_tips;
        this.contributed_by = contributed_by;
        this.image_url = image_url;
    }

    public static final class Volume {
        public final long value;
        public final String unit;

        @JsonCreator
        public Volume(@JsonProperty("value") long value, @JsonProperty("unit") String unit){
            this.value = value;
            this.unit = unit;
        }
    }

    public static final class Boil_volume {
        public final long value;
        public final String unit;

        @JsonCreator
        public Boil_volume(@JsonProperty("value") long value, @JsonProperty("unit") String unit){
            this.value = value;
            this.unit = unit;
        }
    }

    public static final class Method {
        public final Mash_temp mash_temp[];
        public final Fermentation fermentation;
        public final String twist;

        @JsonCreator
        public Method(@JsonProperty("mash_temp") Mash_temp[] mash_temp, @JsonProperty("fermentation") Fermentation fermentation, @JsonProperty("twist") String twist){
            this.mash_temp = mash_temp;
            this.fermentation = fermentation;
            this.twist = twist;
        }

        public static final class Mash_temp {
            public final Temp temp;
            public final int duration;

            @JsonCreator
            public Mash_temp(@JsonProperty("temp") Temp temp, @JsonProperty("duration") int duration){
                this.temp = temp;
                this.duration = duration;
            }

            public static final class Temp {
                public final long value;
                public final String unit;

                @JsonCreator
                public Temp(@JsonProperty("value") long value, @JsonProperty("unit") String unit){
                    this.value = value;
                    this.unit = unit;
                }
            }
        }

        public static final class Fermentation {
            public final Temp temp;

            @JsonCreator
            public Fermentation(@JsonProperty("temp") Temp temp){
                this.temp = temp;
            }

            public static final class Temp {
                public final long value;
                public final String unit;

                @JsonCreator
                public Temp(@JsonProperty("value") long value, @JsonProperty("unit") String unit){
                    this.value = value;
                    this.unit = unit;
                }
            }
        }

        public static final class Twist {

            @JsonCreator
            public Twist(){
            }
        }
    }

    public static final class Ingredients {
        public final Malt malt[];
        public final Hop hops[];
        public final String yeast;

        @JsonCreator
        public Ingredients(@JsonProperty("malt") Malt[] malt, @JsonProperty("hops") Hop[] hops, @JsonProperty("yeast") String yeast){
            this.malt = malt;
            this.hops = hops;
            this.yeast = yeast;
        }

        public static final class Malt {
            public final String name;
            public final Amount amount;

            @JsonCreator
            public Malt(@JsonProperty("name") String name, @JsonProperty("amount") Amount amount){
                this.name = name;
                this.amount = amount;
            }

            public static final class Amount {
                public final double value;
                public final String unit;

                @JsonCreator
                public Amount(@JsonProperty("value") double value, @JsonProperty("unit") String unit){
                    this.value = value;
                    this.unit = unit;
                }
            }
        }

        public static final class Hop {
            public final String name;
            public final Amount amount;
            public final String add;
            public final String attribute;

            @JsonCreator
            public Hop(@JsonProperty("name") String name, @JsonProperty("amount") Amount amount, @JsonProperty("add") String add, @JsonProperty("attribute") String attribute){
                this.name = name;
                this.amount = amount;
                this.add = add;
                this.attribute = attribute;
            }

            public static final class Amount {
                public final double value;
                public final String unit;

                @JsonCreator
                public Amount(@JsonProperty("value") double value, @JsonProperty("unit") String unit){
                    this.value = value;
                    this.unit = unit;
                }
            }
        }
    }
}
