package pl.project.car_rental.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private int id;
    private String name;
    private String surname;
    private long pesel;
}


