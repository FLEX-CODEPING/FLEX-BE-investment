package codeping.flex.investment.adapter.in.web.data.pagination;

import codeping.flex.investment.global.common.exception.ApplicationException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static codeping.flex.investment.global.common.response.code.CommonErrorCode.INVALID_PARAMETER;

@Getter
public class CustomPageRequest {

    @Schema(description = "페이지 번호", nullable = true, example = "1", defaultValue = "1")
    private int page = 1;

    @Schema(description = "페이지 사이즈", nullable = true, example = "20", defaultValue = "20")
    private int size = 20;

    @Schema(description = "정렬 조건 (createdAt | ...)", nullable = true, example = "createdAt", defaultValue = "createdAt")
    private String property = "createdAt";

    @Schema(description = "정렬 방향 (desc | asc)", nullable = true, example = "desc", defaultValue = "desc")
    private String direction = "desc";

    public CustomPageRequest(int page, int size, String property, String direction) {
        this.page = Math.max(0, page - 1);
        this.size = Math.max(1, size);
        this.property = property;
        this.direction = validateDirection(direction);
    }

    /**
     * 정렬 방향을 검증합니다.
     * @param dir 정렬 방향
     * @return desc | asc
     */
    private String validateDirection(String dir) {
        if ((!dir.equalsIgnoreCase("asc") && !dir.equalsIgnoreCase("desc"))) {
            throw ApplicationException.from(INVALID_PARAMETER);
        }
        return dir.toLowerCase();
    }

    /**
     * PageRequest 객체를 생성합니다.
     */
    public PageRequest toPageRequest() {
        Sort.Direction direction = Sort.Direction.fromString(this.direction);
        return PageRequest.of(page, size, Sort.by(direction, property));
    }
}
