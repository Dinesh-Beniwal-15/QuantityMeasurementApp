public class QuantityMeasurementApp {

    // -------- Feet Class --------
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------- Inches Class --------
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------- Static helper methods (reduce main dependency) --------
    public static boolean areFeetEqual(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    public static boolean areInchesEqual(double v1, double v2) {
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);
        return i1.equals(i2);
    }

    // -------- Demo --------
    public static void main(String[] args) {
        System.out.println("Feet 1.0 vs 1.0 -> " + areFeetEqual(1.0, 1.0));
        System.out.println("Inch 1.0 vs 1.0 -> " + areInchesEqual(1.0, 1.0));
    }
}