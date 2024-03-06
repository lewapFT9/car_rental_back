package pl.project.car_rental.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_CLIENTS = "SELECT id, name, surname, pesel FROM Client";
    private static final String SELECT_CLIENT_BY_ID = "SELECT id, name, surname, pesel FROM Client WHERE id=?";
    private static final String GET_CLIENT_ID = "SELECT id FROM Client WHERE name=? AND surname=?";
    private static final String ADD_CLIENT = "INSERT INTO Client(id, name, surname,pesel) VALUES(?, ?, ?, ?)";

    private static final String UPDATE_CLIENT = "UPDATE Client SET name=?, surname=?, pesel=? WHERE id=?";
    private static final String UPDATE_CLIENT_IN_RENT = "UPDATE rents SET client_name=?, client_surname=?, client_pesel=? WHERE client_id=?";

    private static final String DELETE_CLIENT = "DELETE FROM Client WHERE id=?";
    public List<Client> getAllClientsR(){
        return jdbcTemplate.query(SELECT_ALL_CLIENTS,
                BeanPropertyRowMapper.newInstance(Client.class));
    }

    public Client getClientByIdR(int id){
        return jdbcTemplate.queryForObject(SELECT_CLIENT_BY_ID,
                BeanPropertyRowMapper.newInstance(Client.class), id);
    }
    public Client getClientId(String name, String surname){
        return jdbcTemplate.queryForObject(GET_CLIENT_ID,
                BeanPropertyRowMapper.newInstance(Client.class), name,surname);
    }


    public String updateClientR(Client client){
        jdbcTemplate.update(UPDATE_CLIENT, client.getName(),client.getSurname(),client.getPesel(),client.getId());
        jdbcTemplate.update(UPDATE_CLIENT_IN_RENT, client.getName(),client.getSurname(),client.getPesel(),client.getId());

        return "Client data updated";
    }
    public String deleteClientR(int id){
        Client client = getClientByIdR(id);
        String name = client.getName();
        String surname = client.getSurname();
        jdbcTemplate.update(DELETE_CLIENT,id);
        return "Client "+name+" "+surname+" deleted";
    }


}