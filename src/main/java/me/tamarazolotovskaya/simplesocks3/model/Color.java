package me.tamarazolotovskaya.simplesocks3.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@AllArgsConstructor
public enum Color {
    WHITE("white"),
    BEIGE("beige"),
    LIGTTGREY("lightgrey"),
    DARKGREY("darkgrey"),
    BLACK("black");

    private String colorName;

    @JsonValue
    public String getColorName() {
        return colorName;
    }

    @Nullable
    public static Color parse(String colorName) {
        for (Color color :
                values()) {
            if (color.colorName.equals(colorName)) {
                return color;
            }
        }
        return null;
    }
}
