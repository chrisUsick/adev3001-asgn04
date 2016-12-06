import java.util.Iterator;

/**
 * Map - The Map interface
 *
 * <pre>
 *
 * Assignment: #4
 * Course: ADEV-3001
 * Date Created: December 05, 2016
 *
 * Revision Log
 * Who          When    Reason
 * --------- ---------- ----------------------------------
 *
 * </pre>
 *
 * @author Chris Usick
 * @version 1.0
 *
 */
public interface Map<K, V> {
    int size();
    boolean isEmpty();
    void clear();
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    Iterator<K> keys();
    Iterator<V> values();
}
