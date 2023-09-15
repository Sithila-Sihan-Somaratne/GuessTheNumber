package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NumberGuessAndGenerator {
    private int generatedNumber;
    private int guessedNumber;
}
