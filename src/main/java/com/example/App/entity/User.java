package com.example.App.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    // Під час серіалізації, середовище виконання
    // Java створює номер версії для класу (SerialVersionUID),
    // так що воно може десереалізувати його пізніше.
    // Якщо під час десеріалізації, SerialVersionUID не відповідний,
    // процес завершиться з винятком InvalidClassException в потоці main.
    //
    // serialVersionUID — універсальний ідентифікатор версії
    // для Serializable класу. Десеріалізація використовує це число,
    // щоб гарантувати, що завантажений клас точно відповідає
    // серіалізованому об’єкту.
    // Якщо відповідності не знайдено, створюється виняткова ситуація
    // InvalidClassException.
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // nullable = false - поле не може бути null
    @Column(nullable = false)
    private String name;
    // unique = true - поле має бути унікальним
    // у таблиці бази даних
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    // @ManyToMany вказує, що зв’язок між сутностями
    // User та Role є «багато-до-багатьох».
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytomany
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable визначає деталі таблиці об’єднання (Join table),
    // яка використовується для реалізації зв’язку «багато-до-багатьох»
    // між сутностями User та Role.
    // Визначає назву таблиці об’єднання (user_roles) і імена стовпців
    // зовнішнього ключа (foreign key) в таблиці об’єднання (user_id та role_id).
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/jointable
    @JoinTable(
            // Найменування таблиці об’єднання (Join table).
            // Буде створюватись програмою.
            name = "user_roles",
            // @JoinColumn позначає стовпець як стовпець об’єднання
            // для асоціації сутності або колекції елементів.
            // joinColumns використовується для встановлення посилання
            // на сутність User, а inverseJoinColumns посилається на
            // сутність Role.
            // З точки зору User, стовпець id таблиці users матиме
            // зв’язок зовнішнього ключа зі стовпцем user_id таблиці user_roles.
            // Атрибут inverseJoinColumns(role_id) посилається на іншу сторону User,
            // яка є сутністю Role.
            // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
            joinColumns = {
                    @JoinColumn(
                            // Стовпчик таблиці user_roles
                            name = "user_id",
                            // Стовпчик таблиці users
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            // Стовпчик таблиці user_roles
                            name = "role_id",
                            // Стовпчик таблиці roles
                            referencedColumnName = "id"
                    )
            }
    )
    private List<Role> roles = new ArrayList<>();

}
