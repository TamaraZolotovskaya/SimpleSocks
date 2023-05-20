package me.tamarazolotovskaya.simplesoсks3.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Socks {
    private  Color color;
    private  Size size;
    private int cottonPart;


    @Override
    public String toString() {
        return "Socks:{" +
                "colorName: " + color.getColorName() +
                ", sizeNumber: " + size.getSizeNumber() +
                ", cottonPart: " + cottonPart +
                '}';
    }
}
