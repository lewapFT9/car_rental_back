package pl.project.car_rental.Rent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.project.car_rental.Car.Car;
import pl.project.car_rental.Car.CarRepository;
import pl.project.car_rental.Client.Client;
import pl.project.car_rental.Client.ClientRepository;

import java.util.List;

@Repository
public class RentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CarRepository carRepository;
    @Autowired
    ClientRepository clientRepository;

    private static final String RENT = "INSERT INTO rents(client_id, client_name, client_surname, client_pesel, car_id, rent_time) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String ADD_CLIENT = "INSERT INTO Client(name, surname, pesel) VALUES(?, ?, ?)";
    private static final String GET_ALL_RENTS = "SELECT id, client_id, client_name, client_surname, client_pesel, car_id, rent_time FROM rents";
    private static final String GET_RENT_BY_ID = "SELECT id, client_id, client_name, client_surname, client_pesel, car_id, rent_time FROM rents WHERE id=?";
    private static final String UPDATE_CLIENT = "UPDATE Client SET name=?, surname=?, pesel=? WHERE id=?";
    private static final String UPDATE_RENT_INFO = "UPDATE rents SET client_name=?, client_surname=?, client_pesel=?, car_id=?, rent_time=? WHERE client_id=?";


    public String rentCarR(Rent rent) {
        jdbcTemplate.update(ADD_CLIENT, rent.getClientName(),
                rent.getClientSurname(), rent.getClientPesel());
        Client client = clientRepository.getClientId(rent.getClientName(),
                rent.getClientSurname());
        jdbcTemplate.update(RENT, client.getId(), rent.getClientName(), rent.getClientSurname(),
                rent.getClientPesel(), rent.getCarId(), rent.getRentTime());
        Car car = carRepository.getById(rent.getCarId());
        carRepository.addCarToRented(car);
        carRepository.deleteCar(car.getId());
        return "Rent succeded";
    }

    public List<Rent> getAllRents() {
        return jdbcTemplate.query(GET_ALL_RENTS, BeanPropertyRowMapper.newInstance(Rent.class));
    }

    public Rent getRentById(int id) {
        return jdbcTemplate.queryForObject(GET_RENT_BY_ID, BeanPropertyRowMapper.newInstance(Rent.class), id);
    }


    public void updateClient(Rent rent) {
        jdbcTemplate.update(UPDATE_CLIENT, rent.getClientName(), rent.getClientSurname(),
                rent.getClientPesel(), rent.getClientId());
    }

    public void updateRent(Rent rent){
        jdbcTemplate.update(UPDATE_RENT_INFO, rent.getClientName(),rent.getClientSurname(), rent.getClientPesel(),
                rent.getCarId(),rent.getRentTime(), rent.getClientId());
    }

}

