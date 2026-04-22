static class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {

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

    // Conversion
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = this.toBase();
        double result = targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(result, targetUnit);
    }

    // Addition (UC6 equivalent)
    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    // Addition with target (UC7 equivalent)
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sum = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}