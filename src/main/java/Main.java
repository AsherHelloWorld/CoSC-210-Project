public class Main {
    public static void main(String[] args){

        Task test = new Task("Charlie", "Monday", 2, "This is a description", "YMH");

        System.out.println(test.getName());
        System.out.println(test.getDate());
        System.out.println(test.getDescription());
    }
}