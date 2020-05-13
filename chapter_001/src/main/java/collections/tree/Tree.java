package collections.tree;

import java.util.*;
/**
 * 1. Создать элементарную структуру дерева [#281961]
 * @author Kirill Asmanov
 * @since 13.05.2020
 * @param <E> any type
 */
class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Adds unique element on Tree
     * @param parent parental node for added object
     * @param child added object
     * @return true if object was added on tree
     */
    @Override
    public boolean add(E parent, E child) {
        // Не уверен насчет того, можем ли мы добавлять в дерево null значения.
        // По-идее это лишает смысла условие об отсутствии повторяющихся значений
        // (либо null в девреве может храниться только в единственном экземпляре),
        // и нужно ли нам хранить ноды с null value, которые Optional распознавал бы как непустые.
        if (child == null) {
            throw new NullPointerException();
        }
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> foundParent = findBy(parent);
            if (foundParent.isPresent()) {
                Node<E> added = new Node<E>(child);
                foundParent.get().children.add(added);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks is the tree is binary
     * @return true if it's binary
     */
    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                return false;
            }
            data.addAll(el.children);
        }
        return true;
    }

    /**
     * Find node by its value
     * @param value founded value
     * @return found node
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
