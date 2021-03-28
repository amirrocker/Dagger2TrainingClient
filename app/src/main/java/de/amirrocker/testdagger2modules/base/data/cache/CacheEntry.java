package de.amirrocker.testdagger2modules.base.data.cache;

/*
@AutoValue
abstract class CacheEntry<T> {

    abstract T getCachedObject();


    abstract Long getCreationTimestamp();

    static Builder builder() {
        return new AutoValue_CacheEntry.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder<T> {
        abstract CacheEntry.Builder<T> cachedObject(T value);
        abstract CacheEntry.Builder<T> creationTimestamp(Long value);
        abstract CacheEntry<T> build();
    }
}
*/

public class CacheEntry<T> {

    // TODO encapsulate both!
    public T cachedObject;
    public Long creationTimestamp;

    private CacheEntry(
            T cachedObject,
            Long creationTimestamp) {
        this.cachedObject = cachedObject;
        this.creationTimestamp = creationTimestamp;
    }


    T getCachedObject() {
        return cachedObject;
    }


    Long getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override
    public String toString() {
        return "CacheEntry{"
                + "cachedObject=" + cachedObject + ", "
                + "creationTimestamp=" + creationTimestamp
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof CacheEntry) {
            CacheEntry<?> that = (CacheEntry<?>) o;
            return this.cachedObject.equals(that.getCachedObject())
                    && this.creationTimestamp.equals(that.getCreationTimestamp());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h$ = 1;
        h$ *= 1000003;
        h$ ^= cachedObject.hashCode();
        h$ *= 1000003;
        h$ ^= creationTimestamp.hashCode();
        return h$;
    }

    static final class Builder<T> {
        private T cachedObject;
        private Long creationTimestamp;
        Builder() {
        }

        Builder<T> cachedObject(T cachedObject) {
            if (cachedObject == null) {
                throw new NullPointerException("Null cachedObject");
            }
            this.cachedObject = cachedObject;
            return this;
        }

        Builder<T> creationTimestamp(Long creationTimestamp) {
            if (creationTimestamp == null) {
                throw new NullPointerException("Null creationTimestamp");
            }
            this.creationTimestamp = creationTimestamp;
            return this;
        }

        CacheEntry<T> build() {
            String missing = "";
            if (this.cachedObject == null) {
                missing += " cachedObject";
            }
            if (this.creationTimestamp == null) {
                missing += " creationTimestamp";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new CacheEntry<T>(
                    this.cachedObject,
                    this.creationTimestamp);
        }
    }

}
