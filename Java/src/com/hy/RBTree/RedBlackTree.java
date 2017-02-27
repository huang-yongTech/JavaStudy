package com.hy.RBTree;

/**
 * Created by huangyong on 2017/1/22.
 * 红黑树实现:
 * 性质:
 * 1.节点要么红，要么黑;
 * 2.根是黑色;
 * 3.所有叶子都是黑色;(叶子为null节点)
 * 4.每个红色节点的两个子节点都是黑色(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 5.从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点
 */
public class RedBlackTree<T extends Comparable<? super T>> {
    private Node<T> root;//树的根节点
    private int size;//树的大小
    private Node<T> NIL = new Node<T>(null, null, null, null, Color.BLACK);//标志叶子节点

    public static void main(String[] args) {

    }

    /**
     * 获取叔叔节点
     *
     * @param node 当前节点
     * @return 其叔叔节点
     */
    private Node<T> uncle(Node<T> node) {
        Node<T> grandParent = grandParent(node);
        if (grandParent == null)
            return null;
        if (node.parent == grandParent.left) {
            return grandParent.right;
        } else {
            return grandParent.left;
        }
    }

    /**
     * 获取祖父节点
     *
     * @param node 当前节点
     * @return 其祖父节点
     */
    private Node<T> grandParent(Node<T> node) {
        if (node.parent == null)
            return null;
        return node.parent.parent;
    }

    /**
     * 获取某节点为根的树的最小元素
     *
     * @param node 树根节点
     * @return 最小元素
     */
    private T minValue(Node<T> node) {
        Node<T> min = minNode(node);
        if (min != null) {
            return min == NIL ? null : min.value;
        } else {
            return null;
        }
    }

    /**
     * 获取某节点为根的树的最小节点
     *
     * @param node 树根节点
     * @return 最小节点
     */
    private Node<T> minNode(Node<T> node) {
        Node<T> min = node;
        while (min.left != NIL) {
            min = min.left;
        }

        return min == NIL ? null : min;
    }

    /**
     * 获取某节点为根的树的最大元素
     *
     * @param node 树根节点
     * @return 最大元素
     */
    private T maxValue(Node<T> node) {
        Node<T> max = maxNode(node);
        if (max != null) {
            return max == NIL ? null : max.value;
        } else {
            return null;
        }
    }

    /**
     * 获取某节点为根的树的最大节点
     *
     * @param node 树根节点
     * @return 最大节点
     */
    private Node<T> maxNode(Node<T> node) {
        Node<T> max = node;
        while (max.right != NIL) {
            max = max.right;
        }
        return max == NIL ? null : max;
    }

