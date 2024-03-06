package pl.project.car_rental.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String GET_ALL = "SELECT id, brand, model, color, priceforday FROM car";
    private static final String GET_BY_ID = "SELECT id, brand, model, color, priceforday FROM car WHERE id=?";
    private static final String GET_BY_ID_FROM_RENTED = "SELECT id, brand, model, color, priceforday FROM rented_car WHERE id=?";
    private static final String ADD_CAR = "INSERT INTO car(brand, model, color, priceforday) VALUES(?, ?, ?, ?)";
    private static final String ADD_CAR_FROM_RENTED = "INSERT INTO car(id, brand, model, color, priceforday) VALUES(?, ?, ?, ?, ?)";
    private static final String ADD_CAR_TO_RENTED = "INSERT INTO rented_car(id, brand, model, color, priceforday) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_CAR = "UPDATE car SET brand=?,model=?, color=?, priceforday=? WHERE id=?";
    private static final String DELETE_CAR = "DELETE FROM car WHERE id=?";
    private static final String DELETE_CAR_FROM_RENTED = "DELETE FROM rented_car WHERE id=?";

    public List<Car> getAll() {
        return jdbcTemplate.query(GET_ALL,
                BeanPropertyRowMapper.newInstance(Car.class));
    }

    public Car getById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID,
                BeanPropertyRowMapper.newInstance(Car.class), id);
    }

    public Car findRentedCar(int id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_FROM_RENTED, BeanPropertyRowMapper.newInstance(Car.class), id);
    }


    public String addCar(Car car) {
        jdbcTemplate.update(ADD_CAR, car.getBrand(),
                car.getModel(), car.getColor(), car.getPriceforday());
        return "car " + car.getBrand() + " " + car.getModel() + " added";
    }


    public void addCarFromRented(Car car) {
        jdbcTemplate.update(ADD_CAR_FROM_RENTED,car.getId(), car.getBrand(),
                car.getModel(), car.getColor(), car.getPriceforday());
    }

    public void addCarToRented(Car car) {
        jdbcTemplate.update(ADD_CAR_TO_RENTED, car.getId(), car.getBrand(),
                car.getModel(), car.getColor(), car.getPriceforday());
    }

    public String updateCar(Car car) {
        String model = car.getModel();
        String brand = car.getBrand();
        jdbcTemplate.update(UPDATE_CAR, car.getBrand(),
                car.getModel(), car.getColor(), car.getPriceforday(), car.getId());
        return "car " + brand + " " + model + " updated";
    }

    public String deleteCar(int id) {
        Car car = getById(id);
        String model = car.getModel();
        String brand = car.getBrand();
        jdbcTemplate.update(DELETE_CAR, id);
        return "car " + brand + " " + model + " deleted";
    }


    public void deleteCarFromRented(int id) {
        jdbcTemplate.update(DELETE_CAR_FROM_RENTED, id);
    }



}
