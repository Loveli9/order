package com.example.order.common;

import org.apache.commons.collections.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author author
 */
@SuppressWarnings("unchecked")
public class ListResponse<T> extends BaseResponse {

    /**
     * default serial version UID
     */
    private static final long serialVersionUID = 1L;

    private List<T> data;

    protected int totalCount;

    public List<T> getData() {
        return CollectionUtils.isEmpty(data) ? new ArrayList<>() : data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalCount() {
        totalCount = this.data != null ? this.data.size() : 0;
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static ListResponse ok(List list) {
        ListResponse response = new ListResponse();

        response.setData(list);

        return response;
    }

    public static ListResponse fail(String errorMsg) {
        ListResponse plainResponse = new ListResponse<>();
        plainResponse.setErrorMessage(CommonResultCode.NOT_IMPLEMENTED.code, errorMsg);
        return plainResponse;
    }
}
