package single_linked;

public class SingleLinkedDemo {

    public static void main(String[] args) {
        SingLinkedList list1 = new SingLinkedList();
        HeroNode node1 = new HeroNode(1);
        HeroNode node2 = new HeroNode(5);
        HeroNode node3 = new HeroNode(3);
        HeroNode node4 = new HeroNode(8);
        list1.add(node1);
        list1.add(node2);
        list1.add(node3);
        list1.add(node4);
        list1.list();


        SingLinkedList list2 = new SingLinkedList();
        HeroNode node5 = new HeroNode(2);
        HeroNode node6 = new HeroNode(7);
        HeroNode node7 = new HeroNode(4);
        list2.add(node5);
        list2.add(node6);
        list2.add(node7);
        list2.list();

        System.out.println("*******************");
        list1.addList(list2.head);
        list1.list();

    }


}

class SingLinkedList {
    public HeroNode head = new HeroNode(0);

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            }
            temp = temp.next;

        }
        temp.next = heroNode;
    }

    public void addList(HeroNode heroNode) {
        HeroNode temp = head;

        while (true){
            if (heroNode.next == null){
                break;
            }
            HeroNode temp2 = heroNode.next;
            HeroNode next = temp2.next;
            if (temp.next.no > temp2.no) {
                temp2.next = temp.next;
                temp.next = temp2;
                break;
            }
        }
        temp = temp.next;

    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        return "SingLinkedList{" +
                "head=" + head +
                '}';
    }
}

class HeroNode {
    public int no;
    public HeroNode next;

    public HeroNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                '}';
    }
}
