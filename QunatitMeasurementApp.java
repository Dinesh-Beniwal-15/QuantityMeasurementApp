public class QuantityMeasurementApp {

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

    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        // ✅ ADDITION METHOD (core of UC6)
        public Quantity add(Quantity other) {

            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            double sumInFeet = this.toBase() + other.toBase();

            double resultValue = unit.fromFeet(sumInFeet);

            return new Quantity(resultValue, this.unit);
        }

        // Optional static version
        public static Quantity add(Quantity q1, Quantity q2) {
            return q1.add(q2);
        }

        // Conversion (UC5)
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double base = source.toFeet(value);
            return target.fromFeet(base);
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

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2)); // 2 FEET
    }
}