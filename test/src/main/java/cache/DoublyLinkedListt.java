package cache;

/**
 * @author harish.kumar-mbp
 * createdOn 12/01/24
 */
public class DoublyLinkedListt {
    Node head;
    Node tail;

    public DoublyLinkedListt() {
        this.head = null;
        this.tail = null;
    }

    public void updateHead(Node dNode) {
        if(this.head == null){
            this.head = dNode;
            this.tail = head;
        }
        dNode.prevNode = null;
        dNode.nextNode = this.head;
        head.prevNode = dNode;

        head = dNode;
    }

    public void removeTail() {
        if(this.tail == null)
            return;
        if(this.tail == this.head){
            this.tail = null;
            this.head = null;
        }
        Node node = tail.prevNode;
        this.tail = node;
        this.tail.nextNode = null;
    }
}

class Node{
    String val;
    String key;
    Node prevNode;
    Node nextNode;

    public Node(String key, String val) {
        this.val = val;
        this.key = key;
        this.prevNode = null;
        this.nextNode = null;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
