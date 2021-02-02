import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("김개똥");
        helloLombok.setAge(10);

        System.out.println(helloLombok.name);
        System.out.println(helloLombok.age);
        System.out.println(helloLombok);
    }
}
