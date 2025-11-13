/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package themes;

import static com.formdev.flatlaf.FlatLaf.setup;
import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author PERSONAL
 */
public class RiuRauLaf extends FlatLightLaf  {
    public static boolean setup() {
        
        return setup(new RiuRauLaf());
    }

    @Override
    public String getName() {
        return "RiuRauLaf";
    }
}

