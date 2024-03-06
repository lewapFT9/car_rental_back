package pl.project.car_rental.Car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:3000")
public class CarControler {
    @Autowired
    CarRepository carRepository;

    @GetMapping("")
    public List<Car> getAll(){
        return carRepository.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") int id){
        return carRepository.getById(id);

    }

    @PostMapping("/add")
    public String addCar(@RequestBody Car car){
        return carRepository.addCar(car);

    }
    @PatchMapping("/update/{id}")
    public String updateCar(@PathVariable("id") int id, @RequestBody Car updateData){
        Car carToUpdate = carRepository.getById(id);
        if(carToUpdate!=null){
             carToUpdate.setBrand(updateData.getBrand());
             carToUpdate.setModel(updateData.getModel());
             carToUpdate.setColor(updateData.getColor());
             carToUpdate.setPriceforday(updateData.getPriceforday());
            return carRepository.updateCar(carToUpdate);
        }else return "Update failed\nWrong id";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        Car carToDelete = carRepository.getById(id);
        if(carToDelete!=null) {
            return carRepository.deleteCar(id);
        }else return "Delete failed\nWrong id";
    }
}
