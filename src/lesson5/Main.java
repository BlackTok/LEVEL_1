package lesson5;

public class Main {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];

        persArray[0] = new Person("Иванов Иван Иванович","Сантехник","ivanov@mail.ru","1100555",30000,45);
        persArray[1] = new Person("Петров Петр Петрович","Слесарь КИПиА","petrov@mail.ru","2200555",45000,32);
        persArray[2] = new Person("Максимов Максим Максимович","Электрик","imaksimov@mail.ru","3300555",33000,38);
        persArray[3] = new Person("Семенов Семен Семенович","Охранник","semenov@mail.ru","4400555",30000,53);
        persArray[4] = new Person("Ильин Илья Ильич","Инженер","ilin@mail.ru","5500555",52000,37);

        for (int i = 0; i < persArray.length; i++) {
            if (persArray[i].getAge() > 40) {
                persArray[i].printInfo();
                System.out.println();
            }
        }
    }
}
