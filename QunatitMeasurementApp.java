public class QuantityMeasurementApp {

    // ENUM (base = feet)
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeet;
        }
    }

    // Quantity Class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }

        // Convert to another unit
        public Quantity convertTo(LengthUnit targetUnit) {
            double base = unit.toFeet(value);
            double converted = targetUnit.fromFeet(base);
            return new Quantity(converted, targetUnit);
        }

        // Static API method
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double base = source.toFeet(value);
            return target.fromFeet(base);
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

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // Demo
    public static void main(String[] args) {

        System.out.println("1 ft → inch = " +
                Quantity.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("3 yard → feet = " +
                Quantity.convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        Quantity q = new Quantity(36, LengthUnit.INCH);
        System.out.println("Instance convert: " +
                q.convertTo(LengthUnit.YARD));
    }
}