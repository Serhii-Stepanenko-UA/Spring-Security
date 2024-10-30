package com.example.App.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    // @ManyToMany вказує, що зв’язок між сутностями
    // User та Role є «багато-до-багатьох».
    // Значенням mappedBy є ім’я атрибута відображення
    // асоціації на стороні власника (owner) (тут, User).
    // Тобто, в User має бути властивість (змінна) roles.
    // Сутність Role, при цьому, є зворотньою (inverse)
    // або посилальною (referencing) стороною, яка
    // співставляється (map) до володіючої (owning) сторони.
    // Таким чином утворюється двонаправлена (bidirectional)
    // асоціація між сторонами/сутностями User та Role.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytomany
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
