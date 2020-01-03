import java.net.SocketOption;

public class IDLListTest {

    public static void main(String[] args) {
        IDLList<Character> check = new IDLList<Character>();
        check.add('3');
        check.add(0, 'a');
        check.add(1, 'b');
        check.append('d');
        check.append('L');
        check.add(2,'d');
        check.add(5,'d');
        check.add('5');
        System.out.println("First Linked List: ");
        String s1 = check.toString();
        System.out.println(s1);

        System.out.println("\nError checking for Add");
        try {
            check.add(50, 's');
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index out of bounds");
        }
        try {
            check.add(-1, 'a');
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index out of bounds");
        }

        System.out.println("\nError check for Get");
        try {
            check.get(50);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index out of bounds");
        }
        try {
            check.get(-1);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index out of bounds");
        }

        System.out.println("\nError check for RemoveAt");
        try {
            check.removeAt(50);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index out of bounds");
        }

        try {
            check.removeAt(-1);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index out of bounds");
        }
        System.out.println("");

        char Head = check.getHead();
        System.out.println("Head element is : " + Head);
        char Last = check.getLast();
        System.out.println("Last element is : " + Last);
        char atIndex = check.get(2);
        System.out.println("Element at given index is : " + atIndex);
        int size = check.size();
        System.out.println("Size is : " + size);
        check.add(size, 'x');
        System.out.println("Linked List: ");
        String s2 = check.toString();
        System.out.println(s2);
        check.removeAt(2);
        check.remove();
        check.removeLast();
        boolean removed = check.remove('d');
        System.out.println("Status of remove : " + removed);
        System.out.println("Final Linked List: ");
        String s = check.toString();
        System.out.println(s);
    }
}
