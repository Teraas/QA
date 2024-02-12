package cache;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author harish.kumar-mbp
 * createdOn 12/01/24
 */
public class LRUCacheDLinkedList {
    public static void main(String[] args){
        LRUCache cache = new LRUCache(4);
        cache.insertKeyValuePair("a", 1);
        cache.insertKeyValuePair("b", 2);
        cache.insertKeyValuePair("c", 3);
        cache.insertKeyValuePair("d", 4);
        cache.getValueFromKey("a");
        cache.insertKeyValuePair("e", 5);
        cache.getValueFromKey("a");
        cache.getValueFromKey("b");
        cache.getValueFromKey("c");
        cache.insertKeyValuePair("f", 5);
        cache.getValueFromKey("c");
        cache.getValueFromKey("d");
        cache.insertKeyValuePair("g", 5);
        cache.getValueFromKey("e");
        cache.getValueFromKey("a");
        cache.getValueFromKey("c");
        cache.getValueFromKey("f");
        cache.getValueFromKey("g");

        Queue<String> q = new ArrayDeque<>();

    }

        static class LRUCache {
            // use a HashMap to get keys in O(1)
            // for Cache LRU use Map with DoublyLinkedList to keep recently used in order
            int maxSize;
            int currentSize;
            Map<String, DNode> cache = new HashMap<>();
            DoublyLinkedList dList;

            public LRUCache(int maxSize) {
                this.maxSize = maxSize;
                dList = new DoublyLinkedList();
            }

            public void insertKeyValuePair(String key, int value) {
                System.out.println(" inserting - key =" + key + " value=" + value + " and maxsize is " + this.maxSize + " current size is " + currentSize);
                if (!cache.containsKey(key)) { // check already not exists
                    if (currentSize < maxSize) {
                        cache.put(key, new DNode(key, value));
                        // update the recently used

                        currentSize++;

                    } else {
                        // evict the leastRecent
                        removeLeastRecentFromCache();
                        // now add to cache
                        insertKeyValuePair(key, value);
                    }
                } else {
                    replaceKey(key, value);
                }
                updateMostRecentKey(cache.get(key));


            }

            private void replaceKey(String key, int value) {
                DNode node = cache.get(key);
                node.val = value;
                cache.put(key, node);
            }

            public LRUResult getValueFromKey(String key) {
                if (!cache.containsKey(key)) {
                    return new LRUResult(false, 0);
                } else {
                    int res = cache.get(key).val;
                    // delete the current node from position
                    dList.updateNodeConnection(cache.get(key));
                    updateMostRecentKey(cache.get(key)); // set new head
                    return new LRUResult(true, res);
                }

            }

            public String getMostRecentKey() {
                // Write your code here.
                if (this.dList.head == null)
                    return "NA";

                return this.dList.head.key;
            }

            public void updateMostRecentKey(DNode node) {
                // Write your code here.
                dList.updateHead(node);
            }

            public void removeLeastRecentFromCache() {
                if(this.dList.tail == null)
                    return;
                DNode tail = dList.tail;
                String key = tail.key;
                dList.removeTail();
                cache.remove(key);
                this.currentSize--;

            }
        }
    static class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }


    static class DoublyLinkedList {
        DNode head;
        DNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void updateHead(DNode dNode) {
            if (this.head == null) {
                this.head = dNode;
                this.tail = head;
            }
            else if(head == tail) {
                head = dNode;
                tail.prevNode = dNode;
                head.nextNode = tail;
            }
            else{

            dNode.nextNode = this.head;
            head.prevNode = dNode;
                head = dNode;
        }

        }

        public void removeTail() {
            if(this.tail == null)
                return;
            else if(this.tail == this.head){
                this.tail = null;
                this.head = null;
            }
            else{
                DNode node = tail.prevNode;
                this.tail = node;
                this.tail.nextNode = null;
            }

        }

        public void updateNodeConnection(DNode dNode) {
            DNode prev = dNode.prevNode;
            DNode next = dNode.nextNode;
            if(prev == null){

            }

            prev.nextNode = next;
            next.prevNode = prev;
        }
    }

    static class DNode{
        int val;
        String key;
        DNode prevNode;
        DNode nextNode;

        public DNode(String key, int val) {
            this.val = val;
            this.key = key;
            this.prevNode = null;
            this.nextNode = null;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public DNode getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(DNode prevNode) {
            this.prevNode = prevNode;
        }

        public DNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(DNode nextNode) {
            this.nextNode = nextNode;
        }
    }
}
