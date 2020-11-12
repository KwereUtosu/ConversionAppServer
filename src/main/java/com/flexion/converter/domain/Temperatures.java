package com.flexion.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.stream.Stream;

@AllArgsConstructor
public enum Temperatures {
    KELVIN("Kelvin") {
        public double toKelvin(double in) {
            return in;
        }

        public double fromKelvin(double inKelvin) {
            return inKelvin;
        }
    },
    CELSIUS("Celsius") {
        public double toKelvin(double in) {
            return in + 273.15;
        }

        public double fromKelvin(double inKelvin) {
            return inKelvin - 273.15;
        }
    },
    FAHRENHEIT("Fahrenheit") {
        public double toKelvin(double in) {
            return (in + 459.67) * (5.0 / 9.0);
        }

        public double fromKelvin(double inKelvin) {
            return (inKelvin * 1.8) - 459.67;
        }
    },
    RANKINE("Rankine") {
        public double toKelvin(double in) {
            return in / (5.0 / 9.0);
        }

        public double fromKelvin(double inKelvin) {
            return inKelvin * 1.8;
        }
    };
    @Getter
    private String temp;

    public abstract double toKelvin(double in);

    public abstract double fromKelvin(double kelvin);

    public static Temperatures getTemperatures(String temp) {
        return Stream.of ( Temperatures.values () )
                .filter ( temperatures -> temperatures.getTemp ()
                        .equalsIgnoreCase ( temp ) )
                .findFirst ()
                .get ();
    }

    public double convert(double in, Temperatures to) {
        double inKelvin = this.toKelvin(in);

        return to.fromKelvin(inKelvin);
    }





}