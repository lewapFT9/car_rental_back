package pl.project.car_rental.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin("http://localhost:3000")
public class ClientControler {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("")
    public List<Client> getAllClients(){
        return clientRepository.getAllClientsR();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") int id){
        return clientRepository.getClientByIdR(id);
    }

//    @PostMapping("/add")
//    public String addClient(@RequestBody Client client){
//        return clientRepository.addClient(client);
//    }
    @PatchMapping("/update/{id}")
    public String updateClient(@PathVariable("id") int id, @RequestBody Client updateData){
        Client clientToUpdate = clientRepository.getClientByIdR(id);
        if(clientToUpdate!=null){
            if(updateData.getName()!=null) clientToUpdate.setName(updateData.getName());
            if(updateData.getSurname()!=null) clientToUpdate.setSurname(updateData.getSurname());
            if(updateData.getPesel()>0) clientToUpdate.setPesel(updateData.getPesel());
            return clientRepository.updateClientR(clientToUpdate);
        }else return "Update failed\nWrong id";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        Client clientToDelete = clientRepository.getClientByIdR(id);
        if(clientToDelete!=null) {
            return clientRepository.deleteClientR(id);
        }else return "Delete failed\nWrong id";
    }
}

