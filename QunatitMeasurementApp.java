public class QuantityMeasurementApp {

    // ENUM (base unit = feet)
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12),
        YARD(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.0328084);     // 1 cm ≈ 0.0328084 feet

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }

    // Generic Quantity Class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }
    }

    // Demo
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("Yard vs Feet: " + q1.equals(q2)); // true

        Quantity q3 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q4 = new Quantity(0.393701, LengthUnit.INCH);

        System.out.println("CM vs Inch: " + q3.equals(q4)); // true
    }
}