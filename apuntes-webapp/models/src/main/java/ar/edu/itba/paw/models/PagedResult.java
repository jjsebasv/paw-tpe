package ar.edu.itba.paw.models;

import java.util.List;

public class PagedResult<T> {

    private final List<T> results;

    private final int start;
    private final int limit;
    private final int total;

    public PagedResult(List<T> results, int start, int limit, int total) {
        this.results = results;
        this.start = start;
        this.limit = limit;
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCurrentPage() {
        return start / limit + 1;
    }

    public int getMaxPage() {
        return (total / limit) + 1;
    }
}
