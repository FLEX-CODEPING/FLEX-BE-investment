package codeping.flex.investment.adapter.in.web.data.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class CustomPageRequest {

    @Schema(description = "페이지 번호", nullable = true, example = "1", defaultValue = "1")
    private int page = 1;

    @Schema(description = "페이지 사이즈", nullable = true, example = "20", defaultValue = "20")
    private int size = 20;

    @Schema(description = "정렬 조건 (createdAt | ...)", nullable = true, example = "createAt", defaultValue = "createAt")
    private String property = "createdAt";

    @Schema(description = "정렬 방향 (desc | asc)", nullable = true, example = "desc", defaultValue = "desc")
    private String direction = "desc";

    public CustomPageRequest(boolean paging, int page, int size, String property, String direction) {
        this.page = Math.max(0, page - 1);
        this.size = Math.max(1, size);
        this.property = property;
        this.direction = validateDirection(direction); // 정렬 방향 검증
    }

    private String validateDirection(String dirs) {
        if ((!dirs.equalsIgnoreCase("asc") && !dirs.equalsIgnoreCase("desc"))) {
            return "desc";
        }
        return dirs.toUpperCase();
    }

    /**
     * PageRequest 객체를 생성합니다.
     */
    public PageRequest toPageRequest() {
        Sort.Direction direction = Sort.Direction.fromString(this.direction);
        return PageRequest.of(page, size, Sort.by(direction, property));
    }
}