    /**
     * 左旋以node节点为根的子树
     * 1:将rightChild的左子树作为node的右子树
     * 2:将rightChild作为新的根节点
     * 3:将node作为rightChild的左孩子
     *
     * @param node 根节点
     */
    private void leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left == NIL) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node.parent.left == node) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    /**
     * 右旋以node节点为根的子树
     * 1:将leftChild的右子树作为node的左子树
     * 2:将leftChild作为新的根节点
     * 3:将node作为leftChild的右孩子
     *
     * @param node 根节点
     */
    private void rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != NIL) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node.parent.left == node) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    /**
     * 插入元素
     *
     * @param value 元素的值
     * @return 返回是否成功插入
     */
    public boolean insert(T value) {
        //默认每次都是插入红色节点
        Node<T> node = new Node<T>(value, null, NIL, NIL, Color.RED);
        Node<T> pointer = root;
        boolean inserted = false;
        //遍历插入
        while (!inserted) {
            if (root == null) {
                root = node;
                inserted = true;
            } else if (node.value.compareTo(pointer.value) > 0) {//向右子树查找
                if (pointer.right == NIL) {
                    node.parent = pointer;
                    pointer.right = node;
                    inserted = true;
                } else {
                    pointer = pointer.right;
                }
            } else if (node.value.compareTo(pointer.value) < 0) {//向左子树查找
                if (pointer.left == NIL) {
                    node.parent = pointer;
                    pointer.left = node;
                    inserted = true;
                } else {
                    pointer = pointer.left;
                }
            } else {//相等了
                return false;
            }
        }

        size++;
        insertFixUp(node);//调整树
        return true;
    }

    /**
     * 插入修复（调整树以满足红黑树的性质）
     *
     * @param node 新插入的节点
     */
    private void insertFixUp(Node<T> node) {
        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        //父节点为黑色，无需调整
        if (node.parent.color == Color.BLACK) {
            return;
        }

        Node<T> uncle = uncle(node);
        Node<T> grandParent = grandParent(node);

        if (grandParent != null) {
            //父节点及叔叔节点为红色
            if (uncle != null && uncle.color == Color.RED) {
                //将parent和uncle颜色置为黑色
                node.parent.color = Color.BLACK;
                uncle.color = Color.BLACK;
                //将grandParent置为红色
                grandParent.color = Color.RED;
                insertFixUp(grandParent);
            } else {//父节点是红色而叔叔节点为黑色或者叔叔节点不存在
                if (node == node.parent.right && node.parent == grandParent.left) {//node为父节点的右孩子，父节点为祖父节点的左孩子
                    //以父节点左旋
                    leftRotate(node.parent);
                    node = node.left;
                } else if (node == node.parent.left && node.parent == grandParent.right) {//node为父节点的左孩子，父节点为祖父节点的右孩子
                    //以父节点右旋
                    rightRotate(node.parent);
                    node = node.right;
                }
                node.parent.color = Color.BLACK;//将parent颜色置为黑色
                grandParent.color = Color.RED;

                if (node == node.parent.left && node.parent == grandParent.left) {//node为父节点左孩子，父节点为祖父节点的左孩子
                    //以祖父节点右旋
                    rightRotate(grandParent);
                } else {//node为父节点的右孩子，父节点为祖父节点的右孩子
                    //以祖父节点左旋
                    leftRotate(grandParent);
                }
            }
        }
    }

    /**
     * 删除节点
     *
     * @param value 要删除的节点的值
     * @return 是否成功删除
     */
    public boolean remove(T value) {
        boolean removed = false;
        Node<T> node = getNode(value);
        Node<T> replace = null;//用于替换节点node
        Node<T> child = null;//后继节点next的孩子节点

        if (node != null) {
            if (node.left == NIL || node.right == NIL) {//若最多有一个非空孩子
                replace = node;
            } else {//若有两个非空孩子
                replace = locateNextNode(node);
            }

            //获取替换节点replace的孩子，有可能为NIL
            child = replace.left != null ? replace.left : replace.right;
            //删除节点replace，连接replace父节点-->child
            child.parent = replace.parent;
            if (replace.parent == null) {//根节点
                root = child;
            } else if (replace == replace.parent.left) {//replace为其父节点的左孩子
                replace.parent.left = child;
            } else {//replace为其父节点的右孩子
                replace.parent.right = child;
            }

            //替换node节点的值为replace节点的值
            if (replace != node) {
                node.value = replace.value;
            }

            //若后继节点为黑色，则需要做调整，因为删除红色replace节点对红黑树性质无影响
            if (replace.color == Color.BLACK) {
                removeFixUp(child);
            }

            removed = true;
        }
        return removed;
    }

    /**
     * 删除修复（调整树以满足红黑树的性质）
     *
     * @param node 删除节点的后继节点的孩子
     */
    private void removeFixUp(Node<T> node) {
        while (node != root && node.color == Color.BLACK) {
            if (node == node.parent.left) {//node为其父节点的左孩子
                Node<T> rightBrother = rightBrother(node);
                if (rightBrother.color == Color.RED) {//右兄弟为红色
                    rightBrother.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    leftRotate(node.parent);//以父节点左旋
                    rightBrother = node.parent.right;
                }
                //右兄弟的两个孩子都为黑色
                if (rightBrother.left.color == Color.BLACK && rightBrother.right.color == Color.BLACK) {
                    rightBrother.color = Color.RED;
                    node = node.parent;
                } else if (rightBrother.right.color == Color.BLACK) {//右兄弟右孩子为黑色
                    rightBrother.left.color = Color.BLACK;
                    rightBrother.color = Color.RED;
                    rightRotate(rightBrother);
                    rightBrother = node.parent.right;
                } else {//右兄弟右孩子为红色或其他情况，比如为空叶子节点NIL
                    rightBrother.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    rightBrother.right.color = Color.BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {//node为其父节点的右孩子
                Node<T> leftBrother = leftBrother(node);
                if (leftBrother.color == Color.RED) {//左兄弟为红色
                    leftBrother.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rightRotate(node.parent);
                    leftBrother = node.parent.left;
                }
                //左兄弟的左孩子和右孩子都为黑色
                if (leftBrother.left.color == Color.BLACK && leftBrother.right.color == Color.BLACK) {
                    leftBrother.color = Color.RED;
                    node = node.parent;
                } else if (leftBrother.left.color == Color.BLACK) {//仅左兄弟左孩子为黑色
                    leftBrother.color = Color.RED;
                    leftBrother.right.color = Color.BLACK;
                    leftRotate(leftBrother);
                    leftBrother = node.parent.left;
                } else {//左兄弟左孩子为红色或其他情况
                    leftBrother.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    leftBrother.left.color = Color.BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            }
        }

        node.color = Color.BLACK;
    }

    /**
     * 寻找node的替代节点（规则是右分支的最左边和左分支的最右边节点）
     *
     * @param node 当前节点
     * @return 当前节点的替代节点
     */
    private Node<T> locateNextNode(Node<T> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {//选择右子树的最左子树
            Node<T> t = node.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        } else {//选择左子树的最右子树
            Node<T> t = node.parent;
            Node<T> p = node;
            while (t != null && p == t.right) {
                p = t;
                t = t.parent;
            }
            return t;
        }
    }

    /**
     * 根据元素值获取节点
     *
     * @param value 节点的值
     * @return 返回节点
     */
    private Node<T> getNode(T value) {
        Node<T> node = root;
        while (node != null) {
            int cmp = value.compareTo(node.value);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 获取节点的左兄弟
     *
     * @param node 当前节点
     * @return 该节点的左兄弟
     */
    private Node<T> leftBrother(Node<T> node) {
        return node == null ? null : (node.parent == null ? null : node.parent.left);
    }

    /**
     * 获取节点的右兄弟
     *
     * @param node 当前节点
     * @return 该节点的右兄弟
     */
    private Node<T> rightBrother(Node<T> node) {
        return node == null ? null : (node.parent == null ? null : node.parent.right);
    }

    /**
     * 节点类
     *
     * @param <E> 泛型
     */
    private static class Node<E> {
        E value;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        Color color;

        Node(E value, Node<E> parent, Node<E> left, Node<E> right, Color color) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    /**
     * 节点颜色
     */
    private enum Color {
        RED, BLACK
    }
}
