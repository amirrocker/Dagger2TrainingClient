package de.amirrocker.testdagger2modules.base.data.cache;

import de.amirrocker.testdagger2modules.base.common.providers.TimestampProvider;
import io.reactivex.annotations.NonNull;

public class CacheEntryFactory<Key, Value> {

    @NonNull
    private final TimestampProvider timestampProvider;

    public CacheEntryFactory(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    @NonNull
    public CacheEntry<Value> createCacheEntry(@NonNull final Value value) {
        return new CacheEntry.Builder().cachedObject(value)
                .creationTimestamp(timestampProvider.currentTimeMillis())
                .build();
    }

}
