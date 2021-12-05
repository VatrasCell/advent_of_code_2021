import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HydrothermalVentureTest {

    @Test
    void readInputValuesToList() {

        //act
        List<Vent> result = HydrothermalVenture.readInputValuesToList("test_input.data");

        //
        assertEquals(10, result.size());
    }

    @Test
    void mapVentsOnField() {

        //arrange
        List<Vent> vents = HydrothermalVenture.readInputValuesToList("test_input.data");
        HydrothermalField field = new HydrothermalField(vents, 10);

        //act
        field.mapVentsOnField(false);

        //assert
        assertTrue(true);
    }

    @Test
    void getDangerousAreasCount() {

        //arrange
        List<Vent> vents = HydrothermalVenture.readInputValuesToList("test_input.data");
        HydrothermalField field = new HydrothermalField(vents, 10);
        field.mapVentsOnField(false);

        //act
        int result = field.getDangerousAreasCount();

        //assert
        assertEquals(5, result);
    }
}