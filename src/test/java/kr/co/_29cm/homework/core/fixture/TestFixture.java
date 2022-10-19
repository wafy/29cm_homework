package kr.co._29cm.homework.core.fixture;

import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.item.Item;

/**
 * 테스트에 사용할 객체 정보를 관리합니다.
 */
public class TestFixture {

    public static final int _수량_1개 = 1;
    public static final Long _디오디너리_데일리_세트_상품번호 = 748943L;
    public static final String _디오디너리_데일리_세트_상품명 = "디오디너리 데일리 세트 (Daily set)";
    public static final int _디오디너리_데일리_세트_가격 = 3_000;
    public static final int _디오디너리_데일리_세트_재고 = 3;

    public static final Long _캠핑덕_우드롤테이블_상품번호 = 778422L;
    public static final String _캠핑덕_우드롤테이블_상품명 = "캠핑덕 우드롤테이블";
    public static final int _캠핑덕_우드롤테이블_가격 = 45_000;
    public static final int _캠핑덕_우드롤테이블_재고 = 7;

    public static final Long _무설탕_프로틴_초콜릿_틴볼스_상품번호 = 628066L;
    public static final String _무설탕_프로틴_초콜릿_틴볼스_상품명 = "무설탕 프로틴 초콜릿 틴볼스";
    public static final int _무설탕_프로틴_초콜릿_틴볼스_가격 = 25_000;
    public static final int _무설탕_프로틴_초콜릿_틴볼스_재고 = 2;


    public static Item _디오디너리() {
        return Item.of(_디오디너리_데일리_세트_상품번호, _디오디너리_데일리_세트_상품명, _디오디너리_데일리_세트_가격, _디오디너리_데일리_세트_재고);
    }

    public static Item _캠핑덕() {
        return Item.of(_캠핑덕_우드롤테이블_상품번호, _캠핑덕_우드롤테이블_상품명, _캠핑덕_우드롤테이블_가격, _캠핑덕_우드롤테이블_재고);
    }

    public static Item _틴블스()  {
        return Item.of(_무설탕_프로틴_초콜릿_틴볼스_상품번호, _무설탕_프로틴_초콜릿_틴볼스_상품명, _무설탕_프로틴_초콜릿_틴볼스_가격, _무설탕_프로틴_초콜릿_틴볼스_재고);
    }

    public static Cart _카트_디오디너리(String sessionId) {
        return Cart.of(sessionId,
                _디오디너리_데일리_세트_상품번호,
                _디오디너리_데일리_세트_상품명,
                _디오디너리_데일리_세트_가격,
                1);
    }

    public static Cart _카트_캠핑덕(String sessionId) {
        return Cart.of(sessionId,
                _캠핑덕_우드롤테이블_상품번호,
                _캠핑덕_우드롤테이블_상품명,
                _캠핑덕_우드롤테이블_가격,
                1);
    }

    public static Cart _초콜릿(String sessionId) {
        return Cart.of(sessionId,
                _무설탕_프로틴_초콜릿_틴볼스_상품번호,
                _무설탕_프로틴_초콜릿_틴볼스_상품명,
                _무설탕_프로틴_초콜릿_틴볼스_가격,
                1);
    }

}
