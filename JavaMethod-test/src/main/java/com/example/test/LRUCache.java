package com.example.test;

import java.util.HashMap;

class LRUCache {
    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final DoubleLinkedList cache;
    private int size;

    /**
     * 构造方法，初始化LRU缓存。
     *
     * @param capacity 缓存容量，即缓存中最多可以存储的键值对数量
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.cache = new DoubleLinkedList();
        this.size = 0;
    }

    /**
     * 获取指定键的值。
     *
     * @param key 需要获取值的键
     * @return 如果缓存中存在该键，则返回对应的值；否则返回-1
     */
    public int get(int key) {
        // 检查缓存中是否包含指定键
        if (!map.containsKey(key)) {
            return -1;
        }
        // 从缓存中获取节点
        Node node = map.get(key);
        // 将节点移到链表末尾
        cache.moveToEnd(node); // 将节点移到链表末尾
        // 返回节点的值
        return node.value;
    }



    /**
     * 向缓存中添加或更新键值对。
     *
     * @param key   需要添加的键
     * @param value 与键对应的值
     */
    public void put(int key, int value) {
        // 如果缓存中已经存在该键
        if (map.containsKey(key)) {
            // 获取该键对应的节点
            Node node = map.get(key);
            // 更新节点的值
            node.value = value;
            // 将节点移到链表末尾
            cache.moveToEnd(node); // 更新后移到链表末尾
        } else {
            // 创建新节点
            Node newNode = new Node(key, value);
            // 如果缓存已满
            if (size == capacity) {
                // 移除链表头部节点
                Node removed = cache.removeFirst(); // 移除链表头部节点
                // 从缓存映射中移除该节点
                map.remove(removed.key);
                // 缓存大小减一
                size--;
            }
            // 将新节点添加到链表末尾
            cache.addLast(newNode); // 添加新节点到链表末尾
            // 将新节点添加到缓存映射中
            map.put(key, newNode);
            // 缓存大小加一
            size++;
        }
    }


    private static class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            // 设置节点的键
            this.key = key;
            // 设置节点的值
            this.value = value;
        }
    }

    private static class DoubleLinkedList {
        private final Node head, tail;
        DoubleLinkedList() {
            // 创建头节点，key和value都设置为0
            head = new Node(0, 0);
            // 创建尾节点，key和value都设置为0
            tail = new Node(0, 0);
            // 将头节点的next指向尾节点
            head.next = tail;
            // 将尾节点的prev指向头节点
            tail.prev = head;
        }

        public void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }
        public void moveToEnd(Node node) {
            remove(node);
            addLast(node);
        }
        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}
