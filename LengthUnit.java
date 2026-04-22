public class QuantityMeasurementApp {

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
            return unit.convertToBaseUnit(value);
        }

        // UC6
        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        // UC7
        public Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sum = this.toBase() + other.toBase();

            double result = targetUnit.convertFromBaseUnit(sum);

            return new Quantity(result, targetUnit);
        }

        // UC5
        public Quantity convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base = this.toBase();

            double result = targetUnit.convertFromBaseUnit(base);

            return new Quantity(result, targetUnit);
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

        System.out.println(q1.add(q2, LengthUnit.FEET));   // 2 FEET
        System.out.println(q1.convertTo(LengthUnit.INCH)); // 12 INCH
        System.out.println(q1.equals(q2));                 // true
    }
}