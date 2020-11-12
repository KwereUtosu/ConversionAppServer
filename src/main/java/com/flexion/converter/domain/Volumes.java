package com.flexion.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Volumes {

    LITERS("liters") {
        public double toLitre(double in) {
            return in;
        }

        public double fromLitre(double inLitre) {
            return inLitre;
        }
    },
    TABLESPOONS("tablespoons") {
        public double toLitre(double in) {
            return in * 1 / 67.628;
        }

        public double fromLitre(double inLitre) {
            return inLitre / 67.628;
        }
    },
    CUBIC_INCHES("cubic-inches") {
        public double toLitre(double in) {
            return in *  0.0163871;
        }

        public double fromLitre(double inLitre) {
            return inLitre  * 1 / 0.0163871;
        }
    },
    CUPS("cups") {
        public double toLitre(double in) {
            return in  * 0.236588;
        }

        public double fromLitre(double inLitre) {
            return inLitre / 61.024;
        }
    },
    CUBIC_FEET("cubic-feet") {
        public double toLitre(double in) {
            return in * 28.3168;
        }

        public double fromLitre(double inLitre) {
            return inLitre * 1 / 28.317;
        }
    },
    GALLONS("gallons") {
        public double toLitre(double in) {
            return in * 4.546;
        }

        public double fromLitre(double inLitre) {
            return inLitre * 1 / 4.546;
        }
    };
    @Getter
    private String volumes;

    public abstract double toLitre(double in);

    public abstract double fromLitre(double litre);

    public static Volumes getVolumes(String volume) {
        return Stream.of(Volumes.values())
                .filter(volumes -> volumes.getVolumes()
                        .equalsIgnoreCase(volume))
                .findFirst()
                .get();
    }

    public double convert(double in, Volumes to) {
        double inLitre = this.toLitre(in);

        return to.fromLitre(inLitre);
    }

    public static void main(String[] args) {
        double volumes = Volumes.TABLESPOONS.convert(15, Volumes.CUBIC_INCHES);
        System.out.println(volumes);
    }
}
