package pl.project.car_rental.Rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.car_rental.Car.Car;
import pl.project.car_rental.Car.CarRepository;


import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/rent")
public class RentControler {
    @Autowired
    RentRepository rentRepository;
    @Autowired
    CarRepository carRepository;

    @GetMapping("")
    public List<Rent> displaRents(){
        return rentRepository.getAllRents();
    }


    @GetMapping("/{id}")
    public Rent getRentById(@PathVariable("id") int id){
        return rentRepository.getRentById(id);
    }


    @PostMapping("/getrent")
        public String rentCar(@RequestBody Rent rent){
        return rentRepository.rentCarR(rent);
    }


    @PatchMapping("/update/{id}")
    public String updateRentInfo(@PathVariable("id")int id, @RequestBody Rent updateData) {
        if (updateData != null) {
            Rent rentToUpdate = rentRepository.getRentById(id);
            if (updateData.getClientName() != null) {
                rentToUpdate.setClientName(updateData.getClientName());
            }
            if (updateData.getClientSurname() != null) {
                rentToUpdate.setClientSurname(updateData.getClientSurname());
            }
            if (updateData.getClientPesel() > 0) {
                rentToUpdate.setClientPesel(updateData.getClientPesel());
            }
            if (updateData.getCarId() > 0) {
                Car carToChange = carRepository.findRentedCar(rentToUpdate.getCarId());
                carRepository.addCarFromRented(carToChange);
                carRepository.deleteCarFromRented(carToChange.getId());
                rentToUpdate.setCarId(updateData.getCarId());
                Car carToInsert = carRepository.getById(updateData.getCarId());
                carRepository.addCarToRented(carToInsert);
                carRepository.deleteCar(carToInsert.getId());
            }
            if (updateData.getRentTime() > 0) {
                rentToUpdate.setRentTime(updateData.getRentTime());
            }
            rentRepository.updateClient(rentToUpdate);
            rentRepository.updateRent(rentToUpdate);
            return "Data updated";
        }else{
            return "ERROR";
        }

    }


}
