package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        var res = App.take(List.of(1, 2, 3, 4), 2);
        assertThat(res).hasSize(2);
        assertThat(res).isEqualTo(List.of(1, 2));
        // END
    }
}
