package main.rupizzeria;

/**
 * This class defines the size of the Pizza
 * It is an enum class and so far have 3 type of sizes offered.
 * @author Gordon Lin modified Nove. 08, 2024
 */
public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    /**
     * This is a string representation of Size.
     * @return a string representation of the enum.
     */
    @Override
    public String toString(){
        return this.name().charAt(0)+ this.name().substring(1).toLowerCase();
    }
}
