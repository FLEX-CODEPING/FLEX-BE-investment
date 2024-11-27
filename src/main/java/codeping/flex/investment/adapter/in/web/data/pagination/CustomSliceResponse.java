package codeping.flex.investment.adapter.in.web.data.pagination;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"content", "hasNext", "isFirst", "isLast"})
public class CustomSliceResponse<T> implements Serializable {

    private List<T> content;
    private boolean hasNext;
    private boolean isFirst;
    private boolean isLast;

    @Builder
    private CustomSliceResponse(List<T> content, boolean hasNext, boolean isFirst, boolean isLast) {
        this.content = content != null ? new ArrayList<>(content) : new ArrayList<>();
        this.hasNext = hasNext;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    public static <T, R> CustomSliceResponse<T> of(List<T> contents, Slice<R> sliceResult) {
        return CustomSliceResponse.<T>builder()
                .content(contents)
                .hasNext(sliceResult.hasNext())
                .isFirst(sliceResult.isFirst())
                .isLast(sliceResult.isLast())
                .build();
    }
}
