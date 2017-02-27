package com.hy;

/**
 * Created by huangyong on 2017/1/18.
 * 自顶向下伸展树
 */
public class SplayTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;
    private BinaryNode<T> nullNode;
    private BinaryNode<T> header = new BinaryNode<>(null);
    private BinaryNode<T> newNode = null;

    private SplayTree(){
        nullNode = new BinaryNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        root = nullNode;
    }

    public static void main(String[] args) {
        SplayTree<Integer> splayTree = new SplayTree<>();
        final int NUMS = 40000;
        final int GAP = 307;

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            splayTree.insert( i );
        System.out.println( "Inserts complete" );

        for( int i = 1; i < NUMS; i += 2 )
            splayTree.remove( i );
        System.out.println( "Removes complete" );

        if( splayTree.findMin( ) != 2 || splayTree.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i += 2 )
            if( !splayTree.contains( i ) )
                System.out.println( "Error: find fails for " + i );

        for( int i = 1; i < NUMS; i += 2 )
            if( splayTree.contains( i ) )
                System.out.println( "Error: Found deleted item " + i );
    }

    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T element) {
            this(element, null, null);
        }

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 左旋转
     *
     * @param k2 左旋转的根节点
     * @return 返回旋转过后产生的新的根节点
     */
    private BinaryNode<T> rotateWithLeftChild(BinaryNode<T> k2) {
        BinaryNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * 右旋转
     *
     * @param k1 右旋转的根节点
     * @return 返回旋转后产生的新的根节点
     */
    private BinaryNode<T> rotateWithRightChild(BinaryNode<T> k1) {
        BinaryNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    //将一个新节点插入到伸展树中
    private void insert(T x) {
        if (newNode == null) {
            newNode = new BinaryNode<>(null);
        }
        newNode.element = x;

        if (root == newNode) {
            newNode.left = newNode.right = null;
            root = newNode;
        } else {
            root = splay(x, root);
            if (x.compareTo(root.element) < 0) {
                newNode.left = root.left;
                newNode.right = root;
                root.left = nullNode;
                root = newNode;
            } else if (x.compareTo(root.element) > 0) {
                newNode.right = root.right;
                newNode.left = root;
                root.right = nullNode;
                root = newNode;
            }
        }
        newNode = null;
    }

    //从伸展树中移除一个节点
    private void remove(T x) {
        BinaryNode<T> newTree;
        //如果找到了x节点，那么x肯定位于根节点位置
        root = splay(x, root);

        if (root.element.compareTo(x) != 0) {
            return;
        }

        if (root.left == nullNode) {
            newTree = root.right;
        } else {
            //在左子树中寻找最大节点，并展开到根节点位置
            //然后将原根节点的右孩子链接到新展开的根节点的右孩子上
            newTree = root.left;
            newTree = splay(x, newTree);
            newTree.right = root.right;
        }
        root = newTree;
    }

    //查找伸展树中的最小节点
    private T findMin() {
        if (isEmpty())
            System.out.println("所查找的树为空树，查找失败！");
        BinaryNode<T> node = root;
        while (node.left != nullNode) {
            node = node.left;
        }
        root = splay(node.element, root);
        return node.element;
    }

    //查找伸展树中的最大节点
    private T findMax() {
        if (isEmpty())
            System.out.println("所查找的树为空树，查找失败！");
        BinaryNode<T> node = root;
        while (node.right != nullNode) {
            node = node.right;
        }
        root = splay(node.element, root);
        return node.element;
    }

    //判断伸展树中是否包含某个节点
    private boolean contains(T x) {
        if (isEmpty())
            return false;
        root = splay(x, root);
        return x.compareTo(root.element) == 0;
    }

    //判断树是否为空
    private boolean isEmpty() {
        return root == nullNode;
    }

    //清空伸展树
    private void makeEmpty() {
        root = nullNode;
    }

    /**
     * 自顶向下展开方法，最后访问到的节点成为新的根节点
     *
     * @param x 需要展开查找的目标节点
     * @param t 当前展开树的根节点（也为当前展开树）
     * @return 返回展开后的一个新的子树
     */
    private BinaryNode<T> splay(T x, BinaryNode<T> t) {
        BinaryNode<T> leftTreeMax, rightTreeMin;

        header.left = header.right = nullNode;
        leftTreeMax = rightTreeMin = header;

        nullNode.element = x;

        for (; ; ) {
            int compareResult = x.compareTo(t.element);
            if (compareResult < 0) {
                if (x.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild(t);
                }
                if (t.left == nullNode)
                    break;
                //将大于 x 的节点链接到右树
                rightTreeMin.left = t;
                rightTreeMin = t;
                //将原根节点的左海子设为新的根节点
                t = t.left;
            } else if (compareResult > 0) {
                if (x.compareTo(t.right.element) > 0) {
                    t = rotateWithRightChild(t);
                }
                if (t.right == nullNode)
                    break;
                //将小于 x 的节点链接到左树中
                leftTreeMax.right = t;
                leftTreeMax = t;
                //将原根节点的右孩子设为新的根节点
                t = t.right;
            } else {
                break;
            }
        }

        //重组，将 M 树原来的左孩子设为leftTreeMax的右孩子，将 M 树原来的右孩子设为rightTreeMin的左孩子
        leftTreeMax.right = t.left;
        rightTreeMin.left = t.right;
        //将 M 树的左孩子设为 L 树的根节点，将 M 树的右孩子设为 R 树的根节点
        t.left = header.right;
        t.right = header.left;
        //返回展开后的树
        return t;
    }
}
