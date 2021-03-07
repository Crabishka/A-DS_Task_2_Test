import java.util.*;

public class SimpleLinkedList<T extends Comparable<? super T>> implements Iterable<T> {


    /*
     * по-хорошему тут надо удалить половину методов
     * и реализовать List<T>
     */

    private class ListNode<T extends Comparable<? super T>> implements Comparable<ListNode<T>> {  // wildcards
        public T value;
        public ListNode<T> next;

        public ListNode(T value, ListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        public ListNode(T value) {
            this(value, null);
        }

        public ListNode() {
            this(null);
        }


        @Override
        public int compareTo(ListNode<T> o) {
            return this.value.compareTo(o.value);  // ПОЧЕМУ ЭТО РАБОТАЕТ
            //    return this.value - o.value;
            //    return (this.value < o.value) ? -1 : ((this.value == o.value) ? 0 : 1);
            //     return Integer.parseInt(this.value.toString()) - Integer.parseInt(o.value.toString());
        }


    }

    private ListNode<T> head = null;
    private ListNode<T> tail = null;

    private int count = 0;

    public void addFirst(T value) {
        head = new ListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (count > 0) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        count++;
    }

    private void emptyError() throws Exception {
        if (count == 0) {
            throw new Exception("List is empty");
        }
    }

    public T getFirst() throws Exception {
        emptyError();
        return head.value;
    }

    public T getLast() throws Exception {
        emptyError();
        return tail.value;
    }

    private ListNode<T> getNode(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Wrong index");
        }
        ListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index) throws Exception {
        return getNode(index).value;
    }

    public void removeFirst() throws Exception {
        emptyError();
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
    }

    public void removeLast() throws Exception {
        emptyError();
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            tail = getNode(count - 1);
            tail.next = null;
        }
    }

    public void clear() {
        head = tail = null;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<T> asArrayList() {
        ArrayList<T> list = new ArrayList<>();
        ListNode<T> curr = head;
        while (curr != null) {
            list.add(curr.value);
            curr = curr.next;
        }
        return list;
    }

    public int[] asArray() {
        int[] a = new int[this.count];
        ListNode<T> curr = head;
        for (int i = 0; i < count; i++) {
            a[i] = Integer.parseInt(curr.value.toString());
            curr = curr.next;

        }
        return a;
    }

    public void insertionSort() {

//        class SortByValue implements Comparator<ListNode<T>> {  // ладно
//            public int compare(ListNode<T> o1, ListNode<T> o2) {
//                return Integer.parseInt(o1.value.toString()) - Integer.parseInt(o2.value.toString());
//            }
//        }

//        SortByValue sort = new SortByValue();


        ListNode<T> sortedHead = null; // указатель на второй список // переопределить после сортировки
        ListNode<T> main = head;
        while (main != null) {
            ListNode<T> temp = main.next;
            if (sortedHead == null || sortedHead.compareTo(main) > 0) { // обработка для первого или нулевого элемента
                main.next = sortedHead;                                     // так как мы не можем вставить элемент между null и элементом без частного случая
                sortedHead = main;
            } else { // main - текущий в общем списке, current - текущий в побочном // невероятно
                ListNode<T> current = sortedHead;  // проходим
                while (current.next != null && current.next.compareTo(main) < 0) {
                    current = current.next;
                }

                main.next = current.next;
                current.next = main;

            }
            main = temp;
        }


        head = sortedHead;


    }

    public void sortByArray() {


    }

    /*
        public void sort() {
            int count = this.count;
            int[] a = new int[count];
            int i = 0;
            for (T number : this) {
                a[i] = Integer.parseInt(number.toString());
                i++;
            }
            Arrays.sort(a);
            this = arrayToIntLinkedList(a);
        }


        public static SimpleLinkedList<Integer> arrayToIntLinkedList(int[] a) {
            SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
            for (int number : a) {
                list.addLast(number);
            }
            return list;
        }

    */
    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            ListNode<T> curr;

            public LinkedListIterator(ListNode<T> head) {
                curr = head;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T result = curr.value;
                curr = curr.next;
                return result;
            }
        }

        return new LinkedListIterator(head);
    }


}


