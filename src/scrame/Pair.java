package scrame;

import java.io.Serializable;

/**
 * A pair utility class
 *
 * @author abhishekbhagwat
 *
 * @param <L> The Left Component
 * @param <R> The Right Component
 * Code cloned and cleaned up from https://gist.github.com/anuvrat/370901.js git gist
 * Java API docs link https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/tuple/Pair.html
 */
public class Pair<L, R> implements Serializable {
    public final L left;
    public final R right;

    /**
     * Assignment of the parameter components to the field in the current buffer
     * @param left
     * @param right
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }
}
