package io.github.resilience4j.core.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapUtils {
    private MapUtils() {
    }

    public static <K, V> Map<K, V> of(K k, V v) {
        HashMap<K, V> objectObjectHashMap = new HashMap<>(1);
        objectObjectHashMap.put(k, v);
        return objectObjectHashMap;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        HashMap<K, V> objectObjectHashMap = new HashMap<>(1);
        objectObjectHashMap.put(k1, v1);
        objectObjectHashMap.put(k2, v2);
        return objectObjectHashMap;
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        HashMap<K, V> objectObjectHashMap = new HashMap<>(1);
        objectObjectHashMap.put(k1, v1);
        objectObjectHashMap.put(k2, v2);
        objectObjectHashMap.put(k3, v3);
        return objectObjectHashMap;
    }


    @SafeVarargs
    private static <K, V> Map<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
        if (entries.length == 0) {
            return Collections.emptyMap();
        } else if (entries.length == 1) {
            return Collections.singletonMap(entries[0].getKey(), entries[0].getValue());
        } else {
            HashMap<K, V> map = new HashMap<>();
            int var3 = entries.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Map.Entry<? extends K, ? extends V> entry = ((Map.Entry[]) entries)[var4];
                map.put(entry.getKey(), entry.getValue());
            }

            return Collections.unmodifiableMap(map);
        }
    }

    public static <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return ofEntries((Map.Entry[]) map.entrySet().toArray(new Map.Entry[0]));
    }

    public static <K, V> Map.Entry<K, V> entry(K k, V v) {
        // KeyValueHolder checks for nulls
        return new KeyValueHolder<>(k, v);
    }

    public static final class KeyValueHolder<K, V> implements Map.Entry<K, V> {
        final K key;
        final V value;

        KeyValueHolder(K k, V v) {
            key = Objects.requireNonNull(k);
            value = Objects.requireNonNull(v);
        }

        /**
         * Gets the key from this holder.
         *
         * @return the key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Gets the value from this holder.
         *
         * @return the value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Throws {@link UnsupportedOperationException}.
         *
         * @param value ignored
         * @return never returns normally
         */
        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException("not supported");
        }

        /**
         * Compares the specified object with this entry for equality.
         * Returns {@code true} if the given object is also a map entry and
         * the two entries' keys and values are equal. Note that key and
         * value are non-null, so equals() can be called safely on them.
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return key.equals(e.getKey()) && value.equals(e.getValue());
        }

        /**
         * Returns the hash code value for this map entry. The hash code
         * is {@code key.hashCode() ^ value.hashCode()}. Note that key and
         * value are non-null, so hashCode() can be called safely on them.
         */
        @Override
        public int hashCode() {
            return key.hashCode() ^ value.hashCode();
        }

        /**
         * Returns a String representation of this map entry.  This
         * implementation returns the string representation of this
         * entry's key followed by the equals character ("{@code =}")
         * followed by the string representation of this entry's value.
         *
         * @return a String representation of this map entry
         */
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
