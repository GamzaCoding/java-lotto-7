package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.policy.LottoNumberScalePolicy;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSixNumber(numbers);
        validateDuplication(numbers);
    }

    private void validateSixNumber(List<Integer> numbers) {
        int scale = LottoNumberScalePolicy.LOTTO_NUMBER_SCALE.getNumberScale();
        if (numbers.size() != scale) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
            // 이거 따로 에러 메시지 상수 관리하는 데로 옮기자
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> nonDuplicateNumbers = new HashSet<>(numbers);
        if (nonDuplicateNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }
}
