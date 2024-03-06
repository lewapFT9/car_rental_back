package pl.project.car_rental.Rent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    private int id;
    private int clientId;
    private String clientName;
    private String clientSurname;
    private double clientPesel;
    private int carId;
    private int rentTime;
}
