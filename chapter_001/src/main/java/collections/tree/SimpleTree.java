package collections.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * 1. Создать элементарную структуру дерева [#281961]
 * @author Kirill Asmanov
 * @since 13.05.2020
 * @param <E> any type
 */
public interface SimpleTree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    /**
     * Node realize
     * @param <E>
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}