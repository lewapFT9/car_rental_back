package pl.project.car_rental.Car;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractCar {
    protected int id;
    protected String brand;
    protected String model;
    protected String color;
    protected int priceforday;


}
