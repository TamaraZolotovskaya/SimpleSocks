package me.tamarazolotovskaya.simplesocks3.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@AllArgsConstructor
public enum Size {
    S(23),
    M(25),
    L(27),
    XL(29),
    XXL(31);

    private int sizeNumber;

    @JsonValue
    public int getSizeNumber() {
        return sizeNumber;
    }

    @Nullable
    public static Size parse(int sizeNumber) {
        for (Size size :
                values()) {
            if (size.sizeNumber == sizeNumber) {
                return size;
            }
        }
        return null;
    }
}
