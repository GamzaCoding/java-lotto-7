package lotto.service;

import java.util.List;
import java.util.stream.Stream;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.Winning;
import lotto.policy.LottoPricePolicy;
import lotto.policy.PrizeMoneyPolicy;

public class LottoService {

    private LottoMachine lottoMachine;
    private Winning winning;

    public LottoService() {
        this.lottoMachine = new LottoMachine();
    }

    public List<Lotto> buyLottos(int money){
        return Stream.generate(lottoMachine::buyLotto)
                .limit(ticketCount(money))
                .toList();
    }

    public int ticketCount(int money) {
        return money / LottoPricePolicy.LOTTO_TICKET_PRICE.price();
    }

    private PrizeMoneyPolicy whatRank(Lotto lotto){
        int matchedCount = winning.match(lotto);
        boolean bonusMatch = winning.isBonusMatch(lotto);

        return PrizeMoneyPolicy.getRank(matchedCount, bonusMatch);
    }

    public int getMatchedCount(Lotto lotto){
        return whatRank(lotto).getMatchedCount();
    }

    public long getPrizeMoney(Lotto lotto){
        return whatRank(lotto).getPriceMoney();
    }
}
